package com.leyou.image.service;


import org.springframework.web.multipart.MultipartFile;


public interface UploadService {

    String upload(MultipartFile file);
}
