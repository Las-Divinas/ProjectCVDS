package edu.eci.cvds.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.subject.Subject;

import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;

public class LoggerApacheShiro implements Logger {

    @Override
    public void login(String email, String password, boolean remember) throws ExceptionHistorialDeEquipos{
        try {
            System.out.println("Hola entre 3");
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(email,new Sha512Hash(password).toHex(), remember);
            subject.getSession().setAttribute("correo", email);
            subject.login(token);
        } catch (UnknownAccountException e) {
            throw new ExceptionHistorialDeEquipos("The User is not registered", e);
        } catch (IncorrectCredentialsException e){
            throw new ExceptionHistorialDeEquipos("Incorrect Password Sr", e);
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

    @Override
    public boolean isAdmin() {
        return SecurityUtils.getSubject().hasRole("admin");
    }

    @Override
    public boolean isUser() {
        return SecurityUtils.getSubject().hasRole("user");
    }
    
}
