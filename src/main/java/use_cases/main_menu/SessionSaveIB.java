package use_cases.main_menu;

import java.io.IOException;

/**
 * The input boundary interface for saving sessions
 * The SessionSaveUCI interactor class implements this interface, and it is called by
 * the SessionSaveC controller class
 */
public interface SessionSaveIB {
    /**
     * Saves a session into a file according to the input data given
     *
     * @param inputData a SessionSaveID with the user input data
     * @return a SessionSaveOD object with the output data to be used by the View
     * @throws IOException if there is an error while saving the file
     */
    SessionSaveOD save(SessionSaveID inputData) throws IOException;
}
