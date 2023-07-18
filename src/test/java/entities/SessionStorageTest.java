package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    public void SessionStorageCreateNewSession() {
        SessionStorage session1 = new SessionStorage();
        ArrayList<Expense> expected1 = new ArrayList<>();
        Assertions.assertEquals(expected1, session1.getRecurData());
    }

    @Test
    public void SessionStorageSetValidMonthlyData() throws EntityException {
        SessionStorage session1 = new SessionStorage();
        session1.setMonthlyData(month1);
        try {
            Assertions.assertEquals(month1, session1.getMonthlyData(1));
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void SessionStorageSetInvalidMonthlyData() throws EntityException {
        SessionStorage session1 = new SessionStorage();
        session1.setMonthlyData(month1);
        try {
            Assertions.assertEquals(month1, session1.getMonthlyData(2));
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void SessionStorageSetRecurData() {
        SessionStorage session1 = new SessionStorage();
        session1.setRecurData(expense1);
        session1.setRecurData(expense2);
        ArrayList<Expense> expected2 = new ArrayList<>();
        expected2.add(expense1);
        expected2.add(expense2);
        Assertions.assertEquals(expected2, session1.getRecurData());
    }

}