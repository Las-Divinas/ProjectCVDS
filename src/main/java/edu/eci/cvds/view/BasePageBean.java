package edu.eci.cvds.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.google.inject.Injector;


public abstract class BasePageBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Injector injector;

    public Injector getInjector(){
        if(injector==null){
            ServletContext servlet = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            injector = (Injector) servlet.getAttribute(Injector.class.getName());
        }
        return injector;
    }

    public void setInyector(Injector injector){
        this.injector=injector;
    }

    @PostConstruct
    public void init(){
        getInjector().injectMembers(this);
    }
}
    
