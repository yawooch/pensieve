package com.pjt.pensieve.wc.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;


class WoochanMemoControllerTest
{
    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void fileUploadTest()
    {
        try
        {
//            String path = "resources/upload/board";
//            String path = "resources/css/custom";
//            String location = resourceLoader.getResource(path).getFile().getPath();
            
//            System.out.println(location);
            
            FileSystem fileSystem = FileSystems.getDefault();

            for(FileStore store : fileSystem.getFileStores()) {

                System.out.println("드라이버명: " + store.name());

                System.out.println("파일시스템: " + store.type());

                System.out.println("전체 공간: \t\t" + store.getTotalSpace() + " 바이트");

                System.out.println("사용 중인 공간: \t" + (store.getTotalSpace() - store.getUnallocatedSpace()) + " 바이트");

                System.out.println("사용 가능한 공간: \t" + store.getUsableSpace() + " 바이트");

                System.out.println();

               }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
