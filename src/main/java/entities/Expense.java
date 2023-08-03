package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 */
public class Expense implements Serializable {

    private String name;
    private Category category;
    private double value;

    /**
     * Creates a new Expense with the given data
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
     * Sets the name of this Expense
     * @param new_name the new name that this Expense will have
     */
    public void setName(String new_name){
        this.name = new_name;
    }

    /**
     * Sets the Category of this Expense
     * @param new_category the new Category that this Expense will belong to
     */
    public void setCategory(Category new_category){
        this.category = new_category;
    }

    /**
     * Sets the value of this Expense
     * @param new_value the new value that this Expense will have
     */
    public void setValue(double new_value){
        this.value = new_value;
    }

    /**
     * Gets the name of this Expense
     * @return the name of this Expense
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the Category of this Expense
     * @return the Category of this Expense
     */
    public Category getCategory(){
        return this.category;
    }

    /**
     * Gets the value of this Expense
     * @return the value of this Expense
     */
    public double getValue(){
        return this.value;
    }

    /**
     * Check if this Expense is equal to Object
     * @param obj Any instance of Object
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
