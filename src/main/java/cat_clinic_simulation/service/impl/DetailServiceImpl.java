package cat_clinic_simulation.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cat_clinic_simulation.dto.detail.UpdateDetailDto;
import cat_clinic_simulation.exception.EntityNotFoundException;
import cat_clinic_simulation.exception.NameAlreadyExistsException;
import cat_clinic_simulation.mapper.DetailMapper;
import cat_clinic_simulation.model.Detail;
import cat_clinic_simulation.model.Master;
import cat_clinic_simulation.repository.DetailRepository;
import cat_clinic_simulation.service.DetailService;
import cat_clinic_simulation.service.LogService;
import cat_clinic_simulation.service.MasterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DetailServiceImpl implements DetailService {

    private final DetailRepository detailRepository;
    private final LogService logService;
    private final MasterService masterService;
    private final DetailMapper detailMapper;

    @Override
    @Transactional
    public Detail createDetail(Long masterId, Detail detail) {
        Master master = masterService.getMasterByMasterId(masterId);
        checkIfTheNameIsAvailable(master, detail.getName());
        detail.setMaster(master);
        Detail detailSaved = detailRepository.save(detail);
        Double newMasterAmount = recalculateMasterAmount(masterId, detail.getAmount(), "ADD");
        master.setAmount(newMasterAmount);
        log.info("Added a new detail to the master with id '{}'", masterId);
        return detailSaved;
    }

    @Override
    @Transactional
    public Detail updateDetail(Long detailId, UpdateDetailDto updateDetailDto) {
        Detail detail = getDetailById(detailId);
        String oldName = detail.getName();
        Master master = masterService.getMasterByMasterId(detail.getMaster().getId());
        Double newMasterAmount;
        if (updateDetailDto.name() != null) {
            checkIfTheNameIsAvailable(master, updateDetailDto.name());
        }
        if (updateDetailDto.amount() != null) {
            if (detail.getAmount() > updateDetailDto.amount()) {
                newMasterAmount = recalculateMasterAmount(master.getId(),
                        detail.getAmount() - updateDetailDto.amount(), "DECREASE");
            } else {
                newMasterAmount = recalculateMasterAmount(master.getId(),
                        updateDetailDto.amount() - detail.getAmount(), "INCREASE");
            }
            master.setAmount(newMasterAmount);
        }
        detailMapper.updateDetail(updateDetailDto, detail);
        if (updateDetailDto.name().isBlank()) {
            detail.setName(oldName);
        }
        Detail updatedDetail = detailRepository.save(detail);
        log.info("Detail with id '{}' was updated", detailId);
        return updatedDetail;
    }

    @Override
    public Detail getDetailByDetailId(Long detailId) {
        Detail detail = getDetailById(detailId);
        log.info("Detail with id '{}' found", detailId);
        return detail;
    }

    @Override
    public List<Detail> getAllDetailByMasterId(Long masterId) {
        Master master = masterService.getMasterByMasterId(masterId);
        final List<Detail> details = detailRepository.findAllByMaster(master);
        log.info("A list of details for the master with id '{}' has been generated", masterId);
        return details.stream()
                .sorted(Comparator.comparing(Detail::getName))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDetailById(Long detailId) {
        Detail detail = getDetailById(detailId);
        Master master = masterService.getMasterByMasterId(detail.getMaster().getId());
        Double newMasterAmount = recalculateMasterAmount(master.getId(), detail.getAmount(), "DELETE");
        master.setAmount(newMasterAmount);
        detailRepository.deleteById(detailId);
        log.info("Detail with id '{}' was deleted", detailId);
    }

    private Detail getDetailById(Long detailId) {
        return detailRepository.findById(detailId).orElseThrow(()
                -> new EntityNotFoundException(String.format("Detail with id '%s' was not found", detailId)));
    }

    private void checkIfTheNameIsAvailable(Master master, String name) {
        if (detailRepository.existsByMasterAndName(master, name)) {
            logService.saveLogMessage(String.format("Trying to re-add а detail with name '%s' to the master" +
                    "with id '%s'", name, master.getId()));
            throw new NameAlreadyExistsException(String.format("This name '%s' already exists in the master" +
                    "with id '%s'", name, master.getId()));
        }
    }

    private Double recalculateMasterAmount(Long masterId, Double detailAmount, String option) {
        Master master = masterService.getMasterByMasterId(masterId);
        Double oldMasterAmount = master.getAmount();
        return switch (option) {
            case "ADD", "INCREASE" -> oldMasterAmount + detailAmount;
            case "DELETE", "DECREASE" -> oldMasterAmount - detailAmount;
            default -> oldMasterAmount;
        };
    }
}