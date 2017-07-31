package com.dav.shopping.controller;

import com.dav.shopping.Util.UtilConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Duong Vu on 22/06/2017.
 */
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @RequestMapping(value = "/", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public ResponseEntity<?> uploadFile(@RequestParam("avatar") MultipartFile uploadfile) {
        System.out.print(uploadfile.getContentType());
        if (uploadfile.isEmpty()) {

            return new ResponseEntity(new Error("Please select a file!") , HttpStatus.BAD_REQUEST);
        }
        if (!UtilConfig.FILE_IMAGE_TYPE.contains(uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().indexOf('.')).toLowerCase())) {
            return new ResponseEntity(new Error("type file not allow!"), HttpStatus.BAD_REQUEST);
        }
        Map<String, String> result = new HashMap<>();
        try {
            result.put("result", saveUploadedFiles(uploadfile));
        } catch (IOException e) {
            return new ResponseEntity<>(new Error("Can't save this file!"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
    }

    private String saveUploadedFiles(MultipartFile multipartFile) throws IOException {

        final String UPLOADED_FOLDER = new ClassPathResource("static/images/avatars").getFile().getAbsolutePath();

        if (multipartFile.isEmpty())
            return "";

        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + "/" + multipartFile.getOriginalFilename());
        Files.write(path, bytes);

        return  "/images/avatars/" + multipartFile.getOriginalFilename();
    }

}
