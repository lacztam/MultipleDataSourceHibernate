package hu.multipledatasource.mapper;

import hu.multipledatasource.dto.DataPart2Dto;
import hu.multipledatasource.model.Data2.DataPart2;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DataPart2Mapper {

    @Mapping(target = "idDto", source = "id")
    @Mapping(target = "dataPart1IdDto", source = "dataPart1Id")
    @Mapping(target = "part2BinariesDto", ignore = true)
    DataPart2Dto dataPart2ToDto(DataPart2 dataPart2);

    @InheritInverseConfiguration
    DataPart2 dtoToDataPart2(DataPart2Dto dataPart2Dto);
}
