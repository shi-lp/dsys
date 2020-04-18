package com.dsys.base.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.dsys.common.model.BaseModel;
import lombok.Data;

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
@TableName(value="bp_controllerview_tb")
public class ControllerView extends BaseModel{
    // 通用Mapper插入后返回主键
    @TableId(value = "id",type = IdType.INPUT)
    private Long sId;

    private String driveType;

    private String requestUrl;

    private String responseView;

    private String doFlag;

}
