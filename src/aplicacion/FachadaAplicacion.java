package aplicacion;

import gui.FachadaGUI;
import baseDatos.FachadaBD;

public class FachadaAplicacion {
	
	FachadaGUI fgui;
	FachadaBD fbd;
	
	GestionMaquinas gm;

	
	public FachadaAplicacion() {
		fgui = new FachadaGUI(this);
		fbd = new FachadaBD(this);
		
		gm = new GestionMaquinas(fgui, fbd);
	}
	
	public void guardarMaquina(MaquinaExpendedora maquina) {
		gm.anadirMaquina(maquina);
		fbd.guardarMaquina(maquina);
	}
}


