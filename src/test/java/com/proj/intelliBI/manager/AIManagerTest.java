package com.proj.intelliBI.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AIManagerTest {

    @Resource
    private AIManager aiManager;

    @Test
    void chatWithAI() {
        String answer = aiManager.chatWithAI(1659171950288818178L + "分析需求：\n" +
                "分析网站用户的增长情况\n" +
                "原始数据：\n" +
                "日期,用户数\n" +
                "1号,10\n" +
                "2号,20\n" +
                "3号,30\n");
        System.out.println(answer);

    }
}
