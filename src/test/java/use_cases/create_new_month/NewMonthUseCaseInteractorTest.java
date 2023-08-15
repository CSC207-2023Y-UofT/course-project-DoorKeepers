package use_cases.create_new_month;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.create_new_month.NewMonthPresenter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the NewMonthUseCaseInteractor class.
 */
class NewMonthUseCaseInteractorTest {
    static NewMonthOutputBoundary outputBoundary;
    static int monthID1;
    static int monthID2;
    static double budgetValue;
    static MonthlyStorage monthlyStorage1;
    static SessionStorage sessionStorage;

    /**
     * Runs before each method to set up the necessary entities and relevant objects for the tests.
     * @throws EntityException if an error occur with addMonth() in SessionStorage
     */
    @BeforeEach
    void setUp() throws EntityException {
        outputBoundary = new NewMonthPresenter();
        monthID1 = 202305;
        monthID2 = 202308;
        budgetValue = 100;
        monthlyStorage1 = new MonthlyStorage(monthID1,budgetValue);
        sessionStorage = new SessionStorage();
        sessionStorage.addMonth(monthlyStorage1);
    }

    /**
     * Test case when new MonthlyStorage is created with correct ID.
     * @throws EntityException when test fails
     */
    @Test
    void createOutputNoRecurExpenseSuccess() throws EntityException {
        NewMonthInputBoundary interactor = new NewMonthUseCaseInteractor(outputBoundary);
        NewMonthInputData inputData = new NewMonthInputData(sessionStorage,monthID2,budgetValue);

        NewMonthOutputData output = interactor.createOutput(inputData);
        assertTrue(output.isSuccessful());
        assertEquals(monthID2, sessionStorage.getMonthlyData(monthID2).getMonthID());
    }

    /**
     * Test case when new MonthlyStorage is created with correct ID, and recurring expenses added.
     * @throws EntityException when test fails
     */
    @Test
    void createOutputRecurExpenseSuccess() throws EntityException {
        NewMonthInputBoundary interactor = new NewMonthUseCaseInteractor(outputBoundary);

        Category other = sessionStorage.getMonthlyData(monthID1).getCategoryData().get(0);
        Expense recurring = new Expense("Indigo", other, 30.00);
        sessionStorage.addRecurExpense(recurring);

        NewMonthInputData inputData = new NewMonthInputData(sessionStorage,monthID2,budgetValue);

        NewMonthOutputData output = interactor.createOutput(inputData);
        assertTrue(output.isSuccessful());
        assertEquals(monthID2, sessionStorage.getMonthlyData(monthID2).getMonthID());
        assertEquals(recurring,sessionStorage.getMonthlyData(monthID2).getExpenseData().get(0));
    }

    /**
     * Test case when new MonthlyStorage is not added to session because there were duplicate
     * recurring expenses. This should not occur if all use case in the program run correctly.
     */
    @Test
    void createOutputRecurExpenseFail() {
        NewMonthInputBoundary interactor = new NewMonthUseCaseInteractor(outputBoundary);

        Category other = new Category("Other",0);
        Expense recurring = new Expense("Indigo", other, 30.00);
        sessionStorage.addRecurExpense(recurring);
        sessionStorage.addRecurExpense(recurring);

        NewMonthInputData inputData = new NewMonthInputData(sessionStorage,monthID2,budgetValue);

        NewMonthOutputData output = interactor.createOutput(inputData);
        assertFalse(output.isSuccessful());
        assertEquals("An error has occurred. Please reload the program.", output.getWarning());

    }

    /**
     * Test case when new MonthlyStorage is not added to session because there already exist a
     * MonthlyStorage with the same monthID.
     */
    @Test
    void createOutputFailMonthAlreadyExist(){
        NewMonthInputBoundary interactor = new NewMonthUseCaseInteractor(outputBoundary);
        NewMonthInputData inputData = new NewMonthInputData(sessionStorage,monthID1,budgetValue);

        NewMonthOutputData output = interactor.createOutput(inputData);
        assertFalse(output.isSuccessful());
        assertEquals("This month already exist in this session.", output.getWarning());
    }
}