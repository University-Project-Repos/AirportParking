package airport.controller;

/**
 * Constants for controller classes
 * @author Adam Ross
 */
enum ControllerEnum {

    CIAL ("CIAL"),
    NOSY_PARKER ("/view/nosyParker.fxml"),
    REPORT ("Report for "),
    ENTER_REG ("Enter the plate of the vehicle"),
    ENTER_LOT ("Enter a number corresponding to one of the following parking lots"),
    ENTER_ARRIVAL ("Enter the arrival date and time in the format "),
    ENTER_DEPARTURE ("Enter the departure date and time in format "),
    PARK_REPORT ("Vehicles currently occupying "),
    NO_OCCUPANTS (" currently has no vehicles in occupancy"),
    INVALID_LOT (" does not correspond to any parking lot"),
    INVALID_COMMAND ("Invalid command"),
    EMPTY_PLATE ("Empty plate"),
    CHECKED_IN (" is already checked in to "),
    INVALID_PLATE (" is an invalid vehicle registration plate"),
    PARK_FULL (" is currently fully occupied"),
    CHECKED_OUT (" is not checked in to any parking lot at CIAL"),
    DATE_INVALID (" is an invalid date - departure can not come before arrival"),
    IS_DATE_TIME ("\\d{4}-[01]\\d-[0-3]\\d\\s[0-2]\\d((:[0-5]\\d)?){2}"),
    DATE_PATTERN ("yyyy-MM-dd HH:mm"),
    PARKING_LOT ("Parking lot: "),
    ARRIVAL_TIME ("Arrival time: "),
    CHECK_IN_HEADER ("**CHECK IN**"),
    CHECK_OUT_HEADER ("**CHECK OUT**"),
    REPORT_HEADER ("**REPORT**"),
    QUOTE_HEADER ("**QUOTE**"),
    ENTER_CMND ("Enter a command:"),
    PROMPT (">> "),
    IS_INT ("\\d+"),
    COST ("cost"),
    DAYS ("days"),
    HRS ("hours"),
    MINS ("minutes");

    private final String controllerEnum;

    /**
     * Converts a string to a controller class constant
     * @param controllerEnum the string being converted to a controller class constant
     */
    ControllerEnum(final String controllerEnum) {
        this.controllerEnum = controllerEnum;
    }

    /**
     * Converts a controller class constant to a string
     * @return the controller class constant as a string
     */
    String getValue() {
        return controllerEnum;
    }
}