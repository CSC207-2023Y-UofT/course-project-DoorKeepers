package entities;

/**
 * A class that creates and edits a Category. It extends the abstract class MonthObjectFactory.
 */
public class CategoryFactory extends MonthObjectFactory {

    /**
     * Creates and returns a new Category.
     *
     * @param inputData an CategoryCreatorInputData object that is cast to MonthObjectFactoryInputData.
     *                  It holds the information needed to create a Category.
     * @return the newly created Category
     */
    @Override
    public MonthObject createMonthObject(MonthObjectFactoryInputData inputData) {
        CategoryCreatorInputData categoryCreatorInputData = (CategoryCreatorInputData) inputData;
        return new Category(categoryCreatorInputData.getName(), categoryCreatorInputData.getBudget());
    }

    /**
     * Edits an existing Category.
     *
     * @param inputData an CategoryEditorInputData object that is cast to MonthObjectFactoryInputData.
     *                  It holds the information needed to edit a Category.
     * @return the newly edited Category
     */
    @Override
    public MonthObject editMonthObject(MonthObjectFactoryInputData inputData) {
        CategoryEditorInputData categoryEditorInputData = (CategoryEditorInputData) inputData;
        Category category = categoryEditorInputData.getCategory();
        category.setName(categoryEditorInputData.getName());
        category.setBudget(categoryEditorInputData.getBudget());
        return category;
    }
}