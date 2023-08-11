package use_cases.session_load;

import entities.EntityException;
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

    /**
     * Creates a sample SessionStorage object that following test cases will use.
     * @throws EntityException if an error occur with addMonth() in SessionStorage
     */
    @BeforeAll
    public static void SessionLoadUCICreateSampleSession() throws EntityException {
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

        // Create SessionLoadUCI to test
        SessionLoadUCI uci = new SessionLoadUCI(
                new views.file_session_storage.FileSessionStorage(),
                new views.session_load.SessionLoadP(),
                session
        );

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
     * @throws IOException if an IO error that's outside the scope of this test happens
     */
    @Test
    public void SessionLoadUCILoadFile() throws IOException {
        // This 'session' variable would be like the one we would have in the actual Main method
        SessionStorage session = new SessionStorage();

        // Create SessionLoadUCI to test
        SessionLoadUCI uci = new SessionLoadUCI(
                new views.file_session_storage.FileSessionStorage(),
                new views.session_load.SessionLoadP(),
                session
        );

        // Save sample session to a file
        new views.file_session_storage.FileSessionStorage().save("UCILoadFileTest.ser", sampleSession);

        // Load sample session from the file
        SessionLoadID inputData = new SessionLoadID("UCILoadFileTest.ser");
        Assertions.assertDoesNotThrow(() -> {
            uci.load(inputData);
        });

        Assertions.assertEquals(session, sampleSession);

        // Cleanup
        new File("UCILoadFileTest.ser").delete();
    }

    /**
     * Tests trying to load a file that doesn't exist and that a SessionLoadException will be raised
     */
    @Test
    public void SessionLoadUCILoadNonexistentFile() {
        SessionStorage session = sampleSession;

        // Create SessionLoadUCI to test
        SessionLoadUCI uci = new SessionLoadUCI(
                new views.file_session_storage.FileSessionStorage(),
                new views.session_load.SessionLoadP(),
                session
        );

        // Test loading nonexistent file
        SessionLoadID inputData = new SessionLoadID("TestNonexistentFile.ser");
        Assertions.assertThrows(SessionLoadException.class, () -> uci.load(inputData));

        // Check that our session is left unchanged
        Assertions.assertEquals(session, sampleSession);
    }

    /**
     * Tests trying to load an invalid file  and that a SessionLoadException will be raised
     * @throws IOException if an IO error that's outside the scope of this test happens
     */
    @Test
    public void SessionLoadUCILoadInvalidFile() throws IOException {
        SessionStorage session = sampleSession;

        // Create SessionLoadUCI to test
        SessionLoadUCI uci = new SessionLoadUCI(
                new views.file_session_storage.FileSessionStorage(),
                new views.session_load.SessionLoadP(),
                session
        );

        // Build an invalid file to load
        new File("Hello world.txt").createNewFile();

        // Test loading invalid file
        SessionLoadID inputData = new SessionLoadID("Hello world.txt");
        Assertions.assertThrows(SessionLoadException.class, () -> uci.load(inputData));

        // Check that our session is left unchanged
        Assertions.assertEquals(session, sampleSession);

        // Cleanup
        new File("Hello world.txt").delete();
    }
}
