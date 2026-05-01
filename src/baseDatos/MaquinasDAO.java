package baseDatos;

import java.sql.*;
import java.util.HashMap;

import aplicacion.MaquinaExpendedora;

public class MaquinasDAO extends AbstractDAO{

	
	public MaquinasDAO(aplicacion.FachadaAplicacion fa, Connection conexion) {
		super(fa, conexion);
	}
	
	public HashMap<String, MaquinaExpendedora> cargarMaquinas() {
		// TO-DO
		
		return new HashMap<String, MaquinaExpendedora>();
	}
	
	public void guardarMaquinas(HashMap<String, MaquinaExpendedora> maquinas) {
		// TO-DO
		
		return;
	}
	public void guardarMaquina(MaquinaExpendedora maquina) {
		// TO-DO
		
		return;
	}
}
