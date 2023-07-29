package use_cases.session_load;

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