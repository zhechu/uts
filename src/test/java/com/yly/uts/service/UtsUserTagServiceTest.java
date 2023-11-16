package com.yly.uts.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UtsUserTagServiceTest {

    @Autowired
    private UtsUserTagService utsUserTagService;

    /**
     * 测试使用多线程同时对用户批量进行打标签和移除标签
     * @throws InterruptedException
     */
    @Test
    public void updateUserTag() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    updateUserTagDetail();
                }
            }.start();
        }
        Thread.sleep(1000_000);
    }

    private void updateUserTagDetail() {
        List<Integer> userIds = new ArrayList<>();
        for (int i = 8000; i < 9000; i++) {
            userIds.add(i);
        }
        List<String> addTags = new ArrayList<>();
        addTags.add("测试");
        addTags.add("越越跃");
        addTags.add("积极");
        List<String> removeTags = new ArrayList<>();
        removeTags.add("临淄");
        removeTags.add("山东");
        utsUserTagService.updateUserTag(userIds, addTags, removeTags);
    }

}
