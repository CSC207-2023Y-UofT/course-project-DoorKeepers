package views.session_load;

import use_cases.session_load.SessionLoadException;
import use_cases.session_load.SessionLoadIB;
import use_cases.session_load.SessionLoadID;
import use_cases.session_load.SessionLoadOD;

/**
 * The controller class for the session load menu.
 * It is called by the view with the user input, and it calls the interactor
 * through the SessionLoadIB interface
 */
public class SessionLoadC {
    private final SessionLoadIB interactor;

    public SessionLoadC(SessionLoadIB interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls the interactor to load a new session from a file
     *
     * @param filename the filename to load the session from
     * @return a SessionLoadOD object with the output data to be used by the View
     * @throws SessionLoadException if there is an error while opening the file
     */
    public SessionLoadOD loadFile(String filename) throws SessionLoadException {
        SessionLoadID inputData = new SessionLoadID(filename);
        return this.interactor.load(inputData);
    }

    /**
     * Calls the interactor to create a new empty session
     *
     * @return a SessionLoadOD object with the output data to be used by the View
     */
    public SessionLoadOD loadNew() {
        SessionLoadID inputData = new SessionLoadID();
        try {
            return this.interactor.load(inputData);
        } catch (SessionLoadException e) {
            // Loading a new empty session never throws
            throw new AssertionError("Unreachable code");
        }
    }
}