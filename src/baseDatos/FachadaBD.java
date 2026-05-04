package baseDatos;

import java.time.Instant;
import java.util.ArrayList;

import aplicacion.*;

public class FachadaBD {
	aplicacion.FachadaAplicacion fa;
	
	private java.sql.Connection conexion;
	
	private MaquinasDAO daoMaquinas;
	private ProductosDAO daoProductos;
	private EstimacionDAO daoEstimacion;
	
	public FachadaBD(aplicacion.FachadaAplicacion fa) {
		this.fa = fa;
		
		daoEstimacion = new EstimacionDAO(fa, null);
		
		// Apertura de conexión
		
		// ...
		
		//
	}
	
	public Instant fechaFinStock(Estimacion est){
		return daoEstimacion.fechaFinStock(est);
	}
	
	public float historicoConsumo(int dias, ArrayList<Venta> ventas) {
		return daoEstimacion.historicoConsumo(dias, ventas);
	}
	
	public void actualizarConsumo(Estimacion est, ArrayList<Venta> ventas) {
		return daoEstimacion.actualizarConsumo(est, ventas);
	}
}
