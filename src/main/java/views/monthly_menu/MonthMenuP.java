package views.monthly_menu;

import use_cases.monthly_menu.MonthMenuOB;
import use_cases.monthly_menu.MonthMenuOD;

public class MonthMenuP implements MonthMenuOB {
    @Override
    public MonthMenuOD createOutput(MonthMenuOD output) {
        return output;
    }
}