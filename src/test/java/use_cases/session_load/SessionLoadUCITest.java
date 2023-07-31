package use_cases.session_load;

import entities.SessionStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests the SessionLoadUCI class
 */
class SessionLoadUCITest {
    static SessionStorage sampleSession;
    @BeforeAll
    public static void SessionLoadUCICreateSampleSession() {
        sampleSession = new SessionStorage();
        sampleSession.addRecurExpense(new entities.Expense("abc", new entities.Category("lala", 10.0), 3.0));
        sampleSession.addMonth(new entities.MonthlyStorage(1, 20.0));
    }

    /**
     * Tests loading a new empty session
     */
    @Test
    public void SessionLoadUCILoadNew() {
        SessionStorage session = new SessionStorage();
        session.copyDataFrom(sampleSession);
        Assertions.assertEquals(session, sampleSession);

        // Create our SessionLoadUCI that we'll test
        SessionLoadUCI uci = new SessionLoadUCI(
                new views.file_session_storage.FileSessionStorage(),
                new views.session_load.SessionLoadP(),
                session);

        // Test loading an empty session
        SessionLoadID inputData = new SessionLoadID();
        Assertions.assertDoesNotThrow(() -> {
            uci.load(inputData);
        });

        // session should be empty now
        assert session.equals(new SessionStorage());
    }

    /**
     * Tests loading an existing session from a file
     */
    @Test
    public void SessionLoadUCILoadFile() {
        // This 'session' variable would be like the one we would have in the actual Main method
        SessionStorage session = new SessionStorage();

        // Create our SessionLoadUCI that we'll test
        SessionLoadUCI uci = new SessionLoadUCI(
                new views.file_session_storage.FileSessionStorage(),
                new views.session_load.SessionLoadP(),
                session);

        // Save sample session to a file
        try {
            new views.file_session_storage.FileSessionStorage().save("UCILoadFileTest.ser", sampleSession);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Load sample session from the file
        SessionLoadID inputData = new SessionLoadID("UCILoadFileTest.ser");
        Assertions.assertDoesNotThrow(() -> {
            uci.load(inputData);
        });

        Assertions.assertEquals(session, sampleSession);

        // Cleanup
        new File("UCILoadFileTest.ser").delete();
    }
}
