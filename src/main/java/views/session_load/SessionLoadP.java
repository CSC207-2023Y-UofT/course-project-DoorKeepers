package views.session_load;

import entities.SessionStorage;
import use_cases.session_load.SessionLoadException;
import use_cases.session_load.SessionLoadOB;
import use_cases.session_load.SessionLoadOD;

public class SessionLoadP implements SessionLoadOB {
    /**
     * Returns a SessionLoadOD signifying success and containing a success message and a session object to display
     *
     * @param message a string containing a success message
     * @param session the session data that was loaded
     * @return a SessionLoadOD object with the output data to be used by the View
     */
    @Override
    public SessionLoadOD displaySuccess(String message, SessionStorage session) {
        return new SessionLoadOD(message, session);
    }

    /**
     * Signifies an error by throwing a SessionLoadException with the message provided
     *
     * @param message a string containing an error message
     * @return a SessionLoadOD object with the output data to be used by the View
     * @throws SessionLoadException always
     */
    @Override
    public SessionLoadOD displayError(String message) throws SessionLoadException {
        throw new SessionLoadException(message);
    }
}