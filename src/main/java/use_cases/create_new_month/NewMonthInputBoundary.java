package use_cases.create_new_month;

/**
 * The input boundary interface for creating a new MonthlyStorage.
 * The interactor class implements this interface, and returns
 * NewMonthOutputData object.
 */
public interface NewMonthInputBoundary {
    /**
     * Pass in and use NewMonthInputData containing input data to create new MonthlyStorage.
     *
     * @param input input passed in from the controller class
     * @return NewMonthOutputData object that contains output data
     */
    NewMonthOutputData createOutput(NewMonthInputData input);
}
