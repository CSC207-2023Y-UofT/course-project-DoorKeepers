package use_cases.main_menu;

import use_cases.session_load.SessionStorageG;

import java.io.IOException;

/**
 * The use case interactor for saving a session to a serialized file
 * This class implements the SessionSaveIB interface and is the one responsible for
 * doing the file storage
 */
public class SessionSaveUCI implements SessionSaveIB {
    private final SessionStorageG storageGateway;
    private final SessionSaveOB presenter;

    /**
     * Creates a new SessionSaveUCI
     *
     * @param storageGateway a storage gateway that implements the SessionStorageG interface
     * @param presenter a presenter that implements the SessionSaveOB interface
     */
    public SessionSaveUCI(SessionStorageG storageGateway, SessionSaveOB presenter) {
        this.storageGateway = storageGateway;
        this.presenter = presenter;
    }

    /**
     * Saves a session into a file according to the input data given
     *
     * @param inputData a SessionSaveID with the user input data
     * @return a SessionSaveOD object with the output data to be used by the View
     * @throws IOException if there is an error while saving the file
     */
    @Override
    public SessionSaveOD save(SessionSaveID inputData) throws IOException {
        this.storageGateway.save(inputData.getFilename(), inputData.getSession());
        return this.presenter.displaySuccess(new SessionSaveOD("Session saved successfully"));
    }
}
