package com.dsys.base.util.controller;

import com.dsys.base.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Title: BaseController
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: Controller工具类
 * @created 2020/1/20 14:22
 */
@Slf4j
public class BaseController extends ApplicationObjectSupport {

    @Value("${project.version}")
    public String ver;

    protected ModelAndView go(String path) {
        return new ModelAndView(path);
    }

    protected Object getObject(String name) {
        return this.getApplicationContext().getBean(name);
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public HttpServletResponse getResponse() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取国际化信息
     *
     * @param key
     * @return
     */
    public String getI18nMsg(String key) throws Exception {
        // 从后台代码获取国际化信息
        String value = new RequestContext(this.getRequest()).getMessage(key);
        return value != null ? value : "";
    }

    /**
     * 返回国际化国家标识串
     *
     * @return
     * @throws Exception
     */
    public String getI18nCountry() throws Exception {
        String lc = null;
        RequestContext requestContext = new RequestContext(this.getRequest());
        String language = requestContext.getLocale().getLanguage();
        String country = requestContext.getLocale().getCountry();
        if (country == null || "".equals(country)) {
            if ("zh".equals(language)) {
                country = "CN";
            } else if ("en".equals(language)) {
                country = "US";
            }
        }
        // logger.debug("本地国际化语言：" + language + "本地国际化国家：" + country);
        lc = "_" + language + (StringUtils.isBlank(country) ? "" : "_" + country);
        return lc;
    }

    /**
     * 请求方式判断
     *
     * @param request
     * @return
     */
    public boolean isAjaxRequest(HttpServletRequest request) {
        if (!(request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)
                || "XMLHttpRequest".equalsIgnoreCase(request.getParameter("X_REQUESTED_WITH")))) {
            return false;
        }
        return true;
    }

    /**
     * 保存信息到request中
     *
     * @param key
     * @param value
     */
    public void setRequestAttribute(String key, Object value) {
        this.getRequest().setAttribute(key, value);
    }

    protected String getCookie(String cookieName) {
        Cookie[] cookies = getRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 设置 cookie
     *
     * @param cookieName
     * @param value
     * @param age
     */
    protected void setCookie(String cookieName, String value, int age) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(age);
        // cookie.setHttpOnly(true);
        getResponse().addCookie(cookie);
    }

    /**
     * 方法名称: render<br>
     * 描述：返回给浏览器
     */
    public void render(String text, String contentType){
        HttpServletResponse response;
        try{
            response = getResponse();
            response.setContentType(contentType);
            response.getWriter().write(text);
            response.getWriter().flush();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 方法名称: getCurrentLoginGeCustomer<br>
     * 描述：获取当前登录用户<br>
     *     保全、登录
     * 作者: <br>
     * 修改日期：2014-9-30下午07:06:26<br>
     */
    /*protected GeCustomer getCurrentLoginGeCustomer() {
        Object obj = this.getRequest().getSession().getAttribute(UserPersonalConstant.LOGIN_USER);
        if (obj instanceof GeCustomer) {
            return (GeCustomer) obj;
        } else {
            return null;
        }
    }

    *//**
     * 方法名称: getCurrentLoginGeCustomerProperty<br>
     * 描述：获取当前登录用户属性信息<br>
     * 作者: <br>
     * 修改日期：2014-9-30下午07:06:26<br>
     *//*
    protected GeCustomerProperty getCurrentLoginGeCustomerProperty() {
        Object obj = this.getRequest().getSession().getAttribute(UserPersonalConstant.LOGIN_USER_PROPERTY);
        if (obj instanceof GeCustomerProperty) {
            return (GeCustomerProperty) obj;
        } else {
            return null;
        }
    }*/

    /**
     * 方法名称：initCurrentLoginGeCustomer<br>
     * 描述：存放登录用户信息<br>
     * 作者：<br>
     * 修改日期：2014年8月18日下午7:57:07<br>
     */
    /*protected void initCurrentLoginGeCustomer() {
        Object obj = this.getRequest().getSession().getAttribute(UserPersonalConstant.LOGIN_USER);
        if (obj != null && obj instanceof GeCustomer) {
            // 有登陆用户，带登陆信息到填写投保信息页
            this.setRequestAttribute("geCustomer", (GeCustomer) obj);
        }
    }*/

    /**
     * 方法名称: renderText<br>
     * 描述：返回普通文本浏览器
     */
    public void renderText(String text){
        render(text, "text/plain;charset=UTF-8");
    }

}
