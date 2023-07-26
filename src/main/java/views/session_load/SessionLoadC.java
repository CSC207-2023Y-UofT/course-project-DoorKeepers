package views.session_load;

import use_cases.session_load.SessionLoadIB;
import use_cases.session_load.SessionLoadID;

public class SessionLoadC {
    private final SessionLoadIB interactor;

    public SessionLoadC(SessionLoadIB interactor) {
        this.interactor = interactor;
    }

    /**
     * Calls the interactor to load a new session from a file
     * @param filename the filename to load the session from
     */
    public void loadFile(String filename) {
        SessionLoadID inputData = new SessionLoadID(filename);
        this.interactor.load(inputData);
    }

    /**
     * Calls the interactor to create a new empty session
     */
    public void loadNew() {
        SessionLoadID inputData = new SessionLoadID();
        this.interactor.load(inputData);
    }
}