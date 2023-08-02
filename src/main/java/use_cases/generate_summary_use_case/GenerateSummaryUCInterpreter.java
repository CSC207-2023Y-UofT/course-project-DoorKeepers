package use_cases.generate_summary_use_case;

import entities.Category;
import entities.Expense;
import entities.MonthlyStorage;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that generates all the data in the correct format to make the Bar Graph and Pie Chart.
 */
public class GenerateSummaryUCInterpreter {

    private final ArrayList<Category> categories;
    private final Map<Category, ArrayList<Expense>> mappedExpenses;
    private double remainder;

    /**
     * Creates a new GenerateSummaryUCInterpreter with the provided presenter.
     * @param monthlyStorage the MonthlyStorage object that the data will be generated for
     */
    public GenerateSummaryUCInterpreter(MonthlyStorage monthlyStorage) {
        this.categories = monthlyStorage.getCategoryData();
        ArrayList<Expense> expenses = monthlyStorage.getExpenseData();
        this.mappedExpenses = new HashMap<>();

        for (Category c: categories){
            mappedExpenses.put(c, new ArrayList<>());
        }

        this.remainder = monthlyStorage.getMonthlyBudget();
        for (Expense e: expenses){
            mappedExpenses.get(e.getCategory()).add(e);
            this.remainder -= e.getValue();
        }

    }

    /**
     * Generates the statistical data required for creating the graphs.
     * @return a Map holding data used to make the graphs
     */
    public Map<String, ArrayList<Double>> getStatisticalData(){
        Map<String, ArrayList<Double>> statisticalData = new HashMap<>();
        for (Category c: this.categories) {
            statisticalData.put(c.getName(), new ArrayList<>());
            double expenseSum = 0;
            for (Expense e: this.mappedExpenses.get(c)){
                expenseSum += e.getValue();
            }
            statisticalData.get(c.getName()).add(expenseSum);
            statisticalData.get(c.getName()).add(c.getBudget());
        }
        return statisticalData;
    }

    /**
     * Gets the remainder of this MonthlyStorage object.
     * @return the remainder of this MonthlyStorage
     */
    public double getRemainder(){
        return this.remainder;
    }

}