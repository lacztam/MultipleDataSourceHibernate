package hu.multipledatasource.mapper;

import hu.multipledatasource.dto.AssembledDataModelDto;
import hu.multipledatasource.model.AssembledData.AssembledDataModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssembledDataMapper {

    @Mapping(target = "idDto", source = "id")
    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "dataBinariesDto", ignore = true)
    AssembledDataModelDto dataToDto(AssembledDataModel assembledDataModel);

    @InheritInverseConfiguration
    AssembledDataModel dtoToData(AssembledDataModelDto assembledDataModelDto);

}
