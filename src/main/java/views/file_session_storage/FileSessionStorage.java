package views.file_session_storage;

import entities.SessionStorage;
import use_cases.session_load.SessionStorageG;

import java.io.*;

/**
 * A storage system for loading and saving SessionLoad to files.
 * Implements the SessionStorageG interface and is called from the SessionLoadUCI
 * interactor class.
 * Implementation of serializing and deserializing inspired from
 * <a href="https://www.tutorialspoint.com/java/java_serialization.htm">here</a>
 */
public class FileSessionStorage implements SessionStorageG {
    /**
     * Saves a SessionStorage object into a file on disk.
     *
     * @param filename the name of file to save the session to.
     * @param session  the SessionStorage object to serialize and save to the file.
     * @throws IOException if an error occurred while saving the file.
     */
    @Override
    public void save(String filename, SessionStorage session) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(session);

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * Loads a SessionStorage object from a file on disk.
     *
     * @param filename the name of the file to read the session from.
     * @return the SessionStorage object obtained from the file.
     * @throws IOException            if an error occurred while reading the file.
     * @throws ClassNotFoundException if the file isn't a valid serialization of a SessionObject.
     */
    @Override
    public SessionStorage load(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        SessionStorage session = (SessionStorage) objectInputStream.readObject();

        objectInputStream.close();

        return session;
    }
}