package com.example.task1.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    private final String _upload_dir = "/Users/muzamil/Desktop/Java web/JwtRoleBasedAuthentication/src/main/resources/CSVData";

    public boolean uploadFile(MultipartFile multipartFile){
        boolean flag = false;

        try {
            Files.copy(multipartFile.getInputStream(), Paths.get(_upload_dir+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }


}
