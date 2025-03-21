package cat_clinic_simulation.repository;

import cat_clinic_simulation.model.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {

}