package proyectoappdis.model;

/*librerias para la clase persona*/
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/*anotacion para que la clase se covierta en entidad*/
@Entity
public class Persona implements Serializable {
	
	/*las siguientes lineas son los atributos de la entidad persona
	 ademas de las anotaciones necesarias para las validaciones de los campos 
	 como son el notnull, campos vacios y el tamaño de las cadenas de texto
	 que se ejecutan cuando se ingresan mas caracteres de lo permitido*/
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Id
	@NotEmpty
	@NotNull
	private String cedula;
	
	@NotNull
    @Size(min = 1, max = 25)
	@NotEmpty
    @Pattern(regexp = "[^0-9]*")
	@NotBlank(message = "Ingrese un nombre Porfavor")
	private String nombres;
	
	@NotNull
    @Size(min = 1, max = 25)
	@NotEmpty
    @Pattern(regexp = "[^0-9]*")
	@NotBlank(message = "Ingrese un nombre Porfavor")
	private String apellidos;
	
	@NotBlank(message = "Ingrese un correo Porfavor")
	@Email
	@NotEmpty
	private String correo;
	
	@Size(min = 4, message = "Debe ingresar un minimo de 4 caracteres")
	private String contraseña;
	
	private String estado;
	
	private String perfil;
	
	@NotEmpty
	@NotNull
	private String direccion;
	
	@NotEmpty
	@NotNull
	private String sexo;
	
	@NotEmpty
	@NotNull
	private String telefono;
	
	@NotEmpty
	private String fecha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
