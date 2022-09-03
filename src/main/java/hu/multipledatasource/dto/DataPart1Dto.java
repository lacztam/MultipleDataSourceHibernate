package hu.multipledatasource.dto;

public class DataPart1Dto {

    private long idDto;
    private String nameDto;
    private byte[] part1BinariesDto;

    public DataPart1Dto() {
    }

    public long getIdDto() {
        return idDto;
    }

    public void setIdDto(long idDto) {
        this.idDto = idDto;
    }

    public String getNameDto() {
        return nameDto;
    }

    public void setNameDto(String nameDto) {
        this.nameDto = nameDto;
    }

    public byte[] getPart1BinariesDto() {
        return part1BinariesDto;
    }

    public void setPart1BinariesDto(byte[] part1BinariesDto) {
        this.part1BinariesDto = part1BinariesDto;
    }
}
