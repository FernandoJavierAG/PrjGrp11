package aplicacion;

import java.time.Instant;
import java.util.ArrayList;

import baseDatos.Venta;

public class GestionEstimacion {
	
	gui.FachadaGUI fgui;
	baseDatos.FachadaBD fbd;

	public GestionEstimacion(gui.FachadaGUI fgui, baseDatos.FachadaBD fbd) {
		this.fgui = fgui;
		this.fbd = fbd;
	}
	
	public Instant fechaFinStock(Estimacion est){
		return fbd.fechaFinStock(est);
	}
	
	public float historicoConsumo(int dias, ArrayList<Venta> ventas) {
		return fbd.historicoConsumo(dias, ventas);
	}
	
	public void actualizarConsumo(Estimacion est, ArrayList<Venta> ventas) {
		return fbd.actualizarConsumo(est, ventas);
	}
}