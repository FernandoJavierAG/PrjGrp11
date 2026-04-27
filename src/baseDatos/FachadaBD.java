package baseDatos;

import java.util.HashMap;
import aplicacion.*;

public class FachadaBD {
	aplicacion.FachadaAplicacion fa;
	
	private java.sql.Connection conexion;
	
	private MaquinasDAO daoMaquinas;
	private ProductosDAO daoProductos;
	
	public FachadaBD(aplicacion.FachadaAplicacion fa) {
		this.fa = fa;
		
		// TO-DO
		// Apertura de conexión
		
		// ...
		
		//
	}
	
	public HashMap<String, MaquinaExpendedora> cargarMaquinas() {
		return daoMaquinas.cargarMaquinas();
	}
	
	public void guardarMaquinas(HashMap<String, MaquinaExpendedora> maquinas) {
		daoMaquinas.guardarMaquinas(maquinas);
	}
	
}
