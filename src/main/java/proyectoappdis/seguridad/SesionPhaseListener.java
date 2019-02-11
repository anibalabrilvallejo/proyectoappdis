package proyectoappdis.seguridad;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.wildfly.security.credential.source.FactoryCredentialSource;

import proyectoappdis.util.SessionUtils;

public class SesionPhaseListener implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Fase "+event.getPhaseId());
		FacesContext fc = event.getFacesContext();
		String paginaActual =fc.getViewRoot().getViewId();
		
		boolean eslogin =(paginaActual.lastIndexOf("principal.xhtml")>-1);
		
		 SessionUtils controlsesion = (SessionUtils) FacesUtils.getManagedBean("Sesion");
		 
		 if (eslogin) {
			 String redireccion="";
			 
			 if(controlsesion.getSession()!=null)
				 {
//				 switch(controlsesion.getSession()){
//				 	case "principal";
//				 	 	redireccion="/proyectoappdis/faces/principal.xhtml";
//				 	    break;
//				 	case "principal";
//				 	redireccion="/proyectoappdis/faces/principal.xhtml";
//			 	    break;
				 }	
				 }
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}
	
}
