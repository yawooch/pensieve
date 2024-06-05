package com.pjt.pensieve.wc.model.service;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.pensieve.main.common.MultipartFileUtil;
import com.pjt.pensieve.wc.model.mapper.MemoryFileMapper;
import com.pjt.pensieve.wc.model.vo.MemoryFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoryFileServiceImpl implements MemoryFileService
{
    private final ResourceLoader resourceLoader;
    private final MemoryFileMapper memoryFileMapper;

    @Override
    public int saveFile(MultipartFile upfile, int memoryId, String resourcePath)
    {
        MemoryFile memoryFile = new MemoryFile();

        memoryFile.setFileRelaMemoryId(memoryId);

        // ���� ����
        if (upfile == null || upfile.isEmpty()){ return 0; }
        
        try
        {
            String location        = null;
            String renamedFileName = null;
            String originalFileName = upfile.getOriginalFilename();
            
            location = resourceLoader.getResource(resourcePath).getFile().getPath();
            //���ϸ��� ������ �ؼ� ���ε��Ѵ�
            renamedFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" 
                                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"))
                                + originalFileName.substring(originalFileName.lastIndexOf("."));
            renamedFileName = MultipartFileUtil.save(upfile, location, renamedFileName);
            log.info("renamedFileName : {}", renamedFileName);

            if (renamedFileName != null)
            {
                memoryFile.setFileOrigName(upfile.getOriginalFilename());
                memoryFile.setFileReName(renamedFileName);
            }

        } catch (IOException e) { e.printStackTrace(); }

        
        return memoryFileMapper.insertMemoryFile(memoryFile);
    }

}
