package com.pjt.pensieve.wc.model.service;

import org.springframework.web.multipart.MultipartFile;

public interface MemoryFileService
{
    int saveFile(MultipartFile upfile, int memoryId, String resourcePath);
}
