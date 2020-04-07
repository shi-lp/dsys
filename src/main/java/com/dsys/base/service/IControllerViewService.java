package com.dsys.base.service;

import com.dsys.base.bean.ControllerView;
import java.util.List;

/**
 * Title: IControllerView
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/1/19 10:27
 */
public interface IControllerViewService {

    boolean addCvm(ControllerView cv);

    List<ControllerView> findAll(ControllerView cv);
}
