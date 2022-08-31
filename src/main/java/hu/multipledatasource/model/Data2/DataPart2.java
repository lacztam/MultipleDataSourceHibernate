package hu.multipledatasource.model.Data2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DataPart2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dataPart1Id;
    private String name;

    private byte[] part2;

    public DataPart2() {
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

    public byte[] getPart2() {
        return part2;
    }

    public void setPart2(byte[] part2) {
        this.part2 = part2;
    }
}
