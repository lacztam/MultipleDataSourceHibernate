package hu.multipledatasource.model.AssembledData;

import javax.persistence.*;

//Assembled data
@Entity
public class AssembledDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private byte[] dataBinaries;

    public AssembledDataModel() {
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

    public byte[] getDataBinaries() {
        return dataBinaries;
    }

    public void setDataBinaries(byte[] dataBinaries) {
        this.dataBinaries = dataBinaries;
    }
}
