package use_cases.session_load;

import entities.SessionStorage;

import java.io.IOException;

/**
 * The use case interactor for loading a session.
 * This class implements the SessionLoadInputBoundary interface and is the one responsible for
 * doing the file accesses and modifying our entities.
 */
public class SessionLoadUseCaseInteractor implements SessionLoadInputBoundary {
    private final SessionStorageGateway storageGateway;
    private final SessionLoadOutputBoundary presenter;
    private final SessionStorage sessionToModify;


    public SessionLoadUseCaseInteractor(SessionStorageGateway storageGateway, SessionLoadOutputBoundary presenter, SessionStorage sessionToModify) {
        this.storageGateway = storageGateway;
        this.presenter = presenter;
        this.sessionToModify = sessionToModify;
    }

    /**
     * Either loads a SessionStorage from a file, or creates a new empty one.
     * Then, mutates this.sessionToModify to reflect the data loaded/created.
     *
     * @param inputData the user input data
     * @return a SessionLoadOutputData object with the output data to be used by the View
     * @throws SessionLoadException if the user chose to load a file and there is an error while opening it
     */
    @Override
    public SessionLoadOutputData load(SessionLoadInputData inputData) throws SessionLoadException {
        if (inputData.getIsNew()) {
            // Create a new empty SessionStorage
            SessionStorage new_session = new SessionStorage();
            this.sessionToModify.copyDataFrom(new_session);
            return this.presenter.displaySuccess("Created new session successfully", this.sessionToModify);
        } else {
            // Load a SessionStorage from a file
            try {
                SessionStorage loaded_session = this.storageGateway.load(inputData.getFilename());
                this.sessionToModify.copyDataFrom(loaded_session);
                return this.presenter.displaySuccess("Loaded session successfully", this.sessionToModify);
            } catch (IOException e) {
                return this.presenter.displayError("File access failed");
            } catch (ClassNotFoundException e) {
                return this.presenter.displayError("File contents incompatible");
            }
        }
    }
}