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

    public boolean getIsNew() {
        return this.isNew;
    }

    public String getFilename() {
        return this.filename;
    }
}