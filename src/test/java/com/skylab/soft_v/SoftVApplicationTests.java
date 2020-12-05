package com.skylab.soft_v;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SoftVApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(StrUtil.toCamelCase("GPIO_detection"));
    }
    @Test
    void t1(){
        System.out.println("0f6180a3019a003ba586bc4dc6f303db".length());
    }
}
