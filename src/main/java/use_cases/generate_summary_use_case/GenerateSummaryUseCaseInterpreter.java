package use_cases.generate_summary_use_case;

import entities.Category;
import entities.Expense;
import entities.MonthlyStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that generates all the data in the correct format to make the Bar Graph and Pie Chart.
 */
public class GenerateSummaryUseCaseInterpreter {

    private final Map<String, ArrayList<Double>> statisticalData;
    private final double remainder;

    /**
     * Creates a new GenerateSummaryUseCaseInterpreter with the provided MonthlyStorage.
     * @param monthlyStorage the MonthlyStorage object that the data will be generated for
     */
    public GenerateSummaryUseCaseInterpreter(MonthlyStorage monthlyStorage) {
        Map<Category, ArrayList<Expense>> mappedExpenses = sortExpenses(monthlyStorage);

        this.statisticalData = generateStatisticalData(monthlyStorage, mappedExpenses);
        this.remainder = generateRemainder(monthlyStorage, mappedExpenses);
    }

    /**
     * Gets the statistical data required for creating the graphs.
     * @return a Map holding data used to make the graphs
     */
    public Map<String, ArrayList<Double>> getStatisticalData() {
        return statisticalData;
    }

    /**
     * Gets the remainder of this MonthlyStorage object.
     * @return the remainder of this MonthlyStorage
     */
    public double getRemainder() {
        return remainder;
    }

    /**
     * Generates a map that has Category objects as keys and a list of Expense objects associated with that Category as
     * values.
     * @param monthlyStorage a MonthlyStorage object for the current month
     * @return a Map holding sorted month data
     */
    private Map<Category, ArrayList<Expense>> sortExpenses(MonthlyStorage monthlyStorage){
        Map<Category, ArrayList<Expense>> mappedExpenses = new HashMap<>();

        for (Category c: monthlyStorage.getCategoryData()){
            mappedExpenses.put(c, new ArrayList<>());
        }
        for (Expense e: monthlyStorage.getExpenseData()){
            mappedExpenses.get(e.getCategory()).add(e);
        }
        return mappedExpenses;
    }

    /**
     * Generates a map containing String names of Category objects as the keys and doubles representing money spent
     * and budget as the values.
     * @param monthlyStorage a MonthlyStorage object for the current month
     * @param mappedExpenses a Map that has Category objects as keys and a list of Expense objects associated with that
     *                       Category as values
     * @return a Map holding data used to make the graphs. The key values are Stings representing Category names, and
     * the values are lists with the first double as the money spent in the Category and the second as the remainder of
     * money left in the budget of that Category.
     */
    private Map<String, ArrayList<Double>> generateStatisticalData(MonthlyStorage monthlyStorage, Map<Category,
            ArrayList<Expense>> mappedExpenses){
        Map<String, ArrayList<Double>> statisticalData = new HashMap<>();
        for (Category c: monthlyStorage.getCategoryData()) {
            statisticalData.put(c.getName(), new ArrayList<>());
            double expenseSum = 0;
            for (Expense e: mappedExpenses.get(c)){
                expenseSum += e.getValue();
            }
            statisticalData.get(c.getName()).add(expenseSum);
            // Check to see if remainder is greater than 0. If it is, add the remainder, if not add 0.0.
            if (c.getBudget() - expenseSum > 0){
                statisticalData.get(c.getName()).add(c.getBudget() - expenseSum);
            } else {
                statisticalData.get(c.getName()).add(0.0);
            }
        }
        return statisticalData;
    }

    /**
     * Generates a double representing the money that the user has not spent in their budget.
     * @param monthlyStorage a MonthlyStorage object for the current month
     * @param mappedExpenses a Map that has Category objects as keys and a list of Expense objects associated with that
     * Category as values
     * @return the remainder of this MonthlyStorage
     */
    private double generateRemainder(MonthlyStorage monthlyStorage, Map<Category, ArrayList<Expense>> mappedExpenses){
        double remainder = monthlyStorage.getMonthlyBudget();
        for (Expense e: monthlyStorage.getExpenseData()){
            mappedExpenses.get(e.getCategory()).add(e);
            remainder -= e.getValue();
        }
        return remainder;
    }

}