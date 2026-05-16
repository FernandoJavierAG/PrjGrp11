package aplicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

class StockTest {

	/* ============================== *
	 * === PREPARACIÓN DE PRUEBAS === *
	 * ============================== */
	@BeforeEach
	void setUp() throws Exception{
		/* Preparar mocks de máquinas y productos para el repositorio de stocks */
		// Máquinas
		MaquinaExpendedora m1 = mock(MaquinaExpendedora.class);
		MaquinaExpendedora m2 = mock(MaquinaExpendedora.class);
		
		// Productos
		Producto p1 = mock(Producto.class);
		Producto p2 = mock(Producto.class);
		
		/* Definición de comportamiento de los mocks */
		// IDs de máquinas
		when(m1.getID()).thenReturn("Vendomatic-2931BIA3");
		when(m2.getID()).thenReturn("Snackmaster-ABBC77789");
		
		// Instantes de últimas actualizaciones de máquinas
		when(m1.getUltimaActualizacion()).thenReturn(Instant.ofEpochSecond(1000000000));
		when(m2.getUltimaActualizacion()).thenReturn(Instant.ofEpochSecond(1000000000));
		
		// Asignación de productos a máquinas
		// No devolvemos nada al no necesitar probar el setter de la clase máquina
		when(m1.setProductos(any(List.class))).thenReturn();
		when(m2.setProductos(any(List.class))).thenReturn();
		
		// IDs de productos
		when(p1.getID()).thenReturn("A3");
		when(p2.getID()).thenReturn("B11");
		
		/* Creación de stocks, que se añadirán automáticamente al repositorio */
		new Stock(p2, m1, 6, 15);
		new Stock(p1, m1, 0, 20);
		new Stock(p2, m2, 1, 10);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		// Destruimos el repositorio tras cada test
		Stock.repositorio = null;
	}
	
/* ======================================================================================================================================== */
	
	/* ===================== *
	 * === PRUEBAS - HU4 === *
	 * ===================== */
	
	// CP A
	@Test
	@DisplayName("Test 4A - CP válido")
	void testAsignarProductos() {
		// Arrange
		// Preparamos los mocks para la lista de productos y la máquina pertinentes
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Vendomatic-2931BIA3");
		
		Producto prod = mock(Producto.class);
		
		List<Producto> nuevosProductos = new ArrayList<>();
		nuevosProductos.add(prod);
		
		// Preparamos lista con 1 stock, que es el resultado esperado
		List<Stock> resultadoEsperado = new ArrayList<>();
		Stock stock = new Stock(prod, maquina, 0, 0);
		
		// Act
		// Obtenemos el repositorio, que debería estar modificado
		Stock.asignarProductos(maquina, nuevosProductos);
		List<Stock> repo = Stock.repositorio;
		
		// Assert
		// Comprobamos igualdad de arrays
		assertArrayEquals(resultadoEsperado, repo, "El repositorio no contiene el stock esperado.");
	}
	
	// CP B
	@Test
	@Tag("Nulo")
	@DisplayName("Test 4B - CP no válido, máquina nula")
	void testAsignarProductos_MaquinaNula() {
		// Arrange
		// Preparamos la lista de productos no nulos
		Producto prod = mock(Producto.class);
		
		List<Producto> nuevosProductos = new ArrayList<>();
		nuevosProductos.add(prod);
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.asignarProductos(null, nuevosProductos);}, "El método no lanza la excepción de máquina nula.");
	}
	
	// CP C
	@Test
	@Tag("Nulo")
	@DisplayName("Test 4C - CP no válido, ID máquina nulo")
	void testAsignarProductos_IDMaquinaNulo() {
		// Arrange
		// Preparamos la lista de productos no nulos y la máquina con ID nulo
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn(null);
		
		Producto prod = mock(Producto.class);
		
		List<Producto> nuevosProductos = new ArrayList<>();
		nuevosProductos.add(prod);
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.asignarProductos(maquina, nuevosProductos);}, "El método no lanza la excepción de ID de máquina nulo.");
	}
	
	// CP D
	@Test
	@Tag("Nulo")
	@DisplayName("Test 4D - CP no válido, producto nulo")
	void testAsignarProductos_ProductoNulo() {
		// Arrange
		// Preparamos la lista de productos no nulos y la máquina con ID nulo
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Vendomatic-2931BIA3");
		
		List<Producto> nuevosProductos = new ArrayList<>();
		nuevosProductos.add(null);
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.asignarProductos(maquina, nuevosProductos);}, "El método no lanza la excepción de producto nulo.");
	}
	
