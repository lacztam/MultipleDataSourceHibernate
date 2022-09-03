package hu.multipledatasource.model;

import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;

public class DatasWrapper {
    private DataPart1 dataPart1;
    private DataPart2 dataPart2;
    private DataPart3 dataPart3;

    public DatasWrapper(DataPart1 dataPart1, DataPart2 dataPart2, DataPart3 dataPart3) {
        this.dataPart1 = dataPart1;
        this.dataPart2 = dataPart2;
        this.dataPart3 = dataPart3;
    }

    public DatasWrapper() {
    }

    public DataPart1 getDataPart1() {
        return dataPart1;
    }

    public void setDataPart1(DataPart1 dataPart1) {
        this.dataPart1 = dataPart1;
    }

    public DataPart2 getDataPart2() {
        return dataPart2;
    }

    public void setDataPart2(DataPart2 dataPart2) {
        this.dataPart2 = dataPart2;
    }

    public DataPart3 getDataPart3() {
        return dataPart3;
    }

    public void setDataPart3(DataPart3 dataPart3) {
        this.dataPart3 = dataPart3;
    }
}
