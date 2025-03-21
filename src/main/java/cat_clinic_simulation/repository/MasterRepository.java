package cat_clinic_simulation.repository;

import cat_clinic_simulation.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Long> {

    boolean existsByNumber(Integer number);

}
