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

	@Test
	@DisplayName("Comportamiento general del constructor básico")
	@Tag("Tests sobre constructores")
	@ParameterizedTest
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
	
	@Test
	@DisplayName("Comportamiento del constructor básico ante datos no válidos")
	@Tag("Tests sobre constructores")
	@ParameterizedTest
	@CsvSource({"CasoNoValido-, 15","-C4S0N0V4L1D0, 15","CasoNoValido-//&%$, 15","SatisfactoryServices-OEIJD45, -3"})
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
	
	@Test
	@DisplayName("Comportamiento general del constructor complejo")
	@Tag("Tests sobre constructores")
	@ParameterizedTest
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
	
	@Test
	@DisplayName("Comportamiento del constructor complejo ante datos no válidos")
	@Tag("Tests sobre constructores")
	@ParameterizedTest
	@CsvSource({"CasoNoValido, '', 15","'', C4S0N0V4L1D0, 15","CasoNoValido, //&%$, 15","SatisfactoryServices, OEIJD45, -3"})
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
		
		@Test
		@DisplayName("Comportamiento general de setID")
		@ParameterizedTest
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
		
		@Test
		@DisplayName("Comportamiento de setID ante datos no válidos")
		@ParameterizedTest
		@CsvSource({"CasoNoValido-","-C4S0N0V4L1D0","CasoNoValido-//&%$"})
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
		
		@Test
		@DisplayName("Comportamiento general de setMarca")
		@ParameterizedTest
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
		
		@Test
		@DisplayName("Comportamiento de setMarca ante datos no válidos")
		@ParameterizedTest
		@CsvSource({"''"})
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
		
		@Test
		@DisplayName("Comportamiento general de setNumSerie")
		@ParameterizedTest
		@CsvSource({"OGF56HG7"})
		void testSetNumSerieValido(String numSerieEsperado) {
			
			//Arrange
			//
			
			//Act
			String IDEsperado =  "SatisfactoryServices-" + numSerieEsperado;
			maquina.setNumSerie(numSerieEsperado);
			String IDReal = maquina.getID();
			
			//Assert
			assertEquals(IDEsperado,IDReal,"Número de serie mal registrado");
			
		}
		
		@Test
		@DisplayName("Comportamiento de setNumSerie ante datos no válidos")
		@ParameterizedTest
		@CsvSource({"//&%$","''"})
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
		
		@Test
		@DisplayName("Comportamiento general de setCapacidad")
		@ParameterizedTest
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
		
		@Test
		@DisplayName("Comportamiento de setCapacidad ante datos no válidos")
		@ParameterizedTest
		@CsvSource({"-3"})
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
