package baseDatos;

import aplicacion.MaquinaExpendedora;
import aplicacion.Venta;

public class FachadaBD {
	aplicacion.FachadaAplicacion fa;
	
	private java.sql.Connection conexion;
	
	private MaquinasDAO daoMaquinas;
	private ProductosDAO daoProductos;
	private VentasDAO daoVentas;
	
	public FachadaBD(aplicacion.FachadaAplicacion fa) {
		this.fa = fa;
		
		// Apertura de conexión
		
		// ...
		
		//
	}

	public void guardarVenta(MaquinaExpendedora maquina, Venta venta) {
		daoVentas.guardarVentas(maquina, venta);
		
	}
}
