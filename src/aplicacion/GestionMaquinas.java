package aplicacion;

import java.util.HashMap;

public class GestionMaquinas {

	gui.FachadaGUI fgui;
	baseDatos.FachadaBD fbd;
	
	public GestionMaquinas(gui.FachadaGUI fgui, baseDatos.FachadaBD fbd) {
		this.fgui = fgui;
		this.fbd = fbd;
		
	}
	
	public void guardarMaquina(MaquinaExpendedora maquina) {
		fbd.guardarMaquina(maquina);
	}
	
	public void guardarMaquinas(HashMap<String, MaquinaExpendedora> maquinas) {
		fbd.guardarMaquinas(maquinas);
	}
	
}
