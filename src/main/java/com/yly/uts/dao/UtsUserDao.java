package com.yly.uts.dao;

import com.yly.uts.core.model.UtsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UtsUserDao {

    List<UtsUser> pageList(@Param("allMatch") List<String> allMatch,
						   @Param("anyMatch") List<String> anyMatch,
						   @Param("notMatch") List<String> notMatch,
						   @Param("offset") int offset,
						   @Param("pageSize") int pageSize);

}
