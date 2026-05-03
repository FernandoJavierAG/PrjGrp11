package aplicacion;

public class FachadaAplicacion {
	
	gui.FachadaGUI fgui;
	baseDatos.FachadaBD fbd;
	
	GestionMaquinas gm;
	GestionEstimacion gest;

	
	public FachadaAplicacion() {
		fgui = new fgui();
		fbd = new fbd();
		
		gm = new GestionMaquinas(fgui, fbd);
		gest = new GestionEstimacion(fgui, fbd);
	}
}


