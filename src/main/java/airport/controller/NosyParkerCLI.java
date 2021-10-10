package airport.controller;

import airport.model.nosying.NosyParkable;
import airport.model.nosying.NosyParker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * NosyParkerCLI class
 * @author Adam Ross
 */
public class NosyParkerCLI {
    private static final String terminate = "exit";
    private final NosyParkable nosy;
    private final List<String> lots;
    private boolean exit;
    private String command;

    /**
     * Constructor for NosyParkerCLI
     */
    public NosyParkerCLI() {
        nosy = new NosyParker();
        lots = new ArrayList<>(nosy.getParkNames());
        exit = false;
        startMessage();
        nosyParkerCLIMain();
    }

    /**
     * Main NosyParkerCLI method - sorts input commands
     */
    private void nosyParkerCLIMain() {
        final String checkIn = "checkin";
        final String checkOut = "checkout";
        final String report = "report";
        final String quote = "quote";

        while (!exit) {
            switch (getCommand().toLowerCase()) {
                case checkIn:
                    checkIn(); break;
                case checkOut:
                    checkOut(); break;
                case report:
                    report(); break;
                case quote:
                    quote(); break;
                case terminate:
                    break;
                default:
                    System.out.println(ControllerEnum.INVALID_COMMAND.getValue());
            }
        }
    }

    /**
     * Gets a given user command from the CLI terminal
     * @return the command entered by the user
     */
    private String getCommand() {
        System.out.print(ControllerEnum.PROMPT.getValue());
        command = new Scanner(System.in).nextLine().trim();
        exit = (exit && command.equalsIgnoreCase(terminate));
        return command;
    }

    /**
     * Prints to terminal a start message for the CLI
     */
    private void startMessage() {
        System.out.println("**************************************");
        System.out.println("** Christchurch Airport Parking CLI **");
        System.out.println("**************************************");
        System.out.println("Commands: ");
        System.out.println("    checkin  - check in a vehicle");
        System.out.println("    checkout - check out a vehicle");
        System.out.println("    report   - list vehicles in a park");
        System.out.println("    quote    - quote parking cost");
        System.out.println("    exit     - terminate the CLI");
        System.out.println("**************************************");
        System.out.println(ControllerEnum.ENTER_CMND.getValue());
    }

    /**
     * Checks in a vehicle to a parking lot
     */
    private void checkIn() {
        System.out.println(ControllerEnum.CHECK_IN_HEADER.getValue());
        getVehiclePlate();

        if (!exit) {
            if (nosy.getVehicle().getParkingLot() == null) {
                getParkingLot();

                if (!exit) {
                    nosy.getLot().admit(nosy.getVehicle());
                    nosy.getVehicle().setParkingLot(nosy.getLot());
                    nosy.getVehicle().setArrival(getArrivalTime());
                }
            } else {
                System.out.println(nosy.getVehicle() + ControllerEnum.CHECKED_IN.getValue() + nosy.getVehicle().
                        getParkingLot());
            }
        }

        if (!exit) {
            System.out.println(ControllerEnum.ENTER_CMND.getValue());
        }
    }

    /**
     * Checks out a vehicle from a parking lot
     */
    private void checkOut() {
        System.out.println(ControllerEnum.CHECK_OUT_HEADER.getValue());
        getVehiclePlate();

        if (!exit) {
            if (nosy.getVehicle().getParkingLot() != null) {
                System.out.println(ControllerEnum.PARKING_LOT.getValue() + nosy.getVehicle().getParkingLot().toString());
                System.out.println(ControllerEnum.ARRIVAL_TIME.getValue() + DateTimeFormatter.
                        ofPattern(ControllerEnum.DATE_PATTERN.getValue()).format(nosy.getVehicle().getArrival()));
                nosy.getVehicle().setDeparture(getDepartureTime(nosy.getVehicle().getArrival()));

                if (!exit) {
                    nosy.setLot(nosy.getVehicle().getParkingLot());
                    nosy.getLot().release(nosy.getVehicle());
                    displayParkingData(nosy.computeCost(nosy.getLot().getDuration(nosy.getVehicle().getArrival(),
                            nosy.getVehicle().getDeparture())));
                }
            } else {
                System.out.println(nosy.getVehicle() + ControllerEnum.CHECKED_OUT.getValue());
            }
        }

        if (!exit) {
            System.out.println(ControllerEnum.ENTER_CMND.getValue());
        }
    }

