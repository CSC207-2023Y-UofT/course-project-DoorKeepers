package views.monthly_menu;

import use_cases.monthly_menu.MonthMenuOB;
import use_cases.monthly_menu.MonthMenuOD;

/**
 * The presenter class for creating the Month Menu view. This
 * class implements the MonthMenuOB interface. The interactor
 * class calls this class to get the MonthMenuOD object for
 * success and fail cases.
 */
public class MonthMenuP implements MonthMenuOB {

    /**
     * Pass in and return output data.
     * @param output output to be shown in MonthMenuView
     * @return MonthMenuOD object that contains output data
     */
    @Override
    public MonthMenuOD createOutput(MonthMenuOD output) {
        return output;
    }
}
