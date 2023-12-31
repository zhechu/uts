package com.yly.uts.service;

import com.yly.uts.core.model.UtsUser;

import java.util.List;

public interface UtsUserService {

	/**
	 * 分页查询用户
	 * @param allMatch
	 * @param anyMatch
	 * @param notMatch
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<UtsUser> pageList(List<String> allMatch, List<String> anyMatch, List<String> notMatch, int pageNumber, int pageSize);

}
