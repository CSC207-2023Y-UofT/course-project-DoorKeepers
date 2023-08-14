package use_cases.main_menu;

import entities.EntityException;
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
     * Tests the save method with valid inputs and no IOExceptions
     *
     * @throws IOException shouldn't throw since we are saving and loading to valid files
     * @throws ClassNotFoundException shouldn't throw since we are only loading the file we just created
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
     * Tests the save method with an invalid filename that will cause an IOException to throw
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