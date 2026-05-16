package aplicacion;

import baseDatos.FachadaBD;
import gui.FachadaGUI;

public class FachadaAplicacion {
	
	gui.FachadaGUI fgui;
	baseDatos.FachadaBD fbd;
	
	GestionMaquinas gm;

	
	public FachadaAplicacion() {
		fgui = new FachadaGUI(this);
		fbd = new FachadaBD(this);
		
		gm = new GestionMaquinas(fgui, fbd);
	}
}


