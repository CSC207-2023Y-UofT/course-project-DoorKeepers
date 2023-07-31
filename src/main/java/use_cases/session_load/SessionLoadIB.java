package use_cases.session_load;

/**
 * The input boundary interface for loading sessions
 * The SessionLoadUCI interactor class implements this interface and it is called by
 * the SessionLoadC controller class
 */
public interface SessionLoadIB {
    /**
     * Either loads a SessionStorage from a file, or creates a new empty one.
     *
     * @param inputData the user input data
     * @return a SessionLoadOD object with the output data to be used by the View
     * @throws SessionLoadException if the user chose to load a file and there is an error while opening it
     */
    SessionLoadOD load(SessionLoadID inputData) throws SessionLoadException;
}