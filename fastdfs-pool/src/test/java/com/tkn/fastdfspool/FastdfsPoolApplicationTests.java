package com.tkn.fastdfspool;

import cc.siyecao.fastdfs.model.StoreFile;
import cc.siyecao.fastdfs.service.IStorageService;
import cc.siyecao.fastdfs.service.ITrackerService;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@SpringBootTest
@Slf4j
class FastdfsPoolApplicationTests {
    @Autowired
    private IStorageService storageService;

    @Autowired
    private ITrackerService trackerService;
    // 普通上传
    @Test
    public void fileTest() throws FileNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("C:\\Users\\李柱\\Pictures\\wallpaper_15_4k_1.jpg");
        byte[] bytes = IoUtil.readBytes(fileInputStream);
        StoreFile storeFile=storageService.uploadFile(bytes,"jpg");
        log.info("上传文件路径{}",storeFile.getWebUrl());
        log.info("删除文件路径{}",storeFile);
        int result=storageService.deleteFile(storeFile.getGroupName(),storeFile.getFileName());
        log.info("删除文件结果{}",result);
    }
    // 断点上传
    @Test
    public void fileTest2() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\李柱\\Pictures\\wallpaper_15_4k_1.jpg");
        byte[] bytes = IoUtil.readBytes(fileInputStream);
        StoreFile storeFile = storageService.uploadAppenderFile(bytes, "jpg");
        log.info("上传文件路径{}", storeFile.getWebUrl());
        log.info("删除文件路径{}", storeFile);
        int result = storageService.deleteFile(storeFile.getGroupName(), storeFile.getFileName());
        log.info("删除文件结果{}", result);
    }
   // DownloadTest（文件下载），支持下载为OutputStream，File,byte[]

    @Test
    public void streamTest()throws FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\李柱\\Pictures\\wallpaper_15_4k_1.jpg");
        byte[] bytes = IoUtil.readBytes(fileInputStream);
        StoreFile storeFile = storageService.uploadAppenderFile(bytes, "jpg");
        log.info("上传文件路径{}", storeFile.getWebUrl());
        log.info("下载文件开始{}",storeFile);
        File file=new File("D:\\project\\spring-cloud\\sringcloud-label\\fastdfs-pool\\src\\main\\resources\\wallpaper_15_4k_1.jpg");
        FileOutputStream fos=new FileOutputStream(file,true);
        int result=storageService.downloadFile(storeFile.getGroupName(),storeFile.getFileName(),fos);
        log.info("下载文件结果{}",result);
        log.info("删除文件路径{}",storeFile);
        result=storageService.deleteFile(storeFile.getGroupName(),storeFile.getFileName());
        log.info("删除文件结果{}",result);
    }


}
