package entities;

public class ExpenseFactory {
    public Expense create(String name, Category category, double value){return new Expense(name, category, value);}
}
