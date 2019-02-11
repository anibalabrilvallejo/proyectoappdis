package proyectoappdis.view;

/*librerias utilizadas en esta clase que sirve de conexion 
 * de las paginas jsf con lcas clases de java*/
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import proyectoappdis.bussiness.CuentoBussiness;
import proyectoappdis.model.Cuento;
import proyectoappdis.model.Persona;
import proyectoappdis.model.Pregunta;



@ManagedBean
//@SessionScoped
@ViewScoped
/*controlador cuento bean*/
public class CuentoBean {
	
	/**variables que son requeridas para el enviio y recepcion de los datos almacenado en la base*/
	
	@Inject
	private FacesContext facesContext;
	
	private int id;
	
	private Cuento newcuento;
	
	//private Persona persona;
	
	@Inject
	private CuentoBussiness cBussiness;
	
	private List<Cuento>cuentos;
	
	private boolean bandera;
	
	/*constructor de la clase y se ejecuta luego de iniciar el proyecto*/
	@PostConstruct
	public void init() {
		newcuento=new Cuento();
		newcuento.addPreguntas(new Pregunta());
		bandera=false;
		cuentos=cBussiness.getListadoCuentos();	
	
	}
	
	/*metodos getters and setters*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isBandera() {
		return bandera;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	public Cuento getNewcuento() {
		return newcuento;
	}
	public void setNewcuento(Cuento newcuento) {
		this.newcuento = newcuento;
	}	
	public List<Cuento> getCuentos() {
		return cuentos;
	}
	public void setCuentos(List<Cuento> cuentos) {
		this.cuentos = cuentos;
	}
	/*metodo para cargar los datos en las pagina jsf al moento de 
	 * actualizar los mismos*/
	public void loadData() {
		
		System.out.println("load data " + id);
		String num=Integer.toString(id);
		if(num==null)
			return;
		try {
			newcuento = cBussiness.getCuento(id);
			bandera = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					e.getMessage(), "Error");
            facesContext.addMessage(null, m);
		}
	}

	/*metodo que me permite enviar los datos al modelo de negocio
	 * ya sea si se estan actualizando los datos o guardando por primera ves*/
	public String guardar() {
		System.out.println("editando "+ bandera);
		try {
			if(bandera)
				cBussiness.actualizar(newcuento);
			else {
//				Persona p= new Persona();
//				p.getId();
//				System.out.println("obtenr persona    "+p.getId());
//				newcuento.setPersona(p);
				cBussiness.save(newcuento);
				
			System.out.println("guardado");
			//return "list-cuentos?faces-redirect=true";
			}
			return "indexlistarcuento?faces-redirect=true";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error al guardar");
			//e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),"error");
            facesContext.addMessage(null, m);
		}
		return null;
	}
	
	/*metodo que me sirve para recibir el codigo como parametro y enviar al modelo de negocio
	 * para la validacion correspondiente y luego eliminar ese registro*/
	public String eliminar(int codigo) {
		try {
			cBussiness.eliminar(codigo);
			System.out.println("registro elimanado");
			return "indexlistarcuento?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error al eliminar");
			//e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),"error");
            facesContext.addMessage(null, m);
		}
		return null;
	}
	
	/*metodo para editar los datos  de los  cuentos*/
	public String editar(Cuento cuento) {
		//bandera=true;
		System.out.println(cuento);
		//newpersona=persona;
		//return "create-persona?faces-redirect=true&id="+persona.getCedula();
		return "indexcrearcuento?faces-redirect=true&id="+cuento.getCodigo();
	}
	/*metodo que se ejecuta cuando el usuario quiere leer un cuento
	 * de la lista tiene un parametro que es el codigo */
	public String leercuento(int codigo) {
		try {
			cBussiness.getCuento(codigo);
			System.out.println("leer cuento");
			return "indexlistarcuentousuario2?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error al eliminar");
			//e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),"error");
            facesContext.addMessage(null, m);
		}
		return null;
	}
	/*metodo que agrega preguntas por cada cuento que se aagregue*/
	public String addPreguntas() {
		newcuento.addPreguntas(new Pregunta());
		return null;
	}

}
