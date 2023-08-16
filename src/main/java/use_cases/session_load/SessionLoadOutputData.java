package use_cases.session_load;

import entities.SessionStorage;

/**
 * The output data used by the view to display the results of loading a session.
 * The SessionLoadPresenter presenter class creates an instance of this class to represent
 * the result, and it gets returned up the call stack until it reaches the view.
 */
public class SessionLoadOutputData {
    private final String message;
    private final SessionStorage session;

    public SessionLoadOutputData(String message, SessionStorage session) {
        this.message = message;
        this.session = session;
    }

    /**
     * Gets the message to display
     *
     * @return a String containing the message to display
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the session data that was loaded
     *
     * @return a SessionStorage object with the loaded data
     */
    public SessionStorage getSession() {
        return session;
    }
}
