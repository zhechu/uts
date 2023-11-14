package com.yly.uts.dao;

import com.yly.uts.core.model.UtsTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UtsTagDao {

	int batchSave(List<UtsTag> utsTags);

	List<UtsTag> listByTagNames(List<String> utsTagNames);

}
