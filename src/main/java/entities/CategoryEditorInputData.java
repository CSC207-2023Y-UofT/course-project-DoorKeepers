package entities;

/**
 * A class that holds the input data for use by the CategoryFactory. It extends MonthObjectFactoryInputData, so that it
 * can be used in place of this type. It includes the new name, new budget, and Category object to edit the Category.
 */
public class CategoryEditorInputData extends MonthObjectFactoryInputData {
    private final String name;
    private final double budget;
    private final Category category;

    /**
     * Creates a new instance of CategoryEditorInputData.
     * @param name the new name of the category
     * @param budget the new budget of the category
     * @param category the Category object to edit
     */
    public CategoryEditorInputData(String name, double budget, Category category){
        this.name = name;
        this.budget = budget;
        this.category = category;
    }

    /**
     * Gets the new name of this CategoryEditorInputData.
     * @return the new name of this CategoryEditorInputData
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the new budget of this CategoryEditorInputData.
     * @return the new budget of this CategoryEditorInputData
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Gets the Category object of this CategoryEditorInputData.
     * @return the Category object of this CategoryEditorInputData
     */
    public Category getCategory() {
        return category;
    }
}
