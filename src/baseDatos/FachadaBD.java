package baseDatos;

public class FachadaBD {
	aplicacion.FachadaAplicacion fa;
	
	private java.sql.Connection conexion;
	
	private MaquinasDAO daoMaquinas;
	private ProductosDAO daoProductos;
	
	public FachadaBD(aplicacion.FachadaAplicacion fa) {
		this.fa = fa;
		
		// Apertura de conexión
		
		// ...
		
		//
	}
}
