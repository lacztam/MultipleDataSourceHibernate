package hu.multipledatasource.web;

import hu.multipledatasource.dto.DatasWrapperDto;
import hu.multipledatasource.exception.BadRequestException;
import hu.multipledatasource.mapper.DatasWrapperMapper;
import hu.multipledatasource.model.AssembledData.AssembledDataModel;
import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import hu.multipledatasource.model.DatasWrapper;
import hu.multipledatasource.model.LobModel.LobModel;
import hu.multipledatasource.service.DataBaseService;
import hu.multipledatasource.service.DataService;
import hu.multipledatasource.service.LobService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired DataService dataService;
    @Autowired DatasWrapperMapper datasWrapperMapper;
    @Autowired DataBaseService dataBaseService;
    @Autowired LobService lobService;

    @GetMapping("/{id}/download")
    public @ResponseBody byte[] download(@PathVariable long id) {

        AssembledDataModel assembledDataModel = dataService.assembleData(id);
        byte[] assembled = assembledDataModel.getDataBinaries();

        return assembled;
    }

    @PostMapping("/upload")
    public DatasWrapperDto upload(@RequestPart("file") MultipartFile file) throws IOException {
        DatasWrapper datasWrapper = dataService.uploadFileToDatabase(file);
        DatasWrapperDto datasWrapperDto = datasWrapperMapper.datasToDto(datasWrapper);

        return datasWrapperDto;
    }

    @PostMapping("/test_upload")
    public void test_upload(HttpServletRequest request) throws IOException {
        //file.transferTo(new File("/tmp/multipartFile"));

        if(!ServletFileUpload.isMultipartContent(request))
            throw new NullPointerException("Multipart request expected");

        InputStream inputStream = new BufferedInputStream(request.getInputStream());
        LineIterator it = IOUtils.lineIterator(inputStream, "UTF-8");

        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                line.getBytes();
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(it);
        }
    }

}
