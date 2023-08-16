package use_cases.main_menu;

/**
 * The output data used by the view to display the results of saving a session.
 * It contains a String with a message to display to the user.
 * The MainMenuP presenter class creates an instance of this class to represent
 * the result, and it gets returned up the call stack until it reaches the view.
 */
public class SessionSaveOutputData {
    private final String message;

    /**
     * Creates a new SessionSaveOutputData
     *
     * @param message a message to display to the user
     */
    public SessionSaveOutputData(String message) {
        this.message = message;
    }

    /**
     * Gets the message to display
     *
     * @return a String containing the message
     */
    public String getMessage() {
        return message;
    }
}
