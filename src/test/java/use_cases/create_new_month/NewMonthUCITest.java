package use_cases.create_new_month;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.create_new_month.NewMonthP;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the NewMonthUCI class.
 */
class NewMonthUCITest {
    static NewMonthOB outputBoundary;
    static int monthID1;
    static int monthID2;
    static double budgetValue;
    static MonthlyStorage monthlyStorage1;
    static SessionStorage sessionStorage;

    @BeforeEach
    void setUp() {
        outputBoundary = new NewMonthP();
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
        NewMonthIB interactor = new NewMonthUCI(outputBoundary);
        NewMonthID inputData = new NewMonthID(sessionStorage,monthID2,budgetValue);

        NewMonthOD output = interactor.createOutput(inputData);
        assertTrue(output.isSuccessful());
        assertEquals(monthID2, sessionStorage.getMonthlyData(monthID2).getMonthID());
    }

    /**
     * Test case when new MonthlyStorage is created with correct ID, and recurring expenses added.
     * @throws EntityException when test fails
     */
    @Test
    void createOutputRecurExpenseSuccess() throws EntityException {
        NewMonthIB interactor = new NewMonthUCI(outputBoundary);

        Category other = sessionStorage.getMonthlyData(monthID1).getCategoryData().get(0);
        Expense recurring = new Expense("Indigo", other, 30.00);
        sessionStorage.addRecurExpense(recurring);

        NewMonthID inputData = new NewMonthID(sessionStorage,monthID2,budgetValue);

        NewMonthOD output = interactor.createOutput(inputData);
        assertTrue(output.isSuccessful());
        assertEquals(monthID2, sessionStorage.getMonthlyData(monthID2).getMonthID());
        assertEquals(recurring,sessionStorage.getMonthlyData(monthID2).getExpenseData().get(0));
    }

    /**
     * Test case when new MonthlyStorage is not added to session because there were duplicate
     * recurring expenses. This should not occur.
     * @throws EntityException when @BeforeEach not run correctly
     */
    @Test
    void createOutputRecurExpenseFail() throws EntityException {
        NewMonthIB interactor = new NewMonthUCI(outputBoundary);

        Category other = sessionStorage.getMonthlyData(monthID1).getCategoryData().get(0);
        Expense recurring = new Expense("Indigo", other, 30.00);
        sessionStorage.addRecurExpense(recurring);
        sessionStorage.addRecurExpense(recurring);

        NewMonthID inputData = new NewMonthID(sessionStorage,monthID2,budgetValue);

        NewMonthOD output = interactor.createOutput(inputData);
        assertFalse(output.isSuccessful());
        assertEquals("Something went wrong, please try again.", output.getWarning());

    }

    /**
     * Test case when new MonthlyStorage is not added to session because there already exist a
     * MonthlyStorage with the same monthID.
     */
    @Test
    void createOutputFailMonthAlreadyExist(){
        NewMonthIB interactor = new NewMonthUCI(outputBoundary);
        NewMonthID inputData = new NewMonthID(sessionStorage,monthID1,budgetValue);

        NewMonthOD output = interactor.createOutput(inputData);
        assertFalse(output.isSuccessful());
        assertEquals("This month already exist in this session.", output.getWarning());
    }
}