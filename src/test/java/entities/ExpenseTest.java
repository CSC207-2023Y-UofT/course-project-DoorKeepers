package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests the Expense class
 */
class ExpenseTest {

    static Category category;
    static Category other_category;

    @BeforeAll
    public static void ExpenseCreateBaseEntities(){
        category = new Category("Food", 100.00);
        other_category = new Category("Shopping", 70.00);
    }

    /**
     * Tests the Expense initializer, getName(), getCategory(), and getValue()
     */
    @Test
    public void ExpenseCreateExpense() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals("Loblaws", e1.getName());
        Assertions.assertEquals(category, e1.getCategory());
        Assertions.assertEquals(50.00, e1.getValue());
    }

    /**
     * Tests setName() and getName()
     */
    @Test
    public void ExpenseSetName() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals("Loblaws", e1.getName());
        e1.setName("Grocery Trip 1");
        Assertions.assertEquals("Grocery Trip 1", e1.getName());
    }

    /**
     * Tests setCategory() and getCategory()
     */
    @Test
    public void ExpenseSetCategory() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals(category, e1.getCategory());
        e1.setCategory(other_category);
        Assertions.assertEquals(other_category, e1.getCategory());
    }

    /**
     * Tests setBudget() and getBudget()
     */
    @Test
    public void ExpenseSetBudget() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals(50.00, e1.getValue());
        e1.setValue(70.00);
        Assertions.assertEquals(70.00, e1.getValue());
    }

    /**
     * Tests equals() override
     */
    @Test
    public void ExpenseCheckEquals() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Expense e2 = new Expense("Loblaws", category, 100.00 );
        Expense e3 = new Expense("Shoppers", category, 50.00 );
        Assertions.assertEquals(e1, e2);
        Assertions.assertNotEquals(e1, e3);
    }

}