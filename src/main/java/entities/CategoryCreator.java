package entities;

/**
 * A class that creates an Expense. It extends the abstract class MonthObjectCreator.
 */
public class CategoryCreator extends MonthObjectCreator{

    /**
     * Creates and returns a new Category.
     * @param inputData an CategoryCreatorInputData object that is cast to MonthObjectCreatorInputData.
     *                  It holds the information needed to create a Category.
     * @return the newly created Category
     */
    @Override
    public MonthObject createMonthObject(MonthObjectCreatorInputData inputData) {
        CategoryCreatorInputData categoryCreatorInputData = (CategoryCreatorInputData) inputData;
        return new Category(categoryCreatorInputData.getName(), categoryCreatorInputData.getBudget());
    }
}