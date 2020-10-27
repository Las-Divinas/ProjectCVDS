package edu.eci.cvds.view;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.security.Logger;

@SuppressWarnings("deprecation")
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BasePageBean{

    private static final long serialVersionUID = -5223360388656378877L;
    /*
     * @Inject private ServiciosUsuario servicio;
     */
    @Inject
    private Logger logger;
    private String email;
    private String password;
    private Boolean remember;
    private String message;

    public void login() throws IOException, ExceptionHistorialDeEquipos{
        boolean isLogger = logger.isLogged();
        if(!isLogger){
            logger.login(email, password, false);
            /* redirect to home pa */
        } else{
            /* sesion existente */
        }
    }

    public void redirectHomeUser() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(logger.isAdmin()){
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("email", email);
            /* Implementar admin.xhtml */
            facesContext.getExternalContext().redirect("../admin/admin.xhtml");
        }
        if(logger.isUser()){
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("email", email);
            /* Implementar users.xhtml */
            facesContext.getExternalContext().redirect("../public/user.xhtml");
        }
    }

    public void existingSession() throws IOException{
        this.message = "Another user with those credentials already exists";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("");
    }

    public void logOut() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("../Login.xhtml");
        logger.logout();
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
