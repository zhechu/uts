package com.yly.uts.service;

import com.yly.uts.core.model.UtsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户标签服务
 *
 * @author lingyuwang
 * @date 2023-11-14 4:48 上午
 * @since 0.0.1
 */
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
