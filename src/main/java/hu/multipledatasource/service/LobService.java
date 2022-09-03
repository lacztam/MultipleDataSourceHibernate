package hu.multipledatasource.service;

import hu.multipledatasource.config.LobDirectoryConfig;
import hu.multipledatasource.model.LobModel.LobModel;
import hu.multipledatasource.repository.Lob.LobRepository;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.persistence.Lob;
import java.io.*;
import java.util.List;

import static java.lang.String.format;

@Service
public class LobService {
    @Autowired LobDirectoryConfig lobDirectoryConfig;
    @Autowired LobRepository lobRepository;

    public LobModel save(LobModel lobModel){
        LobModel save = lobRepository.save(lobModel);
        return save;
    }

    public LobModel findById(long id) throws FileNotFoundException {
        return lobRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File with '" + id + "' not found"));
    }

    public List<Long> getAllLobIds(){
        return lobRepository.getAllLobIds();
    }

    public LobModel uploadLob(FileItemIterator itemIterator) throws IOException {
        LobModel lobModel = null;
        while (itemIterator.hasNext()) {
            FileItemStream item = itemIterator.next();

            if(item.isFormField())
                continue;

            lobModel = uploadLob(item);
        }

        if(lobModel == null)
            throw new FileNotFoundException();
        else
            return lobModel;
    }

    private LobModel uploadLob(FileItemStream item) throws IOException {
        boolean mkdirs = lobDirectoryConfig.getSaveDir().mkdirs();
        String fileName = item.getName();
        System.out.println("fileName: " + fileName);
        String type = item.getContentType();
        InputStream inputStream = item.openStream();
        //File destination = new File(lobDirectoryConfig.getSaveDir() + "/" + application_username + "/" + fileName));
        File destination = new File(lobDirectoryConfig.getSaveDir() + "/" + fileName);
        System.out.println(destination.getName());
        FileOutputStream fileOutputStream = new FileOutputStream(destination);

        IOUtils.copy(inputStream, fileOutputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(fileOutputStream);

        LobModel lobModel = new LobModel();
        lobModel.setName(fileName);
        lobModel.setPath(destination);
        lobModel.setSize(destination.length());
        lobModel.setMediaType(MediaType.parseMediaType(type));

        LobModel savedLob = lobRepository.save(lobModel);
        return savedLob;
    }

}
