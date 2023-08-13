package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * A class that tests the ExpenseFactory, ExpenseCreatorInputData, and ExpenseEditorInputData classes.
 * Note: all error handling is done in the ExpenseUseCaseInteractor, so all expense info in the input data should
 * be valid.
 */
class ExpenseFactoryTest {

    /**
     * Tests createMonthObject() with valid expense information.
     */
    @Test
    public void ExpenseCreate() {
        Category c1 = new Category("Dine out", 50);
        ExpenseCreatorInputData inputData = new ExpenseCreatorInputData("Subway", c1, 10);
        MonthObjectFactory creator = new ExpenseFactory();
        MonthObject monthObject = creator.createMonthObject(inputData);

        Expense expense = (Expense) monthObject;
        Assertions.assertEquals(expense.getName(), inputData.getName());
        Assertions.assertEquals(expense.getCategory(), inputData.getCategory());
        Assertions.assertEquals(expense.getValue(), inputData.getValue());
    }

    /**
     * Tests editMonthObject() with valid expense information and a different name.
     */
    @Test
    public void ExpenseEditName() {
        Category c1 = new Category("Dine out", 50);
        Expense e1 = new Expense("Subway", c1, 10);
        ExpenseEditorInputData inputData = new ExpenseEditorInputData("Pizza", c1, 10, e1);
        MonthObjectFactory editor = new ExpenseFactory();
        MonthObject monthObject = editor.editMonthObject(inputData);

        Expense expense = (Expense) monthObject;
        Assertions.assertEquals(expense.getName(), inputData.getName());
        Assertions.assertEquals(expense.getCategory(), inputData.getCategory());
        Assertions.assertEquals(expense.getValue(), inputData.getValue());
        Assertions.assertEquals(expense, inputData.getExpense());
    }

    /**
     * Tests editMonthObject() with valid expense information and a different category.
     */
    @Test
    public void ExpenseEditCategory() {
        Category c1 = new Category("Dine out", 50);
        Category c2 = new Category("Groceries", 100);
        Expense e1 = new Expense("Subway", c1, 10);
        ExpenseEditorInputData inputData = new ExpenseEditorInputData("Subway", c2, 10, e1);
        MonthObjectFactory editor = new ExpenseFactory();
        MonthObject monthObject = editor.editMonthObject(inputData);

        Expense expense = (Expense) monthObject;
        Assertions.assertEquals(expense.getName(), inputData.getName());
        Assertions.assertEquals(expense.getCategory(), inputData.getCategory());
        Assertions.assertEquals(expense.getValue(), inputData.getValue());
        Assertions.assertEquals(expense, inputData.getExpense());
    }

    /**
     * Tests editMonthObject() with valid expense information and a different category.
     */
    @Test
    public void ExpenseEditValue() {
        Category c1 = new Category("Dine out", 50);
        Expense e1 = new Expense("Subway", c1, 10);
        ExpenseEditorInputData inputData = new ExpenseEditorInputData("Subway", c1, 20, e1);
        MonthObjectFactory editor = new ExpenseFactory();
        MonthObject monthObject = editor.editMonthObject(inputData);

        Expense expense = (Expense) monthObject;
        Assertions.assertEquals(expense.getName(), inputData.getName());
        Assertions.assertEquals(expense.getCategory(), inputData.getCategory());
        Assertions.assertEquals(expense.getValue(), inputData.getValue());
        Assertions.assertEquals(expense, inputData.getExpense());
    }

    /**
     * Tests editMonthObject() with valid expense information and nothing different.
     * Note: this test is included since The ExpenseUseCaseInteractor considers this as a valid option for the user to
     * edit, and sends the request to the ExpenseFactory. Thus, it is added to ensure that the ExpenseFactory will
     * correctly handle this case.
     */
    @Test
    public void ExpenseEditNothing() {
        Category c1 = new Category("Dine out", 50);
        Expense e1 = new Expense("Subway", c1, 10);
        ExpenseEditorInputData inputData = new ExpenseEditorInputData("Subway", c1, 10, e1);
        MonthObjectFactory editor = new ExpenseFactory();
        MonthObject monthObject = editor.editMonthObject(inputData);

        Expense expense = (Expense) monthObject;
        Assertions.assertEquals(expense.getName(), inputData.getName());
        Assertions.assertEquals(expense.getCategory(), inputData.getCategory());
        Assertions.assertEquals(expense.getValue(), inputData.getValue());
        Assertions.assertEquals(expense, inputData.getExpense());
    }
}