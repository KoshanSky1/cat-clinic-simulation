package cat_clinic_simulation.mapper;

import cat_clinic_simulation.dto.detail.DetailDto;
import cat_clinic_simulation.dto.detail.NewDetailDto;
import cat_clinic_simulation.dto.detail.UpdateDetailDto;
import cat_clinic_simulation.model.Detail;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface DetailMapper {
    Detail toDetailFromNewDetailDto(NewDetailDto newDetailDto);

    DetailDto toDetailDto(Detail detail);

    List<DetailDto> toDtoList(List<Detail> details);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateDetail(UpdateDetailDto updateDetailDto, @MappingTarget Detail detailToUpdate);
}