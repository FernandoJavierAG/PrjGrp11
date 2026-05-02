package aplicacion;

import java.util.HashMap;

public class GestionMaquinas {

	gui.FachadaGUI fgui;
	baseDatos.FachadaBD fbd;
	HashMap<String, MaquinaExpendedora> maquinasExpendedoras;
	
	public GestionMaquinas(gui.FachadaGUI fgui, baseDatos.FachadaBD fbd) {
		this.fgui = fgui;
		this.fbd = fbd;
		
		maquinasExpendedoras = new HashMap<String, MaquinaExpendedora>();
	}
	
	public void registrarVenta(String maquina, int producto, int unidades) throws IllegalArgumentException {
		
		if(maquinasExpendedoras.get(maquina) == null) {
			throw new IllegalArgumentException("No existe ninguna máquina identificada como " + maquina + ".");
		}
		
		maquinasExpendedoras.get(maquina).venta(producto, unidades);
		fbd.guardarVenta(maquinasExpendedoras.get(maquina), new Venta(unidades));
		
	}
	
}
