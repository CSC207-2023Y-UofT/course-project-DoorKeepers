package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * A class that tests the MonthlyStorage class.
 */
class MonthlyStorageTest {

    static Category category1;
    static Category category2;
    static Category other;
    static Expense expense1;
    static Expense expense2;
    static Expense expense3;
    static MonthlyStorage month1;
    static MonthlyStorage month2;

    /**
     * Runs once before the methods to set up the necessary entities for the tests.
     */
    @BeforeAll
    public static void MonthlyStorageCreateBaseEntities(){
        category1 = new Category("Food", 100.00);
        category2 = new Category("Shopping", 70.00);
        expense1 = new Expense("Loblaws", category1, 50.00 );
        expense2 = new Expense("Indigo", category2, 30.00 );
        expense3 = new Expense("Loblaws", category2, 100.00);
        other = new Category("Other", 0);
    }

    /**
     * Tests the MonthlyStorage initializer, getMonthID(), and getMonthlyBudget().
     */
    @Test
    public void MonthlyStorageCreateMonth() {
        MonthlyStorage new_month = new MonthlyStorage(1, 1000.00);
        Assertions.assertEquals(1, new_month.getMonthID());
        Assertions.assertEquals(1000, new_month.getMonthlyBudget());
    }

    /**
     * Tests addExpense() and getExpense() with a valid test case.
     */
    @Test
    public void MonthlyStorageAddExpenseSuccess() {
        try {
            month1 = new MonthlyStorage(1, 1000.00);
            month1.addExpense(expense1);
            month1.addExpense(expense2);
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        } finally {
            ArrayList<Expense> expected_expenses_add = new ArrayList<>();
            expected_expenses_add.add(expense1);
            expected_expenses_add.add(expense2);
            Assertions.assertEquals(expected_expenses_add, month1.getExpenseData());
        }
    }

    /**
     * Tests addExpense() and getExpense() with an invalid test case (two Expense objects with the same name).
     */
    @Test
    public void MonthlyStorageAddExpenseFail() {
        EntityException thrown = Assertions.assertThrows(EntityException.class, () -> {
            month2 = new MonthlyStorage(2, 1000.00);
            month2.addExpense(expense1);
            month2.addExpense(expense3);
        });
        Assertions.assertEquals("There is already an Expense with that name in this MonthlyStorage.",
                thrown.getMessage());
    }

    /**
     * Tests addCategory() and getCategory() with a valid test case.
     */
    @Test
    public void MonthlyStorageAddCategorySuccess() {
        try {
            month1 = new MonthlyStorage(1, 1000.00);
            month1.addCategory(category1);
            month1.addCategory(category2);
        } catch (EntityException e) {
            System.out.println(e.getMessage());
        } finally {
            ArrayList<Category> expected_categories_add = new ArrayList<>();
            expected_categories_add.add(other);
            expected_categories_add.add(category1);
            expected_categories_add.add(category2);

            ArrayList<Category> actual = month1.getCategoryData();

            Assertions.assertEquals(expected_categories_add.get(0).getName(), actual.get(0).getName());
            Assertions.assertEquals(expected_categories_add.get(1), actual.get(1));
            Assertions.assertEquals(expected_categories_add.get(2), actual.get(2));
        }
    }

    /**
     * Tests addCategory() and getCategory() with an invalid test case (two Category objects with the same name).
     */
    @Test
    public void MonthlyStorageAddCategoryFail() {
        EntityException thrown = Assertions.assertThrows(EntityException.class, () -> {
            month2 = new MonthlyStorage(2, 1000.00);
            month2.addCategory(category1);
            month2.addCategory(category2);
            month2.addCategory(category2);
        });
        Assertions.assertEquals("There is already a Category with that name in this MonthlyStorage.",
                thrown.getMessage());
    }

    /**
     * Tests deleteExpense() with a valid test case.
     * Note: EntityException is thrown because of addExpense() method that is not tested here.
     * @throws EntityException if a valid method call throws
     */
    @Test
    public void MonthlyStorageDeleteExpenseSuccess() throws EntityException{
        MonthlyStorage new_month = new MonthlyStorage(3, 1000);
        new_month.addExpense(expense1);
        new_month.addExpense(expense2);
        new_month.deleteExpense(expense1.getName());

        ArrayList<Expense> expected_expenses_delete = new ArrayList<>();
        expected_expenses_delete.add(expense2);

        Assertions.assertEquals(expected_expenses_delete, new_month.getExpenseData());
    }

    /**
     * Tests deleteExpense() with an Expense not in the MonthlyStorage.
     * Note: EntityException is thrown because of addExpense() method that is not tested here.
     * @throws EntityException if a valid method call throws
     */
    @Test
    public void MonthlyStorageDeleteExpenseFail() throws EntityException {
        MonthlyStorage new_month = new MonthlyStorage(4, 1000);
        new_month.addExpense(expense1);
        new_month.deleteExpense("Candy");

        ArrayList<Expense> expected_expenses_delete = new ArrayList<>();
        expected_expenses_delete.add(expense1);

        Assertions.assertEquals(expected_expenses_delete, new_month.getExpenseData());
    }

    /**
     * Tests deleteCategory() with a valid test case.
     * Note: EntityException is thrown because of addCategory() method that is not tested here.
     * @throws EntityException if a valid method call throws
     */
    @Test
    public void MonthlyStorageDeleteCategorySuccess() throws EntityException{
        MonthlyStorage new_month = new MonthlyStorage(5, 1000.00);
        new_month.addCategory(category1);
        new_month.addCategory(category2);
        new_month.deleteCategory(category1.getName());

        ArrayList<Category> expected_categories_delete = new ArrayList<>();
        expected_categories_delete.add(other);
        expected_categories_delete.add(category2);

        ArrayList<Category> actual = new_month.getCategoryData();

        Assertions.assertEquals(expected_categories_delete.get(0).getName(), actual.get(0).getName());
        Assertions.assertEquals(expected_categories_delete.get(1), actual.get(1));
    }

    /**
     * Tests deleteCategory() with a Category not in the MonthlyStorage.
     * Note: EntityException is thrown because of addCategory() method that is not tested here.
     * @throws EntityException if a valid method call throws
     */
    @Test
    public void MonthlyStorageDeleteCategoryFail() throws EntityException{
        MonthlyStorage new_month = new MonthlyStorage(5, 1000.00);
        new_month.addCategory(category1);
        new_month.deleteCategory("Treats");

        ArrayList<Category> expected_categories_delete = new ArrayList<>();
        expected_categories_delete.add(other);
        expected_categories_delete.add(category1);

        ArrayList<Category> actual = new_month.getCategoryData();

        Assertions.assertEquals(expected_categories_delete.get(0).getName(), actual.get(0).getName());
        Assertions.assertEquals(expected_categories_delete.get(1), actual.get(1));

    }

}