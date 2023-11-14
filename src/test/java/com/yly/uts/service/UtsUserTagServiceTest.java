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
    public void updateUserTag() {
        List<Integer> userIds = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            userIds.add(i);
        }
        List<String> addTags = new ArrayList<>();
        addTags.add("女性");
        addTags.add("活跃");
        List<String> removeTags = new ArrayList<>();
        removeTags.add("广东");
        removeTags.add("沿海");
        utsUserTagService.updateUserTag(userIds, addTags, removeTags);
    }

}
