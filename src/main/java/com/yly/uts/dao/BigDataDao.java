package com.yly.uts.dao;

import com.yly.uts.core.model.BigData;
import com.yly.uts.core.model.UtsTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BigDataDao {

	List<BigData> selectAllId(@Param("tableName") String tableName);

	BigData getById(
			@Param("tableName") String tableName,
			@Param("id") String id
	);

	int updateById(
			@Param("tableName") String tableName,
			@Param("id") String id,
			@Param("blockDataJsonGzip") byte[] blockDataJsonGzip
	);

	int updateBlockDataJsonById(
			@Param("tableName") String tableName,
			@Param("id") String id,
			@Param("blockDataJson") String blockDataJson
	);

}
