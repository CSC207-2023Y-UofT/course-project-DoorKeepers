package use_cases.main_menu;

import entities.SessionStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import views.file_session_storage.FileSessionStorage;
import views.main_menu.MainMenuP;

import java.io.File;
import java.io.IOException;

/**
 * Tests the SessionSaveUCI class
 */
class SessionSaveUCITest {
    static SessionStorage sampleSession;

    @BeforeAll
    public static void SessionLoadUCICreateSampleSession() {
        sampleSession = new SessionStorage();
        sampleSession.addRecurExpense(new entities.Expense("abc", new entities.Category("lala", 10.0), 3.0));
        sampleSession.addMonth(new entities.MonthlyStorage(1, 20.0));
    }

    /**
     * Tests saving a session into a file correctly
     */
    @Test
    void SessionSaveUCISave() throws IOException, ClassNotFoundException {
        FileSessionStorage fileSessionStorage = new FileSessionStorage();
        SessionSaveUCI uci = new SessionSaveUCI(fileSessionStorage, new MainMenuP());

        // Save sample session to a file
        SessionSaveID inputData = new SessionSaveID(sampleSession, "UCISaveFileTest.ser");
        SessionSaveOD outputData = uci.save(inputData);

        // Test output data correctly returned
        Assertions.assertEquals(outputData.getMessage(), "Session saved successfully");

        // Test session file correct
        SessionStorage loadedSession = fileSessionStorage.load("UCISaveFileTest.ser");
        Assertions.assertEquals(loadedSession, sampleSession);

        // Cleanup
        new File("UCISaveFileTest.ser").delete();
    }

    /**
     * Tests failure when saving a session into a file
     */
    @Test
    void SessionSaveUCIFailSave() {
        FileSessionStorage fileSessionStorage = new FileSessionStorage();
        SessionSaveUCI uci = new SessionSaveUCI(fileSessionStorage, new MainMenuP());

        // Test UCI throws IOException
        String invalidFilename = "/";
        SessionSaveID inputData = new SessionSaveID(sampleSession, invalidFilename);

        Assertions.assertThrows(IOException.class, () -> uci.save(inputData));
    }
}