package com.pjt.pensieve.main.model.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@ExtendWith(SpringExtension.class)
//@RunWith(SpringJUnit4ClassRunner.class)

class AdminServiceImplTest
{
    @Autowired
    private AdminServiceImpl service;
    
    @Test
    void test()
    {
        List<Map<String, Object>> menuForNodes = service.getMenuForNodes();
        log.info("menuForNodes : {}", menuForNodes);
        fail("Not yet implemented");
    }

}
