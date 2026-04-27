package baseDatos;

import java.sql.*;
import java.sql.Connection;

public class ProductosDAO extends AbstractDAO{

	public ProductosDAO(aplicacion.FachadaAplicacion fa, Connection conexion) {
		super(fa, conexion);
	}
}
