package use_cases.monthly_menu;

import entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.monthly_menu.MonthMenuP;

import java.util.ArrayList;

/**
 * Tests the UpdateViewUCI class.
 */
class UpdateViewUCITest {
    static MonthMenuOB outputBoundary;
    static Category category1;
    static Category category2;
    static Category other;
    static Expense expense1;
    static Expense expense2;
    static int monthID;
    static MonthlyStorage monthlyStorage;
    static SessionStorage sessionStorage;

    /**
     * @throws EntityException if an error occur with addMonth() in SessionStorage
     */
    @BeforeEach
    void setUp() throws EntityException {
        outputBoundary = new MonthMenuP();
        category1 = new Category("Food", 100.00);
        category2 = new Category("Shopping", 70.00);
        expense1 = new Expense("Loblaws", category1, 50.00 );
        expense2 = new Expense("Indigo", category2, 30.00 );
        other = new Category("Other", 0);
        monthID = 202307;
        monthlyStorage = new MonthlyStorage(202307,100);
        sessionStorage = new SessionStorage();
        sessionStorage.addMonth(monthlyStorage);
    }

    /**
     * Test case when viewing a new month created. Method should output MonthMenuOD
     * object storing empty list of Expense and list of Category containing other.
     */
    @Test
    void createOutputNewMonthSuccess() {
        UpdateViewIB interactor = new UpdateViewUCI(outputBoundary);
        UpdateViewID inputData = new UpdateViewID(sessionStorage, monthID);

        ArrayList<Expense> expenses = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(other);
        MonthMenuOD actualOutput = interactor.createOutput(inputData);
        Assertions.assertEquals(expenses,actualOutput.getExpenseData());
        Assertions.assertEquals(categories,actualOutput.getCategoryData());
        Assertions.assertEquals(100,actualOutput.getMonthlyBudget());
    }

    /**
     * Test case when accessing a MonthlyStorage in the specified SessionStorage.
     * Method should output MonthMenuOD storing list of Expense and list of Category.
     * @throws EntityException the exception thrown when adding an Expense/Category that already exist in the month
     */
    @Test
    void createOutputNormalMonthSuccess() throws EntityException {
            UpdateViewIB interactor = new UpdateViewUCI(outputBoundary);
            monthlyStorage.addCategory(category1);
            monthlyStorage.addCategory(category2);
            monthlyStorage.addExpense(expense1);
            monthlyStorage.addExpense(expense2);
            UpdateViewID inputData = new UpdateViewID(sessionStorage, monthID);

            ArrayList<Expense> expenses = new ArrayList<>();
            expenses.add(expense1);
            expenses.add(expense2);
            ArrayList<Category> categories = new ArrayList<>();
            categories.add(other);
            categories.add(category1);
            categories.add(category2);
            MonthMenuOD actualOutput = interactor.createOutput(inputData);
            Assertions.assertEquals(expenses,actualOutput.getExpenseData());
            Assertions.assertEquals(categories,actualOutput.getCategoryData());
            Assertions.assertEquals(100,actualOutput.getMonthlyBudget());
    }

    /**
     * Test case when accessing a MonthlyStorage that does not exist in the
     * specified SessionStorage. Method should output MonthMenuOD storing
     * a String of error message.
     */
    @Test
    void createOutputFail(){
            UpdateViewIB interactor = new UpdateViewUCI(outputBoundary);
            UpdateViewID inputData = new UpdateViewID(sessionStorage, 202207);

            MonthMenuOD actualOutput = interactor.createOutput(inputData);
            Assertions.assertEquals("An error has occurred. Please reload the program.",actualOutput.getWarning());
    }
}