package com.dsys.base.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Title: ControllerView
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/1/19 10:22
 */
@Data
public class ControllerView {

    // 通用Mapper插入后返回主键
    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    private String driveType;

    private String requestUrl;

    private String responseView;

    private String doFlag;

    private String createUser;

    private Timestamp createTime;

    private String updateUser;

    private Timestamp updateTime;

}