/* ======================================================================================================================================== */
	
/* ======================================================================================================================================== */
		
	/* ======================== *
	 * === SUBPRUEBAS - 7.1 === *
	 * ======================== */
	
	// CP A
	@Test
	@DisplayName("Test 7.1A - CP válido, 0 salidas")
	void testConsultarPorMaquina_0Salidas() {
		// Arrange
		// Preparamos los mocks para la máquina
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Inexistente-12345678");
		
		// Act
		List<Stock> resultadoReal = Stock.consultarPorMaquina(maquina);
		
		// Assert
		// Comprobamos igualdad de arrays
		assertEquals(0, resultadoReal.size(), "No se devuelve una lista vacía.");
	}
	
	// CP B
	@Test
	@DisplayName("Test 7.1B - CP válido, 1 salida")
	void testConsultarPorMaquina_1Salida() {
		// Arrange
		// Preparamos los mocks para la máquina
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Snackmaster-ABBC77789");
		
		// Act
		List<Stock> resultadoReal = Stock.consultarPorMaquina(maquina);
		
		// Assert
		// Comprobamos igualdad de tamaños de array
		assertEquals(1, resultadoReal.size(), "No se devuelve una lista de 1 elemento.");
		// Solo comprobaremos que el Id de la máquina asociada es el correcto
		assertEquals("Snackmaster-ABBC77789", resultadoReal.get(0).getMaquina().getID(), "No se devuelve el stock correcto.");
	}
	
	// CP C
	@Test
	@DisplayName("Test 7.1C - CP válido, 2 salidas")
	void testConsultarPorMaquina_2Salidas() {
		// Arrange
		// Preparamos los mocks para la máquina
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Vendomatic-2931BIA3");
		
		// Act
		List<Stock> resultadoReal = Stock.consultarPorMaquina(maquina);
		
		// Assert
		// Comprobamos igualdad de tamaños de array
		assertEquals(2, resultadoReal.size(), "No se devuelve una lista de 2 elementos.");
		// Comprobamos que las máquinas asociadas poseen el ID pedido
		assertAll(
				() -> {assertEquals("Vendomatic-2931BIA3", resultadoReal.get(0).getMaquina().getID(), "Primer stock no tiene la máquina pedida.");},
				() -> {assertEquals("Vendomatic-2931BIA3", resultadoReal.get(1).getMaquina().getID(), "Segundo stock no tiene la máquina pedida.");}
		);
	}
	
	// CP D
	@Test
	@Tag("Nulo")
	@DisplayName("Test 7.1D - CP no válido, máquina nula")
	void testConsultarPorMaquina_MaquinaNula() {
		// Arrange
		// No necesario
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.consultarPorMaquina(null);}, "El método no lanza la excepción de máquina nula.");
	}
	
	// CP E
	@Test
	@Tag("Nulo")
	@DisplayName("Test 7.1E - CP no válido, ID máquina nulo")
	void testConsultarPorMaquina_IDMaquinaNulo() {
		// Arrange
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn(null);
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.consultarPorMaquina(maquina);}, "El método no lanza la excepción de ID de máquina nulo.");
	}
	
