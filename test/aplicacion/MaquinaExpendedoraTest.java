package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MaquinaExpendedoraTest {

	@ParameterizedTest
	@DisplayName("Comportamiento general del constructor básico")
	@Tag("TestsSobreConstructores")
	@CsvSource({"SatisfactoryServices-OEIJD45, 15"})
	void testConstructorBasicoValido(String IDEsperado, int capacidadEsperada) {
		
		//Arrange
		Localizacion locEsperada = new Localizacion(0,0,0);
		
		//Act
		MaquinaExpendedora maquina = new MaquinaExpendedora(IDEsperado,capacidadEsperada,locEsperada);
		String IDReal = maquina.getID();
		int capacidadReal = maquina.getCapacidad();
		Localizacion locReal = maquina.getLoc();
		
		//Assert
		assertAll(
			"Comprobación completa de registro correcto para el constructor básico",
			() -> assertEquals(IDEsperado,IDReal,"ID mal registrado"),
			() -> assertEquals(capacidadEsperada,capacidadReal,"Capacidad mal registrada"),
			() -> assertEquals(locEsperada,locReal,"Localización mal registrada")
		);
		
	}
	
	@ParameterizedTest
	@DisplayName("Comportamiento del constructor básico ante datos no válidos")
	@Tag("TestsSobreConstructores")
	@CsvSource({"CasoNoValido-, 15, Error de formato de ID: Campos insuficientes.",
		"-C4S0N0V4L1D0, 15, Error de formato de ID: Campos insuficientes.",
		"CasoNoValido-//&%$, 15, Error de formato de número de serie: Debe ser una cadena alfanumérica.",
		"SatisfactoryServices-OEIJD45, -3, Error: La capacidad debe ser no-negativa."})
	void testConstructorBasicoNoValido(String IDEsperado, int capacidadEsperada, String mensajeErrorEsperado) {
		
		//Arrange
		Localizacion locEsperada = new Localizacion(0,0,0);
		
		//Act
		//
		
		//Assert
		Throwable excepcion = assertThrows(
			IllegalArgumentException.class,
			() -> new MaquinaExpendedora(IDEsperado,capacidadEsperada,locEsperada),
			"Excepción no generada ante datos erróneos");
		assertEquals(mensajeErrorEsperado,excepcion.getMessage(),"Generada excepción inesperada");
		
	}
	
	@ParameterizedTest
	@DisplayName("Comportamiento general del constructor complejo")
	@Tag("TestsSobreConstructores")
	@CsvSource({"SatisfactoryServices, OEIJD45, 15"})
	void testConstructorComplejoValido(String marcaEsperada, String numSerieEsperado, int capacidadEsperada) {
		
		//Arrange
		Localizacion locEsperada = new Localizacion(0,0,0);
		String IDEsperado = marcaEsperada + "-" + numSerieEsperado;
		
		//Act
		MaquinaExpendedora maquina = new MaquinaExpendedora(marcaEsperada,numSerieEsperado,capacidadEsperada,locEsperada);
		String IDReal = maquina.getID();
		int capacidadReal = maquina.getCapacidad();
		Localizacion locReal = maquina.getLoc();
		
		//Assert
		assertAll(
			"Comprobación completa de registro correcto para el constructor básico",
			() -> assertEquals(IDEsperado,IDReal,"ID mal registrado"),
			() -> assertEquals(capacidadEsperada,capacidadReal,"Capacidad mal registrada"),
			() -> assertEquals(locEsperada,locReal,"Localización mal registrada")
		);
		
	}
	
	@ParameterizedTest
	@DisplayName("Comportamiento del constructor complejo ante datos no válidos")
	@Tag("TestsSobreConstructores")
	@CsvSource({"CasoNoValido, '', 15, Error de formato de número de serie: No puede ser vacío.",
		"'', C4S0N0V4L1D0, 15, Error de formato de marca: No puede ser vacía.",
		"CasoNoValido, //&%$, 15, Error de formato de número de serie: Debe ser una cadena alfanumérica.",
		"SatisfactoryServices, OEIJD45, -3, Error: La capacidad debe ser no-negativa."})
	void testConstructorComplejoNoValido(String marcaEsperada, String numSerieEsperado, int capacidadEsperada, String mensajeErrorEsperado) {
		
		//Arrange
		Localizacion locEsperada = new Localizacion(0,0,0);
		
		//Act
		//
		
		//Assert
		Throwable excepcion = assertThrows(
				IllegalArgumentException.class,
				() -> new MaquinaExpendedora(marcaEsperada,numSerieEsperado,capacidadEsperada,locEsperada),
				"Excepción no generada ante datos erróneos");
		assertEquals(mensajeErrorEsperado,excepcion.getMessage(),"Generada excepción inesperada");
		
	}
	
	@Nested
	@DisplayName("Tests que parten de un estado inicial")
	class OperacionesSobreMaquinas{
		
		MaquinaExpendedora maquina;
		
		@BeforeEach
		void setUpEach() {
			maquina = new MaquinaExpendedora("SatisfactoryServices-OEIJD45",10,new Localizacion(0,0,0));
		}
		
		@ParameterizedTest
		@DisplayName("Comportamiento general de setID")
		@CsvSource({"SnackExperience-OGF56HG7"})
		void testSetIDValido(String IDEsperado) {
			
			//Arrange
			//
			
			//Act
			maquina.setID(IDEsperado);
			String IDReal = maquina.getID();
			
			//Assert
			assertEquals(IDEsperado,IDReal,"ID mal registrado");
			
		}
		
		@ParameterizedTest
		@DisplayName("Comportamiento de setID ante datos no válidos")
		@CsvSource({"CasoNoValido-, Error de formato de ID: Campos insuficientes.",
			"-C4S0N0V4L1D0, Error de formato de ID: Campos insuficientes.",
			"CasoNoValido-//&%$, Error de formato de número de serie: Debe ser una cadena alfanumérica."})
		void testSetIDNoValido(String IDEsperado, String mensajeErrorEsperado) {
			
			//Arrange
			//
			
			//Act
			//
			
			//Assert
			Throwable excepcion = assertThrows(
					IllegalArgumentException.class,
					() -> maquina.setID(IDEsperado),
					"Excepción no generada ante datos erróneos");
			assertEquals(mensajeErrorEsperado,excepcion.getMessage(),"Generada excepción inesperada");
			
		}
		
		@ParameterizedTest
		@DisplayName("Comportamiento general de setMarca")
		@CsvSource({"SnackExperience"})
		void testSetMarcaValido(String marcaEsperada) {
			
			//Arrange
			//
			
			//Act
			String IDEsperado = marcaEsperada + "-OEIJD45";
			maquina.setMarca(marcaEsperada);
			String IDReal = maquina.getID();
			
			//Assert
			assertEquals(IDEsperado,IDReal,"Marca mal registrada");
			
		}
		
		@ParameterizedTest
		@DisplayName("Comportamiento de setMarca ante datos no válidos")
		@CsvSource({"'', Error de formato de marca: No puede ser vacía."})
		void testSetMarcaNoValido(String marcaEsperada, String mensajeErrorEsperado) {
			
			//Arrange
			//
			
			//Act
			//
			
			//Assert
			Throwable excepcion = assertThrows(
					IllegalArgumentException.class,
					() -> maquina.setMarca(marcaEsperada),
					"Excepción no generada ante datos erróneos");
			assertEquals(mensajeErrorEsperado,excepcion.getMessage(),"Generada excepción inesperada");
			
		}
		
		@ParameterizedTest
		@DisplayName("Comportamiento general de setNumSerie")
		@CsvSource({"OGF56HG7"})
		void testSetNumSerieValido(String numSerieEsperado) {
			
			//Arrange
			//
			
			//Act
			String IDEsperado =  "SatisfactoryServices-" + numSerieEsperado;
			System.out.println(maquina.getID());
			maquina.setNumSerie(numSerieEsperado);
			String IDReal = maquina.getID();
			
			//Assert
			assertEquals(IDEsperado,IDReal,"Número de serie mal registrado");
			
		}
		
		@ParameterizedTest
		@DisplayName("Comportamiento de setNumSerie ante datos no válidos")
		@CsvSource({"//&%$, Error de formato de número de serie: Debe ser una cadena alfanumérica.",
			"'', Error de formato de número de serie: No puede ser vacío."})
		void testSetNumSerieNoValido(String numSerieEsperado, String mensajeErrorEsperado) {
			
			//Arrange
			//
			
			//Act
			//
			
			//Assert
			Throwable excepcion = assertThrows(
					IllegalArgumentException.class,
					() -> maquina.setNumSerie(numSerieEsperado),
					"Excepción no generada ante datos erróneos");
			assertEquals(mensajeErrorEsperado,excepcion.getMessage(),"Generada excepción inesperada");
			
		}
		
		@ParameterizedTest
		@DisplayName("Comportamiento general de setCapacidad")
		@CsvSource({"15"})
		void testCapacidadValido(int capacidadEsperada) {
			
			//Arrange
			//
			
			//Act
			maquina.setCapacidad(capacidadEsperada);
			int capacidadReal = maquina.getCapacidad();
			
			//Assert
			assertEquals(capacidadEsperada,capacidadReal,"Capacidad mal registrada");
			
		}
		
		@ParameterizedTest
		@DisplayName("Comportamiento de setCapacidad ante datos no válidos")
		@CsvSource({"-3, Error: La capacidad debe ser no-negativa."})
		void testSetCapacidadNoValido(int capacidadEsperada, String mensajeErrorEsperado) {
			
			//Arrange
			//
			
			//Act
			//
			
			//Assert
			Throwable excepcion = assertThrows(
					IllegalArgumentException.class,
					() -> maquina.setCapacidad(capacidadEsperada),
					"Excepción no generada ante datos erróneos");
			assertEquals(mensajeErrorEsperado,excepcion.getMessage(),"Generada excepción inesperada");
			
		}
		
	}

}
