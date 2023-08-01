package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * A class that tests the Expense class.
 */
class ExpenseTest {

    static Category category;
    static Category other_category;

    /**
     * Runs once before the methods to set up the necessary entities for the tests.
     */
    @BeforeAll
    public static void ExpenseCreateBaseEntities(){
        category = new Category("Food", 100.00);
        other_category = new Category("Shopping", 70.00);
    }

    /**
     * Tests the Expense initializer, getName(), getCategory(), and getValue() with a valid test case.
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void ExpenseCreateExpense() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals("Loblaws", e1.getName());
        Assertions.assertEquals(category, e1.getCategory());
        Assertions.assertEquals(50.00, e1.getValue());
    }

    /**
     * Tests setName() and getName() with a valid test case
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void ExpenseSetName() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals("Loblaws", e1.getName());
        e1.setName("Grocery Trip 1");
        Assertions.assertEquals("Grocery Trip 1", e1.getName());
    }

    /**
     * Tests setCategory() and getCategory() with a valid test case
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void ExpenseSetCategory() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals(category, e1.getCategory());
        e1.setCategory(other_category);
        Assertions.assertEquals(other_category, e1.getCategory());
    }

    /**
     * Tests setBudget() and getBudget() with a valid test case.
     * Note: not testing for invalid cases, since the Add/Edit UCIs make sure the parameters are valid.
     */
    @Test
    public void ExpenseSetBudget() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals(50.00, e1.getValue());
        e1.setValue(70.00);
        Assertions.assertEquals(70.00, e1.getValue());
    }

    /**
     * Tests equals() override on two Expense objects with the same name.
     */
    @Test
    public void ExpenseCheckEqualsSameName() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Expense e2 = new Expense("Loblaws", other_category, 100.00 );
        Assertions.assertEquals(e1, e2);
    }

    /**
     * Tests equals() override on two Expense objects with the same Category.
     */
    @Test
    public void ExpenseCheckEqualsSameCategory() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Expense e3 = new Expense("Shoppers", category, 50.00 );
        Assertions.assertNotEquals(e1, e3);
    }

    /**
     * Tests equals() override on two Expense objects initialized with the same name, but one is changed.
     * Note: This is important because when we edit an Expense, we need to check if this new Expense is equal to
     * another Expense in the month.
     */
    @Test
    public void ExpenseCheckEqualsChangeName() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Expense e2 = new Expense("Shoppers", other_category, 100.00 );
        Expense e3 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals(e1, e3);
        Assertions.assertNotEquals(e2, e3);
        e3.setName("Shoppers");
        Assertions.assertEquals(e2, e3);
        Assertions.assertNotEquals(e1, e3);
    }

}