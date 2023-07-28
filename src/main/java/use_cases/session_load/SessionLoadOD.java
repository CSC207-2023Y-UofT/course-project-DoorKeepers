package use_cases.session_load;

import entities.SessionStorage;

public class SessionLoadOD {
    private final String message;
    private final SessionStorage session;

    public SessionLoadOD(String message, SessionStorage session) {
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
