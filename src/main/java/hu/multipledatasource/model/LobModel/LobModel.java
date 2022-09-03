package hu.multipledatasource.model.LobModel;

import org.springframework.http.MediaType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;

@Entity
public class LobModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private File path;
    private long size;
    private MediaType mediaType;

    public LobModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        return "LobModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path=" + path +
                ", size=" + size +
                ", mediaType=" + mediaType +
                '}';
    }
}
