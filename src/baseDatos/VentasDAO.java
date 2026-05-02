package baseDatos;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

import aplicacion.MaquinaExpendedora;
import aplicacion.Venta;

public class VentasDAO {
	
	//Simulación del almacenamiento persistente
	HashMap<MaquinaExpendedora, List<Venta>> ventas;

	public void guardarVenta(String maquina, int producto, int unidades) throws IllegalArgumentException {
		
		MaquinaExpendedora maqExp = null;
		for (MaquinaExpendedora me : ventas.keySet()) {
			if (me.getID().equals(maquina)) {
				maqExp = me;
				break;
			}
		}
		
		if (maqExp == null) {
			throw new IllegalArgumentException("No existe ninguna máquina identificada como " + maquina + ".");
		}
		
		if(producto < 0 || producto > maqExp.getCapacidad()) {
			throw new IllegalArgumentException("Esta máquina no tiene un producto numerado como " + maquina + ".");
		}
		
		ventas.get(maqExp).add(new Venta(maqExp, maqExp.getProducto(producto), unidades));
	}

}
