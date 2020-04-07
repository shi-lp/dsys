package com.dsys.base.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dsys.base.bean.User;
import com.dsys.base.dao.UserDao;
import com.dsys.base.service.IUserService;

@Primary
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean addUser(User user) {
		userDao.addUser(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		return false;
	}

	@Override
	public List<User> getUserList(Map<String, Object> params) {
		return null;
	}

	@Override
	public boolean isLogin(User user) {

		return false;
	}

	@Override
	public User findUserById(String userId) {
		User user = userDao.findUserById(userId);
		return user;
	}

	@Override
	public User findByUsername(String userName) {
		User user = userDao.findByUsername(userName);
		return user;
	}

	@Override
	public boolean accountIsExist(String account) {
		int i = userDao.accountIsExist(account);
		if (i > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean existPhone(String phoneNumber) {
		if(userDao.existPhone(phoneNumber) > 0){
			return true;
		}
		return false;
	}

	@Override
	public Page <User> selectPageUser(Page <User> page, User user) {
		page.setRecords(userDao.selectUserList(page,user));
		return page;
	}


	@Override
	public List<User> findUser(User user) {
		return userDao.findUser(user);
	}

}
