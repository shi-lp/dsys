package com.dsys.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.goods.GoodsSpec;
import com.dsys.api.service.goods.IGoodsSpecService;
import com.dsys.goods.mapper.GoodsSpecMapper;
import org.springframework.stereotype.Service;

/**
 * Title: GoodsSpecServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:45
 */
@Service
public class GoodsSpecServiceImpl extends ServiceImpl<GoodsSpecMapper, GoodsSpec> implements IGoodsSpecService{
}
