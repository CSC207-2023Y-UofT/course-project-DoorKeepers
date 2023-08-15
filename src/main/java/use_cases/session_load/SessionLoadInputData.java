package use_cases.session_load;

/**
 * The input data used by the interactor to load a session.
 * The SessionLoadController controller class creates an instance of this class from user input
 * and uses it as a parameter to call the SessionLoadUseCaseInteractor interactor.
 */
public class SessionLoadInputData {
    private final boolean isNew;
    private final String filename;

    public SessionLoadInputData() {
        this.isNew = true;
        this.filename = "";
    }
    public SessionLoadInputData(String filename) {
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