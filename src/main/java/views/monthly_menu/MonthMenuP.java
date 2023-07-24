package views.monthly_menu;

import use_cases.monthly_menu.MonthMenuOB;
import use_cases.monthly_menu.MonthMenuOD;

public class MonthMenuP implements MonthMenuOB {
    @Override
    public MonthMenuOD createSuccessView(MonthMenuOD output) {
        return output;
    }

    @Override
    public MonthMenuOD createFailView(String warning) {
        //TODO: add valid return statement
        return null;
    }
}
