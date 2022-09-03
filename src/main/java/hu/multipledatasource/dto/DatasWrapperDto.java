package hu.multipledatasource.dto;

public class DatasWrapperDto {
    private DataPart1Dto dataPart1Dto;
    private DataPart2Dto dataPart2Dto;
    private DataPart3Dto dataPart3Dto;

    public DatasWrapperDto() {
    }

    public DataPart1Dto getDataPart1Dto() {
        return dataPart1Dto;
    }

    public void setDataPart1Dto(DataPart1Dto dataPart1Dto) {
        this.dataPart1Dto = dataPart1Dto;
    }

    public DataPart2Dto getDataPart2Dto() {
        return dataPart2Dto;
    }

    public void setDataPart2Dto(DataPart2Dto dataPart2Dto) {
        this.dataPart2Dto = dataPart2Dto;
    }

    public DataPart3Dto getDataPart3Dto() {
        return dataPart3Dto;
    }

    public void setDataPart3Dto(DataPart3Dto dataPart3Dto) {
        this.dataPart3Dto = dataPart3Dto;
    }
}
