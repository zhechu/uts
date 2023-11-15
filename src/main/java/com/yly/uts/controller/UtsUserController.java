package com.yly.uts.controller;

import com.yly.uts.core.common.ReturnT;
import com.yly.uts.core.model.UtsUser;
import com.yly.uts.core.vdo.UtsUserTagPageListVdo;
import com.yly.uts.service.UtsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UtsUserController {

    @Autowired
    private UtsUserService utsUserService;

    @PostMapping("/pageList")
    public ReturnT<List<UtsUser>> pageList(@RequestBody @Validated UtsUserTagPageListVdo utsUserTagPageListVdo) {
        List<UtsUser> utsUsers = utsUserService.pageList(
                utsUserTagPageListVdo.getAllMatch(),
                utsUserTagPageListVdo.getAnyMatch(),
                utsUserTagPageListVdo.getNotMatch(),
                utsUserTagPageListVdo.getPageNumber(),
                utsUserTagPageListVdo.getPageSize()
        );
        return new ReturnT<>(utsUsers);
    }

}
