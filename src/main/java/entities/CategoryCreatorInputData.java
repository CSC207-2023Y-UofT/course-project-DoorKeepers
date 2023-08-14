package entities;

/**
 * A class that holds the input data for use by the CategoryFactory. It extends MonthObjectFactoryInputData, so that it
 * can be used in place of this type. It includes the name and budget that the new Category will have.
 */
public class CategoryCreatorInputData extends MonthObjectFactoryInputData {

    private String name;
    private double budget;

    /**
     * Sets the name of this CategoryCreatorInputData.
     * @param name the name that the Category will have
     */
    @Override
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the budget of this CategoryCreatorInputData.
     * @param newBudget the budget that the Category will have
     */
    public void setBudget(double newBudget){
        this.budget = newBudget;
    }

    /**
     * Gets the name of this CategoryCreatorInputData.
     * @return the name of this CategoryCreatorInputData
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the budget of this CategoryCreatorInputData.
     * @return the budget of this CategoryCreatorInputData
     */
    public double getBudget() {
        return budget;
    }
}