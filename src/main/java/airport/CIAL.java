package airport;

import airport.controller.NosyParkerCLI;
import airport.controller.NosyParkerGUI;
import javafx.application.Application;

import java.util.Arrays;

/**
 * CIAL class - main class for the app
 * @author Adam Ross
 */
public class CIAL {

    private static final String GUI = "gui";

    /**
     * Constructor for the CIAL class
     */
    public CIAL() {}

    /**
     * Starts the application
     * @param args command input string
     **/
    public static void main(String[] args) {
        CIAL app = new CIAL();

        if (Arrays.asList(args).contains(GUI)) {
            app.initiateGUI();
        } else {
            app.initiateCLI();
        }
    }

    /**
     * Starts the GUI for the CIAL app
     */
    private void initiateGUI() {
        Application.launch(NosyParkerGUI.class);
    }

    /**
     * Strats the CLI for the CIAL app
     */
    private void initiateCLI() {
        new NosyParkerCLI();
    }
}