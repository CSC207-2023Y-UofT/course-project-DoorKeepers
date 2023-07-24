package views.monthly_menu;

import entities.SessionStorage;
import use_cases.monthly_menu.MonthMenuOD;
import use_cases.monthly_menu.UpdateViewIB;
import use_cases.monthly_menu.UpdateViewID;

public class UpdateViewC {
    final UpdateViewIB inputBoundary;

    public UpdateViewC(UpdateViewIB inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    MonthMenuOD getOutput(SessionStorage session, int monthID){
        UpdateViewID inputData = new UpdateViewID(session, monthID);
        return inputBoundary.createOutput(inputData);
    }
}
