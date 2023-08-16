package use_cases.main_menu;

/**
 * The output boundary interface for saving sessions.
 * The MainMenuP presenter class implements this interface, and it is called by
 * the SessionSaveUseCaseInteractor interactor class
 */
public interface SessionSaveOutputBoundary {
    /**
     * Returns a SessionSaveOutputData signifying success which contains a success message to display
     *
     * @param sessionSavedSuccessfully the output data to be returned
     * @return a SessionSaveOutputData object with the output data to be used by the View
     */
    SessionSaveOutputData displaySuccess(SessionSaveOutputData sessionSavedSuccessfully);
}
