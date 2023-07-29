package views.session_load;

import entities.SessionStorage;

public interface SessionLoadMainMenuVB {
    /**
     * Makes the main menu visible which displays the session data passed and displays a message in a popup
     * @param message a message to display in a popup after opening the menu
     * @param session the session data to display
     */
    void openMainMenu(String message, SessionStorage session);
}