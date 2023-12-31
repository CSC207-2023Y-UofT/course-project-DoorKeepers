package use_cases.generate_summary_use_case;

import entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import views.generate_summary_views.GenerateSummaryPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that tests the GenerateSummaryUseCaseInteractor.
 */
class GenerateSummaryUseCaseInteractorTest {

    private static Category category1;
    private static Category category2;
    private static MonthlyStorage month1;
    private static MonthlyStorage month2;
    private static SessionStorage session;

    /**
     * Runs once before the methods to set up the necessary entities for the tests.
     *
     * @throws EntityException if Category and Expense names are the same, which is not a concern for this test.
     */
    @BeforeAll
    public static void GenerateSummaryUCICreateBaseEntities() throws EntityException {
        month1 = new MonthlyStorage(1, 1000.00);
        month2 = new MonthlyStorage(2, 100.00);
        category1 = new Category("Food", 100.00);
        category2 = new Category("Shopping", 70.00);
        Expense expense1 = new Expense("Loblaws", category1, 50.00);
        Expense expense2 = new Expense("Indigo", category2, 30.00);
        Expense expense3 = new Expense("Walmart", category1, 20.00);
        Expense expense4 = new Expense("Target", category2, 60.00);
        Expense expense5 = new Expense("LCBO", month1.getCategoryData().get(0), 20.00);

        month1.addCategory(category1);
        month1.addCategory(category2);
        month1.addExpense(expense1);
        month1.addExpense(expense2);
        month1.addExpense(expense3);
        month1.addExpense(expense4);
        month1.addExpense(expense5);

        month2.addCategory(category1);
        month2.addCategory(category2);
        month2.addExpense(expense1);
        month2.addExpense(expense2);
        month2.addExpense(expense3);
        month2.addExpense(expense4);

        session = new SessionStorage();
        session.addMonth(month1);
        session.addMonth(month2);
    }

    /**
     * Tests generateNewSummary() with a MonthlyStorage with Expense and Category objects.
     *
     * @throws EntityException if there is no MonthID corresponding to a month in a monthly storage. This error being
     *                         raised is a sign that there is something broken in the way that the MonthlyStorage objects are stored in the
     *                         SessionStorage, and is not something the user can fix.
     */
    @Test
    public void GenerateSummaryUCINormalMonth() throws EntityException {
        GenerateSummaryOutputBoundary presenter = new GenerateSummaryPresenter();
        GenerateSummaryInputData inputData = new GenerateSummaryInputData(session, month1.getMonthID());
        GenerateSummaryInputBoundary interactor = new GenerateSummaryUseCaseInteractor(presenter);
        GenerateSummaryOutputData outputData = interactor.generateNewSummary(inputData);

        Map<String, ArrayList<Double>> statisticalData = outputData.getStatisticalData();
        Assertions.assertEquals(70, statisticalData.get(category1.getName()).get(0));
        Assertions.assertEquals(30, statisticalData.get(category1.getName()).get(1));
        Assertions.assertEquals(90, statisticalData.get(category2.getName()).get(0));
        Assertions.assertEquals(0, statisticalData.get(category2.getName()).get(1));
        Assertions.assertEquals(20, statisticalData.get("Other").get(0));
        Assertions.assertEquals(0, statisticalData.get("Other").get(1));

        Assertions.assertEquals(820, outputData.getRemainder());

    }

    /**
     * Tests generateNewSummary() with a MonthlyStorage with Expense and Category objects and is over budget.
     *
     * @throws EntityException if there is no MonthID corresponding to a month in a monthly storage. This error being
     *                         raised is a sign that there is something broken in the way that the MonthlyStorage objects are stored in the
     *                         SessionStorage, and is not something the user can fix.
     */
    @Test
    public void GenerateSummaryUCIFullMonth() throws EntityException {
        GenerateSummaryOutputBoundary presenter = new GenerateSummaryPresenter();
        GenerateSummaryInputData inputData = new GenerateSummaryInputData(session, month2.getMonthID());
        GenerateSummaryInputBoundary interactor = new GenerateSummaryUseCaseInteractor(presenter);
        GenerateSummaryOutputData outputData = interactor.generateNewSummary(inputData);

        Map<String, ArrayList<Double>> statisticalData = outputData.getStatisticalData();
        Assertions.assertEquals(70, statisticalData.get(category1.getName()).get(0));
        Assertions.assertEquals(30, statisticalData.get(category1.getName()).get(1));
        Assertions.assertEquals(90, statisticalData.get(category2.getName()).get(0));
        Assertions.assertEquals(0, statisticalData.get(category2.getName()).get(1));
        Assertions.assertEquals(0, statisticalData.get("Other").get(0));
        Assertions.assertEquals(0, statisticalData.get("Other").get(1));

        Assertions.assertEquals(-60, outputData.getRemainder());

    }

    /**
     * Tests generateNewSummary() with an empty MonthlyStorage.
     *
     * @throws EntityException if there is no MonthID corresponding to a month in a monthly storage. This error being
     *                         raised is a sign that there is something broken in the way that the MonthlyStorage objects are stored in the
     *                         SessionStorage, and is not something the user can fix.
     */
    @Test
    public void GenerateSummaryUCIEmptyMonth() throws EntityException {
        MonthlyStorage newMonth = new MonthlyStorage(3, 1000);
        SessionStorage newSession = new SessionStorage();
        newSession.addMonth(newMonth);

        GenerateSummaryOutputBoundary presenter = new GenerateSummaryPresenter();
        GenerateSummaryInputData inputData = new GenerateSummaryInputData(newSession, newMonth.getMonthID());
        GenerateSummaryInputBoundary interactor = new GenerateSummaryUseCaseInteractor(presenter);
        GenerateSummaryOutputData outputData = interactor.generateNewSummary(inputData);

        Map<String, ArrayList<Double>> expected = new HashMap<>();
        ArrayList<Double> otherValues = new ArrayList<>();
        otherValues.add(0.0);
        otherValues.add(0.0);
        expected.put("Other", otherValues);

        Assertions.assertEquals(expected, outputData.getStatisticalData());

        Assertions.assertEquals(1000, outputData.getRemainder());
    }

    /**
     * Tests generateNewSummary() with an invalid test case.
     * Note: An invalid test case should never occur in this use case. If an EntityException is thrown, it means that
     * the monthID that is passed in from the MonthMenuView is not the monthID of that MonthlyStorage. If this happens,
     * there is nothing the user can do except for try to restart the program. Additionally, valid entity data is
     * checked by other use cases, so all Expense and Category data should have distinct names and non-negative numbers
     * in this MonthlyStorage.
     */
    @Test
    public void GenerateSummaryUCIFailCase() {
        GenerateSummaryOutputBoundary presenter = new GenerateSummaryPresenter();
        GenerateSummaryInputData inputData = new GenerateSummaryInputData(session, 3);
        GenerateSummaryInputBoundary interpreter = new GenerateSummaryUseCaseInteractor(presenter);

        EntityException thrown = Assertions.assertThrows(EntityException.class, () ->
                interpreter.generateNewSummary(inputData));
        Assertions.assertEquals("That is not a valid monthID for this SessionStorage.",
                thrown.getMessage());

    }
}