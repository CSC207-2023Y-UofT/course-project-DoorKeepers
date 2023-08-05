package views.session_load;

import entities.SessionStorage;

/**
 * A view boundary interface to open up the main menu and display a success message
 * The MainMenuV view class implements this interface, and it is called by the SessionLoadMenuV
 * class to switch over to the main menu after loading the session
 */
public interface SessionLoadMainMenuVB {
    /**
     * Makes the main menu visible which displays the session data passed and displays a message in a popup
     * @param message a message to display in a popup after opening the menu
     * @param session the session data to display, or null if displaying a message is not needed
     */
    void openMainMenu(String message, SessionStorage session);
}