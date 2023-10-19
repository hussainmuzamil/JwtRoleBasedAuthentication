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
<<<<<<< HEAD
    private final String _upload_dir = "C:\\Users\\muham\\IdeaProjects\\Task1\\src\\main\\resources\\CSVData";
=======
    private final String _upload_dir = "/Users/muzamil/Desktop/Java web/JwtRoleBasedAuthentication/src/main/resources/CSVData";
>>>>>>> 8d9d15d4dfa3119c9bc50c2519de168900b042b9

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
