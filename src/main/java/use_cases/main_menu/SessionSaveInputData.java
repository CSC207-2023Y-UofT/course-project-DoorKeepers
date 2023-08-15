package use_cases.main_menu;

import entities.SessionStorage;

/**
 * The input data used by the interactor to save a session.
 * The MainMenuC controller class creates an instance of this class from user input
 * and uses it as a parameter to call the SessionSaveUseCaseInteractor interactor.
 */
public class SessionSaveInputData {
    private final SessionStorage session;
    private final String filename;

    /**
     * Creates a new SessionSaveInputData
     *
     * @param session  a SessionStorage object to save to a file
     * @param filename a String with the filename to save the session to
     */
    public SessionSaveInputData(SessionStorage session, String filename) {
        this.session = session;
        this.filename = filename;
    }

    /**
     * Gets the session to save
     *
     * @return a SessionStorage with the session data to be saved
     */
    public SessionStorage getSession() {
        return session;
    }

    /**
     * Gets the filename to save the session to
     *
     * @return a String containing the filename
     */
    public String getFilename() {
        return filename;
    }
}
