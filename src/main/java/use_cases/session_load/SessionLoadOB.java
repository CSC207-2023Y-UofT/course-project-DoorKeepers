package use_cases.session_load;

import entities.SessionStorage;

public interface SessionLoadOB {
    void displaySuccess(String message, SessionStorage session);
    void displayError(String message);
}