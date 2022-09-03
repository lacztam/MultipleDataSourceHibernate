package hu.multipledatasource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.File;

@Configuration
public class LobDirectoryConfig {

    private final File saveDir;

    public LobDirectoryConfig(@Value("${save-dir}")File saveDir){
        this.saveDir = saveDir;
        this.saveDir.mkdirs();
    }

    public File getSaveDir() {
        return saveDir;
    }
}
