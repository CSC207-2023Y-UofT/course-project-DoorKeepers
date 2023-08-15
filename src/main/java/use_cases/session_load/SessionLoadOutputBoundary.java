package use_cases.session_load;

import entities.SessionStorage;

/**
 * The output boundary interface for displaying the result of loading a session
 * The SessionLoadPresenter presenter class implements this interface, and it is called by
 * the SessionLoadUseCaseInteractor interactor class
 */
public interface SessionLoadOutputBoundary {
    /**
     * Returns a SessionLoadOutputData signifying success and containing a success message and a session object to display
     *
     * @param message a string containing a success message
     * @param session the session data that was loaded
     * @return a SessionLoadOutputData object with the output data to be used by the View
     */
    SessionLoadOutputData displaySuccess(String message, SessionStorage session);

    /**
     * Signifies an error by throwing a SessionLoadException with the message provided
     *
     * @param message a string containing an error message
     * @return a SessionLoadOutputData object with the output data to be used by the View
     * @throws SessionLoadException always
     */
    SessionLoadOutputData displayError(String message) throws SessionLoadException;
}