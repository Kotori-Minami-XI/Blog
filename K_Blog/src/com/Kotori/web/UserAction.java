package com.Kotori.web;

import com.Kotori.domain.User;
import com.Kotori.service.UserService;
import com.Kotori.service.impl.ArticleServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    private UserService userService;

    @Override
    public User getModel() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getAllUser() throws IOException {
        List<User> list = this.userService.getAllUser();
        ActionContext.getContext().getValueStack().set("userList", list);
        return "USER_OBTAIN_ALL_SUCCESS";
    }

    public String deleteUser() {
        this.userService.deleteUser(this.user);
        return "USER_DELETE_SUCCESS";
    }
}
