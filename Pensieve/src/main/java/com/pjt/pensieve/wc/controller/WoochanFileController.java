package com.pjt.pensieve.wc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pjt.pensieve.wc.model.service.MemoryFileService;
import com.pjt.pensieve.wc.model.vo.MemoryFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/wc/file")
@RequiredArgsConstructor
public class WoochanFileController
{
    private final ResourceLoader resourceLoader;
    private final MemoryFileService memoryFileService;

    @GetMapping("/fileDown")
    public ResponseEntity<Resource> fileDown(@RequestParam String memoryId, 
                                             @RequestParam String memoryFileId,
                                             @RequestHeader("user-agent") String userAgent)
    {
        Resource resource = null;
        String downName = null;

        MemoryFile memoryFile = memoryFileService.getFile(Integer.parseInt(memoryFileId));
        
        String oname = memoryFile.getFileOrigName(); 
        String rname = memoryFile.getFileReName();
        
        log.info("oname : {}, rname : {}", oname, rname);

        try
        {
            // 1. Ŭ���̾�Ʈ�� ������ ������ �����´�.
            resource = resourceLoader.getResource("resources/img/upload/wc/memo/" + rname);

            // 2. �������� ���ڵ� ó��
            boolean isMSIE = userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("Trident") != -1;

            if (isMSIE)
            {
                downName = URLEncoder.encode(oname, "UTF-8").replaceAll("\\+", "%20");
            } else
            {
                downName = new String(oname.getBytes("UTF-8"), "ISO-8859-1");
            }

            // 3. ���� �޽��� �ۼ� & Ŭ���̾�Ʈ�� ���(����)�ϱ�
            return ResponseEntity.ok()
//                       .header("Content-Type", "application/octet-stream")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
//                       .header("Content-Disposition", "attachment;filename=" + downName)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + downName).body(resource);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();

//          return ResponseEntity.status(500).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/fileDelete")
    public ResponseEntity<Map<String, Object>> fileDelete(@RequestParam String memoryFileId)
    {
        Resource resource = null;
        int result = 1;
        String location = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();

        MemoryFile memoryFile = memoryFileService.getFile(Integer.parseInt(memoryFileId));
        
        String oname = memoryFile.getFileOrigName(); 
        String rname = memoryFile.getFileReName();
        
        
        result = memoryFileService.deleteFile(Integer.parseInt(memoryFileId));

        try
        {
            // 1. Ŭ���̾�Ʈ�� ������ ������ �����´�.
            resource = resourceLoader.getResource("resources/img/upload/wc/memo/" + rname);
            File file = resource.getFile();
            location = resourceLoader.getResource("resources/img/delete/wc/memo/").getFile().getPath();
            
            System.out.println("file" + ", " + Files.probeContentType(file.toPath()) + ", " + false + ", " + file.getName() + ", " + (int) file.length() + ", " + file.getParentFile());
            
            FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

            InputStream input = new FileInputStream(file);
            OutputStream outputStream = fileItem.getOutputStream();
            IOUtils.copy(input, outputStream);
            MultipartFile partFile = new CommonsMultipartFile(fileItem);

            // location�� ������ �������� ������ ������ �����ϴ� ����
            File folder = new File(location);

            if (!folder.exists())
            {
                folder.mkdirs();
            }

            // ���ε��� ���� �����͸� ������ ���Ͽ� �����Ѵ�.
            partFile.transferTo(new File(location + "/" + rname));
            
            file.delete();
        } catch (IllegalStateException | IOException e)
        {
            log.error("���� ���� ����");
            e.printStackTrace();
            result = 0;
        }
        resultMap.put("result",  result);
        return ResponseEntity.ok(resultMap);
    }
}