/* ======================================================================================================================================== */
	
	/* ======================== *
	 * === SUBPRUEBAS - 7.2 === *
	 * ======================== */
	
	// CP A
	@Test
	@DisplayName("Test 7.2A - CP válido, 0 salidas")
	void testConsultarPorProducto_0Salidas() {
		// Arrange
		// Preparamos los mocks para el producto
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn("A14");
		
		// Act
		List<Stock> resultadoReal = Stock.consultarPorProducto(prod);
		
		// Assert
		// Comprobamos igualdad de arrays
		assertArrayEquals(0, resultadoReal.size(), "No se devuelve una lista vacía.");
	}
	
	// CP B
	@Test
	@DisplayName("Test 7.2B - CP válido, 1 salida")
	void testConsultarPorProducto_1Salida() {
		// Arrange
		// Preparamos los mocks para el producto
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn("A3");
		
		// Act
		List<Stock> resultadoReal = Stock.consultarPorProducto(prod);
		
		// Assert
		// Comprobamos igualdad de tamaños de array
		assertEquals(1, resultadoReal.size(), "No se devuelve una lista de 1 elemento.");
		// Solo comprobaremos que el Id del producto asociada es el correcto
		assertEquals("A3", resultadoReal.get(0).getProducto().getID(), "No se devuelve el stock correcto.");
	}
	
	// CP C
	@Test
	@DisplayName("Test 7.1C - CP válido, 2 salidas")
	void testConsultarPorProducto_2Salidas() {
		// Arrange
		// Preparamos los mocks para el producto
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn("B11");
		
		// Act
		List<Stock> resultadoReal = Stock.consultarPorProducto(prod);
		
		// Assert
		// Comprobamos igualdad de tamaños de array
		assertEquals(2, resultadoReal.size(), "No se devuelve una lista de 2 elementos.");
		// Comprobamos que las máquinas asociadas poseen el ID pedido
		assertAll(
				() -> {assertEquals("B11", resultadoReal.get(0).getProducto().getID(), "Primer stock no tiene el producto pedido.");},
				() -> {assertEquals("B11", resultadoReal.get(1).getProducto().getID(), "Segundo stock no tiene el producto pedido.");}
		);
	}
	
	// CP D
	@Test
	@Tag("Nulo")
	@DisplayName("Test 7.2D - CP no válido, producto nulo")
	void testConsultarPorProducto_ProductoNulo() {
		// Arrange
		//
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.consultarPorProducto(null);}, "El método no lanza la excepción de producto nulo.");
	}
	
	// CP E
	@Test
	@Tag("Nulo")
	@DisplayName("Test 7.2E - CP no válido, ID producto nulo")
	void testConsultarPorProducto_IDProductoNulo() {
		// Arrange
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn(null);
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.consultarPorProducto(prod);}, "El método no lanza la excepción de ID de producto nulo.");
	}
	
