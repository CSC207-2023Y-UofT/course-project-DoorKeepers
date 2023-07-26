package views.session_load;

import use_cases.session_load.SessionLoadOB;

public class SessionLoadP implements SessionLoadOB {
    /**
     * Displays a success message to the user
     * @param message a string containing a success message
     */
    @Override
    public void displaySuccess(String message) {
        // TODO: this is just a temporary way to check that the message is working, will be replaced by code that
        // updates the view
        System.out.println(message);
    }

    /**
     * Displays an error message to the user
     * @param message a string containing an error message
     */
    @Override
    public void displayError(String message) {
        // TODO: this is just a temporary way to check that the message is working, will be replaced by code that
        // updates the view
        System.out.println(message);
    }
}