package hu.multipledatasource.model.Data3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DataPart3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dataPart1Id;
    private String name;

    private byte[] part3;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPart3() {
        return part3;
    }

    public void setPart3(byte[] part3) {
        this.part3 = part3;
    }
}
