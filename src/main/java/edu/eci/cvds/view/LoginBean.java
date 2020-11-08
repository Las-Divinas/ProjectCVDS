package edu.eci.cvds.view;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.security.Logger;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BasePageBean{

    private static final long serialVersionUID = -5223360388656378877L;
    /*
     * @Inject private UserService service;
     */
    @Inject
    private Logger logger;
    private String email;
    private String password;
    private Boolean remember;
    private String message;

    public void login() throws IOException, ExceptionHistorialDeEquipos{
        System.out.println("Hola entre en el login");
        boolean isLogger = logger.isLogged();
        if(!isLogger){
            System.out.println("Hola entre 2");
            logger.login(email, password, false);
            redirectHomeUser();
        } else{
            existingSession();
        }
    }

    public void redirectHomeUser() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(logger.isAdmin()){
            System.out.println("Entre a admin");
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("email", email);
            facesContext.getExternalContext().redirect("../admin/admin.xhtml");
        }
        if(logger.isUser()){
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("email", email);
            facesContext.getExternalContext().redirect("../public/user.xhtml");
        }
    }

    public void existingSession() throws IOException{
        this.message = "Another user with those credentials already exists";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("");
    }

    public void register() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../Register.xhtml");
    }

    public void logout() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../Login.xhtml");
        logger.logout();
    }

    public void comeBack() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(logger.isAdmin()){
            facesContext.getExternalContext().redirect("../admin/admin.xhtml");
        }
        if(logger.isUser()){
            facesContext.getExternalContext().redirect("../public/user.xhtml");
        }
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public boolean getRemember(){
        return remember;
    }

    public void setRemember(boolean remember){
        this.remember = remember;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
