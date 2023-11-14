package com.yly.uts.core.vdo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UtsUserTagVdo {

    @NotEmpty(message = "用户不能为空")
    private List<Integer> userIds;

    private List<String> addTags;

    private List<String> removeTags;

}
