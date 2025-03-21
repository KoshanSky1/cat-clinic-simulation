package cat_clinic_simulation.mapper;

import cat_clinic_simulation.dto.master.MasterDto;
import cat_clinic_simulation.dto.master.NewMasterDto;
import cat_clinic_simulation.dto.master.UpdateMasterDto;
import cat_clinic_simulation.model.Master;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface MasterMapper {
    Master toMasterFromNewMasterDto(NewMasterDto newMasterDto);

    MasterDto toMasterDto(Master master);

    List<MasterDto> toDtoList(List<Master> masters);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateMaster(UpdateMasterDto updateMasterDto, @MappingTarget Master masterToUpdate);
}