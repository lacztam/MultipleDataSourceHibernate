package hu.multipledatasource.web;


import hu.multipledatasource.model.Data1.DataPart1;
import hu.multipledatasource.model.Data2.DataPart2;
import hu.multipledatasource.model.Data3.DataPart3;
import hu.multipledatasource.service.AssemblerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AssemblerController {

    @Autowired
    AssemblerService assembleService;

    @GetMapping("/getAllId")
    public List<Long> getAllIds(){
        return assembleService.getAllIds();
    }

    @GetMapping("/download/{id}")
    public @ResponseBody byte[] download(@PathVariable long id) {

        byte[] assembled = assembleService.download(id);

        return assembled;
    }

    @PostMapping("/upload")
    public DataPart1 upload(@RequestPart("file") MultipartFile file){

        byte[] fileByteArr;
        try {
            fileByteArr = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Byte> list = new ArrayList<>();
        for(int i = 0; i < fileByteArr.length; i++){
            list.add(fileByteArr[i]);
        }

        int listSize1 = fileByteArr.length / 3;
        int listSize2 = (fileByteArr.length - listSize1) / 2;
        int listSize3 = fileByteArr.length - listSize1 - listSize2;

        byte[] part1 = new byte[listSize1];
        for(int i = 0; i < listSize1; i++){
            part1[i] = fileByteArr[i];
        }
        byte[] part2 = new byte[listSize2];
        for(int i = listSize1, j =0; i < (listSize1 + listSize2); i++, j++){
            part2[j] = fileByteArr[i];
        }
        byte[] part3 = new byte[listSize3];
        for(int i = listSize1+listSize2, j = 0; i < listSize1+listSize2+listSize3; i++, j++){
            part3[j] = fileByteArr[i];
        }

        DataPart1 dataPart1 = new DataPart1();
        dataPart1.setPart1(part1);

        DataPart2 dataPart2 = new DataPart2();
        dataPart2.setPart2(part2);

        DataPart3 dataPart3 = new DataPart3();
        dataPart3.setPart3(part3);

        DataPart1 upload = assembleService.upload(dataPart1, dataPart2, dataPart3);
        upload.setPart1(null);
        return upload;
    }
}
