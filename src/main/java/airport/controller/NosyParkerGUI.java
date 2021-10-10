package airport.controller;

import airport.model.nosying.NosyParkable;
import airport.model.nosying.NosyParker;
import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tornadofx.control.DateTimePicker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
 * NosyParkerGUI controller class
 * @author Adam Ross
 */
public class NosyParkerGUI extends Application {

    @FXML
    private ListView<Object> listView;

    @FXML
    private TextField vehicleReg;

    @FXML
    private Label availableParking;

    @FXML
    private ChoiceBox<String> parkingLot;

    @FXML
    private DateTimePicker arrival;

    @FXML
    private DateTimePicker departure;

    @FXML
    private TextField days;

    @FXML
    private TextField hours;

    @FXML
    private TextField mins;

    @FXML
    private TextField parkingCharge;

    private NosyParkable nosy;

    /**
     * Starts the GUI by opening the login pane
     * @param primaryStage Primary stage
     * @throws IOException exception for error reading the fxml file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle(ControllerEnum.CIAL.getValue());
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(ControllerEnum.NOSY_PARKER.getValue()))));
        primaryStage.show();
    }

    /**
     * Initializes the NosyParker pane
     */
    @FXML
    public void initialize() {
        nosy = new NosyParker();
        resetVehicleRegistration();
        arrival.setValue(LocalDate.now());
        departure.setValue(LocalDate.now());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setOnMouseClicked(event -> setVehicleRegistrationTextField());
        parkingLot.setItems(FXCollections.observableArrayList(nosy.getParkNames()));
        parkingLot.valueProperty().addListener((ov, oldValue, newValue) -> setParkingLot(newValue));
        parkingLot.getSelectionModel().selectFirst();
        mins.setOpacity(1.0);
        hours.setOpacity(1.0);
        days.setOpacity(1.0);
        parkingCharge.setOpacity(1.0);
    }

    /**
     * Displays to the ListView all the vehicles currently parked in the selected parking lot
     */
    private void report() {
        listView.itemsProperty().bind(new SimpleListProperty<>(FXCollections.observableArrayList(nosy.getLot().occupants())));
        setParkingSpacesAvailable();
    }

    /**
     * Checks in a vehicle registration plate entered to the vehicleRegistration TextField if not already checked in
     */
    @FXML
    public void checkIn() {
        if (vehicleReg.getText() != null && nosy.vehicleRegistrationIsValid(vehicleReg.getText())) {
            if (nosy.getLot().availability() > 0) {
                nosy.setVehicle(vehicleReg.getText());

                if (nosy.getVehicle().getParkingLot() == null) {
                    nosy.getLot().admit(nosy.getVehicle());
                    nosy.getVehicle().setArrival(arrival.getDateTimeValue().truncatedTo(ChronoUnit.MINUTES));
                    nosy.getVehicle().setParkingLot(nosy.getLot());
                    report();
                } else {
                    confirmationPopUp(nosy.getVehicle().regNo() + ControllerEnum.CHECKED_IN.getValue() +
                            nosy.getVehicle().getParkingLot());
                }
            } else {
                confirmationPopUp(nosy.getLot().toString() + ControllerEnum.PARK_FULL.getValue());
            }
        } else {
            if (vehicleReg.getText() == null || vehicleReg.getText().length() == 0) {
                confirmationPopUp(ControllerEnum.EMPTY_PLATE.getValue() + ControllerEnum.INVALID_PLATE.getValue());
            } else {
                confirmationPopUp(vehicleReg.getText().toUpperCase() + ControllerEnum.INVALID_PLATE.getValue());
            }
            resetVehicleRegistration();
        }
    }

