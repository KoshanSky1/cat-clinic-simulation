package cat_clinic_simulation.service;

@FunctionalInterface
public interface LogService {

    void saveLogMessage(String message);
}