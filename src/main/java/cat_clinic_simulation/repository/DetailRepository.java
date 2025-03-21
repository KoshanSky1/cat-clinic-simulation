package cat_clinic_simulation.repository;

import cat_clinic_simulation.model.Detail;
import cat_clinic_simulation.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    boolean existsByMasterAndName(Master master, String name);

    List<Detail> findAllByMaster(Master master);
}