package aplicacion;

import gui.FachadaGUI;

import java.util.HashMap;

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
		gm.guardarMaquina(maquina);
	}
	
	public HashMap<String, MaquinaExpendedora> cargarMaquinas()  {
		return gm.cargarMaquinas();
	}
	
	public HashMap<String, MaquinaExpendedora> cargarMaquinas(String ID)  {
		return gm.cargarMaquinas(ID);
	}
	
	public static void main(String args[]) {
		new FachadaAplicacion();
	}
}


