package cat_clinic_simulation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cat_clinic_simulation.dto.detail.DetailDto;
import cat_clinic_simulation.dto.detail.NewDetailDto;
import cat_clinic_simulation.dto.detail.UpdateDetailDto;
import cat_clinic_simulation.mapper.DetailMapper;
import cat_clinic_simulation.model.Detail;
import cat_clinic_simulation.service.DetailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
@Slf4j
public class DetailsController {

    private final DetailService service;
    private final DetailMapper mapper;

    @GetMapping("/{masterId}")
    public List<DetailDto> getAllDetailsByMasterId(@PathVariable("masterId") Long masterId) {
        log.info("---START GET ALL DETAILS BY MASTER ID ENDPOINT---");
        return mapper.toDtoList(service.getAllDetailByMasterId(masterId));
    }

    @GetMapping("/{masterId}/{detailId}")
    public DetailDto getDetailById(@PathVariable String masterId, @PathVariable("detailId") Long detailId) {
        log.info("---START GET DETAIL BY ID ENDPOINT---");
        return mapper.toDetailDto(service.getDetailByDetailId(detailId));
    }

    @PostMapping("/{masterId}")
    @ResponseStatus(HttpStatus.CREATED)
    public DetailDto createDetail(@PathVariable("masterId") Long masterId, @Valid @RequestBody NewDetailDto dto) {
        log.info("---START CREATE DETAIL ENDPOINT---");
        Detail detail = service.createDetail(masterId, mapper.toDetailFromNewDetailDto(dto));
        return mapper.toDetailDto(detail);
    }

    @PatchMapping("/{detailId}")
    public DetailDto updateDetail(@PathVariable("detailId") Long detailId, @Valid @RequestBody UpdateDetailDto dto) {
        log.info("---START UPDATE DETAIL ENDPOINT---");
        return mapper.toDetailDto(service.updateDetail(detailId, dto));
    }

    @DeleteMapping("/{detailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDetail(@PathVariable("detailId") Long detailId) {
        log.info("---START DELETE DETAIL ENDPOINT---");
        service.deleteDetailById(detailId);
    }

}