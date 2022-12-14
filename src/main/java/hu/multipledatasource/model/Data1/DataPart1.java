package hu.multipledatasource.model.Data1;

import javax.persistence.*;

@Entity
//@Table(name = "data_part1",schema="file_data")
public class DataPart1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private byte[] part1Binaries;

    public DataPart1() {
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

    public byte[] getPart1Binaries() {
        return part1Binaries;
    }

    public void setPart1Binaries(byte[] part1) {
        this.part1Binaries = part1;
    }
}
