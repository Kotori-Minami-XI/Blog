package com.Kotori.web;

import com.Kotori.domain.User;
import com.Kotori.service.LoginService;
import com.Kotori.service.impl.LoginServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    private LoginService loginService;

    public void setUser(User user) {
        this.user = user;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public User getModel() {
        return user;
    }

    public String login(){
        User curUser = loginService.login(user);
        if (null == curUser){
            this.addActionError("用户或者密码错误");
            return "LOGIN_FAIL";
        } else {
            ActionContext.getContext().getSession().put("curUser", curUser);
            return "LOGIN_SUCCESS";
        }
    }

    public String logout(){
        // Clear stored user info in session
        if (null != ActionContext.getContext().getSession().get("curUser")) {
            ActionContext.getContext().getSession().remove("curUser");
        }
        return "LOGOUT_SUCCESS";
    }


}
