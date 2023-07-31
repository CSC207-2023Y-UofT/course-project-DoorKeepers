package use_cases.session_load;

/**
 * An exception used when there was an issue loading a session from a file
 */
public class SessionLoadException extends Exception {
    /**
     * An exception used when there was an issue loading a session from a file
     */
    public SessionLoadException(String message) {
        super(message);
    }
}
