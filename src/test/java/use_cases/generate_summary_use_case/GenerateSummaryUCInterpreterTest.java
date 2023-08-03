package use_cases.generate_summary_use_case;

import entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that tests the GenerateSummaryUCInterpreter class.
 */
class GenerateSummaryUCInterpreterTest {
    static MonthlyStorage month1;
    static Category category1;
    static Category category2;

    /**
     * Runs once before the methods to set up the necessary entities for the tests.
     */
    @BeforeAll
    public static void GenerateSummaryUCInterpreterCreateBaseEntities() throws EntityException{
        month1 = new MonthlyStorage(1, 1000.00);
        category1 = new Category("Food", 100.00);
        category2 = new Category("Shopping", 70.00);
        Expense expense1 = new Expense("Loblaws", category1, 50.00 );
        Expense expense2 = new Expense("Indigo", category2, 30.00 );
        Expense expense3 = new Expense("Walmart", category1, 20.00 );
        Expense expense4 = new Expense("Target", category2, 60.00 );
        Expense expense5 = new Expense("LCBO", month1.getCategoryData().get(0), 20.00 );

        month1.addCategory(category1);
        month1.addCategory(category2);
        month1.addExpense(expense1);
        month1.addExpense(expense2);
        month1.addExpense(expense3);
        month1.addExpense(expense4);
        month1.addExpense(expense5);
    }

    /**
     * Tests getStatisticalData() and getRemainder() on a MonthlyStorage that contains Expense and Category objects.
     */
    @Test
    public void GenerateSummaryFullMonth(){
        GenerateSummaryUCInterpreter interpreter = new GenerateSummaryUCInterpreter(month1);
        Map<String, ArrayList<Double>> statisticalData = interpreter.getStatisticalData();

        Assertions.assertEquals(70, statisticalData.get(category1.getName()).get(0));
        Assertions.assertEquals(100, statisticalData.get(category1.getName()).get(1));
        Assertions.assertEquals(90, statisticalData.get(category2.getName()).get(0));
        Assertions.assertEquals(70, statisticalData.get(category2.getName()).get(1));
        Assertions.assertEquals(20, statisticalData.get("Other").get(0));
        Assertions.assertEquals(0, statisticalData.get("Other").get(1));

        Assertions.assertEquals(820, interpreter.getRemainder());
    }

    /**
     * Tests getStatisticalData() and getRemainder() on an empty MonthlyStorage object.
     */
    @Test
    public void GenerateSummaryEmptyMonth(){
        MonthlyStorage new_month = new MonthlyStorage(2, 1000);

        GenerateSummaryUCInterpreter interpreter = new GenerateSummaryUCInterpreter(new_month);
        Map<String, ArrayList<Double>> statisticalData = interpreter.getStatisticalData();

        Map<String, ArrayList<Double>> expected = new HashMap<>();
        ArrayList<Double> otherValues = new ArrayList<>();
        otherValues.add(0.0);
        otherValues.add(0.0);
        expected.put("Other", otherValues);

        Assertions.assertEquals(expected, statisticalData);

        Assertions.assertEquals(1000, interpreter.getRemainder());
    }
}