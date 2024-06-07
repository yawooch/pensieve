package com.pjt.pensieve.wc.model.service;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

        // 파일 저장
        if (upfile == null || upfile.isEmpty()){ return 0; }
        
        try
        {
            String location        = null;
            String renamedFileName = null;
            String originalFileName = upfile.getOriginalFilename();
            
            location = resourceLoader.getResource(resourcePath).getFile().getPath();
            //파일명을 재정의 해서 업로드한다
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

    @Override
    public MemoryFile getFile(int memoryFileId)
    {
        return memoryFileMapper.selectMemoryFile(memoryFileId);
    }

    @Override
    public int deleteFile(int memoryFileId)
    {
        return memoryFileMapper.updateDelMemoryFile(memoryFileId);
    }

    @Override
    public int saveFiles(List<MultipartFile> upfiles, int memoryId, String resourcePath)
    {
        List<MemoryFile> memoryFileList = new ArrayList<MemoryFile>();
        int result = 0;

        // 파일 저장
        if (upfiles == null || upfiles.isEmpty()){ return 0; }
        
        for (MultipartFile upfile : upfiles)
        {
            MemoryFile memoryFile = new MemoryFile();
            
            memoryFile.setFileRelaMemoryId(memoryId);
            
            // 파일 저장
            if (upfile == null || upfile.isEmpty()){ return 0; }
            
            try
            {
                String location        = null;
                String renamedFileName = null;
                String originalFileName = upfile.getOriginalFilename();
                
                location = resourceLoader.getResource(resourcePath).getFile().getPath();
                //파일명을 재정의 해서 업로드한다
                renamedFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" 
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"))
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                
                renamedFileName = MultipartFileUtil.save(upfile, location, renamedFileName);
                
                if (renamedFileName != null)
                {
                    memoryFile.setFileOrigName(upfile.getOriginalFilename());
                    memoryFile.setFileReName(renamedFileName);
                }
                
                memoryFileList.add(memoryFile);
                
            } catch (IOException e) { e.printStackTrace(); }
        }
        
        for(MemoryFile memoryFile : memoryFileList)
        {
            result += memoryFileMapper.insertMemoryFile(memoryFile);
        }
        
        return result;
    }
}
