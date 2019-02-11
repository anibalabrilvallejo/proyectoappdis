package proyectoappdis.service;

/*Librerias que se utilizan en esta clase*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import proyectoappdis.bussiness.CuentoBussiness;
import proyectoappdis.bussiness.PersonaBussiness;
import proyectoappdis.model.Cuento;
import proyectoappdis.model.Persona;

/*clase que permite tener dentro del proyecto los web services rest*/
@Path("/personas")
public class PersonaRestService {

	/*conexion con el modelo del negocio */
	@Inject
	private PersonaBussiness pBussiness;
	
	@Inject
	private CuentoBussiness cBussiness;

	/*obtencion de las personas que estan registrados en la base de datos*/
	@Path("/listado")
	@GET
	@Produces("application/json")
	public List<Persona> getPersonas() {
		return pBussiness.getListadoPersona();
	}
	
	/*listado de cuentos previa seleccion por parte del usuario */
	@Path("/listadocuentos")
	@GET
	@Produces("application/json")
	public List<Cuento> getCuentos() {
		return cBussiness.getListadoCuentos();
	}
	
	/*obtener personas pasandole un codigo como parametro*/
	@Path("/getPersona")
	@GET
	@Produces("application/json")
	public Persona getPersona(@QueryParam("codigo") String cedula) throws Exception {
		return pBussiness.getPersona(cedula);
	}
	
	/*metodo de autenticacion de los usuarios y admin*/
	@GET
    @Path("/login")
    @Produces("application/json")  
	@Consumes("consumes/json")  
    public List<Persona> getAdmin(@QueryParam("user") String email, @QueryParam("password") String pass)
    {
		System.out.println("web service login ");
    	RegistroRest res = new RegistroRest();
    	List<Persona> admin=null;
    	try
        {
    		admin = pBussiness.login(email, pass);
        	res.setId(1);
            res.setDescripcion("Login Exitoso!!!");
        }
    	 catch(Exception e)
        {
    		 res.setId(0);
             res.setDescripcion("Error al Ingresar!!!");
        }
    	return admin;
    }
}
