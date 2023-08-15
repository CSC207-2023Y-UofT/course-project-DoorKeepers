package use_cases.main_menu;

import java.io.IOException;

/**
 * The input boundary interface for saving sessions
 * The SessionSaveUseCaseInteractor interactor class implements this interface, and it is called by
 * the SessionSaveC controller class
 */
public interface SessionSaveInputBoundary {
    /**
     * Saves a session into a file according to the input data given
     *
     * @param inputData a SessionSaveInputData with the user input data
     * @return a SessionSaveOutputData object with the output data to be used by the View
     * @throws IOException if there is an error while saving the file
     */
    SessionSaveOutputData save(SessionSaveInputData inputData) throws IOException;
}
