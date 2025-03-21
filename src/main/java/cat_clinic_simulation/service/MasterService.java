package cat_clinic_simulation.service;

import cat_clinic_simulation.dto.master.UpdateMasterDto;
import cat_clinic_simulation.model.Master;

import java.util.List;

public interface MasterService {
    Master createMaster(Master master);

    Master updateMaster(Long masterId, UpdateMasterDto updateMasterDto);

    Master getMasterByMasterId(Long masterId);

    List<Master> getAllMasters();

    void deleteMasterById(Long masterId);
}