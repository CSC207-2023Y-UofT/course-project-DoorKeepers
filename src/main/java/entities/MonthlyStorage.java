package entities;

import java.util.ArrayList;

public class MonthlyStorage {
    private final int currentMonth;
    private final double monthlyBudget;
    private final ArrayList<Category> categoryData;
    private final ArrayList<Expense> expenseData;



    /**
     * Creates a new MonthlyStorage with the given data
     * @param currentMonth: integer representing the id of the MonthlyStorage
     * @param monthlyBudget: budget of the MonthlyStorage
     */
    public MonthlyStorage(int currentMonth, double monthlyBudget){
        this.currentMonth = currentMonth;
        this.monthlyBudget = monthlyBudget;

        Category other = new Category("Other", 0);
        this.categoryData = new ArrayList<>();
        this.categoryData.add(other);
        this.expenseData = new ArrayList<>();
    }

    public void setCategoryData(Category category){
        this.categoryData.add(category);
    }

    public void setExpenseData(Expense expense){
        this.expenseData.add(expense);
    }

    public int getCurrentMonth(){
        return this.currentMonth;
    }

    public double getMonthlyBudget(){
        return this.monthlyBudget;
    }

    public ArrayList<Category> getCategoryData(){
        return this.categoryData;
    }

    public ArrayList<Expense> getExpenseData(){
        return this.expenseData;
    }
}
