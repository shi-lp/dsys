package com.dsys.base.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.dsys.common.model.BaseModel;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * ClassName: User
 * 
 * @Description: 用户表
 * @author shilp
 * @date 2019年11月5日
 */
@Data
/**     
 * @discription 使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数/
*/
@AllArgsConstructor
/**
 * 使用后创建一个无参构造函数
 */
@NoArgsConstructor
public class User extends BaseModel{

	// 主键
	/**
	 * @discription / 通用Mapper插入后返回主键
	*/
	@TableId(value = "id",type = IdType.INPUT)
	private String sId;

	// 名称
	private String userName;

	// 密码
	private String pwd;

	// 账号
	private String account;

	// 手机
	private String mobilePhone;

	// 固话
	private String phone;

	// 在线状态
	private String onlineStatus;

	// 登录IP
	private String ip;

	// 邮件信息存储
	private String eMail;

	// 当前信息是否生效 1、生效 0、未生效 2、被删除
	private String doFlag;

	// 主题风格
	private String themeType;

	// 设备类型
	private String deviceType;

	// 优先级
	private String level;

	
	@TableField(exist = false)
	private String token;

}
