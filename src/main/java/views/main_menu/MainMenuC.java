package views.main_menu;

import entities.SessionStorage;
import use_cases.main_menu.SessionSaveInputBoundary;
import use_cases.main_menu.SessionSaveInputData;
import use_cases.main_menu.SessionSaveOutputData;

import java.io.IOException;

/**
 * The controller class for the main menu.
 * It is called by the view with the user input, and it calls the interactor
 * through the SessionSaveInputBoundary interface
 */
public class MainMenuC {
    private final SessionSaveInputBoundary interactor;

    /**
     * Creates a new MainMenuC
     *
     * @param interactor an interactor that implements the SessionSaveInputBoundary interface
     */
    public MainMenuC(SessionSaveInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Saves the given session into the given filename
     *
     * @param session  the SessionStorage object to save
     * @param filename a String with the filename to save the session to
     * @return a SessionSaveOutputData with a message to display to the user
     * @throws IOException if there is an error while saving the file
     */
    public SessionSaveOutputData save(SessionStorage session, String filename) throws IOException {
        SessionSaveInputData inputData = new SessionSaveInputData(session, filename);
        return this.interactor.save(inputData);
    }
}
