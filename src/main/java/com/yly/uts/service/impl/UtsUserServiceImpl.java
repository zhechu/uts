package com.yly.uts.service.impl;

import com.yly.uts.core.model.UtsUser;
import com.yly.uts.dao.UtsUserDao;
import com.yly.uts.service.UtsUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务实现
 *
 * @author lingyuwang
 * @date 2023-11-14 4:51 上午
 * @since 0.0.1
 */
@Service
public class UtsUserServiceImpl implements UtsUserService {

	@Resource
	private UtsUserDao utsUserDao;

	@Override
	public List<UtsUser> pageList(List<String> allMatch, List<String> anyMatch, List<String> notMatch, int pageNumber, int pageSize) {
		int offset = (pageNumber - 1) * pageSize;
		return utsUserDao.pageList(allMatch, anyMatch, notMatch, offset, pageSize);
	}

}
