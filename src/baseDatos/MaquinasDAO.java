package baseDatos;

import java.sql.*;
import java.util.HashMap;

import aplicacion.Localizacion;
import aplicacion.MaquinaExpendedora;

public class MaquinasDAO extends AbstractDAO{
	
	HashMap<String, MaquinaExpendedora> simulacionBD;

	
	public MaquinasDAO(aplicacion.FachadaAplicacion fa, Connection conexion) {
		super(fa, conexion);
		
		simulacionBD = new HashMap<String, MaquinaExpendedora>();
		initBD(simulacionBD);
	}
	
	public HashMap<String, MaquinaExpendedora> cargarMaquinas() {	
		return simulacionBD;
	}
	
	public HashMap<String, MaquinaExpendedora> cargarMaquinas(String ID) {
		HashMap<String, MaquinaExpendedora> coincidencias = new HashMap<String, MaquinaExpendedora>();
		
		for(MaquinaExpendedora maquina : simulacionBD.values())
			if(maquina.getID().contains(ID))
				coincidencias.put(maquina.getID(), maquina);
		
		return coincidencias;
	}
	
	public void guardarMaquinas(HashMap<String, MaquinaExpendedora> maquinas) {
		simulacionBD = maquinas;
	}
	public void guardarMaquina(MaquinaExpendedora maquina) {
		simulacionBD.put(maquina.getID(), maquina);
	}
	
	private void initBD(HashMap<String, MaquinaExpendedora> simBD) {
		
		MaquinaExpendedora maquina;
		
		maquina = new MaquinaExpendedora(
				"Vendomatic-2931BIA3",
				130,
				new Localizacion((float) -31.5239, (float) 28.5851, (float) 20.9312)
				);
		simBD.put(maquina.getID(), maquina);
		maquina = new MaquinaExpendedora(
				"Snackmaster-ABBC4566",
				130,
				new Localizacion((float) 57.7847, (float) -38.1234, (float) 88.3124)
				);
		simBD.put(maquina.getID(), maquina);
		maquina = new MaquinaExpendedora(
				"Snackmaster-ABBC77789",
				130,
				new Localizacion((float) 0.031, (float) 0.031, (float) 10.6877)
				);
		simBD.put(maquina.getID(), maquina);
		maquina = new MaquinaExpendedora(
				"Coriolis-0001D",
				130,
				new Localizacion((float) -60.381, (float) -38.7868, (float) 0.0314)
				);
		simBD.put(maquina.getID(), maquina);
	}
}
