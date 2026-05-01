package aplicacion;

import java.util.Objects;

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
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
	}

	@Override
	public int hashCode() {
		return Objects.hash(altitud, latitud, longitud);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localizacion other = (Localizacion) obj;
		return Float.floatToIntBits(altitud) == Float.floatToIntBits(other.altitud)
				&& Float.floatToIntBits(latitud) == Float.floatToIntBits(other.latitud)
				&& Float.floatToIntBits(longitud) == Float.floatToIntBits(other.longitud);
	}

	@Override
	public String toString() {
		return latitud + ", " + longitud + ", "+ altitud;
	}
	
	
	
}
