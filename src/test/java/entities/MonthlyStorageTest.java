package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Tests the MonthlyStorage class
 */
class MonthlyStorageTest {

    static Category category1;
    static Category category2;
    static Category other;
    static Expense expense1;
    static Expense expense2;

    @BeforeAll
    public static void MonthlyStorageCreateBaseEntities(){
        category1 = new Category("Food", 100.00);
        category2 = new Category("Shopping", 70.00);
        expense1 = new Expense("Loblaws", category1, 50.00 );
        expense2 = new Expense("Indigo", category2, 30.00 );
        other = new Category("Other", 0);
    }

    /**
     * Tests the MonthlyStorage initializer, getMonthID(), and getMonthlyBudget()
     */
    @Test
    public void MonthlyStorageCreateMonth() {
        MonthlyStorage month1 = new MonthlyStorage(1, 1000.00);
        Assertions.assertEquals(1, month1.getMonthID());
        Assertions.assertEquals(1000, month1.getMonthlyBudget());
    }

    /**
     * Tests addExpense() and getExpense()
     */
    @Test
    public void MonthlyStorageAddExpense() {
        try {
            MonthlyStorage month1 = new MonthlyStorage(1, 1000.00);
            month1.addExpense(expense1);
            month1.addExpense(expense1);
            month1.addExpense(expense2);

            ArrayList<Expense> expected_expenses_add = new ArrayList<>();
            expected_expenses_add.add(expense1);
            expected_expenses_add.add(expense2);

            Assertions.assertEquals(expected_expenses_add, month1.getExpenseData());
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests addCategory() and getCategory()
     */
    @Test
    public void MonthlyStorageAddCategory() {
        try {
            MonthlyStorage month1 = new MonthlyStorage(1, 1000.00);
            month1.addCategory(category1);
            month1.addCategory(category2);
            month1.addCategory(category2);

            ArrayList<Category> expected_categories_add = new ArrayList<>();
            expected_categories_add.add(other);
            expected_categories_add.add(category1);
            expected_categories_add.add(category2);

            ArrayList<Category> actual = month1.getCategoryData();

            Assertions.assertEquals(expected_categories_add.get(0).getName(), actual.get(0).getName());
            Assertions.assertEquals(expected_categories_add.get(1), actual.get(1));
            Assertions.assertEquals(expected_categories_add.get(2), actual.get(2));
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests deleteExpense()
     */
    @Test
    public void MonthlyStorageDeleteExpense() {
        try {
            MonthlyStorage month1 = new MonthlyStorage(1, 1000.00);
            month1.addExpense(expense1);
            month1.addExpense(expense2);
            month1.deleteExpense(expense1.getName());

            ArrayList<Expense> expected_expenses_delete = new ArrayList<>();
            expected_expenses_delete.add(expense2);

            Assertions.assertEquals(expected_expenses_delete, month1.getExpenseData());
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests deleteCategory()
     */
    @Test
    public void MonthlyStorageDeleteCategory() {
        try {
            MonthlyStorage month1 = new MonthlyStorage(1, 1000.00);
            month1.addCategory(category1);
            month1.addCategory(category2);
            month1.deleteCategory(category1.getName());

            ArrayList<Category> expected_categories_delete = new ArrayList<>();
            expected_categories_delete.add(other);
            expected_categories_delete.add(category2);

            ArrayList<Category> actual = month1.getCategoryData();

            Assertions.assertEquals(expected_categories_delete.get(0).getName(), actual.get(0).getName());
            Assertions.assertEquals(expected_categories_delete.get(1), actual.get(1));
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        }
    }

}