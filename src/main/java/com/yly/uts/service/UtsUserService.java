package com.yly.uts.service;

import com.yly.uts.core.model.UtsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户服务
 *
 * @author lingyuwang
 * @date 2023-11-14 4:48 上午
 * @since 0.0.1
 */
public interface UtsUserService {

	/**
	 * 分页查询用户
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	List<UtsUser> pageList(@Param("offset") int offset,
						   @Param("pagesize") int pagesize);



}
