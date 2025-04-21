package cat_clinic_simulation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cat_clinic_simulation.dto.master.MasterDto;
import cat_clinic_simulation.dto.master.NewMasterDto;
import cat_clinic_simulation.dto.master.UpdateMasterDto;
import cat_clinic_simulation.mapper.MasterMapper;
import cat_clinic_simulation.model.Master;
import cat_clinic_simulation.service.MasterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masters")
@RequiredArgsConstructor
@Slf4j
public class MasterController {

    private final MasterService service;
    private final MasterMapper mapper;

    @GetMapping
    public List<MasterDto> getAllMasters() {
        log.info("---START GET ALL MASTERS ENDPOINT---");
        return mapper.toDtoList(service.getAllMasters());
    }

    @GetMapping("/{masterId}")
    public MasterDto getMasterById(@PathVariable("masterId") Long masterId) {
        log.info("---START GET MASTER BY ID ENDPOINT---");
        return mapper.toMasterDto(service.getMasterByMasterId(masterId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MasterDto createMaster(@Valid @RequestBody NewMasterDto dto) {
        log.info("---START CREATE MASTER ENDPOINT---");
        Master master = service.createMaster(mapper.toMasterFromNewMasterDto(dto));
        return mapper.toMasterDto(master);
    }

    @PatchMapping("/{masterId}")
    public MasterDto updateMaster(@PathVariable("masterId") Long masterId, @Valid @RequestBody UpdateMasterDto dto) {
        log.info("---START UPDATE MASTER ENDPOINT---");
        Master master = service.updateMaster(masterId, dto);
        return mapper.toMasterDto(master);
    }

    @DeleteMapping("/{masterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaster(@PathVariable("masterId") Long masterId) {
        log.info("---START DELETE MASTER ENDPOINT---");
        service.deleteMasterById(masterId);
    }

}