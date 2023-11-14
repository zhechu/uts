package com.yly.uts.dao;

import com.yly.uts.core.model.UtsTag;
import com.yly.uts.core.model.UtsUserTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UtsUserTagDao {

	int batchSave(List<UtsUserTag> utsUserTags);

	int batchDelete(List<UtsUserTag> utsUserTags);

}
