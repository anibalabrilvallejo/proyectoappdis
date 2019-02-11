package proyectoappdis.view;

/*librerias para la clase */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import proyectoappdis.bussiness.PersonaBussiness;
import proyectoappdis.model.Persona;
import proyectoappdis.util.SessionUtils;

	@ManagedBean
	//@SessionScoped
	@ViewScoped
public class PersonaBean {
		
	/*anotaccion que sirve para llevar y traer los datos 
	 * de la pagina jsf */
	@Inject
	private FacesContext facesContext;
	private String ideditar;
	private Persona newpersona;
	private Persona myuser;
	private int idEditAdmin;
	private int idrecuprerar;
	
	@Inject
	private PersonaBussiness pBussiness;
	
	/*lista que me sirve para guardar la coleccion de los datos */
	private List<Persona>personas;
	
	private boolean bandera;
	
	
	@NotBlank(message = "Ingrese sus Credenciales")
	private String coincidencia;
	private String contrasenia;
	private String Loginexiste;
	
	private String sexo;
	
	private Date date;
	
	/*variable que contiene el patron para validar 
	 * el correode la persona*/
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@PostConstruct
	public void init() {
		newpersona=new Persona();
		bandera=false;
		personas=pBussiness.getListadoPersona();
	}
	//*Metodos getters and setters propios de esta clase**/
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCoincidencia() {
		return coincidencia;
	}
	public void setCoincidencia(String coincidencia) {
		this.coincidencia = coincidencia;
	}	
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
		
	public String getLoginexiste() {
		return Loginexiste;
	}
	public void setLoginexiste(String loginexiste) {
		Loginexiste = loginexiste;
	}
	public String getIdeditar() {
		return ideditar;
	}

	public void setIdeditar(String ideditar) {
		this.ideditar = ideditar;
	}
	
	public Persona getMyuser() {
		return myuser;
	}
	public void setMyuser(Persona myuser) {
		this.myuser = myuser;
	}
	public boolean isBandera() {
		return bandera;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	
	public Persona getNewpersona() {
		return newpersona;
	}

	public void setNewpersona(Persona newpersona) {
		this.newpersona = newpersona;
	}
	
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
		
	public int getIdEditAdmin() {
		return idEditAdmin;
	}
	public void setIdEditAdmin(int idEditAdmin) {
		this.idEditAdmin = idEditAdmin;
	}
		
	public int getIdrecuprerar() {
		return idrecuprerar;
	}
	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
	}
	/*metodo para cargar los datosn en las paginas jsf 
	 * para editar los mismos */
	public void loadData() {
		
		System.out.println("datos editar " + ideditar);
		//String num = Integer.toString(ideditar);
		if(ideditar==null)
			return;
		try {
			newpersona = pBussiness.getPersona(ideditar);
			bandera = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					e.getMessage(), "Error");
            facesContext.addMessage(null, m);
		}
	}
	/*metodo al que le llegan los datos de lapagina jsf y le envian al la clase del modelo
	 * de negocio para hacer las valiadaciones necesarias antes en enviar a la base de datos*/
	public String guardar() {
		System.out.println("editando "+ bandera);
		try {
			if(bandera)
				pBussiness.actualizar(newpersona);
			else {				
				/*if((pBussiness.validarcedula(newpersona.getCedula()) && 
					coincidirContrasenia() && validarCorreo()) == true)*/
				System.out.println("la cedula ingresada es "   +newpersona.getCedula()+"  sexo   "+this.sexo);
				if(verificacioncedula()&& validarCorreo() && coincidirContrasenia())
				//if(pBussiness.validarcedula(newpersona.getCedula()) && validarCorreo() && coincidirContrasenia()== true)
				{
					newpersona.setPerfil("USUARIO");
					newpersona.setEstado("U");
					validarsexo();
					pBussiness.save(newpersona);
					inicializar();
					this.coincidencia = "Grabado exitoso!";
				}
				else {
					System.out.println("ingrese correctamente la cedula");
					this.coincidencia = "El formato del correo es incorrecto";
				}
			
			System.out.println("guardado");
			this.coincidencia = "Ingrese las mismas contrasenias";
			}
			  return "indexlistarpersona?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error al guardar");
			//e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),"error");
            facesContext.addMessage(null, m);
		}
		return null;
	}
