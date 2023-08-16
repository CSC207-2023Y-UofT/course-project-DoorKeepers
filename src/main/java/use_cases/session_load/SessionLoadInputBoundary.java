package use_cases.session_load;

/**
 * The input boundary interface for loading sessions
 * The SessionLoadUseCaseInteractor interactor class implements this interface, and it is called by
 * the SessionLoadController controller class
 */
public interface SessionLoadInputBoundary {
    /**
     * Either loads a SessionStorage from a file, or creates a new empty one.
     *
     * @param inputData the user input data
     * @return a SessionLoadOutputData object with the output data to be used by the View
     * @throws SessionLoadException if the user chose to load a file and there is an error while opening it
     */
    SessionLoadOutputData load(SessionLoadInputData inputData) throws SessionLoadException;
}