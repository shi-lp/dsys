package com.dsys.api.bean.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value="bp_controllerview_tb")
public class ControllerView extends BaseModel implements Serializable{
    // 通用Mapper插入后返回主键
    @TableId(value = "id",type = IdType.INPUT)
    private Long sId;

    private String driveType;

    private String requestUrl;

    private String responseView;

    private String doFlag;

}
