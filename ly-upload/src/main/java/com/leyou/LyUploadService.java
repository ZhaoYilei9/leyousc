package com.leyou;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication
@EnableDiscoveryClient
//解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@Import(FdfsClientConfig.class)//只需要一行注解 @Import(FdfsClientConfig.class)就可以拥有带有连接池的FastDFS Java客户端了
public class LyUploadService {
    public static void main(String[] args) {
        SpringApplication.run(LyUploadService.class,args);
    }
}
