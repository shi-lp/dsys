package com.dsys.api.bean.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import java.net.InetAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 
 * ClassName: User
 * 
 * @Description: 用户表
 * @author shilp
 * @date 2019年11月5日
 */
@EqualsAndHashCode(callSuper = true)
@Data
/**     
 * @discription 使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数/
*/
@AllArgsConstructor
/**
 * 使用后创建一个无参构造函数
 */
@NoArgsConstructor
@TableName(value = "BP_USER_TB")
public class User extends BaseModel implements Serializable{

	// 主键
	/**
	 * @discription / 通用Mapper插入后返回主键
	*/
	@JSONField(serializeUsing= ToStringSerializer.class)
	@TableId(type = IdType.INPUT)
	private Long sId;

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
	private DoFlagEnum doFlag;

	// 主题风格
	private String themeType;

	// 设备类型
	private String deviceType;

	// 优先级
	private String level;

	/**非数据库字段*/
	@TableField(exist = false)
	private String token;
	@TableField(exist = false)
	private String deptId;
	@TableField(exist = false)
	private String deptName;
	@TableField(exist = false)
	private String roleId;
	@TableField(exist = false)
	private String roleName;
	
}
