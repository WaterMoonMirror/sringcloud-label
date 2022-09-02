package com.tkn.fastdfs.controller;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import com.tkn.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @description: Fastdfs测试客户端
 * @author: lz
 * @time: 2022/9/2 11:53
 */
@RestController
@Slf4j
@RequestMapping("/fastdfs")
public class FastdfsController {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return R.error("文件不能为空！");
        }
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getBytes(), "jpg");
            log.info("上传的服务器分组: " + storePath.getGroup());
            log.info("文件全路径: " + storePath.getFullPath());
            log.info("文件路径: " + storePath.getPath());
            return R.ok("文件上传成功！").put("上传的服务器分组",storePath.getGroup())
                    .put("文件全路径",storePath.getFullPath())
                    .put("文件路径",storePath.getPath());
        } catch (Exception e) {
            log.error("Upload file error: " + e.getMessage());
            return R.error("文件上传失败");
        }
    }

}
