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

	/**
	 * 分页查询用户
	 * @param offset
	 * @param pagesize
	 * @return
	 */
    List<UtsUser> pageList(@Param("offset") int offset,
                           @Param("pagesize") int pagesize);

}
