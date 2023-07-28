package use_cases.session_load;

public interface SessionLoadIB {
    SessionLoadOD load(SessionLoadID inputData) throws SessionLoadException;
}