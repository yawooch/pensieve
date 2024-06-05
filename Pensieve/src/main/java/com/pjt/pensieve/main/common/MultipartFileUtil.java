package com.pjt.pensieve.main.common;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MultipartFileUtil
{
    public static String save(MultipartFile upfile, String location)
    {
        return save(upfile, location, null);
    }
    public static String save(MultipartFile upfile, String location, String renamedFileName)
    {
        String originalFileName = upfile.getOriginalFilename();

        log.info("Upfile Name : {}", originalFileName);
        log.info("location : {}", location);

        // location이 실제로 존재하지 않으면 폴더를 생성하는 로직
        File folder = new File(location);

        if (!folder.exists())
        {
            folder.mkdirs();
        }
        
        //renamedFileName 이 null 이면 기존 방법으로 rename 하고 아니면 전달되 값으로 전송 
        renamedFileName = renamedFileName==null?LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"))
                 + originalFileName.substring(originalFileName.lastIndexOf(".")):renamedFileName;

        try
        {
            // 업로드한 파일 데이터를 지정한 파일에 저장한다.
            upfile.transferTo(new File(location + "/" + renamedFileName));

        } catch (IllegalStateException | IOException e)
        {
            log.error("파일 전송 에러");
            e.printStackTrace();
        }

        return renamedFileName;
    }

    public static void delete(String location)
    {
        log.info("location : {}", location);

        File file = new File(location);

        if (file.exists())
        {
            file.delete();
        }
    }
}