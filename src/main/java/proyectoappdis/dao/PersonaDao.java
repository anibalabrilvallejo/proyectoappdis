package proyectoappdis.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import proyectoappdis.model.Persona;

//tienen automaticamnete rollback y comit 
@Stateless
public class PersonaDao {
	//conexion con la base de datos se da por el entity manager 
		//objetos gestionados  por el servidor
		
		//conexion con la base de datos por medio de jpa
		@Inject
		private EntityManager em;
		
		//cdi =inject y desinject  
		/*metodo que se utiliz para insertar los datos de la persona*/
		public void insert(Persona persona) {
			em.persist(persona);
		}
		/*metodo que se utiliz para actualizar los datos de la persona*/
		public void update(Persona persona) {
			em.merge(persona);
		}
		/*metodo que se utiliz para eliminar los datos de la persona*/
		public void remove(String id) {
			em.remove(read(id));
		}
		/*metodo que se utiliz para leer los datos de la persona 
		 * con el paso de la cedula como parametro*/
		public Persona read(String cedula) {
			Persona aux=em.find(Persona.class,cedula);
			return aux;
		}
		/*Listado de personas alamcenadas en la base de datos asi mismo retorna
		 * lista de las personas encontradas*/
		public List <Persona>getPersonas(){
			String jpql="SELECT p FROM Persona p";
			Query query=em.createQuery(jpql,Persona.class);
			List <Persona>lista=query.getResultList();
			return lista;
		}
		
		/*metodo que me recupera de la base de datos previo ingreso del usuario 
		 * y contraseña de la persona*/
		public List<Persona> login(String user, String pass) 
		{
			System.out.println("USUARIO: "+user+", Pass: "+pass);
			String sql = "SELECT p FROM Persona p WHERE p.correo = '"+user+"' AND p.contraseña='"+pass+"'";
			TypedQuery<Persona> query = em.createQuery(sql, Persona.class);
			List<Persona> admin = query.getResultList();
			//System.out.println("datos de la base  ===>>> "+admin.toString());
			return admin;
		}
}
