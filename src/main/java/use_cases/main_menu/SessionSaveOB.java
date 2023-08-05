package use_cases.main_menu;

/**
 * The output boundary interface for saving sessions.
 * The MainMenuP presenter class implements this interface, and it is called by
 * the SessionSaveUCI interactor class
 */
public interface SessionSaveOB {
    /**
     * Returns a SessionSaveOD signifying success which contains a success message to display
     *
     * @param sessionSavedSuccessfully the output data to be returned
     * @return a SessionSaveOD object with the output data to be used by the View
     */
    SessionSaveOD displaySuccess(SessionSaveOD sessionSavedSuccessfully);
}
