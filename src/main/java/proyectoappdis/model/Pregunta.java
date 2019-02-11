package proyectoappdis.model;

/*librrias que se utilizan dentro de esta clase*/
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pregunta implements Serializable{
	
	/*variables y anotaciones que se utilizan para la entidad 
	 * preguntas que se utilizan luego de que se termina de leer el cuento*/
	@Id
	@GeneratedValue
	private int codigo;
	
	private String descripcion;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
