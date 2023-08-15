package views.monthly_menu;

import use_cases.monthly_menu.MonthMenuOutputBoundary;
import use_cases.monthly_menu.MonthMenuOutputData;

/**
 * The presenter class for creating the Month Menu view. This
 * class implements the MonthMenuOutputBoundary interface. The interactor
 * class calls this class to get the MonthMenuOutputData object for
 * success and fail cases.
 */
public class MonthMenuPresenter implements MonthMenuOutputBoundary {

    /**
     * Pass in and return output data.
     * @param output output to be shown in MonthMenuView
     * @return MonthMenuOutputData object that contains output data
     */
    @Override
    public MonthMenuOutputData createOutput(MonthMenuOutputData output) {
        return output;
    }
}
