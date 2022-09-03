package hu.multipledatasource.dto;

import hu.multipledatasource.model.AssembledData.AssembledDataModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public class AssembledDataModelDto {
    private Long idDto;
    private String nameDto;
    private byte[] dataBinariesDto;

    public AssembledDataModelDto() {
    }

    public Long getIdDto() {
        return idDto;
    }

    public void setIdDto(Long idDto) {
        this.idDto = idDto;
    }

    public String getNameDto() {
        return nameDto;
    }

    public void setNameDto(String nameDto) {
        this.nameDto = nameDto;
    }

    public byte[] getDataBinariesDto() {
        return dataBinariesDto;
    }

    public void setDataBinariesDto(byte[] dataBinariesDto) {
        this.dataBinariesDto = dataBinariesDto;
    }
}
