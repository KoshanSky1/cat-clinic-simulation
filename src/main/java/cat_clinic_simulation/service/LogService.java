package cat_clinic_simulation.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@FunctionalInterface
public interface LogService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void saveLogMessage(String message);
}