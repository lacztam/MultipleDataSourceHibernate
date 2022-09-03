package hu.multipledatasource.web;

import hu.multipledatasource.dto.DatasWrapperDto;
import hu.multipledatasource.mapper.DatasWrapperMapper;
import hu.multipledatasource.model.AssembledData.AssembledDataModel;
import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import hu.multipledatasource.model.DatasWrapper;
import hu.multipledatasource.service.DataBaseService;
import hu.multipledatasource.service.DataService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    @Autowired
    DataService assembleService;
    @Autowired
    DatasWrapperMapper datasWrapperMapper;
    @Autowired
    DataBaseService dataBaseService;

    @GetMapping("/{id}/download")
    public @ResponseBody byte[] download(@PathVariable long id) {

        AssembledDataModel assembledDataModel = assembleService.assembleData(id);
        byte[] assembled = assembledDataModel.getDataBinaries();

        return assembled;
    }

    @PostMapping("/upload")
    public DatasWrapperDto upload(@RequestPart("file") MultipartFile file) throws IOException {
        DatasWrapper datasWrapper = assembleService.uploadFileToDatabase(file);
        DatasWrapperDto datasWrapperDto = datasWrapperMapper.datasToDto(datasWrapper);

        return datasWrapperDto;
    }

  /*  @PostMapping("/test_upload")
    public void test_upload(HttpServletRequest request) throws IOException {
        //file.transferTo(new File("/tmp/multipartFile"));

        if(!ServletFileUpload.isMultipartContent(request)) {
            throw new NullPointerException("Multipart request expected");
        }
        InputStream inputStream = new BufferedInputStream(request.getInputStream());
        LineIterator it = IOUtils.lineIterator(inputStream, "UTF-8");
        long fileSize = file.getBytes().length;

        long filePart1Size = fileSize / 3;
        long filePart2Size = (fileSize - filePart1Size) / 2;
        long filePart3Size = fileSize - filePart1Size - filePart2Size;




        DataPart1 dataPart1 = new DataPart1();
        dataPart1.setName(file.getOriginalFilename());
        dataPart1.setPart1Binaries(part1);

        DataPart2 dataPart2 = new DataPart2();
        dataPart2.setPart2Binaries(part2);

        DataPart3 dataPart3 = new DataPart3();
        dataPart3.setPart3Binaries(part3);
        IOUtils.copy();
        DatasWrapper upload = upload(dataPart1, dataPart2, dataPart3);

        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                // do something with line
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(it);
        }
    }*/

}
