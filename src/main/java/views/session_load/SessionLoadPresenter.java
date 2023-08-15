package views.session_load;

import entities.SessionStorage;
import use_cases.session_load.SessionLoadException;
import use_cases.session_load.SessionLoadOutputBoundary;
import use_cases.session_load.SessionLoadOutputData;

/**
 * The presenter class for loading a session.
 * It is called by the interactor through the SessionLoadOutputBoundary interface, and it is the
 * class responsible for building the SessionLoadOutputData objects
 */
public class SessionLoadPresenter implements SessionLoadOutputBoundary {
    /**
     * Returns a SessionLoadOutputData signifying success and containing a success message and a session object to display
     *
     * @param message a string containing a success message
     * @param session the session data that was loaded
     * @return a SessionLoadOutputData object with the output data to be used by the View
     */
    @Override
    public SessionLoadOutputData displaySuccess(String message, SessionStorage session) {
        return new SessionLoadOutputData(message, session);
    }

    /**
     * Signifies an error by throwing a SessionLoadException with the message provided
     *
     * @param message a string containing an error message
     * @return a SessionLoadOutputData object with the output data to be used by the View
     * @throws SessionLoadException always
     */
    @Override
    public SessionLoadOutputData displayError(String message) throws SessionLoadException {
        throw new SessionLoadException(message);
    }
}