/* ======================================================================================================================================== */
	
	/* ======================== *
	 * === SUBPRUEBAS - 7.3 === *
	 * ======================== */
	
	// CP A
	@Test
	@DisplayName("Test 7.3A - CP válido")
	void testModificarStock() {
		// Arrange
		// Preparamos los mocks para la máquina y producto a buscar
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Vendomatic-2931BIA3");
		
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn("B11");
		
		// Act
		Stock.modificarStock(maquina, prod, 1, 10);
		List<Stock> repo = Stock.repositorio;
		
		// Buscamos en el repositorio el stock con los datos pedidos
		Stock resultadoEsperado = null;
		for(Stock stocks : repo) {
			if(stocks.getMaquina().getID().equals(maquina.getID())){
				if(stocks.getProducto().getID().equals(prod.getID())){
					resultadoEsperado = stocks;
				}
			}
		}
		
		// Assert
		// Comprobamos que el elemento buscado existe
		assertTrue(resultadoEsperado == null, "El repositorio no contiene el stock actualizado.");
		// Comprobamos que las nuevas cantidades son las solicitadas
		assertAll(
				() -> {assertEquals(1, resultadoEsperado.getCantidadActual(), "Cantidad actual no actualizada.");},
				() -> {assertEquals(10, resultadoEsperado.getCantidadMaxima(), "Cantidad máxima no actualizada.");}
		);
	}
	
	// CP B
	@Tag("Nulo")
	@Test
	@DisplayName("Test 7.3B - CP no válido, máquina nula")
	void testModificarStock_MaquinaNula() {
		// Arrange
		// Preparamos los mocks para el producto		
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn("B11");
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.modificarStock(null, prod, 1, 10);}, "El método no lanza la excepción de máquina nula.");
	}
	
	// CP C
	@Tag("Nulo")
	@Test
	@DisplayName("Test 7.3C - CP no válido, ID de máquina nulo")
	void testModificarStock_IDMaquinaNulo() {
		// Arrange
		// Preparamos los mocks para el producto y la máquina de id nulo
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn(null);
		
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn("B11");
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.modificarStock(maquina, prod, 1, 10);}, "El método no lanza la excepción de ID de máquina nulo.");
	}
	
	// CP D
	@Tag("Nulo")
	@Test
	@DisplayName("Test 7.3D - CP no válido, producto nulo")
	void testModificarStock_ProductoNulo() {
		// Arrange
		// Preparamos los mocks para la máquina
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Vendomatic-2931BIA3");
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.modificarStock(maquina, null, 1, 10);}, "El método no lanza la excepción de producto nulo.");
	}
	
	// CP E
	@Tag("Nulo")
	@Test
	@DisplayName("Test 7.3E - CP no válido, ID de producto nulo")
	void testModificarStock_IDProductoNulo() {
		// Arrange
		// Preparamos los mocks para el producto de Id nulo y la máquina
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Vendomatic-2931BIA3");
		
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn(null);
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.modificarStock(maquina, prod, 1, 10);}, "El método no lanza la excepción de ID de producto nulo.");
	}
	
	// CP F y G
	// Hacemos ambos casos de prueba mediante un test parametrizado
	@DisplayName("Test 7.3F+G - CP no válido, cantidades negativas")
	@ParameterizedTest(name="{index}.- cantidad nueva = {0}, cantidad máxima = {1}")
	@CsvSource({"-1,10","1,-1"})
	void testModificarStock_CantidadesIncorrectas(int nuevaCantidad, int cantidadMaxima) {
		// Arrange
		// Preparamos los mocks de máquina y producto
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getID()).thenReturn("Vendomatic-2931BIA3");
		
		Producto prod = mock(Producto.class);
		when(prod.getID()).thenReturn("B11");
		
		// Act + Assert para CP F
		assumingThat(nuevaCantidad < 0,
			() -> { IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
						() -> {Stock.modificarStock(maquina, prod, nuevaCantidad, cantidadMaxima);},
						"El método no lanza la excepción de cantidad nueva negativa."); });
		
		// Act para CP G
		assumingThat(cantidadMaxima < 0,
			() -> { Stock.modificarStock(maquina, prod, nuevaCantidad, cantidadMaxima); });
		
		// Act para verificar cambios correctos
		// Podemos usarlo en ambos CP, así también se comprueba que los datos se actualizan o no dependiendo de si se lanza o no excepción
		List<Stock> repo = Stock.repositorio;
		
		// Buscamos en el repositorio el stock con los datos pedidos
		Stock resultadoEsperado = null;
		for(Stock stocks : repo) {
			if(stocks.getMaquina().getID().equals(maquina.getID())){
				if(stocks.getProducto().getID().equals(prod.getID())){
					resultadoEsperado = stocks;
				}
			}
		}
		
		// Assert
		// CP F
		// Comprobamos que no se actualicen las cantidades, pues con la excepción lanzada no debería suceder
		assumingThat(nuevaCantidad < 0,
			() -> { assertAll(
					() -> {assertNotEquals(nuevaCantidad, resultadoEsperado.getCantidadActual(), "Cantidad actual se ha actualizado.");},
					() -> {assertNotEquals(cantidadMaxima, resultadoEsperado.getCantidadMaxima(), "Cantidad máxima se ha actualizado.");}
			); });
			
		// CP G
		// La cantidad máxima no debería actualizarse, pero la cantidad actual sí
		assumingThat(cantidadMaxima < 0,
			() -> { assertAll(
					() -> {assertEquals(nuevaCantidad, resultadoEsperado.getCantidadActual(), "Cantidad actual no actualizada.");},
					() -> {assertNotEquals(cantidadMaxima, resultadoEsperado.getCantidadMaxima(), "Cantidad máxima se ha actualizado.");}
			); });
	}
	
/* ======================================================================================================================================== */
	
	/* ======================== *
	 * === SUBPRUEBAS - 7.4 === *
	 * ======================== */
	
	// CP B
	@Tag("Nulo")
	@Test
	@DisplayName("Test 7.4B - CP no válido, máquina nula")
	void testConsultarUltimaActualizacion_MaquinaNula() {
		// Arrange
		//
		
		// Act
		//
		
		// Assert
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
				() -> {Stock.consultarUltimaActualizacion(null);}, "El método no lanza la excepción de máquina nula.");
	}
	
	// CP C
	@Tag("Nulo")
	@Test
	@DisplayName("Test 7.4C - CP no válido, máquina de última actualización nula")
	void testConsultarUltimaActualizacion_UltimaActualizacionNula() {
		// Arrange
		// Preparamos los mocks para la máquina
		MaquinaExpendedora maquina = mock(MaquinaExpendedora.class);
		when(maquina.getUltimaActualizacion()).thenReturn(null);
		
		// Act
		Instant resultadoReal = Stock.consultarUltimaActualizacion(maquina);
		
		// Assert
		assertEquals(null, resultadoReal, "El método no devuelve un resultado nulo.");		
	}
	
/* ======================================================================================================================================== */

}
