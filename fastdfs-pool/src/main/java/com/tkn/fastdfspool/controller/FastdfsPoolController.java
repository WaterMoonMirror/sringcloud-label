package com.tkn.fastdfspool.controller;

import cc.siyecao.fastdfs.model.StoreFile;
import cc.siyecao.fastdfs.service.IStorageService;
import com.tkn.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: Fastdfs连接池版客户端
 * @author: lz
 * @time: 2022/9/2 13:38
 */
@Slf4j
@Controller
@RequestMapping("/fastdfsPool")
public class FastdfsPoolController {
    @Autowired
    private IStorageService storageService;

    /**
     *  单个文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return R.error("文件不能为空！");
        }
        try {
            StoreFile storePath = storageService.uploadFile(file.getBytes(), file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
            log.info("上传的服务器分组: " + storePath.getGroupName());
            log.info("文件全路径: " + storePath.getFullPath());
            log.info("文件名: " + storePath.getFileName());
            log.info("访问地址: " + storePath.getWebUrl());
            return R.ok("文件上传成功！").put("上传的服务器分组",storePath.getGroupName())
                    .put("文件全路径",storePath.getFullPath())
                    .put("访问地址",storePath.getWebUrl())
                    .put("文件名",storePath.getFileName());
        } catch (Exception e) {
            log.error("Upload file error: " + e.getMessage());
            return R.error("文件上传失败");
        }
    }
}
