package cat_clinic_simulation.service;

import cat_clinic_simulation.dto.detail.UpdateDetailDto;
import cat_clinic_simulation.model.Detail;

import java.util.List;

public interface DetailService {
    Detail createDetail(Long masterId, Detail detail);

    Detail updateDetail(Long detailId, UpdateDetailDto updateDetailDto);

    Detail getDetailByDetailId(Long detailId);

    List<Detail> getAllDetailByMasterId(Long masterId);

    void deleteDetailById(Long detailId);
}