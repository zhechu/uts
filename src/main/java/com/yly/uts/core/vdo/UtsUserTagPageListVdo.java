package com.yly.uts.core.vdo;

import com.yly.uts.core.page.PageBase;
import lombok.Data;

import java.util.List;

@Data
public class UtsUserTagPageListVdo extends PageBase {

    private List<String> allMatch;

    private List<String> anyMatch;

    private List<String> notMatch;

}
