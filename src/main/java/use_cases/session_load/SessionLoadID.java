package use_cases.session_load;

public class SessionLoadID {
    private final boolean isNew;
    private final String filename;

    public SessionLoadID() {
        this.isNew = true;
        this.filename = "";
    }
    public SessionLoadID(String filename) {
        this.isNew = false;
        this.filename = filename;
    }

    /**
     * Gets the boolean representing whether to create a new session
     * @return whether to create a new session
     */
    public boolean getIsNew() {
        return this.isNew;
    }

    /**
     * Gets the filename that leads to a file to load a session from
     * @return a String with the filename
     */
    public String getFilename() {
        return this.filename;
    }
}