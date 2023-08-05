package views.create_new_month;

import use_cases.create_new_month.NewMonthOB;
import use_cases.create_new_month.NewMonthOD;

/**
 * The presenter class for creating new MonthlyStorage. This
 * class implements the NewMonthOB interface. The interactor
 * class calls this class to get the NewMonthOD object for
 * success and fail cases.
 */
public class NewMonthP implements NewMonthOB {

    /**
     * Pass in and returns MonthMenuOD containing output data.
     * @param output output to be used in NewMonthV to open MonthMenuV
     * @return NewMonthOD object that contains output data
     */
    @Override
    public NewMonthOD createOutput(NewMonthOD output) {
        return output;
    }
}
