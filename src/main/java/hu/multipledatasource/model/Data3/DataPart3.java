package hu.multipledatasource.model.Data3;

import javax.persistence.*;

@Entity
//@Table(name = "data_part3",schema="file_data")
public class DataPart3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dataPart1Id;
    private byte[] part3Binaries;

    public DataPart3() {
    }

    public Long getDataPart1Id() {
        return dataPart1Id;
    }

    public void setDataPart1Id(Long dataPart1Id) {
        this.dataPart1Id = dataPart1Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPart3Binaries() {
        return part3Binaries;
    }

    public void setPart3Binaries(byte[] part3) {
        this.part3Binaries = part3;
    }
}
