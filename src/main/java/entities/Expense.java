package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class that will store all the data associated with an Expense. This includes a name, Category, and value.
 */
public class Expense implements Serializable {

    private String name;
    private Category category;
    private double value;

    /**
     * Creates a new Expense with the given data.
     * @param name name of expense
     * @param category category of expense
     * @param value value (price) of expense
     */
    public Expense(String name, Category category, double value){
        this.name = name;
        this.category = category;
        this.value = value;
    }

    /**
     * Sets the name of this Expense.
     * @param newName the new name that this Expense will have
     */
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * Sets the Category of this Expense.
     * @param newCategory the new Category that this Expense will belong to
     */
    public void setCategory(Category newCategory){
        this.category = newCategory;
    }

    /**
     * Sets the value of this Expense.
     * @param newValue the new value that this Expense will have
     */
    public void setValue(double newValue){
        this.value = newValue;
    }

    /**
     * Gets the name of this Expense.
     * @return the name of this Expense
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the Category of this Expense.
     * @return the Category of this Expense
     */
    public Category getCategory(){
        return this.category;
    }

    /**
     * Gets the value of this Expense.
     * @return the value of this Expense
     */
    public double getValue(){
        return this.value;
    }

    /**
     * Checks if this Expense is equal to Object.
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

        Expense other = (Expense) obj;
        return Objects.equals(this.getName(), other.getName());
    }

}
