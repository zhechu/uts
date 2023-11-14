package com.yly.uts.controller;

import com.yly.uts.core.common.ReturnT;
import com.yly.uts.core.vdo.UtsUserTagVdo;
import com.yly.uts.service.UtsUserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userTag")
public class UtsUserTagController {

    @Autowired
    private UtsUserTagService utsUserTagService;

    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<Integer> update(@RequestBody @Validated UtsUserTagVdo utsUserTagVdo) {
        int updateUtsUserCount = utsUserTagService.updateUserTag(utsUserTagVdo.getUserIds(), utsUserTagVdo.getAddTags(), utsUserTagVdo.getRemoveTags());
        return new ReturnT<>(updateUtsUserCount);
    }

}
