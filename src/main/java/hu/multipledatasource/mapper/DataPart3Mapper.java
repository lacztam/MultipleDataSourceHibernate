package hu.multipledatasource.mapper;

import hu.multipledatasource.dto.DataPart2Dto;
import hu.multipledatasource.dto.DataPart3Dto;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DataPart3Mapper {

    @Mapping(target = "idDto", source = "id")
    @Mapping(target = "dataPart1IdDto", source = "dataPart1Id")
    @Mapping(target = "part3BinariesDto", ignore = true)
    DataPart3Dto dataPart3ToDto(DataPart3 dataPart3);

    @InheritInverseConfiguration
    DataPart3 dtoToDataPart3(DataPart3Dto dataPart3Dto);
}
