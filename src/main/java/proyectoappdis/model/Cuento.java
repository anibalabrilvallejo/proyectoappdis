package proyectoappdis.model;

/*librerias que son necesarias para esta entidad*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
/*anotacion que sirve para convertir la clase en entidad*/
@Entity
public class Cuento implements Serializable{
	
	/*las siguientes validadiones, variables y getters and setters
	 * son los atributos que son necesarios dentro de esta entidad**/
	@Id
	@GeneratedValue
	private int codigo;
	
	@NotNull
	@NotEmpty
	private String titulo;
	
	@NotNull
	@NotEmpty
	private String autor;
	
	@NotNull
	@NotEmpty
	private String contenido;
	
	@NotNull
	@NotEmpty
	private String fecha;

	/*anotacion que me permite enlazar con la entidad preguntas para ello
	 * me debo tener una lista de preguntas que pertenecen a cada cuento*/
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="cuento",referencedColumnName="codigo")
	private List<Pregunta>preguntas;
	
//	@ManyToMany
//	@JoinTable(name = "cuento", joinColumns = { @JoinColumn(name = "codigo") }, inverseJoinColumns = {
//			@JoinColumn(name = "id") })
//	private List<Persona> personas;
//	
//	private Persona persona;
//	
//	
//	public Persona getPersona() {
//		return persona;
//	}
//	public void setPersona(Persona persona) {
//		this.persona = persona;
//	}
//	public List<Persona> getPersonas() {
//		return personas;
//	}
//	public void setPersonas(List<Persona> personas) {
//		this.personas = personas;
//	}
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/*metodo que me permite agregar preguntas relacionas con el cuento*/
	public void addPreguntas(Pregunta preguntas){
		if(this.preguntas==null)
			this.preguntas=new ArrayList<>();
		this.preguntas.add(preguntas);
	}
}
