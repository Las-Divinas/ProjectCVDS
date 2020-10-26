package edu.eci.cvds.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

public class LoggerApacheShiro implements Logger {

    @Override
    public void login(String email, String password, boolean remember) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(email, new Sha256Hash(password).toHex(), remember);
            subject.getSession().setAttribute("Email", email);
            subject.login(token);
        } catch (Exception e) {
            /* Crear excepciones */
        }

    }

    @Override
    public void logout() {
        SecurityUtils.getSubject().logout();

    }

    @Override
    public boolean isLogged() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
    
}
