package baseDatos;


public abstract class AbstractDAO {
	
	protected aplicacion.FachadaAplicacion fa;
	protected java.sql.Connection conexion;

	public AbstractDAO(aplicacion.FachadaAplicacion fa, java.sql.Connection conexion) {
		this.fa = fa;
		this.conexion = conexion;
	}
}
