package views.session_load;

import entities.SessionStorage;
import use_cases.session_load.SessionLoadOB;

public class SessionLoadP implements SessionLoadOB {
    private final SessionLoadMenuVB sessionLoadMenuVB;
    private final SessionLoadMainMenuVB sessionLoadMainMenuVB;
    public SessionLoadP(SessionLoadMenuVB errorView, SessionLoadMainMenuVB successView) {
        this.sessionLoadMenuVB = errorView;
        this.sessionLoadMainMenuVB = successView;
    }

    /**
     * Moves on to the main menu and displays a success message to the user
     *
     * @param message a string containing a success message
     */
    @Override
    public void displaySuccess(String message, SessionStorage session) {
        this.sessionLoadMenuVB.close();
        this.sessionLoadMainMenuVB.openMainMenu(message, session);
    }

    /**
     * Displays an error message to the user
     *
     * @param message a string containing an error message
     */
    @Override
    public void displayError(String message) {
        this.sessionLoadMenuVB.displayError(message);
    }
}