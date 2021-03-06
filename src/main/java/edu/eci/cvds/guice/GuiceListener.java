package edu.eci.cvds.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.LaboratoryDAO;
import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.sampleprj.dao.UsuarioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisElementoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisEquipoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisLaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisNovedadDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisUsuarioDAO;
import edu.eci.cvds.samples.services.ServicioElement;
import edu.eci.cvds.samples.services.ServicioEquipment;
import edu.eci.cvds.samples.services.ServicioLaboratory;
import edu.eci.cvds.samples.services.ServicioNovelty;
import edu.eci.cvds.samples.services.ServicioUsuario;
import edu.eci.cvds.samples.services.impl.ServicioElementImpl;
import edu.eci.cvds.samples.services.impl.ServicioEquipmentImpl;
import edu.eci.cvds.samples.services.impl.ServicioLaboratoryImpl;
import edu.eci.cvds.samples.services.impl.ServicioNoveltyImpl;
import edu.eci.cvds.samples.services.impl.ServicioUsuarioImpl;
import edu.eci.cvds.security.Logger;
import edu.eci.cvds.security.LoggerApacheShiro;
import edu.eci.cvds.view.BasePageBean;
import edu.eci.cvds.view.LoginBean;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.MySQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");
                bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
                bind(LaboratoryDAO.class).to(MyBatisLaboratorioDAO.class);
                bind(EquipoDAO.class).to(MyBatisEquipoDAO.class);
                bind(ElementoDAO.class).to(MyBatisElementoDAO.class);
                bind(NovedadDAO.class).to(MyBatisNovedadDAO.class);
                bind(BasePageBean.class).to(LoginBean.class);
                bind(Logger.class).to(LoggerApacheShiro.class);
                bind(ServicioUsuario.class).to(ServicioUsuarioImpl.class);
                bind(ServicioLaboratory.class).to(ServicioLaboratoryImpl.class);
                bind(ServicioNovelty.class).to(ServicioNoveltyImpl.class);
                bind(ServicioElement.class).to(ServicioElementImpl.class);
                bind(ServicioEquipment.class).to(ServicioEquipmentImpl.class);
            }
        });

        servletContextEvent.getServletContext().setAttribute(Injector.class.getName(), injector);
    }

    
}
