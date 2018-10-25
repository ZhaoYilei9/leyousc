package com.leyou.image.controller;

import com.leyou.image.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("image")
    public ResponseEntity<String> upload(@RequestParam ("file") MultipartFile file){

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());

        String url = uploadService.upload(file);


        if (StringUtils.isEmpty(url)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("上传失败");
        }

        return ResponseEntity.status(HttpStatus.OK).body(url);
    }
}
