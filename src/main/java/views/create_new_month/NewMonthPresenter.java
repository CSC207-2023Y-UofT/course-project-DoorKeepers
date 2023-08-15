package views.create_new_month;

import use_cases.create_new_month.NewMonthOutputBoundary;
import use_cases.create_new_month.NewMonthOutputData;

/**
 * The presenter class for creating new MonthlyStorage. This
 * class implements the NewMonthOutputBoundary interface. The interactor
 * class calls this class to get the NewMonthOutputData object for
 * success and fail cases.
 */
public class NewMonthPresenter implements NewMonthOutputBoundary {

    /**
     * Pass in and returns MonthMenuOutputData containing output data.
     * @param output output to be used in NewMonthView to open MonthMenuView
     * @return NewMonthOutputData object that contains output data
     */
    @Override
    public NewMonthOutputData createOutput(NewMonthOutputData output) {
        return output;
    }
}
