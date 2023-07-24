package use_cases.monthly_menu;

public interface MonthMenuOB {
    MonthMenuOD createSuccessView(MonthMenuOD output);

    MonthMenuOD createFailView(String warning);
}
