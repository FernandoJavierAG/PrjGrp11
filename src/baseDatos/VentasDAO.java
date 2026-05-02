package baseDatos;

import java.time.Instant;
import java.util.HashMap;

import aplicacion.MaquinaExpendedora;
import aplicacion.Venta;

public class VentasDAO {
	
	//Simulación del almacenamiento persistente
	HashMap<Instant,MaquinaExpendedora> ventas;

	public void guardarVentas(MaquinaExpendedora maquina, Venta venta) {
		ventas.put(venta.getFecha(), maquina);
		
	}

}
