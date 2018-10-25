package com.leyou.image.service.impl;

import com.leyou.image.service.UploadService;
import com.leyou.utils.FdfsWapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {

    private Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Autowired
    private FdfsWapper fdfsWapper;
    private static final List<String> suffixes = Arrays.asList("image/png","image/jpeg","image/jpg");
    @Override
    public String upload(MultipartFile file) {

        try {

            String contentType = file.getContentType();
            if(!suffixes.contains(contentType)){
                logger.info("上传失败：文件类型不匹配");
                return null;
            }
            BufferedImage imageContent = ImageIO.read(file.getInputStream());
            if(imageContent == null){
                logger.info("上传失败，文件内容不符合要求");
            }

            //2.验证成功，保存文件
            //首先生成一个文件夹
//            File dir = new File("/home/zyl/upload");
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
           /* //将图片保存在此文件夹中
            file.transferTo(new File(dir, file.getOriginalFilename()));
            //3.生成url
            //测试阶段，先生成一个假的url
            String url = "http://image.leyou.com/upload/" + file.getOriginalFilename();
            return url;*/

            // 2、将图片上传到FastDFS
            // 2.1、获取文件后缀名
//            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            // 2.2、上传
            String path = fdfsWapper.uploadFile(file);
            // 2.3、返回完整路径
            return path;


        }catch (Exception e){
            return null;
        }



    }
}
