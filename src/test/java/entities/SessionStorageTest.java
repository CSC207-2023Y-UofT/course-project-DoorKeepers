package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Tests the SessionStorage class
 */
class SessionStorageTest {

    static Category category1;
    static Category category2;
    static Expense expense1;
    static Expense expense2;
    static MonthlyStorage month1;
    static MonthlyStorage month2;

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
     * Tests the SessionStorage initializer and getRecurData()
     */
    @Test
    public void SessionStorageCreateNewSession() {
        SessionStorage session1 = new SessionStorage();
        ArrayList<Expense> expected1 = new ArrayList<>();
        Assertions.assertEquals(expected1, session1.getRecurData());
    }

    /**
     * Tests addMonth() and getMonthlyData() on a valid case
     */
    @Test
    public void SessionStorageSetValidMonthlyData() {
        SessionStorage session1 = new SessionStorage();
        session1.addMonth(month1);
        try {
            Assertions.assertEquals(month1, session1.getMonthlyData(1));
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Tests addMonth() and getMonthlyData() on an invalid case
     */
    @Test
    public void SessionStorageSetInvalidMonthlyData() {
        SessionStorage session1 = new SessionStorage();
        session1.addMonth(month1);
        try {
            Assertions.assertEquals(month1, session1.getMonthlyData(3));
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests addRecurExpense() and getRecurData()
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
     * Tests deleteRecurExpense()
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

    /**
     * Tests copyDataFrom()
     */
    @Test
    public void SessionStorageCopyDataFrom() {
        SessionStorage source = new SessionStorage();
        SessionStorage target = new SessionStorage();

        source.addRecurExpense(expense1);
        source.addRecurExpense(expense2);
        source.addMonth(month1);
        source.addMonth(month2);

        Assertions.assertNotEquals(source, target);

        target.copyDataFrom(source);

        Assertions.assertEquals(source.getAllMonthlyData(), target.getAllMonthlyData());
        Assertions.assertEquals(source.getRecurData(), target.getRecurData());
    }
}