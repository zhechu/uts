package com.yly.uts.service.impl;

import com.yly.uts.core.model.UtsTag;
import com.yly.uts.core.model.UtsUserTag;
import com.yly.uts.dao.UtsTagDao;
import com.yly.uts.dao.UtsUserTagDao;
import com.yly.uts.service.UtsUserTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户服务实现
 *
 * @author lingyuwang
 * @date 2023-11-14 4:51 上午
 * @since 0.0.1
 */
@Service
@Slf4j
public class UtsUserTagServiceImpl implements UtsUserTagService {

	@Resource
	private UtsTagDao utsTagDao;

	@Resource
	private UtsUserTagDao utsUserTagDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateUserTag(List<Integer> userIds, List<String> addTags, List<String> removeTags) {
		if (CollectionUtils.isEmpty(userIds)) {
			return 0;
		}

		if (!CollectionUtils.isEmpty(addTags)) {
			// 获取还未添加到标签库的标签
			List<UtsTag> tagRecords = utsTagDao.listByTagNames(addTags);
			List<String> tagNameRecords = tagRecords.stream().map(tagRecord -> tagRecord.getTagName()).collect(Collectors.toList());
			addTags = addTags.stream().filter(tag -> !tagNameRecords.contains(tag)).collect(Collectors.toList());

			// 批量添加标签，若已存在，则跳过
			List<UtsTag> tags = addTags.stream().filter(Objects::nonNull)
					.map(o -> {
						UtsTag utsTag = new UtsTag();
						utsTag.setTagName(o);
						return utsTag;
					})
					.collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(tags)) {
				int batchSaveResult = utsTagDao.batchSave(tags);
				log.info("batchSaveResult:{}", batchSaveResult);
			}
		}

		// 批量添加用户标签，若已存在，则跳过
		int addUserTagResult = addUserTag(userIds, addTags);
		log.info("addUserTagResult:{}", addUserTagResult);

		// 批量移除用户标签
		int removeUserTagResult = removeUserTag(userIds, removeTags);
		log.info("removeUserTagResult:{}", removeUserTagResult);

		return userIds.size();
	}

	@Override
	public int addUserTag(List<Integer> userIds, List<String> addTags) {
		if (CollectionUtils.isEmpty(userIds) || CollectionUtils.isEmpty(addTags)) {
			return 0;
		}

		List<UtsTag> tagRecords = utsTagDao.listByTagNames(addTags);
		if (CollectionUtils.isEmpty(tagRecords)) {
			return 0;
		}

		List<UtsUserTag> utsUserTags = getUtsUserTags(userIds, tagRecords);
		if (CollectionUtils.isEmpty(utsUserTags)) {
			return 0;
		}

		return utsUserTagDao.batchSave(utsUserTags);
	}

	@Override
	public int removeUserTag(List<Integer> userIds, List<String> removeTags) {
		if (CollectionUtils.isEmpty(userIds) || CollectionUtils.isEmpty(removeTags)) {
			return 0;
		}

		List<UtsTag> tagRecords = utsTagDao.listByTagNames(removeTags);
		if (CollectionUtils.isEmpty(tagRecords)) {
			return 0;
		}

		List<UtsUserTag> utsUserTags = getUtsUserTags(userIds, tagRecords);
		if (CollectionUtils.isEmpty(utsUserTags)) {
			return 0;
		}

		return utsUserTagDao.batchDelete(utsUserTags);
	}


	private List<UtsUserTag> getUtsUserTags(List<Integer> userIds, List<UtsTag> utsTags) {
		List<UtsUserTag> utsUserTags = new ArrayList<>(userIds.size() * utsTags.size());
		userIds.forEach(userId -> {
			utsTags.forEach(tagRecord -> {
				UtsUserTag utsUserTag = new UtsUserTag();
				utsUserTag.setUserId(userId);
				utsUserTag.setTagId(tagRecord.getId());
				utsUserTags.add(utsUserTag);
			});
		});
		return utsUserTags;
	}

}
