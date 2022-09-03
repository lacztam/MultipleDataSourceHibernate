package hu.multipledatasource.dto;

public class DataPart3Dto {

    private long idDto;
    private long dataPart1IdDto;
    private byte[] part3BinariesDto;

    public DataPart3Dto() {
    }

    public long getIdDto() {
        return idDto;
    }

    public void setIdDto(long idDto) {
        this.idDto = idDto;
    }

    public long getDataPart1IdDto() {
        return dataPart1IdDto;
    }

    public void setDataPart1IdDto(long dataPart1IdDto) {
        this.dataPart1IdDto = dataPart1IdDto;
    }

    public byte[] getPart3BinariesDto() {
        return part3BinariesDto;
    }

    public void setPart3BinariesDto(byte[] part3BinariesDto) {
        this.part3BinariesDto = part3BinariesDto;
    }
}
