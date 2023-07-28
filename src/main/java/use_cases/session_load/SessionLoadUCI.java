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
     */
    @Override
    public void load(SessionLoadID inputData) {
        if (inputData.getIsNew()) {
            // Create a new empty SessionStorage
            SessionStorage new_session = new SessionStorage();
            this.sessionToModify.copyDataFrom(new_session);
            this.presenter.displaySuccess("Created new session successfully", this.sessionToModify);
        } else {
            try {
                // Load a SessionStorage from a file
                SessionStorage loaded_session = this.storageGateway.load(inputData.getFilename());
                this.sessionToModify.copyDataFrom(loaded_session);
                this.presenter.displaySuccess("Loaded session successfully", this.sessionToModify);
            } catch (IOException e) {
                this.presenter.displayError("File access failed");
            } catch (ClassNotFoundException e) {
                this.presenter.displayError("File contents incompatible");
            }
        }
    }
}