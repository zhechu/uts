package com.yly.uts.dao;

import com.yly.uts.core.model.UtsTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UtsTagDao {

	List<UtsTag> selectAllId();

	UtsTag getById(String id);

	List<UtsTag> selectAll();

	List<UtsTag> selectBlockDataJson();

	List<UtsTag> selectBlockData();

	int updateById(String id, byte[] blockData);

	int updateBlockDataJsonById(String id, String blockDataJson);

}
