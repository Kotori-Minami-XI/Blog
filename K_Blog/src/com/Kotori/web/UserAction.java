package com.Kotori.web;

import com.Kotori.domain.User;
import com.Kotori.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.net.httpserver.HttpContext;
import org.apache.commons.fileupload.RequestContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ActionError;
import org.apache.struts2.dispatcher.HttpParameters;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
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

    public String addUser() {
        // Typical way
        String userPwd = ServletActionContext.getRequest().getParameter("firstPwd");
        this.user.setPassword(userPwd);
        this.userService.addUser(this.user);
        return "USER_ADD_SUCCESS";
    }

    public String updateUser() {
        String oldPwd = ServletActionContext.getRequest().getParameter("oldPwd");
        String newPwd = ServletActionContext.getRequest().getParameter("firstPwd");

        /* Step 1: Query User by id to verify if the old password given by the
                   request exactly matches current password in database */
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("id", this.user.getId()));
        User targetUser = this.userService.getUser(detachedCriteria);

        if (!targetUser.getPassword().equals(oldPwd)) {
            ActionContext.getContext().getSession().put("USER_ACTION_ERROR", "密码不正确！");
            // ActionContext.getContext().getValueStack().set("USER_ACTION_ERROR", "密码不正确！");
            // ServletActionContext.getRequest().setAttribute("USER_ACTION_ERROR", "密码不正确！");
            return "USER_UPDATE_FAILURE";
        }

        /* Step 2: Update user info in database */
        this.user.setId(targetUser.getId());
        this.user.setPassword(newPwd);
        this.userService.updateUser(user);

        return "USER_UPDATE_SUCCESS";
    }
}
