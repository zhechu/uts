package com.yly.uts.service;

import com.yly.uts.core.model.UtsUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class UtsUserServiceTest {

    @Autowired
    private UtsUserService utsUserService;

    /**
     * 测试通过标签条件查询用户列表
     */
    @Test
    public void pageList() {
        List<String> allMatch = new ArrayList<>();
        allMatch.add("沿海");
        allMatch.add("新增");
        List<String> anyMatch = new ArrayList<>();
        anyMatch.add("女性");
        anyMatch.add("活跃");
        List<String> notMatch = new ArrayList<>();
        notMatch.add("广东");
        notMatch.add("广西");
        int pageNumber = 1;
        int pageSize = 10;
        List<UtsUser> utsUserList = utsUserService.pageList(allMatch, anyMatch, notMatch, pageNumber, pageSize);
        log.info("utsUserList:{}", utsUserList);
    }

}
