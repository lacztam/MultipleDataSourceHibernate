package hu.multipledatasource.dto;

public class DataPart2Dto {

    private long idDto;
    private long dataPart1IdDto;
    private byte[] part2BinariesDto;

    public DataPart2Dto() {
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

    public byte[] getPart2BinariesDto() {
        return part2BinariesDto;
    }

    public void setPart2BinariesDto(byte[] part2BinariesDto) {
        this.part2BinariesDto = part2BinariesDto;
    }
}
