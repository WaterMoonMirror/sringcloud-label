package com.tkn.fastdfs;

import cn.hutool.core.io.IoUtil;
import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
@Slf4j
@SpringBootTest
class FastdfsApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    void contextLoads() throws FileNotFoundException {
       FileInputStream fileInputStream=new FileInputStream("C:\\Users\\李柱\\Pictures\\wallpaper_15_4k_1.jpg");
        byte[] bytes = IoUtil.readBytes(fileInputStream);
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(bytes, "jpg");
            log.info("上传的服务器分组: " + storePath.getGroup());
            log.info("获取文件全路径: " + storePath.getFullPath());
            log.info("文件全路径: " + storePath.getPath());
        } catch (Exception e) {
            log.error("Upload file error: " + e.getMessage());
        }
    }

}
