package com.pjt.pensieve.wc.controller;

import java.io.IOException;

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
            String path = "resources/css/custom";
            String location = resourceLoader.getResource(path).getFile().getPath();
            
            System.out.println(location);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
