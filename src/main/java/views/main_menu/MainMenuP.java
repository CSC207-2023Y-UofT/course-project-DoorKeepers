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
     * Returns a SessionSaveOD containing a success message
     *
     * @param sessionSavedSuccessfully the output data to be returned
     * @return the SessionSaveOD object passed in
     */
    @Override
    public SessionSaveOD displaySuccess(SessionSaveOD sessionSavedSuccessfully) {
        return sessionSavedSuccessfully;
    }
}
