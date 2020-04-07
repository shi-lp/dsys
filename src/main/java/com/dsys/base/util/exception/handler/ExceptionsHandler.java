package com.dsys.base.util.exception.handler;

import com.dsys.base.util.exception.CustomGenericException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Title: ExceptionsHandler
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/3/18 10:51
 */
public class ExceptionsHandler {

    /**
     * 可以直接写@ExceptionHandler,不指明异常类，会自动映射
     * 还可以声明接收其他任意参数
     * @param exception
     * @return
     */
    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView customGenericExceptionHnadler(CustomGenericException exception){
        ModelAndView modelAndView = new ModelAndView("generic_error");
        modelAndView.addObject("errCode",exception.getErrCode());
        modelAndView.addObject("errMsg",exception.getErrMsg());
        return modelAndView;
    }

    /**
     * 可以直接写@EceptionHandler，IOExeption继承于Exception
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView allExceptionHandler(Exception exception){
        ModelAndView modelAndView = new ModelAndView("generic_error");
        modelAndView.addObject("errMsg", "this is Exception.class");
        return modelAndView;
    }

}