    /**
     * Checks out a vehicle registration plate entered to the vehicleRegistration TextField if not already checked out
     */
    @FXML
    public void checkOut() {
        if (vehicleReg.getText() != null && nosy.vehicleRegistrationIsValid(vehicleReg.getText())) {
            nosy.setVehicle(vehicleReg.getText());

            if (nosy.getVehicle().getParkingLot() != null) {
                if (nosy.datesAreValid(nosy.getVehicle().getArrival(), departure.getDateTimeValue().
                        truncatedTo(ChronoUnit.MINUTES))) {
                    nosy.getVehicle().setDeparture(departure.getDateTimeValue().truncatedTo(ChronoUnit.MINUTES));
                    nosy.setLot(nosy.getVehicle().getParkingLot());
                    nosy.getLot().release(nosy.getVehicle());
                    report();
                    displayParkingData(nosy.computeCost(nosy.getLot().getDuration(nosy.getVehicle().getArrival(),
                            nosy.getVehicle().getDeparture())));
                    nosy.getVehicle().setParkingLot(null);
                } else {
                    confirmationPopUp(ControllerEnum.DATE_INVALID.getValue() + nosy.getVehicle().getParkingLot());
                }
            } else {
                confirmationPopUp(nosy.getVehicle().regNo() + ControllerEnum.CHECKED_OUT.getValue());
            }
        } else {
            if (vehicleReg.getText() == null || vehicleReg.getText().length() == 0) {
                confirmationPopUp(ControllerEnum.EMPTY_PLATE.getValue() + ControllerEnum.INVALID_PLATE.getValue());
            } else {
                confirmationPopUp(vehicleReg.getText().toUpperCase() + ControllerEnum.INVALID_PLATE.getValue());
            }
            resetVehicleRegistration();
        }
    }

    /**
     * Quotes the cost for a vehicle to park in the selected parking lot for the selected duration
     */
    @FXML
    public void quote() {
        if (nosy.datesAreValid(arrival.getDateTimeValue().truncatedTo(ChronoUnit.MINUTES), departure.getDateTimeValue().
                truncatedTo(ChronoUnit.MINUTES))) {
            displayParkingData(nosy.computeCost(nosy.getLot().getDuration(arrival.getDateTimeValue().
                            truncatedTo(ChronoUnit.MINUTES), departure.getDateTimeValue().truncatedTo(ChronoUnit.MINUTES))));
        } else {
            confirmationPopUp(ControllerEnum.DATE_INVALID.getValue());
        }
    }

    /**
     * Sets the selected vehicle registration from the ListView to the vehicleRegistration TextField
     */
    private void setVehicleRegistrationTextField() {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            vehicleReg.setText(listView.getSelectionModel().getSelectedItem().toString());
        }
    }

    /**
     * Sets the selected parking lot when a user selects a parking lot using the ChoiceBox
     * @param selectedLot the selected parking lot
     */
    private void setParkingLot(String selectedLot) {
        nosy.setLotString(selectedLot);
        report();
    }

    /**
     * Displays the number of parking spaces available for a selected parking lot
     */
    private void setParkingSpacesAvailable() {
        availableParking.setText(String.valueOf(nosy.getLot().availability()));
    }

    /**
     * Displays the charge, and duration in days, hours and minutes of parking
     * @param data a HashMap containing the charge, and duration in days, hours and minutes
     */
    private void displayParkingData(HashMap<String, String> data) {
        System.out.println(data);
        parkingCharge.setText(data.get(ControllerEnum.COST.getValue()));
        days.setText(data.get(ControllerEnum.DAYS.getValue()));
        hours.setText(data.get(ControllerEnum.HRS.getValue()));
        mins.setText(data.get(ControllerEnum.MINS.getValue()));
    }

    /**
     * Alerts a user with a pop up to inform that there has been an error in using the app
     * @param msg the alert message being displayed to the user
     */
    private void confirmationPopUp(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Resets the vehicleRegistration TextField to show a prompt for entering a vehicle registration plate
     */
    private void resetVehicleRegistration() {
        vehicleReg.setText(null);
        vehicleReg.setPromptText(ControllerEnum.ENTER_REG.getValue());
    }
}