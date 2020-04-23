package com.Kotori.web;

import com.opensymphony.xwork2.ActionSupport;

public class locationAction extends ActionSupport {
    public String locateLeftPage() {
        return "LOCATE_LEFT_PAGE";
    }

    public String locateTopPage() {
        return "LOCATE_TOP_PAGE";
    }

    public String locateAccountPage() {
        return "LOCATE_ACCOUNT_PAGE";
    }

    public String locateAddArticlePage() {
        return "LOCATE_ADD_ARTICLE_PAGE";
    }
}
