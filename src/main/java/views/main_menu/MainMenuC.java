package views.main_menu;

import entities.SessionStorage;
import use_cases.main_menu.SessionSaveIB;
import use_cases.main_menu.SessionSaveID;
import use_cases.main_menu.SessionSaveOD;

import java.io.IOException;

/**
 * The controller class for the main menu.
 * It is called by the view with the user input, and it calls the interactor
 * through the SessionSaveIB interface
 */
public class MainMenuC {
    private final SessionSaveIB interactor;

    public MainMenuC(SessionSaveIB interactor) {
        this.interactor = interactor;
    }

    /**
     * Saves the given session into the given filename
     *
     * @param session  the SessionStorage object to save
     * @param filename a String with the filename to save the session to
     * @return a SessionSaveOD with a message to display to the user
     * @throws IOException if there is an error while saving the file
     */
    public SessionSaveOD save(SessionStorage session, String filename) throws IOException {
        SessionSaveID inputData = new SessionSaveID(session, filename);
        return this.interactor.save(inputData);
    }
}
