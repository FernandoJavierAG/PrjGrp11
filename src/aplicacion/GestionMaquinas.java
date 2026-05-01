package aplicacion;

import java.util.HashMap;

public class GestionMaquinas {

	gui.FachadaGUI fgui;
	baseDatos.FachadaBD fbd;
	
	HashMap<String, MaquinaExpendedora> maquinasExpendedoras;
	
	public GestionMaquinas(gui.FachadaGUI fgui, baseDatos.FachadaBD fbd) {
		this.fgui = fgui;
		this.fbd = fbd;
		
		maquinasExpendedoras = fbd.cargarMaquinas();	
	}
	
	public void anadirMaquina(MaquinaExpendedora maquina) {
		maquinasExpendedoras.put(maquina.getID(), maquina);
	}
	
	public void guardarCambios() {
		fbd.guardarMaquinas(maquinasExpendedoras);
	}
	
}
