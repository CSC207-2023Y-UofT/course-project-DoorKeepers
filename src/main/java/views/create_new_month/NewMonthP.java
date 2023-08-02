package views.create_new_month;

import use_cases.create_new_month.NewMonthOB;
import use_cases.create_new_month.NewMonthOD;

public class NewMonthP implements NewMonthOB {
    @Override
    public NewMonthOD createOutput(NewMonthOD output) {
        return output;
    }
}
