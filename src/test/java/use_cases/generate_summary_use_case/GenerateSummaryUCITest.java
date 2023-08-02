package use_cases.generate_summary_use_case;

import entities.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import views.generate_summary_views.GenerateSummaryP;

import javax.swing.*;

/**
 * A class that tests the GenerateSummaryUCI.
 */
class GenerateSummaryUCITest {
    private static MonthlyStorage month1;
    private static SessionStorage session;

    /**
     * Runs once before the methods to set up the necessary entities for the tests.
     */
    @BeforeAll
    public static void GenerateSummaryUCICreateBaseEntities() throws EntityException {
        Category category1 = new Category("Food", 100.00);
        Category category2 = new Category("Shopping", 70.00);
        Expense expense1 = new Expense("Loblaws", category1, 50.00 );
        Expense expense2 = new Expense("Indigo", category2, 30.00 );
        month1 = new MonthlyStorage(1, 1000.00);
        month1.addCategory(category1);
        month1.addCategory(category2);
        month1.addExpense(expense1);
        month1.addExpense(expense2);

        session = new SessionStorage();
        session.addMonth(month1);
    }

    /**
     * Tests generateNewSummary() with a MonthlyStorage with Expense and Category objects.
     */
    @Test
    public void GenerateSummaryUCIFullMonth() throws EntityException{
        GenerateSummaryOB presenter = new GenerateSummaryP();
        GenerateSummaryID inputData = new GenerateSummaryID(session, month1.getMonthID());

        GenerateSummaryIB interpreter = new GenerateSummaryUCI(presenter);
        GenerateSummaryOD outputData = interpreter.generateNewSummary(inputData);

        JFrame outputGraphs = new JFrame("Graphs");
        JPanel graphs = new JPanel();
        graphs.setLayout(new BoxLayout(graphs, BoxLayout.X_AXIS));

        outputGraphs.add(graphs);
        outputGraphs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outputGraphs.setSize(800, 400);

        graphs.add(outputData.getBarGraphPanel());
        graphs.add(outputData.getPieChartPanel());
        outputGraphs.setVisible(true);

//        // buffers the project to allow you to see the graphs
//        while (outputGraphs.isVisible()){
//
//        }
    }

    /**
     * Tests generateNewSummary() with an empty MonthlyStorage.
     */
    @Test
    public void GenerateSummaryUCIEmptyMonth() throws EntityException{
        SessionStorage session = new SessionStorage();
        MonthlyStorage month2 = new MonthlyStorage(2, 1000);
        session.addMonth(month2);

        GenerateSummaryOB presenter = new GenerateSummaryP();
        GenerateSummaryID inputData = new GenerateSummaryID(session, month2.getMonthID());

        GenerateSummaryIB interpreter = new GenerateSummaryUCI(presenter);
        GenerateSummaryOD outputData = interpreter.generateNewSummary(inputData);

        JFrame outputGraphs = new JFrame("Graphs");
        JPanel graphs = new JPanel();
        graphs.setLayout(new BoxLayout(graphs, BoxLayout.X_AXIS));

        outputGraphs.add(graphs);
        outputGraphs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outputGraphs.setSize(800, 400);

        graphs.add(outputData.getBarGraphPanel());
        graphs.add(outputData.getPieChartPanel());
        outputGraphs.setVisible(true);

//        // buffers the project to allow you to see the graphs
//        while (outputGraphs.isVisible()){
//
//        }
    }

    /**
     * Tests generateNewSummary() with an invalid test case.
     * Note: An invalid test case should never occur in this use case. If an EntityException is thrown, it means that
     * the monthID that is passed in from the MonthMenuV is not the monthID of that MonthlyStorage. If this happens,
     * there is nothing the user can do except for try to restart the program. Additionally, valid entity data is
     * checked by other use cases, so all Expense and Category data should have distinct names and non-negative numbers
     * in this MonthlyStorage.
     */
    @Test
    public void GenerateSummaryUCIFailCase(){
        GenerateSummaryOB presenter = new GenerateSummaryP();
        GenerateSummaryID inputData = new GenerateSummaryID(session, 3);
        GenerateSummaryIB interpreter = new GenerateSummaryUCI(presenter);

        EntityException thrown = Assertions.assertThrows(EntityException.class, () ->
                interpreter.generateNewSummary(inputData));
        Assertions.assertEquals("That is not a valid currentMonth for this SessionStorage.",
                thrown.getMessage());

    }
}
