package use_cases.session_load;


import entities.SessionStorage;
import views.file_session_storage.SessionStorageG;

import java.io.File;
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
     * @param inputData the user input data
     */
    @Override
    public void load(SessionLoadID inputData) {
        if (inputData.getIsNew()) {
            // Create a new empty SessionStorage
            SessionStorage new_session = new SessionStorage();
            this.sessionToModify.copyDataFrom(new_session);
            this.presenter.displaySuccess("Created new session successfully");
        } else {
            try {
                // Load a SessionStorage from a file
                SessionStorage loaded_session = this.storageGateway.load(inputData.getFilename());
                this.sessionToModify.copyDataFrom(loaded_session);
                this.presenter.displaySuccess("Loaded session successfully");
            } catch (IOException e) {
                this.presenter.displayError("File access failed");
            } catch (ClassNotFoundException e) {
                this.presenter.displayError("File contents incompatible");
            }
        }
    }

    // TODO: Turn this method into tests
    // I used this to test the functionality of my UCI
    public static void main(String[] args) throws IOException {
        // Creating a sample SessionStorage object with one month and one recurring expense
        // Also saving it to a file called test.ser so we can test loading it from a file too
        SessionStorage sample_session = new SessionStorage();
        sample_session.addRecurExpense(new entities.Expense("abc", new entities.Category("lala", 10.0), 3.0));
        sample_session.addMonth(new entities.MonthlyStorage(1, 20.0));
        new views.file_session_storage.FileSessionStorage().save("test.ser", sample_session);

        // This 'session' variable would be like the one we would have in the actual Main method
        SessionStorage session = new SessionStorage();
        session.copyDataFrom(sample_session);
        assert session.equals(sample_session);

        // Create our SessionLoadUCI that we'll test
        SessionLoadUCI uci = new SessionLoadUCI(new views.file_session_storage.FileSessionStorage(), new views.session_load.SessionLoadP(), session);

        // Test loading an empty session
        SessionLoadID inputData = new SessionLoadID();
        uci.load(inputData);
        assert session.equals(new SessionStorage());

        // Test loading a session from a file
        inputData = new SessionLoadID("test.ser");
        uci.load(inputData);
        assert session.equals(sample_session);

        // Cleanup
        new File("test.ser").delete();
    }
}