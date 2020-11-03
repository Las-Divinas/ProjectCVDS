package edu.eci.cvds.view;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import edu.eci.cvds.samples.entities.Usuario;

@ManagedBean(name = "adminBean")
@SessionScoped
public class adminPageBean extends BasePageBean{

    private Usuario usuario;
    private static final long serialVersionUID = 4019951654721141025L;

    public void redirectCreateEquipment() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("addEquipment.xhtml");
        return;
    }

    public void redirectCreateElement() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../addElement.xhtml");
        return;
    }

    public void redirectCreateNovelty() throws IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("../addNovelty.xhtml");
        return;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Usuario getUsuario(){
        return usuario;
    }
    
}
