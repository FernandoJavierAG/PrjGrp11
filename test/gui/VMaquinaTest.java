package gui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import aplicacion.FachadaAplicacion;
import aplicacion.Localizacion;
import aplicacion.MaquinaExpendedora;

class VMaquinaTest {
	
	static FachadaAplicacion fa;
	VMaquina vm;
	
	@BeforeAll
	static void setUpAll() {
		fa = new FachadaAplicacion();
	}
	
	@BeforeEach
	void setUpEach() {
		vm = new VMaquina(fa);
	}

	@ParameterizedTest
	@DisplayName("Comportamiento general de guardarMaquina")
	@CsvSource({"TrickyVending, H4SSL13B3, 15"})
	void testGuardarMaquinaValido(String marcaEsperada, String numSerieEsperado, int capacidadEsperada) {
		
		//Arrange
		String IDEsperado = marcaEsperada + "-" + numSerieEsperado;
		int latitudEsperada = 0;
		int longitudEsperada = 0;
		int altitudEsperada = 0;
		Localizacion locEsperada = new Localizacion(latitudEsperada,longitudEsperada,altitudEsperada);
		
		//Act
		vm.setCampos(marcaEsperada,numSerieEsperado,capacidadEsperada,latitudEsperada,longitudEsperada,altitudEsperada);
		vm.guardarMaquina();
		HashMap<String, MaquinaExpendedora> simulacionBD = fa.cargarMaquinas();
		MaquinaExpendedora maquina = simulacionBD.get(IDEsperado);
		
		//Assert
		assertNotNull(maquina, "Máquina no registrada en almacenamiento");		
		String IDReal = maquina.getID();
		int capacidadReal = maquina.getCapacidad();
		Localizacion locReal = maquina.getLoc();
		assertAll(
			"Comprobación completa de registro correcto en almacenamiento",
			() -> assertEquals(IDEsperado,IDReal,"ID mal registrado"),
			() -> assertEquals(capacidadEsperada,capacidadReal,"Capacidad mal registrada"),
			() -> assertEquals(locEsperada,locReal,"Localización mal registrada")
		);
				
	}
	
	@ParameterizedTest
	@DisplayName("Comportamiento de guardarMaquina para datos no válidos")
	@CsvSource({"TrickyVending, H4SSL13B3, -3"})
	void testGuardarMaquinaNoValido(String marcaEsperada, String numSerieEsperado, int capacidadEsperada) {
		
		//Arrange
		int latitudEsperada = 0;
		int longitudEsperada = 0;
		int altitudEsperada = 0;
		
		//Act
		vm.setCampos(marcaEsperada,numSerieEsperado,capacidadEsperada,latitudEsperada,longitudEsperada,altitudEsperada);
		boolean fallo = vm.guardarMaquina();
		
		//Assert
		assertFalse(fallo, "Operación finalizada para datos erróneos");
				
	}
	
	@AfterEach
	void tearDownEach() {
		vm.dispose();
	}

}
