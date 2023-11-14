package com.yly.uts.service;

import com.yly.uts.core.model.UtsUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UtsUserServiceTest {

    @Autowired
    private UtsUserService utsUserService;

    @Test
    public void pageList() {
        List<UtsUser> utsUserList = utsUserService.pageList(0, 10);
        System.out.println(utsUserList.size());
    }

}
