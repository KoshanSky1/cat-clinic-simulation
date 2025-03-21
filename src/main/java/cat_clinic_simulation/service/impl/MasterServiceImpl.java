package cat_clinic_simulation.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cat_clinic_simulation.dto.master.UpdateMasterDto;
import cat_clinic_simulation.exception.EntityNotFoundException;
import cat_clinic_simulation.exception.NameAlreadyExistsException;
import cat_clinic_simulation.mapper.MasterMapper;
import cat_clinic_simulation.model.Master;
import cat_clinic_simulation.repository.MasterRepository;
import cat_clinic_simulation.service.LogService;
import cat_clinic_simulation.service.MasterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;
    private final LogService logService;
    private final MasterMapper masterMapper;

    @Override
    @Transactional
    public Master createMaster(Master master) {
        checkIfTheNumberIsAvailable(master.getNumber());
        master.setAmount(0.0);
        Master masterSaved = masterRepository.save(master);
        log.info("Added new master with id=" + masterSaved.getId());
        return masterSaved;
    }

    @Override
    @Transactional
    public Master updateMaster(Long masterId, UpdateMasterDto updateMasterDto) {
        if (updateMasterDto.number() != null) {
            checkIfTheNumberIsAvailable(updateMasterDto.number());
        }
        Master master = getMasterById(masterId);
        String oldDescription = master.getDescription();
        masterMapper.updateMaster(updateMasterDto, master);
        if (updateMasterDto.description().isBlank()) {
            master.setDescription(oldDescription);
        }
        Master updatedMaster = masterRepository.save(master);
        log.info("Updated master with id=" + masterId);
        return updatedMaster;
    }

    @Override
    public Master getMasterByMasterId(Long masterId) {
        Master master = getMasterById(masterId);
        log.info("Master with id=" + masterId + " found");
        return master;
    }

    @Override
    public List<Master> getAllMasters() {
        final List<Master> masters = masterRepository.findAll();
        log.info("A list of masters has been generated");
        return masters.stream()
                .sorted(Comparator.comparing(Master::getNumber))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteMasterById(Long masterId) {
        masterRepository.deleteById(masterId);
        log.info("Master with id=" + masterId + "was deleted");
    }

    private Master getMasterById(Long masterId) {
        return masterRepository.findById(masterId).orElseThrow(()
                -> new EntityNotFoundException("Master with id=" + masterId + " was not found"));
    }

    @Transactional
    private void checkIfTheNumberIsAvailable(Integer number) {
        if (masterRepository.existsByNumber(number)) {
            logService.saveLogMessage("Trying to re-add master with number: " + number);
            throw new NameAlreadyExistsException("This number " + number + " already exists");
        }
    }

}