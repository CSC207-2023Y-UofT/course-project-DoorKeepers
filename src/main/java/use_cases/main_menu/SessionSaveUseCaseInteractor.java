package use_cases.main_menu;

import use_cases.session_load.SessionStorageGateway;

import java.io.IOException;

/**
 * The use case interactor for saving a session to a serialized file
 * This class implements the SessionSaveInputBoundary interface and is the one responsible for
 * doing the file storage
 */
public class SessionSaveUseCaseInteractor implements SessionSaveInputBoundary {
    private final SessionStorageGateway storageGateway;
    private final SessionSaveOutputBoundary presenter;

    /**
     * Creates a new SessionSaveUseCaseInteractor
     *
     * @param storageGateway a storage gateway that implements the SessionStorageGateway interface
     * @param presenter      a presenter that implements the SessionSaveOutputBoundary interface
     */
    public SessionSaveUseCaseInteractor(SessionStorageGateway storageGateway, SessionSaveOutputBoundary presenter) {
        this.storageGateway = storageGateway;
        this.presenter = presenter;
    }

    /**
     * Saves a session into a file according to the input data given
     *
     * @param inputData a SessionSaveInputData with the user input data
     * @return a SessionSaveOutputData object with the output data to be used by the View
     * @throws IOException if there is an error while saving the file
     */
    @Override
    public SessionSaveOutputData save(SessionSaveInputData inputData) throws IOException {
        this.storageGateway.save(inputData.getFilename(), inputData.getSession());
        return this.presenter.displaySuccess(new SessionSaveOutputData("Session saved successfully"));
    }
}
