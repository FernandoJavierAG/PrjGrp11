package aplicacion;

public class Localizacion {

	float latitud, longitud, altitud;

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getAltitud() {
		return altitud;
	}

	public void setAltitud(float altitud) {
		this.altitud = altitud;
	}

	public Localizacion(float latitud, float longitud, float altitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
	}
	
	
}
