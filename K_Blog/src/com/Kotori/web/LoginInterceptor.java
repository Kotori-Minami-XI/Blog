package com.Kotori.web;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class LoginInterceptor extends MethodFilterInterceptor {
    /***
     * @brief An interceptor that verifies if user has already logged in. Return LOGIN_FAIL
     *        if current user does not logged in, otherwise permit the method to go through
     *        the interceptor
     * @param actionInvocation
     * @return Result for the current method trying to pass the interceptor
     * @throws Exception
     */
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Object user = ServletActionContext.getContext().getSession().get("curUser");
        if (null == user) {
            ActionSupport actionSupport = (ActionSupport)actionInvocation.getAction();
            actionSupport.addActionError("请先登录！");
            return "LOGIN_FAIL";
        }
        return actionInvocation.invoke();
    }
}
