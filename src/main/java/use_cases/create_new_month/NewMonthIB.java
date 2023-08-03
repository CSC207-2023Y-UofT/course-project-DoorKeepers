package use_cases.create_new_month;

/**
 * The input boundary interface for creating a new MonthlyStorage.
 * The interactor class implements this interface, and returns
 * NewMonthOD object.
 */
public interface NewMonthIB {
    /**
     * Pass in and use NewMonthID containing input data to create new MonthlyStorage.
     * @param input input passed in from the controller class
     * @return NewMonthOD object that contains output data
     */
    NewMonthOD createOutput(NewMonthID input);
}
