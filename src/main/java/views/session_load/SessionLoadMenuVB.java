package views.session_load;

public interface SessionLoadMenuVB {
    /**
     * Displays an error message in a popup
     *
     * @param message an error message to display
     */
    void displayError(String message);

    /**
     * Closes this menu by setting its visibility to false
     */
    void close();
}