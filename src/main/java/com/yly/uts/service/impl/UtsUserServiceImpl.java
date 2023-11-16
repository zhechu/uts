package com.yly.uts.service.impl;

import com.yly.uts.core.model.UtsUser;
import com.yly.uts.dao.UtsUserDao;
import com.yly.uts.service.UtsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtsUserServiceImpl implements UtsUserService {

	@Autowired
	private UtsUserDao utsUserDao;

	@Override
	public List<UtsUser> pageList(List<String> allMatch, List<String> anyMatch, List<String> notMatch, int pageNumber, int pageSize) {
		int offset = (pageNumber - 1) * pageSize;
		return utsUserDao.pageList(allMatch, anyMatch, notMatch, offset, pageSize);
	}

}
