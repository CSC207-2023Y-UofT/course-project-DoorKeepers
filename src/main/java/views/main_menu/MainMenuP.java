package views.main_menu;

import use_cases.main_menu.SessionSaveOutputBoundary;
import use_cases.main_menu.SessionSaveOutputData;

/**
 * The presenter class for the main menu.
 * It is called by the interactor through the SessionSaveOutputBoundary interface, and it is the
 * class responsible for returning the SessionSaveOutputData objects
 */
public class MainMenuP implements SessionSaveOutputBoundary {
    /**
     * Returns a SessionSaveOutputData containing a success message
     *
     * @param sessionSavedSuccessfully the output data to be returned
     * @return the SessionSaveOutputData object passed in
     */
    @Override
    public SessionSaveOutputData displaySuccess(SessionSaveOutputData sessionSavedSuccessfully) {
        return sessionSavedSuccessfully;
    }
}
