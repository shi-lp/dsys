package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.User;
import com.dsys.api.common.enums.DoFlagEnum;
import com.dsys.api.service.base.IUserService;
import com.dsys.base.mapper.UserMapper;
import com.dsys.common.util.Constants;
import com.dsys.common.util.StringUtils;
import com.dsys.common.util.ToolUtil;
import java.util.List;
import java.util.Map;

import org.apache.dubbo.common.utils.NetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**     
 * @discription 用户实现
 * @author shilp       
 * @created 2020/4/13  9:26
 * @Param 
 * @Return
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean addUser(User user) {
		user.setIp(NetUtils.getLocalAddress().toString());
		user.setDoFlag(DoFlagEnum.ENABLED);
		user.setOnlineStatus(Constants.USER_DISONLINE);
		userMapper.insert(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		return false;
	}

	@Override
	public IPage<User> getUserList(Page<User> page, User user) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.like(!StringUtils.isBlank(user.getAccount()),User::getAccount,user.getAccount())
				.like(!StringUtils.isBlank(user.getUserName()),User::getUserName,user.getUserName())
				.eq(!ToolUtil.isNullOrEmpty(user.getDoFlag()),User::getDoFlag,user.getDoFlag());
		IPage<User> userList = userMapper.selectPages(page,queryWrapper,user.getRoleId(),user.getDeptId());
		return userList;
	}

	@Override
	public boolean isLogin(User user) {

		return false;
	}

	@Override
	public User findUserById(String userId) {
		User user = userMapper.selectById(userId);
		return user;
	}

	@Override
	public User findByUsername(String userName) {
//		User user = userMapper.findByUsername(userName);
		return new User();
	}

	@Override
	public boolean accountIsExist(String account) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getAccount,account);
		List<User> users = userMapper.selectList(queryWrapper);
		if(ToolUtil.isNullOrEmpty(users)){
			return true;
		}
		return false;
	}

	@Override
	public boolean existPhone(String phoneNumber) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getPhone,phoneNumber);
		List<User> users = userMapper.selectList(queryWrapper);
		if(ToolUtil.isNullOrEmpty(users)){
			return true;
		}
		return false;
	}

	@Override
	public Page <User> selectPageUser(Page<User> page,User user) {
//		page.setRecords(userDao.selectUserList(page,user));
		return page;
	}


	@Override
	public User findUser(String account,String pwd) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getAccount,account)
				.or()
				.eq(User::getPhone,account)
				.or()
				.eq(User::getEMail,account)
				.eq(User::getPwd,pwd)
				.eq(User::getDoFlag,Constants.STATUS_ENABLE);
		List<User> users = userMapper.selectList(queryWrapper);
		if(ToolUtil.isNullOrEmpty(users)){
			return null;
		}
		return users.get(0);
	}

}
