package hu.multipledatasource.mapper;

import hu.multipledatasource.dto.DataPart1Dto;
import hu.multipledatasource.model.Data1.DataPart1;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DataPart1Mapper {

    @Mapping(target = "idDto", source = "id")
    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "part1BinariesDto", ignore = true)
    DataPart1Dto dataPart1ToDto(DataPart1 dataPart1);

    @InheritInverseConfiguration
    DataPart1 dtoToDataPart1(DataPart1Dto dataPart1Dto);
}
