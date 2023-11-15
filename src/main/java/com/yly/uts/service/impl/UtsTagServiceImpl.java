package com.yly.uts.service.impl;

import com.yly.uts.core.model.UtsTag;
import com.yly.uts.core.model.UtsUserTag;
import com.yly.uts.dao.UtsTagDao;
import com.yly.uts.dao.UtsUserTagDao;
import com.yly.uts.service.UtsTagService;
import com.yly.uts.service.UtsUserTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtsTagServiceImpl implements UtsTagService {

	@Resource
	private UtsTagDao utsTagDao;

	@Override
	public int batchSave(List<String> utsTags) {
		if (CollectionUtils.isEmpty(utsTags)) {
			return 0;
		}

		// 获取还未添加到标签库的标签
		List<UtsTag> tagRecords = utsTagDao.listByTagNames(utsTags);
		List<String> tagNameRecords = tagRecords.stream().map(tagRecord -> tagRecord.getTagName()).collect(Collectors.toList());
		utsTags = utsTags.stream().filter(tag -> !tagNameRecords.contains(tag)).collect(Collectors.toList());

		// 批量添加标签，若已存在，则跳过
		List<UtsTag> tags = utsTags.stream().filter(Objects::nonNull)
				.map(o -> {
					UtsTag utsTag = new UtsTag();
					utsTag.setTagName(o);
					return utsTag;
				})
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(tags)) {
			return 0;
		}

		// TODO 可能存在标签名称冲突异常，可用重试解决
		return utsTagDao.batchSave(tags);
	}

}
