package use_cases.session_load;

import entities.SessionStorage;
import views.file_session_storage.SessionStorageG;

import java.io.IOException;

public class SessionLoadUCI implements SessionLoadIB {
    private final SessionStorageG storageGateway;
    private final SessionLoadOB presenter;
    private final SessionStorage sessionToModify;


    public SessionLoadUCI(SessionStorageG storageGateway, SessionLoadOB presenter, SessionStorage sessionToModify) {
        this.storageGateway = storageGateway;
        this.presenter = presenter;
        this.sessionToModify = sessionToModify;
    }

    /**
     * Either loads a SessionStorage from a file, or creates a new empty one.
     * Then, mutates this.sessionToModify to reflect the data loaded/created.
     *
     * @param inputData the user input data
     * @return a SessionLoadOD object with the output data to be used by the View
     * @throws SessionLoadException if the user chose to load a file and there is an error while opening it
     */
    @Override
    public SessionLoadOD load(SessionLoadID inputData) throws SessionLoadException {
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