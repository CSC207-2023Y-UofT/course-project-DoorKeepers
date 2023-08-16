package views.session_load;

import use_cases.session_load.SessionLoadException;
import use_cases.session_load.SessionLoadInputBoundary;
import use_cases.session_load.SessionLoadInputData;
import use_cases.session_load.SessionLoadOutputData;

/**
 * The controller class for the session load menu.
 * It is called by the view with the user input, and it calls the interactor
 * through the SessionLoadInputBoundary interface
 */
public class SessionLoadController {
    private final SessionLoadInputBoundary interactor;

    public SessionLoadController(SessionLoadInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls the interactor to load a new session from a file
     *
     * @param filename the filename to load the session from
     * @return a SessionLoadOutputData object with the output data to be used by the View
     * @throws SessionLoadException if there is an error while opening the file
     */
    public SessionLoadOutputData loadFile(String filename) throws SessionLoadException {
        SessionLoadInputData inputData = new SessionLoadInputData(filename);
        return this.interactor.load(inputData);
    }

    /**
     * Calls the interactor to create a new empty session
     *
     * @return a SessionLoadOutputData object with the output data to be used by the View
     */
    public SessionLoadOutputData loadNew() {
        SessionLoadInputData inputData = new SessionLoadInputData();
        try {
            return this.interactor.load(inputData);
        } catch (SessionLoadException e) {
            // Loading a new empty session never throws
            throw new AssertionError("Unreachable code");
        }
    }
}