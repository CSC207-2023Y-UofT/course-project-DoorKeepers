package views.main_menu;

import use_cases.main_menu.SessionSaveOB;
import use_cases.main_menu.SessionSaveOD;

/**
 * The presenter class for the main menu.
 * It is called by the interactor through the SessionSaveOB interface, and it is the
 * class responsible for returning the SessionSaveOD objects
 */
public class MainMenuP implements SessionSaveOB {
    /**
     * Returns a SessionSaveOD signifying success which contains a success message to display
     *
     * @param sessionSavedSuccessfully the output data to be returned
     * @return a SessionSaveOD object with the output data to be used by the View
     */
    @Override
    public SessionSaveOD displaySuccess(SessionSaveOD sessionSavedSuccessfully) {
        return sessionSavedSuccessfully;
    }
}
