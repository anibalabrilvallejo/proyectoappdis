package proyectoappdis.bussiness;

/* librerias necesarias para el funcionamiento de esta clase
 * */
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import proyectoappdis.dao.CuentoDao;
import proyectoappdis.model.Cuento;
import proyectoappdis.model.Persona;

/*esta clase sirve para persistir los datos en la base*/
@Stateless
public class CuentoBussiness {
	
	@Inject
	private CuentoDao dao;
	
	/*metodo que permite validar al momento de ingresar un cuento*/
	public boolean save(Cuento cuento) throws Exception {
		Cuento aux = dao.read(cuento.getCodigo());
		if(aux != null)
			throw new Exception("cuento no registrado");
		else
			dao.insert(cuento);
		return true;
	}
	
	/*metodo que permite obtener un listado de los cuentos almacenados
	 * en la base de datos*/
	public List<Cuento> getListadoCuentos(){
		return dao.getCuentos();
	}
	
	/*metodo que permite eliminar un cuento dado un codigo*/
	public void eliminar(int codigo) throws Exception {
		Cuento aux = dao.read(codigo);
		if(aux==null)
			throw new Exception("cuento no registrado");
			
		else
			dao.remove(codigo);
	}
	
	/*metodo que me permite actualizar un cuento */
	public void actualizar(Cuento cuento) throws Exception {
		Cuento aux=dao.read(cuento.getCodigo());
		if(aux==null)
			throw new Exception("cuento no existe");
		else
			dao.update(cuento);
	}

	/*metodo que me permite obetener un cuento dado un codigo en especifico */
	public Cuento getCuento(int codigo) throws Exception {
		Cuento aux = dao.read(codigo);
		if(aux==null)
			throw new Exception("cuento no existe");
		else
			return aux;
	}
	
	/*metodo que me permite obtener el contenido del cuento*/
	public List<Cuento> getContenido(String titulo){
		return dao.getContenidoCuentos(titulo);
	}
}
