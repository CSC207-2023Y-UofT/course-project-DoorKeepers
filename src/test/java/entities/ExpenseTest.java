package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ExpenseTest {

    static Category category;
    static Category other_category;

    @BeforeAll
    public static void ExpenseCreateBaseEntities(){
        category = new Category("Food", 100.00);
        other_category = new Category("Shopping", 70.00);
    }

    @Test
    public void ExpenseCreateExpense() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals("Loblaws", e1.getName());
        Assertions.assertEquals(category, e1.getCategory());
        Assertions.assertEquals(50.00, e1.getValue());
    }

    @Test
    public void ExpenseSetName() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals("Loblaws", e1.getName());
        e1.setName("Grocery Trip 1");
        Assertions.assertEquals("Grocery Trip 1", e1.getName());
    }

    @Test
    public void ExpenseSetCategory() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals(category, e1.getCategory());
        e1.setCategory(other_category);
        Assertions.assertEquals(other_category, e1.getCategory());
    }

    @Test
    public void ExpenseSetBudget() {
        Expense e1 = new Expense("Loblaws", category, 50.00 );
        Assertions.assertEquals(50.00, e1.getValue());
        e1.setValue(70.00);
        Assertions.assertEquals(70.00, e1.getValue());
    }

}