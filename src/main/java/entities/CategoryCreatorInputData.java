package entities;

/**
 * A class that holds the input data for use by the CategoryFactory. It extends MonthObjectFactoryInputData, so that it
 * can be used in place of this type. It includes the name and budget that the new Category will have.
 */
public class CategoryCreatorInputData extends MonthObjectFactoryInputData {

    private final String name;
    private final double budget;

    /**
     * Creates a new instance of CategoryCreatorInputData.
     * @param name the name of the category
     * @param budget the budget of the category
     */
    public CategoryCreatorInputData(String name, double budget){
        this.name = name;
        this.budget = budget;
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