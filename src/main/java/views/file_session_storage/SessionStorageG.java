package views.file_session_storage;

import entities.SessionStorage;

import java.io.IOException;

public interface SessionStorageG {
    void save(String filename, SessionStorage session) throws IOException;
    SessionStorage load(String filename) throws IOException, ClassNotFoundException;
}