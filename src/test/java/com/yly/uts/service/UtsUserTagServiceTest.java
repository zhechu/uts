package com.yly.uts.service;

import com.yly.uts.core.model.UtsUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UtsUserTagServiceTest {

    @Autowired
    private UtsUserTagService utsUserTagService;

    @Test
    public void updateUserTag() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
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
        addTags.add("玉儿");
        addTags.add("月儿");
        addTags.add("活三跃");
        List<String> removeTags = new ArrayList<>();
        removeTags.add("洱东海");
        removeTags.add("山东");
        utsUserTagService.updateUserTag(userIds, addTags, removeTags);
    }

}
