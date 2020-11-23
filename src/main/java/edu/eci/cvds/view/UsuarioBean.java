package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.services.ExceptionHistorialDeEquipos;
import edu.eci.cvds.samples.services.ServicioUsuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean extends BasePageBean{
    @Inject
    private ServicioUsuario servicioUsuario;

    public String consultarNombreUsuario(String userID) throws ExceptionHistorialDeEquipos {
        return servicioUsuario.consultarNombreUsuario(userID);
    }
}
