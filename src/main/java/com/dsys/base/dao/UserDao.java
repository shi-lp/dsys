package com.dsys.base.dao;


import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import com.dsys.base.bean.User;

public interface UserDao extends BaseMapper<User> {

	public void addUser(User user);

	public User findUserById(String userId);

	public User findByUsername(String userName);

	public int accountIsExist(String account);

	public List<User> findUser(@Param("user") User user);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

    int existPhone(String phoneNumber);

	List<User> selectUserList(Pagination page, User user);
}
