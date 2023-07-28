package use_cases.session_load;

public class SessionLoadException extends Exception {
    /**
     * Creates a new SessionLoadException for reporting exceptions specific to the this use case.
     */
    public SessionLoadException(String message) {
        super(message);
    }
}
