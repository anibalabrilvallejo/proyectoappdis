package proyectoappdis.bussiness;

/*librerias uttilizadas la clase del modelo de negocio*/
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;


import proyectoappdis.dao.PersonaDao;
import proyectoappdis.model.Persona;

@Stateless
//para poder hacerle local
/*clase del modelo de negocio de la persona*/
public class PersonaBussiness {
	
	@Inject
	private PersonaDao dao;
	//aqui van las validaciones
	/*metodo que permite validar a la persona antes de persistir los datos*/
	public boolean save(Persona persona) throws Exception {
		Persona aux = dao.read(persona.getCedula());
		if(aux != null)
			throw new Exception("persona ya registrada");
			
		else
			dao.insert(persona);
		return true;
	}
	/*validacion del listado de las personas
	 *que se almacenan en la base de datos*/
	public List<Persona> getListadoPersona(){
		return dao.getPersonas();
	}
	
	/*metodo de validacion al eliminar un registro de las personas 
	 * una ves que se le pasa la cedula de identificacion*/
	public void eliminar(String cedula) throws Exception {
		Persona aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("persona no registrada");
			
		else
			dao.remove(cedula);
	}
	
	/*metodo de validacion al actualizar los datos de la persona previo paso
	 * de la cedula*/
	public void actualizar(Persona persona) throws Exception {
		Persona aux=dao.read(persona.getCedula());
		if(aux==null)
			throw new Exception("persona no existe");
		else
			dao.update(persona);
	}
	
	/*metodo para obtener una persona pasandole como parametro 
	 * el numero de cedula*/
	public Persona getPersona(String cedula) throws Exception {
		Persona aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Persona no existe");
		else
			return aux;
	}
	
	/*metodo de validacion del usuario y contraseña ingresado por parte del usuario*/
	public List<Persona> login(String correo,String contraseña)throws Exception {
		return dao.login(correo,contraseña);
	}
	
	/*metodo para la validacion de la cedula de los usuarios*/
	public boolean validarcedula(String cedula){
		
		int suma=0;
	    if(cedula.length()!=10){
	    	System.out.println("ingrese una cedula valida  "+cedula.length()+"  "+cedula);
	    	return false;
	    }else{
	    	System.out.println("cedula ingresada es valida  "+cedula.length()+"  "+cedula);
	    	int a[]=new int [cedula.length()/2];
	    	int b[]=new int [(cedula.length()/2)];
	    	int c=0;
	    	int d=1;
	    	
	    	for (int i = 0; i < cedula.length()/2; i++) {
	    		a[i]=Integer.parseInt(String.valueOf(cedula.charAt(c)));
	    		c=c+2;
	    		if (i < (cedula.length()/2)-1) {
	    			b[i]=Integer.parseInt(String.valueOf(cedula.charAt(d)));
	    			d=d+2;
	    		}
	    	}
	    	
	    	for (int i = 0; i < a.length; i++) {
	    		a[i]=a[i]*2;
	    		if (a[i] >9){
	    			a[i]=a[i]-9;
	    		}
	    		suma=suma+a[i]+b[i];
	    	} 
		    int aux=suma/10;
		    int dec=(aux+1)*10;
		     
		    if ((dec - suma) == Integer.parseInt(String.valueOf(cedula.charAt(cedula.length()-1))))
		    	  return true;
		    else
		        if(suma%10==0 && cedula.charAt(cedula.length()-1)=='0'){
		          return true;
		        }else{
		          return false;
		        }
	    }
	}
}
