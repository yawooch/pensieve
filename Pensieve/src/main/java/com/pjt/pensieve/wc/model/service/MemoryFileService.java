package com.pjt.pensieve.wc.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pjt.pensieve.wc.model.vo.MemoryFile;

public interface MemoryFileService
{
    int saveFile(MultipartFile upfile, int memoryId, String resourcePath);

    MemoryFile getFile(int memoryFileId);

    int deleteFile(int memoryFileId);

    int saveFiles(List<MultipartFile> imageFiles, int memoryId, String string);
}
