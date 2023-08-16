package use_cases.session_load;

import entities.SessionStorage;

import java.io.IOException;

/**
 * A gateway interface for saving and loading sessions from files.
 * It is implemented by FileSessionStorage and called from the SessionLoadUseCaseInteractor interactor class
 */
public interface SessionStorageGateway {
    /**
     * Saves a SessionStorage object into a file on disk.
     *
     * @param filename the name of file to save the session to.
     * @param session  the SessionStorage object to serialize and save to the file.
     * @throws IOException if an error occurred while saving the file.
     */
    void save(String filename, SessionStorage session) throws IOException;

    /**
     * Loads a SessionStorage object from a file on disk.
     *
     * @param filename the name of the file to read the session from.
     * @return the SessionStorage object obtained from the file.
     * @throws IOException            if an error occurred while reading the file.
     * @throws ClassNotFoundException if the file isn't a valid serialization of a SessionObject.
     */
    SessionStorage load(String filename) throws IOException, ClassNotFoundException;
}