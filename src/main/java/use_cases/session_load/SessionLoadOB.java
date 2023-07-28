package use_cases.session_load;

import entities.SessionStorage;

public interface SessionLoadOB {
    SessionLoadOD displaySuccess(String message, SessionStorage session);
    SessionLoadOD displayError(String message) throws SessionLoadException;
}