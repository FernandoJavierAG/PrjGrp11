	package aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class VentaTest {
	
	static MaquinaExpendedora maqReferencia;
	static Producto prodReferencia;
	
	MaquinaExpendedora maquina;
	Producto producto;
	
	@BeforeAll
	static void setUpAll() {
		maqReferencia = new MaquinaExpendedora("MaquinaDe-PRUEB4", 100);
		prodReferencia = new Producto();
	}
	
	@AfterAll
	
	@BeforeEach
	void setUpEach() throws Exception {
		maquina = new MaquinaExpendedora(maqReferencia.getID(), maqReferencia.getCapacidad());
		producto = new Producto();
	}
	
	@AfterEach
	void tearDownEach() throws Exception {
		maquina = null;
		producto = null;
	}

	@ParameterizedTest
	@DisplayName("Comportamiento general del constructor.")
	@Tag("Tests sobre constructores")
	@CsvSource({"1", "100"})
	void testConstructorValido(int unidades) {
		// Arrange
		Venta v1 = new Venta(maquina, producto, unidades);
		// Act
		
		// Assert
		assertAll("Comprobación completa de registro correcto para el constructor. ",
		() -> assertEquals(v1.getUnidades(), unidades, "Número de unidades mal registrado."),
		() -> assertEquals(v1.getMaquina(), maquina, "Máquina expendedora mal registrada."),
		() -> assertEquals(v1.getProducto(), producto, "Producto mal registrado")
		);
		
	}

	@ParameterizedTest
	@DisplayName("Constructor con datos no validos")
	@Tag("Tests sobre constructores")
	@CsvSource({"-10", "-1", "0", })
	void testConstructorNoValido(int unidades) {
		// Arrange
		
		// Act
				
		// Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class, 
				() -> {new Venta(maquina, producto, unidades);}, 
				"Excepción no generada ante datos erróneos.");
		assertEquals(
				iae.getMessage(), "Unidades no-validas introducidas.", 
				"Generada excepción inesperada.");
	}
	
	
	@Test
	@DisplayName("setMaquina a null")
	@Tag("Integridad de datos")
	void testsetMaquinaNull() {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		
		// Act
		
		//Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class,
				() -> {v1.setMaquina(null);},
				"Excepción no generada introducir una máquina nula.");
		assertEquals(
				iae.getMessage(), "Maquina nula introducida.", 
				"Generada excepción inesperada.");
	}
	
	@Test
	@DisplayName("Constructor con maquina null")
	@Tag("Tests sobre constructores")
	void testConstructorMaquinaNull() {
		// Arrange
		
		//Act
				
		// Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class, 
				() -> {new Venta(null, producto, 10);}, 
				"Excepción no generada ante maquina nula.");
		assertEquals(
				iae.getMessage(), "Maquina nula introducida.", 
				"Generada excepción inesperada.");
	}
	
	@Test
	@DisplayName("setProducto a null")
	@Tag("Integridad de datos")
	void testsetProductoNull() {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		
		// Act
		
		//Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class,
				() -> {v1.setProducto(null);},
				"Excepción no generada introducir un producto nulo.");
		assertEquals(
				iae.getMessage(), "Producto nulo introducido.", 
				"Generada excepción inesperada.");
		
	}
	
	@Test
	@DisplayName("Constructor con producto null")
	@Tag("Tests sobre constructores")
	void testConstructorProductoNull() {
		// Arrange
		
		//Act
				
		// Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class, 
				() -> {new Venta(maquina, null, 10);}, 
				"Excepción no generada ante producto nulo.");
		assertEquals(
				iae.getMessage(), "Producto nulo introducido.", 
				"Generada excepción inesperada.");
	}
		
	@Test
	void testEqualsObject() {
		// Arrange
		MaquinaExpendedora 
			m1 = maquina,
			m2 = new MaquinaExpendedora("Vendomatic-123123", 100);
		
		Producto
			p1 = producto,
			p2 = new Producto();
		
		Venta 	v1 = new Venta(m1, p1, 10),
				v2 = v1,
				v3 = new Venta(m2, p2, 4),
				v4 = new Venta(m1, p1, 10),
				v5 = new Venta(m1, p1, 10);
		
		Instant fecha1 = Instant.now(),
				fecha2 = fecha1.plus(2, ChronoUnit.HOURS);
		
		// Act
		
		v1.setFecha(fecha1);
		v3.setFecha(fecha1);
		v4.setFecha(fecha2);
		v5.setFecha(fecha1);
		
		// Assert
		assertAll(
			"Comprobación completa de igualdades",
			() -> assertEquals(v1, v1, "Una venta no es idéntica a si misma."),
			() -> assertEquals(v1, v2, "Una venta no es idéntica a sí misma tras aliasing."),
			() -> assertNotEquals(v1, v3, "Una venta es identica a otra con productos y máquinas distintos."),
			() -> assertNotEquals(v1, v4, "Una venta es idéntica a otra con fecha distinta."),
			() -> assertEquals(v1, v5, "Una venta no es idéntica a otra venta con la misma fecha, máquina y producto.")
			);
	}
}
