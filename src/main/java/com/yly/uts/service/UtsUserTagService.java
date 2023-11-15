package com.yly.uts.service;

import java.util.List;

public interface UtsUserTagService {

	/**
	 * 更新用户标签
	 * @param userIds
	 * @param addTags
	 * @param removeTags
	 * @return 打标签用户的数量
	 */
	int updateUserTag(List<Integer> userIds, List<String> addTags, List<String> removeTags);

	/**
	 * 添加用户标签
	 * @param userIds
	 * @param addTags
	 * @return 打标签用户的数量
	 */
	int addUserTag(List<Integer> userIds, List<String> addTags);

	/**
	 * 移除用户标签
	 * @param userIds
	 * @param removeTags
	 * @return 移除标签用户的数量
	 */
	int removeUserTag(List<Integer> userIds, List<String> removeTags);

}