/*metodo pque controla el proceso de eliminar un registro**/		
	public String eliminar(String cedula) {
		try {
			pBussiness.eliminar(cedula);
			System.out.println("registro elimanado");
			//return "list-personas?faces-redirect=true";
			return "indexlistarpersona?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error al eliminar");
			//e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),"error");
            facesContext.addMessage(null, m);
		}
		return null;
	}
	/*metodo para editar los datos de una persona se obtiene un codigo 
	y se envia hacia la pagina jsf*/
	public String editar(Persona persona) {
		//bandera=true;
		System.out.println(persona);
		//newpersona=persona;
		//return "create-persona?faces-redirect=true&id="+persona.getCedula();
	  //return "crearpersona?faces-redirect=true&id="+persona.getCedula();
		return "indexcrearpersona?faces-redirect=true&id="+persona.getCedula();
	}
	/**metodo pra verificar que las contraseñas esten iguales */
	public boolean coincidirContrasenia() {
		if (newpersona.getContraseña().equals(this.contrasenia)) {
			return true;
		} else {
			return false;
		}
	}
	/*metodo para agregar el sexo a la persona*/
	public void validarsexo(){
		if(this.sexo=="Masculino")
			newpersona.setSexo(this.sexo);
		else
			newpersona.setSexo(this.sexo);
	}
	/**metodo que me permite validar el correo que se ingresa por parte del usuario*/
	public boolean validarCorreo() {
		String email = newpersona.getCorreo();
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	/**metodo para inicializar los datos */
	public void inicializar() {
		newpersona.setId(0);
		newpersona.setApellidos("");
		newpersona.setNombres("");
		newpersona.setContraseña("");
	}
	/*metodo para iiniciar la sesion teneindo en cuenta de que este registrado ya ea como usuario
	 * o como admin**/
	public void iniciarSesion() throws Exception
	{
		System.out.println("llego al metodo   "+newpersona.getCorreo()+"   "+newpersona.getContraseña() );
		
		if (pBussiness.login(newpersona.getCorreo(), newpersona.getContraseña()).size() != 0) {
			
			System.out.println("iniciar session ingreso if "+newpersona.getCorreo()+"   "+newpersona.getContraseña());
			
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username",pBussiness.login(newpersona.getCorreo(), newpersona.getContraseña()).get(0).getCorreo());
			session.setAttribute("perfil",pBussiness.login(newpersona.getCorreo(),newpersona.getContraseña()).get(0).getPerfil());
			session.setAttribute("estado",pBussiness.login(newpersona.getCorreo(), newpersona.getContraseña()).get(0).getEstado());
			this.Loginexiste = " ";
			FacesContext contex = FacesContext.getCurrentInstance();
			
			if (pBussiness.login(newpersona.getCorreo(),newpersona.getContraseña()).get(0).getPerfil().equals("ADMIN")) {

				try {
					contex.getExternalContext().redirect("indexprilogin.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (pBussiness.login(newpersona.getCorreo(),newpersona.getContraseña()).get(0).getPerfil().equals("USUARIO")) {
				// FacesContext contexAS= FacesContext.getCurrentInstance();
				try {
					contex.getExternalContext().redirect("indexcrearcuentousuario.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
		newpersona.setCorreo("");
		newpersona.setContraseña("");
		this.Loginexiste = "El usuario o la contrasenia son incorrectos";
	}
	/*metodo para verificar  que la session este activa*/
	public void verificaSesion() {
		HttpSession session = SessionUtils.getSession();
		String nusv = (String) session.getAttribute("username");
		if (nusv != null) {
			System.out.println("si tiene sesion");
			FacesContext contex = FacesContext.getCurrentInstance();
			try {
				if (myuser.getPerfil().equals("USUARIO")) {
					contex.getExternalContext().redirect("indexprilogin.xhtml");
				} else if (myuser.getPerfil().equals("ADMIN")) {
					contex.getExternalContext().redirect("indexprilogin.xhtml");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*metodo para cerrar la session del usuario*/
	public String cerrarSesion() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "principal.xhtml";
	}
	public void loadidUser(int id) {
		idEditAdmin = id;
	}

	public void loadid(int id) {
		idrecuprerar = id;	
	}
	/**metodo que verifica que le metodo de la clase que esta en el modelo de negocio 
	 * valide la cedula*/
	public boolean verificacioncedula() {
		if(pBussiness.validarcedula(newpersona.getCedula())) {
			this.coincidencia="cedula valida";
				return true;
		}
		else {
			this.coincidencia="cedula invalida";
			return false;
		}
	}	

}

