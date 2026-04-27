package aplicacion;

public class FachadaAplicacion {
	
	gui.FachadaGUI fgui;
	baseDatos.FachadaBD fbd;
	
	GestionMaquinas gm;

	
	public FachadaAplicacion() {
		fgui = new fgui();
		fbd = new fbd();
		
		gm = new GestionMaquinas(fgui, fbd);
	}
}


