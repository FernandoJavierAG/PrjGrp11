package aplicacion;

import java.util.HashMap;

public class GestionMaquinas {

	gui.FachadaGUI fgui;
	baseDatos.FachadaBD fbd;
	
	public GestionMaquinas(gui.FachadaGUI fgui, baseDatos.FachadaBD fbd) {
		this.fgui = fgui;
		this.fbd = fbd;
	}
	
	public void guardarVenta(String maquina, int producto, int unidades) {
		
		try {
			fbd.guardarVenta(maquina, producto, unidades);
		} catch(IllegalArgumentException e) {
			//Esta funcionalidad no proviene de ninguna ventana, por solo es posiblle informar por consola
			System.out.println(e.getMessage());
		}
	}
	
}
