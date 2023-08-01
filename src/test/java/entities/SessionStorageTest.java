package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * A class that tests the SessionStorage class
 */
class SessionStorageTest {

    static Category category1;
    static Category category2;
    static Expense expense1;
    static Expense expense2;
    static MonthlyStorage month1;
    static MonthlyStorage month2;
    static MonthlyStorage actual;

    /**
     * Runs once before the methods to set up the necessary entities for the tests.
     */
    @BeforeAll
    public static void SessionStorageCreateBaseEntities(){
        category1 = new Category("Food", 100.00);
        category2 = new Category("Shopping", 70.00);
        expense1 = new Expense("Loblaws", category1, 50.00 );
        expense2 = new Expense("Indigo", category2, 30.00 );
        month1 = new MonthlyStorage(1, 1000.00);
        month2 = new MonthlyStorage(2, 900.00);
    }

    /**
     * Tests the SessionStorage initializer and getRecurData().
     */
    @Test
    public void SessionStorageCreateNewSession() {
        SessionStorage session1 = new SessionStorage();
        ArrayList<Expense> expected1 = new ArrayList<>();
        Assertions.assertEquals(expected1, session1.getRecurData());
    }

    /**
     * Tests addMonth() and getMonthlyData() on a valid case.
     */
    @Test
    public void SessionStorageSetValidMonthlyData() {
        SessionStorage session1 = new SessionStorage();
        session1.addMonth(month1);
        try {
            actual = session1.getMonthlyData(1);
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        } finally {
            Assertions.assertEquals(month1, actual);
        }

    }

    /**
     * Tests addMonth() and getMonthlyData() on an invalid case (a monthID that is not in the SessionStorage).
     */
    @Test
    public void SessionStorageSetInvalidMonthlyData() {
        EntityException thrown = Assertions.assertThrows(EntityException.class, () -> {
            SessionStorage session1 = new SessionStorage();
            session1.addMonth(month1);
            actual = session1.getMonthlyData(3);
            Assertions.assertEquals(month1, actual);
        });
        Assertions.assertEquals("That is not a valid monthID for this SessionStorage.",
                thrown.getMessage());
    }

    /**
     * Tests addRecurExpense() and getRecurData() with a valid test case.
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void SessionStorageAddRecurExpense() {
        SessionStorage session1 = new SessionStorage();
        session1.addRecurExpense(expense1);
        session1.addRecurExpense(expense2);

        ArrayList<Expense> expected2 = new ArrayList<>();
        expected2.add(expense1);
        expected2.add(expense2);

        Assertions.assertEquals(expected2, session1.getRecurData());
    }

    /**
     * Tests deleteRecurExpense() with a valid test case.
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void SessionStorageDeleteRecurExpense() {
        SessionStorage session1 = new SessionStorage();
        session1.addRecurExpense(expense1);
        session1.addRecurExpense(expense2);
        session1.deleteRecurExpense(expense1.getName());

        ArrayList<Expense> expected2 = new ArrayList<>();
        expected2.add(expense2);

        Assertions.assertEquals(expected2, session1.getRecurData());
    }

}