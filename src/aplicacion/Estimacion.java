package aplicacion;

import java.time.LocalDate;	// para las fechas a la hora de calcular estimaciones
import java.lang.Math;		// para las operaciones con las que calcular las estimaciones

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
	
	
	/* Función para estimar la fecha en la que se agotará el stock */
	public LocalDate fechaFinStock() throws IllegalArgumentException {
		/* Variables requeridas */
		LocalDate finStock;
		int cantidadStock = this.stock.getCantidad();
		
		/* Excepción en caso de que el consumo diario sea 0 (o menos) */
		if(this.consumoDiario <= 0.0f) {
			throw new IllegalArgumentException("El consumo diario debe ser mayor que 0.");
		}
		
		/* Calculamos los días en los que se terminará el stock */
		int diasHastaFin = (int) Math.floor(cantidadStock / this.consumoDiario);
		
		/* Sumamos desde hoy los días calculados */
		finStock = LocalDate.now().plusDays(diasHastaFin);
		
		return finStock;
	}
}