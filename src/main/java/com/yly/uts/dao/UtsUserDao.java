package com.yly.uts.dao;

import com.yly.uts.core.model.UtsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据访问
 *
 * @author lingyuwang
 * @date 2023-11-14 4:45 上午
 * @since 0.0.1
 */
@Mapper
public interface UtsUserDao {

    List<UtsUser> pageList(@Param("allMatch") List<String> allMatch,
						   @Param("anyMatch") List<String> anyMatch,
						   @Param("notMatch") List<String> notMatch,
						   @Param("offset") int offset,
						   @Param("pageSize") int pageSize);

}
