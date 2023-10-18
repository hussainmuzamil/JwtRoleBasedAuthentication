package com.example.task1.controller;

import com.example.task1.helper.FileUploadHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class FileUploadController {
    private final FileUploadHelper fileUploadHelper;
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        ResponseEntity<String> response = ResponseEntity.ok("");
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
        }
        if(!file.getContentType().equals("csv")){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File type is not supported");
        }
        try {
           response =  fileUploadHelper.uploadFile(file) ? ResponseEntity.ok("File Uploaded Successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!") ;

        }catch (Exception e){
            e.printStackTrace();
            response = ResponseEntity.ok("something went wrong");
        }
        return response;
    }
}
