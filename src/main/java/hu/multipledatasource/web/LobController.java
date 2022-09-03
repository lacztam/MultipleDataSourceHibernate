package hu.multipledatasource.web;

import hu.multipledatasource.exception.BadRequestException;
import hu.multipledatasource.model.LobModel.LobModel;
import hu.multipledatasource.service.LobService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/lob")
public class LobController {
    @Autowired LobService lobService;

    @GetMapping("/get_all_lob_ids")
    public List<Long> getAllLobIds(){
        return lobService.getAllLobIds();
    }

    @PostMapping
    public LobModel lobUpload(HttpServletRequest request) throws IOException {
        if(!ServletFileUpload.isMultipartContent(request))
            throw new BadRequestException("Multipart request expected");

        LobModel lobModel = lobService.uploadLob(new ServletFileUpload().getItemIterator(request));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");

        return lobModel;
        //return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @GetMapping("/{id}/download")
    public void lobDownload(@PathVariable("id") long id, HttpServletResponse response) throws IOException  {
        LobModel record = lobService.findById(id);

        if(record == null){
            System.out.println("record is null");
        }else{
            System.out.println("toSting:");
            System.out.println(record);
        }

        response.setContentType(record.getMediaType().toString());
        response.setHeader("Content-Length", Long.toString(record.getSize()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + record.getName() +"\"");

        InputStream fileInputStream = new FileInputStream(record.getPath());

        IOUtils.copy(fileInputStream, response.getOutputStream());
        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(response.getOutputStream());
    }
}