    /**
     * Lists the vehicles currently occupying a parking lot selected by the user
     */
    private void report() {
        System.out.println(ControllerEnum.REPORT_HEADER.getValue());
        getParkingLot();

        if (!exit) {
            if (nosy.getLot().occupancy() > 0) {
                System.out.println(ControllerEnum.PARK_REPORT.getValue() + nosy.getLot().toString());

                for (Object occupant : nosy.getLot().occupants()) {
                    System.out.println(occupant.toString());
                }
            } else {
                System.out.println(nosy.getLot().toString() + ControllerEnum.NO_OCCUPANTS.getValue());
            }
        }

        if (!exit) {
            System.out.println(ControllerEnum.ENTER_CMND.getValue());
        }
    }

    /**
     * Quotes the cost for a duration of parking in a parking lot
     */
    private void quote() {
        System.out.println(ControllerEnum.QUOTE_HEADER.getValue());
        getParkingLot();

        if (!exit) {
            LocalDateTime arrival = getArrivalTime();

            if (!exit) {
                LocalDateTime departure = getDepartureTime(arrival);

                if (!exit) {
                    displayParkingData(nosy.computeCost(nosy.getLot().getDuration(arrival, departure)));
                }
            }
        }

        if (!exit) {
            System.out.println(ControllerEnum.ENTER_CMND.getValue());
        }
    }

    /**
     * Gets a vehicle registration plate entered by the user
     */
    private void getVehiclePlate() {
        System.out.println(ControllerEnum.ENTER_REG.getValue());
        getCommand();

        while (!exit) {
            if (nosy.vehicleRegistrationIsValid(command)) {
                nosy.setVehicle(command);
                break;
            }
            System.out.println(command.toUpperCase() + ControllerEnum.INVALID_PLATE.getValue());
            getCommand();
        }
    }

    /**
     * Gets a parking lot selected by the user
     */
    private void getParkingLot() {
        System.out.println(ControllerEnum.ENTER_LOT.getValue());

        for (int i = 0; i < lots.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + lots.get(i));
        }
        getCommand();

        while (!exit) {
            if (command.matches(ControllerEnum.IS_INT.getValue()) && (Integer.parseInt(command) - 1) < lots.size()) {
                nosy.setLotString(lots.get(Integer.parseInt(command) - 1));
                break;
            }
            System.out.println(command + ControllerEnum.INVALID_LOT.getValue());
            getCommand();
        }
    }

    /**
     * Gets an arrival date and time entered by the user
     * @return The arrival date and time in LocalDateTime format
     */
    private LocalDateTime getArrivalTime() {
        System.out.println(ControllerEnum.ENTER_ARRIVAL.getValue() + ControllerEnum.DATE_PATTERN.getValue());
        getCommand();

        while (!exit) {
            if (command.matches(ControllerEnum.IS_DATE_TIME.getValue())) {
                return LocalDateTime.parse(command, DateTimeFormatter.ofPattern(ControllerEnum.DATE_PATTERN.getValue()));
            }
            System.out.println(command + ControllerEnum.DATE_INVALID.getValue());
            getCommand();
        }
        return null;
    }

    /**
     * Gets a departure date and time entered by the user
     * @param arrival the arrival date and time
     * @return The departure date and time in LocalDateTime format
     */
    private LocalDateTime getDepartureTime(LocalDateTime arrival) {
        System.out.println(ControllerEnum.ENTER_DEPARTURE.getValue() + ControllerEnum.DATE_PATTERN.getValue());
        getCommand();

        while (!exit) {
            if (command.matches(ControllerEnum.IS_DATE_TIME.getValue())) {
                if (nosy.datesAreValid(arrival, LocalDateTime.parse(command, DateTimeFormatter.
                        ofPattern(ControllerEnum.DATE_PATTERN.getValue())))) {
                    return LocalDateTime.parse(command, DateTimeFormatter.ofPattern(ControllerEnum.DATE_PATTERN.getValue()));
                }
            }
            System.out.println(command + ControllerEnum.DATE_INVALID.getValue());
            getCommand();
        }
        return null;
    }

    /**
     * Displays to terminal the cost and duration of parking for days, hours and minutes
     * @param data the cost, and number of days, hours and minutes of parking charged
     */
    private void displayParkingData(HashMap<String, String> data) {
        System.out.println("Parking costs '" + data.get(ControllerEnum.COST.getValue()) + "' for " +
                data.get(ControllerEnum.DAYS.getValue()) + " " + ControllerEnum.DAYS.getValue() + ", " +
                data.get(ControllerEnum.HRS.getValue()) + " " + ControllerEnum.HRS.getValue() + " and " +
                data.get(ControllerEnum.MINS.getValue()) + " " + ControllerEnum.MINS.getValue());
    }
}