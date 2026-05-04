package baseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.time.Instant;
import java.math.*;

import aplicacion.*;

public class EstimacionDAO extends AbstractDAO{

	public EstimacionDAO(aplicacion.FachadaAplicacion fa, Connection conexion) {
		super(fa, conexion);
	}

	
	/* Función para estimar la fecha en la que se agotará el stock */
	public Instant fechaFinStock(Estimacion est){
		/* Variables requeridas */
		Instant finStock;
		int cantidadStock = est.getStock().getCantidad();
		
		/* Calculamos los días en los que se terminará el stock */
		// Tenemos garantizado que el consumo diario será distinto de 0 gracias a la excepción del setter
		// Usamos floor para evitar las estimaciones en las que se termine el stock antes del final de un día
		int diasHastaFin = (int) Math.floor(cantidadStock / est.getConsumoDiario());
		
		/* Sumamos desde hoy los días calculados */
		// Con chronounit especificamos que diasHastaFin se debe tratar como días en la suma
		finStock = Instant.now().plus(diasHastaFin, ChronoUnit.DAYS);
		
		return finStock;
	}
	
	
	/* Función para consultar el consumo diario de cada producto de cada máquina en un intervalo temporal */
	// El argumento días puede ser 1, 7 (1 semana), 30 (1 mes) o 365 (1 año)
	// La lista de ventas debe contener exclusivamente aquellas asociadas a un solo producto
	public float historicoConsumo(int dias, ArrayList<Venta> ventas) throws IllegalArgumentException {
		/* Variables requeridas */
		Instant rangoVentas;
		float consumo = 0.0f;
		
		/* Excepción si no se especifican los valores apropiados de días */
		if((dias != 1) || (dias != 7) || (dias != 30) || (dias != 365)) {
			throw new IllegalArgumentException("El tiempo especificado no es 1 día, semana, mes o año.");
		}
		
		/* Calcular el límite de días especificado, hacia atrás en el tiempo desde hoy */
		rangoVentas = Instant.now().minus(dias, ChronoUnit.DAYS);
		
		/* Acumular, para las ventas del producto de la máquina, las unidades vendidas en el rango especificado */
		for(Venta v : ventas) {
			// Si la comparación es <= 0, entonces la fecha límite es menor que la de la venta
			// De este modo, estamos dentro del rango de días especificado, con lo que sumamos
			if(rangoVentas.compareTo(v.getFecha()) <= 0) {
				// Sumamos las unidades vendidas del día pertinente
				consumo += (float) v.getUnidades();
			}
		}
		
		/* Calculamos la media pertinente de consumo diario */
		// No hace falta verificar si dias <= 0, puesto que ya se comprueba al lanzar la excepción
		consumo = consumo / (float) dias;
		
		return consumo;
	}
	
	
	/* Función para actualizar el consumo diario en función de las ventas totales */
	// La lista de ventas debe contener exclusivamente aquellas asociadas al producto sobre el que se hace la estimación
	public void actualizarConsumo(Estimacion est, ArrayList<Venta> ventas) {
		/* Variables requeridas */
		float consumo = 0.0f;	// media de consumo diario
		Instant menorFecha;		// variable auxiliar para calcular la menor fecha de 
		int diasTotales = 1;	// variable auxiliar para contar los días distintos en los que se han producido ventas
		
		/* Inicializamos diaConsumo a la jornada actual */
		menorFecha = Instant.now();
		
		/* Buscamos los productos vendidos para calcular la media en función de los datos globales */
		for(Venta v : ventas) {
			/* Sumamos la contribución de la venta al total */
			consumo += (float) v.getUnidades();
			
			/* Comparamos la fecha de la venta con la más reciente almacenada en diaConsumo */
			// Se devolverá true en caso de que la fecha de la venta sea mayor que menorFecha
			if(menorFecha.isAfter(v.getFecha())) {
				menorFecha = v.getFecha();	// Actualizamos el día mínimo
			}
		}

		/* Calculamos el número de días entre la fecha mínima y la jornada actual, para la media */
        diasTotales = (int) ChronoUnit.DAYS.between(menorFecha, Instant.now());
        if(diasTotales == 0) diasTotales = 1;	// Evitamos el caso en el que la menorFecha sea el día actual
		
		/* Consumo medio = consumo total / días de ventas */
		consumo = consumo / (float) diasTotales;
		
		/* Actualizamos el valor de consumoDiario */
		est.setConsumoDiario(consumo);
	}
}
