package aplicacion;

import java.util.Objects;

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
	public void setConsumoDiario(float consumoDiario) throws IllegalArgumentException {
		/* Excepción en caso de que el consumo diario sea 0 (o menos) */
		if(consumoDiario <= 0.0f) {
			throw new IllegalArgumentException("El consumo diario debe ser mayor que 0.");
		}
		this.consumoDiario = consumoDiario;
	}
	
	/* Getter y setter para el consumo diario */
	public Stock getStock() {
		return this.stock;
	}
	public void setStock(Stock stock) throws IllegalArgumentException{
		if(stock == null) {
			throw new IllegalArgumentException("El stock asociado no puede ser nulo.");
		}
		this.stock = stock;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estimacion other = (Estimacion) obj;
		return (this.consumoDiario == other.consumoDiario && this.stock.equals(other.stock));
	}
	
	@Override
	public String toString() {
		return this.consumoDiario + this.stock.toString();
	}
}