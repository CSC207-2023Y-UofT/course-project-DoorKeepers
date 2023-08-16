package entities;

/**
 * A class that holds the input data for use by the CategoryFactory. It extends MonthObjectFactoryInputData, so that it
 * can be used in place of this type. It includes the new name, new budget, and Category object to edit the Category.
 */
public class CategoryEditorInputData extends MonthObjectFactoryInputData {

    private String name;
    private double budget;
    private Category category;

    /**
     * Sets the name of this CategoryEditorInputData.
     *
     * @param name the new name that the edited Category will have
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the budget of this CategoryEditorInputData.
     *
     * @param newBudget the new budget that the edited Category will have
     */
    public void setBudget(double newBudget) {
        this.budget = newBudget;
    }

    /**
     * Sets the Category object of this CategoryEditorInputData.
     *
     * @param category the Category to edit
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets the name of this CategoryEditorInputData.
     *
     * @return the name of this CategoryEditorInputData
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the budget of this CategoryEditorInputData.
     *
     * @return the budget of this CategoryEditorInputData
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Gets the Category object of this CategoryEditorInputData.
     *
     * @return the Category object of this CategoryEditorInputData
     */
    public Category getCategory() {
        return category;
    }
}
