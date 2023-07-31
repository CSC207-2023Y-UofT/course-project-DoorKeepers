package views.session_load;

import entities.SessionStorage;

/**
 * A view boundary interface to display a success or error message when loading a session
 * The SessionLoadMenuV view class implements this interface
 */
public interface SessionLoadMenuVB {
    /**
     * Displays an error message in a popup
     *
     * @param message a String containing an error message to display
     */
    void displayError(String message);

    /**
     * Closes this menu by setting its visibility to false and opens the main menu
     *
     * @param message a String containing a success message to display
     * @param session a SessionStorage object with the loaded session that will be displayed in the main menu
     */
    void displaySuccess(String message, SessionStorage session);
}