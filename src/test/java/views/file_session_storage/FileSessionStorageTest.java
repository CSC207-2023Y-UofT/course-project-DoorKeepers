package views.file_session_storage;


import entities.SessionStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Tests the FileSessionStorage class
 */
class FileSessionStorageTest {
    static SessionStorage sampleSession;

    @BeforeAll
    public static void SessionLoadUCICreateSampleSession() {
        sampleSession = new SessionStorage();
        sampleSession.addRecurExpense(new entities.Expense("abc", new entities.Category("lala", 10.0), 3.0));
        sampleSession.addMonth(new entities.MonthlyStorage(1, 20.0));
    }


    /**
     * Tests the saving and loading functionality of FileSessionStorage
     * This isn't split into two test cases because they are heavily intertwined; we can't test loading
     * a file without saving one first too.
     */
    @Test
    public void FileSessionStorage() {
        FileSessionStorage fileSessionStorage = new FileSessionStorage();

        // Save session into file
        try {
            fileSessionStorage.save("FSSSaveFileTest.ser", sampleSession);
        } catch (Exception e) {
            Assertions.fail();
        }

        // Check file exists
        File f = new File("FSSSaveFileTest.ser");
        Assertions.assertTrue(f.exists());
        Assertions.assertTrue(f.isFile());

        // Load session from file
        SessionStorage savedAndLoadedSession = null;
        try {
            savedAndLoadedSession = fileSessionStorage.load("FSSSaveFileTest.ser");
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(sampleSession, savedAndLoadedSession);

        // Cleanup
        new File("FSSSaveFileTest.ser").delete();
    }
}
