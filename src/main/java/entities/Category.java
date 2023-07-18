package entities;

public class Category {

    private String name;
    private double budget;

    /**
     * Creates a new Category with the given data
     * @param name: name of category
     * @param budget: budget of category
     */
    public Category(String name, double budget){
        this.name = name;
        this.budget = budget;
    }

    public void setName(String new_name){
        this.name = new_name;
    }

    public void setBudget(double new_budget){
        this.budget = new_budget;
    }

    public String getName(){
        return this.name;
    }

    public double getBudget(){
        return this.budget;
    }
}
