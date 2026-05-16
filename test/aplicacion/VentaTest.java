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
	
	// Pendiente reemplazar por Mocks.
	@BeforeAll
	static void setUpAll() {
		maqReferencia = new MaquinaExpendedora("MaquinaDe-PRUEB4", 100);
		prodReferencia = new Producto();
	}
	
	
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
	@Tag("TestsSobreConstructores")
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
	
	@Test
	@DisplayName("Comportamiento general de setMaquina.")
	@Tag("IntegridadDeDatos")
	void testSetMaquina() {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		MaquinaExpendedora nuevaMaquina = new MaquinaExpendedora("VENDTEST-1001", 100);
		// Act
		
		v1.setMaquina(nuevaMaquina);
		
		// Assert
		assertEquals(v1.getMaquina(), nuevaMaquina, "Fallo al modificar maquina.");
	}
	
	@Test
	@DisplayName("Comportamiento general de setProducto.")
	@Tag("IntegridadDeDatos")
	void testSetProducto() {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		Producto nuevoProducto = new Producto();
		// Act
		
		v1.setProducto(nuevoProducto);
		
		// Assert
		assertEquals(v1.getProducto(), nuevoProducto, "Fallo al modificar producto.");
	}
	
	@ParameterizedTest
	@DisplayName("Comportamiento general de setUnidades.")
	@Tag("IntegridadDeDatos")
	@CsvSource({"1", "10", "1000"})
	void testSetUnidades(int unidades) {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 133);
		// Act
		
		v1.setUnidades(unidades);
				
		//Assert
		assertEquals(v1.getUnidades(), unidades, "Fallo al modificar unidades.");
	}
	
	@Test
	@DisplayName("Comportamiento general de setFecha.")
	@Tag("IntegridadDeDatos")
	void testSetFecha() {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		Instant nuevaFecha = Instant.now().minus(2, ChronoUnit.HOURS);
		// Act
		
		v1.setFecha(nuevaFecha);
				
		//Assert
		assertEquals(v1.getFecha(), nuevaFecha, "Fallo al modificar fecha.");
	}
	

	@ParameterizedTest
	@DisplayName("setUnidades no válidas.")
	@Tag("IntegridadDeDatos")
	@CsvSource({"-10", "-1", "0"})
	void testsetUnidadesNoValido(int unidades) {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		
		// Act
		
		//Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class,
				() -> {v1.setUnidades(unidades);},
				"Excepción no generada introducir unidades no-válidas.");
		assertEquals(
				iae.getMessage(), "Unidades no-validas introducidas.", 
				"Generada excepción inesperada.");
		
	}
	
	@ParameterizedTest
	@DisplayName("Constructor con unidades no validas")
	@Tag("TestsSobreConstructores")
	@CsvSource({"-10", "-1", "0"})
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
	@Tag("IntegridadDeDatos")
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
	@Tag("TestsSobreConstructores")
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
	@Tag("IntegridadDeDatos")
	void testsetProductoNull() {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		
		// Act
		
		//Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class,
				() -> {v1.setProducto(null);},
				"Excepción no generada al introducir un producto nulo.");
		assertEquals(
				iae.getMessage(), "Producto nulo introducido.", 
				"Generada excepción inesperada.");
		
	}
	
	@Test
	@DisplayName("Constructor con producto null")
	@Tag("TestsSobreConstructores")
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
	@DisplayName("setFecha a null")
	@Tag("IntegridadDeDatos")
	void testSetFechaNull() {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		
		// Act
		
		//Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class,
				() -> {v1.setFecha(null);},
				"Excepción no generada al introducir una fecha nula.");
		assertEquals(
				iae.getMessage(), "Fecha nula introducida.", 
				"Generada excepción inesperada.");
		
	}
	
	@Test
	@DisplayName("setFecha a futuro")
	@Tag("IntegridadDeDatos")
	void testSetFechaFuturo() {
		// Arrange
		Venta v1 = new Venta(maquina, producto, 10);
		
		// Act
		
		//Assert
		IllegalArgumentException iae = assertThrows(
				IllegalArgumentException.class,
				() -> {v1.setFecha(Instant.now().plus(1, ChronoUnit.HOURS));},
				"Excepción no generada al introducir una fecha futura.");
		assertEquals(
				iae.getMessage(), "Fecha futura introducida.", 
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
				v5 = new Venta(m1, p1, 10),
				v6 = null;
		String v7 = "notAVenta";
		
		Instant fecha1 = Instant.now().minus(1, ChronoUnit.HOURS),
				fecha2 = fecha1.minus(1, ChronoUnit.HOURS);
		
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
			() -> assertEquals(v1, v5, "Una venta no es idéntica a otra venta con la misma fecha, máquina y producto."),
			() -> assertNotEquals(v1, v6, "Una venta inicializada es idéntica a una nula."),
			() -> assertNotEquals(v1, v7, "Una venta es idéntica a un objeto de otra clase.")
			);
	}
}
