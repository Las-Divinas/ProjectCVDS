package edu.eci.cvds.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ValidacionSesion extends AccessControlFilter{
    String welcomeurl;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        Subject subject = getSubject(request, response);
        return !subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception{
        welcomeurl = "/Login.xhtml";
        WebUtils.issueRedirect(request, response, welcomeurl);
        return false;
    }

    public String getWelcomeurl() {
        return welcomeurl;
    }

    public void setWelcomeurl(String welcomeurl) {
        this.welcomeurl = welcomeurl;
    }
}
