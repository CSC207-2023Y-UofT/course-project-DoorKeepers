package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class that will store all the data associated with a Category. This includes a name and budget.
 * It implements the MonthObject interface, which allows for it to be created using a MonthObjectFactory.
 */
public class Category implements MonthObject, Serializable {

    private String name;
    private double budget;

    /**
     * Creates a new Category with the given data.
     * @param name name of category
     * @param budget budget of category
     */
    public Category(String name, double budget){
        this.name = name;
        this.budget = budget;
    }

    /**
     * Sets the name of this Category.
     * @param name the new name that this Category will have
     */
    @Override
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the budget of this Category.
     * @param newBudget the new budget that this Category will have
     */
    public void setBudget(double newBudget){
        this.budget = newBudget;
    }

    /**
     * Gets the name of this Category.
     * @return the name of this Category
     */
    @Override
    public String getName(){
        return this.name;
    }

    /**
     * Gets the budget of this Category.
     * @return the budget of this Category
     */
    public double getBudget(){
        return this.budget;
    }

    /**
     * Checks if this Category is equal to Object.
     * @param obj any instance of Object
     * @return True if equals, False is not equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }

        Category other = (Category) obj;
        return Objects.equals(this.getName(), other.getName());
    }
}
