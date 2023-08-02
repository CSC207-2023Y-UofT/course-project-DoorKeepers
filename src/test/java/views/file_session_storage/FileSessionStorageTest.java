package views.file_session_storage;


import entities.SessionStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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
     * Tests the successful saving and loading functionality of FileSessionStorage
     * This isn't split into two test cases because they are heavily intertwined; we can't test loading
     * a file without saving one first too. And we can't test that a save was successful without
     * loading it back to check.
     */
    @Test
    public void FileSessionStorage() throws IOException, ClassNotFoundException {
        FileSessionStorage fileSessionStorage = new FileSessionStorage();

        // Save session into file
        fileSessionStorage.save("FSSSaveFileTest.ser", sampleSession);

        // Check file exists
        File f = new File("FSSSaveFileTest.ser");
        Assertions.assertTrue(f.exists());
        Assertions.assertTrue(f.isFile());

        // Load session from file
        SessionStorage savedAndLoadedSession = fileSessionStorage.load("FSSSaveFileTest.ser");

        // Check session loaded is correct
        Assertions.assertEquals(sampleSession, savedAndLoadedSession);

        // Cleanup
        new File("FSSSaveFileTest.ser").delete();
    }

    /**
     * Tests that the load method will throw an exception when loading a file that doesn't exist
     */
    @Test
    public void FileSessionStorageLoadNonexistentFile() {
        FileSessionStorage fileSessionStorage = new FileSessionStorage();

        Assertions.assertThrows(IOException.class, () -> fileSessionStorage.load("TestNonexistentFile.ser"));
    }

    /**
     * Tests that the load method will throw an exception when loading a file that is not a serialization
     * of a SessionStorage
     */
    @Test
    public void FileSessionStorageLoadInvalidFile() throws IOException {
        FileSessionStorage fileSessionStorage = new FileSessionStorage();

        // Build an invalid file to load
        new File("Hello world.txt").createNewFile();

        Assertions.assertThrows(IOException.class, () -> fileSessionStorage.load("Hello world.txt"));

        // Cleanup
        new File("Hello world.txt").delete();
    }
}
