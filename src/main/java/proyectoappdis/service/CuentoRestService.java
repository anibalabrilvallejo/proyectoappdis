package proyectoappdis.service;

import java.util.List;

/*librerias necesarias para el funcionamiento de esta clase que
 * sirve para el consumo de los web service*/
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import proyectoappdis.bussiness.CuentoBussiness;
import proyectoappdis.model.Cuento;

@Path("/cuentos")
public class CuentoRestService {
	/*variable que sirve para instanciar el modelo de negocio */
	@Inject
	private CuentoBussiness cBussiness;
	
	/*metodo del web service que sirve para obtener cuentos este metodo recibe un parametro
	 * para poder buscar el cuento seleccionado */
	@Path("/getCuento")
	@GET
	@Produces("application/json")
	public Cuento getContenidoCuento(@QueryParam("codigo") int codigo) throws Exception {
		return cBussiness.getCuento(codigo);
	}
	
	/*obtener el contenido de un cuento seleccionado*/
	@Path("/getContenido")
	@GET
	@Produces("application/json")
	public List<Cuento> getContenido(@QueryParam("titulo") String titulo) throws Exception {
		return cBussiness.getContenido(titulo);
	}
}
