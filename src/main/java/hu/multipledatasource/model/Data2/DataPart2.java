package hu.multipledatasource.model.Data2;

import javax.persistence.*;

@Entity
//@Table(name = "data_part2",schema="file_data")
public class DataPart2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dataPart1Id;
    private byte[] part2Binaries;

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

    public byte[] getPart2Binaries() {
        return part2Binaries;
    }

    public void setPart2Binaries(byte[] part2) {
        this.part2Binaries = part2;
    }
}
