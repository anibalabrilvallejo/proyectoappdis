package proyectoappdis.dao;

/* librerias necesarias para el funcionamiento de esta clase
 * */
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import proyectoappdis.model.Cuento;
/*esta clase sirve para persistir los datos en la base*/
@Stateless
public class CuentoDao {
	
	@Inject
	private EntityManager em;
	
	/*metodo para insertar los datos en la base */
	public void insert(Cuento cuento) {
		em.persist(cuento);
	}
	
	/*metodo para actualizar los datos en la base */
	public void update(Cuento cuento) {
		em.merge(cuento);
	}
	
	/*metodo para eliminar los datos en la base */
	public void remove(int id) {
		em.remove(read(id));
	}
	
	/*Metodo que me permite leer un cuento dado un codigo
	 * parametro
	 * */
	public Cuento read(int codigo) {
		Cuento aux=em.find(Cuento.class,codigo);
		return aux;
	}
	
/*Metodo que me obtiene todos los cuentos ingresados en la base de datos*/
	public List <Cuento>getCuentos(){
		String jpql="SELECT c FROM Cuento c";
		Query query=em.createQuery(jpql,Cuento.class);
		List <Cuento>lista=query.getResultList();
		return lista;
	}
	/*metodo para obtener el contenido del cuento*/
	
	public List<Cuento> getContenidoCuentos(String titulo) 
	{
		System.out.println("titulo!!!!  "+titulo);
		String sql = "SELECT c FROM Cuento c WHERE c.titulo = '"+titulo+"'";
		TypedQuery<Cuento> query = em.createQuery(sql, Cuento.class);
		List<Cuento> admin = query.getResultList();
		return admin;
	}
}
