package aplicacion;

import java.time.LocalDateTime;	// para las fechas y horas al momento de calcular estimaciones
import java.lang.Math;			// para las operaciones con las que calcular las estimaciones
import java.util.HashMap;		// para almacenar el histórico de consumo de cada producto

public class Estimacion{
	private float consumoDiario;	// Valor medio de consumo de un producto en una máquina a diario
	private Stock stock;			// Información sobre el producto y máquina pertinentes para estimar fechas
	
	/* Constructor vacío */
	public Estimacion() {
		consumoDiario = 0.0f;	// valor por defecto
		stock = new Stock();	// faltan datos
	}
	
	/* Constructor con los datos pertinentes */
	public Estimacion(float consumoDiario, Stock stock) {
		this.consumoDiario = consumoDiario;
		this.stock = stock;
	}
	
	/* Getter y setter para el consumo diario */
	public float getConsumoDiario() {
		return this.consumoDiario;
	}
	public void setConsumoDiario(float consumoDiario) {
		this.consumoDiario = consumoDiario;
	}
	
	/* Getter y setter para el consumo diario */
	public Stock getStock() {
		return this.stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
//	/* Función para estimar la fecha en la que se agotará el stock */
//	public LocalDateTime fechaFinStock() throws IllegalArgumentException {
//		/* Variables requeridas */
//		LocalDateTime finStock;
//		int cantidadStock = this.stock.getCantidad();
//		
//		/* Excepción en caso de que el consumo diario sea 0 (o menos) */
//		if(this.consumoDiario <= 0.0f) {
//			throw new IllegalArgumentException("El consumo diario debe ser mayor que 0.");
//		}
//		
//		/* Calculamos los días en los que se terminará el stock */
//		int diasHastaFin = (int) Math.floor(cantidadStock / this.consumoDiario);
//		
//		/* Sumamos desde hoy los días calculados */
//		finStock = LocalDateTime.now().plusDays(diasHastaFin);
//		
//		return finStock;
//	}
//	
//	/* Función para consultar el consumo diario de cada producto de cada máquina en un intervalo temporal */
//	// El argumento recibido, días, puede ser 1, 7 (1 semana), 30 (1 mes) o 365 (1 año)
//	public HashMap<String, float> historicoConsumo(int dias) throws IllegalArgumentException {
//		/* Variables requeridas */
//		HashMap<String, float> resultado = new HashMap<>();
//		LocalDate rangoVentas;
//		float sumaConsumo = 0.0f;
//		
//		/* Excepción si no se especifican los valores apropiados de días */
//		if((dias != 1) || (dias != 7) || (dias != 30) || (dias != 365)) {
//			throw new IllegalArgumentException("El tiempo no especificado no es 1 día, semana, mes o año.");
//		}
//		
//		/* Calcular el límite de días especificado, hacia atrás en el tiempo desde hoy */
//		// Solamente nos quedamos con la fecha sin la hora para poder emplear la función compareTo de LocalDate
//		rangoVentas = LocalDateTime.now().minusDays(dias).toLocalDate();
//		
//		/* Acumular, para las ventas del producto de la máquina, las unidades vendidas en el rango especificado */
//		for(Venta venta : this.stock.getProducto().getVentas()) {
//			// Si la comparación es <= 0, entonces la fecha límite es menor que la de la venta
//			// De este modo, estamos dentro del rango de días especificado, con lo que sumamos
//			if(rangoVentas.compareTo(venta.getFecha().toLocalDate()) <= 0) {
//				// Sumamos las unidades vendidas del día pertinente
//				sumaConsumo += (float) venta.getUnidades();
//			}
//		}
//		
//		/* Calculamos la media pertinente de consumo diario */
//		// No hace falta verificar si dias <= 0, puesto que ya se comprueba al lanzar la excepción
//		sumaConsumo = sumaConsumo / (float) dias;
//		
//		/* Almacenamos el par (nombre de producto, media de consumo) */
//		resultado.put(this.stock.getProducto().getNombre(), sumaConsumo);
//		
//		return resultado;
//	}
//	
//	/* Función para actualizar el consumo diario en función de las ventas totales */
//	public void actualizarConsumo() {
//		/* Variables requeridas */
//		float consumo = 0.0f;	// media de consumo diario
//		LocalDate diaConsumo;	// variable auxiliar para el bucle, para comprobar cuando terminan las ventas de una fecha determinada
//		int diasTotales = 0;	// variable auxiliar para contar los días distintos en los que se han producido ventas
//		
//		/* Inicializamos diaConsumo a la jornada actual */
//		diaConsumo = LocalDate.now();
//		
//		/* Buscamos los productos vendidos para calcular la media en función de los datos globales */
//		for(Venta venta : this.stock.getProducto().getVentas()) {
//			/* Sumamos la contribución de la venta al total */
//			consumo += (float) venta.getUnidades();
//			
//			/* Comparamos la fecha de la venta con la más reciente almacenada en diaConsumo */
//			if(diaConsumo.compareTo(venta.getFecha().toLocalDate()) != 0) {
//				diaConsumo = venta.getFecha().toLocalDate();	// Actualizamos el día más reciente
//				diasTotales++;	// Sumamos 1 día más para la media, al haber encontrado una fecha distinta a la actual
//			}
//		}
//		
//		/* Consumo medio = consumo total / días de ventas */
//		consumo = consumo / (float) diasTotales;
//		
//		/* Actualizamos el valor de consumoDiario */
//		this.setConsumoDiario(consumo);
//	}
}