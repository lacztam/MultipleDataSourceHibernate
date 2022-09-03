package hu.multipledatasource.mapper;

import hu.multipledatasource.dto.DataPart1Dto;
import hu.multipledatasource.dto.DataPart2Dto;
import hu.multipledatasource.dto.DataPart3Dto;
import hu.multipledatasource.dto.DatasWrapperDto;
import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import hu.multipledatasource.model.DatasWrapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DatasWrapperMapper {

    @Mapping(target = "dataPart1Dto.", source = "dataPart1")
    @Mapping(target = "dataPart2Dto", source = "dataPart2")
    @Mapping(target = "dataPart3Dto", source = "dataPart3")
    DatasWrapperDto datasToDto(DatasWrapper datasWrapper);

    @InheritInverseConfiguration
    DatasWrapper dtoToDatas(DatasWrapperDto datasWrapperDto);

    @Mapping(target = "idDto", source = "id")
    @Mapping(target = "part1BinariesDto", ignore = true)
    DataPart1Dto dataPart1ToDto(DataPart1 dataPart1);

    @InheritInverseConfiguration
    DataPart1 dtoToDataPart1(DataPart1Dto dataPart1Dto);

    @Mapping(target = "idDto", source = "id")
    @Mapping(target = "dataPart1IdDto", source = "dataPart1Id")
    @Mapping(target = "part2BinariesDto", ignore = true)
    DataPart2Dto dataPart2ToDto(DataPart2 dataPart2);

    @InheritInverseConfiguration
    DataPart2 dtoToDataPart2(DataPart2Dto dataPart2Dto);

    @Mapping(target = "idDto", source = "id")
    @Mapping(target = "dataPart1IdDto", source = "dataPart1Id")
    @Mapping(target = "part3BinariesDto", ignore = true)
    DataPart3Dto dataPart3ToDto(DataPart3 dataPart3);

    @InheritInverseConfiguration
    DataPart3 dtoToDataPart3(DataPart3Dto dataPart3Dto);

}
