package com.dsys.api.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.base.User;
import java.util.List;
import java.util.Map;

/**     
 * @discription ${在此输入一句话描述此文件的作用}
 * @author shilp       
 * @created 2020/4/24  16:37
 * @Param 
 * @Return 
*/
public interface IUserService extends IService<User>{
	
	public boolean updateUser (User user);
	
	public IPage<User> getUserList (Page<User> page, User user);

	
	/**
	 * @discription 判断用户是否登陆
	 * @author shilp
	 * @created 2019年12月8日 下午9:43:31
	 * @update 2019年12月8日 下午9:43:31
	 * @param user
	 * @return
	*/
	public boolean isLogin (User user);
	
	/**
	 *
	 * @discription 通过设置的用户字段进行用户查询
	 * @author shilp
	 * @created 2019年12月16日 上午10:39:07
	 * @update 2019年12月16日 上午10:39:07
	 * @param account 账号/用户名/邮箱/手机号 pwd密码
	 * @return
	 */
	public User findUser (String account,String pwd);

	
	/**
	 * @discription 添加用户
	 * @author shilp
	 * @created 2019年12月10日 下午9:31:11
	 * @update 2019年12月10日 下午9:31:11
	 * @param user
	 * @return
	*/
	public boolean addUser (User user);

	
	/**
	 * @discription 通过ID获取用户
	 * @author shilp
	 * @created 2019年12月11日 下午8:37:24
	 * @update 2019年12月11日 下午8:37:24
	 * @param userId
	 * @return
	*/
	public User findUserById (String userId);

	
	/**
	 * @discription 通过用户名获取用户
	 * @author shilp
	 * @created 2019年12月11日 下午8:44:13
	 * @update 2019年12月11日 下午8:44:13
	 * @param userName
	 * @return
	*/
	public User findByUsername (String userName);

	
	/**
	 * @discription 校验用户名是否存在
	 * @author shilp
	 * @created 2019年12月15日 上午2:10:24
	 * @update 2019年12月15日 上午2:10:24
	 * @param account
	 * @return
	*/
	public boolean accountIsExist (String account);

	/**
	 * @discription 校验手机号是否存在
	 * @param phoneNumber
	 * @return
	 */
    boolean existPhone (String phoneNumber);

    public Page<User> selectPageUser (Page<User> page,User user);
}
