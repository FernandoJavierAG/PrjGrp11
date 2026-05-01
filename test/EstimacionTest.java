package aplicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.*;
import org.mockito.*;

class EstimacionTest {

    AutoCloseable acl;

    @Mock
    Stock stock; // Clase colaboradora simulada con Mock

    @BeforeEach
    void setUp() throws Exception {
        acl = MockitoAnnotations.openMocks(this); // Inicialización mocks
    }

    @AfterEach
    void tearDown() throws Exception {
        acl.close(); // Eliminación mocks
    }

    // =========================================================
    // Tests de fechaFinStock (usando Mock de Stock)
    // =========================================================

    @Test
    @DisplayName("fechaFinStock: calcula correctamente la fecha con división exacta")
    void testFechaFinStock_divisionExacta() {
        // Arrange: 10 / 2.0 = 5 días exactos
        // Metemos los argmuentos de la clase mockito para probar todo este test
        when(stock.getCantidad()).thenReturn(10);
        Estimacion estimacion = new Estimacion(2.0f, stock);
        Instant ahora = Instant.now();

        // Act: Ejecutamos el metodo con esos datos
        Instant resultado = estimacion.fechaFinStock();

        // Assert: debe ser ~5 días desde ahora (tolerancia de ±1 día por timing)
        // Comparamos el resultado al que esperamos para ver si los test fallan
        assertTrue(resultado.isAfter(ahora.plus(4, ChronoUnit.DAYS)),"La fecha de fin de stock debe ser al menos 4 días en el futuro");
        assertTrue(resultado.isBefore(ahora.plus(6, ChronoUnit.DAYS)),"La fecha de fin de stock debe ser como máximo 6 días en el futuro");
    }

    @Test
    @DisplayName("fechaFinStock: aplica floor en división no exacta")
    void testFechaFinStock_aplicaFloor() {
        // Arrange: 7 / 3.0 = 2.33... -> floor = 2 días
        when(stock.getCantidad()).thenReturn(7);
        Estimacion estimacion = new Estimacion(3.0f, stock);
        Instant ahora = Instant.now();

        // Act
        Instant resultado = estimacion.fechaFinStock();

        // Assert: debe ser exactamente 2 días (no 3)
        assertTrue(resultado.isAfter(ahora.plus(1, ChronoUnit.DAYS)),"La fecha debe ser al menos 1 día en el futuro");
        assertTrue(resultado.isBefore(ahora.plus(3, ChronoUnit.DAYS)),"El floor de 2.33 debe dar 2 días, no 3");
    }

    @Test
    @DisplayName("fechaFinStock: la fecha resultado es posterior a hoy")
    void testFechaFinStock_esFechaFutura() {
        // Arrange
        when(stock.getCantidad()).thenReturn(30);
        Estimacion estimacion = new Estimacion(1.0f, stock);
        Instant ahora = Instant.now();

        // Act
        Instant resultado = estimacion.fechaFinStock();

        // Assert
        assertTrue(resultado.isAfter(ahora),"La fecha estimada de fin de stock debe ser en el futuro");
    }

    @Test
    @DisplayName("fechaFinStock: invoca getCantidad exactamente una vez")
    void testFechaFinStock_invocaGetCantidadUnaVez() {
        // Arrange
        when(stock.getCantidad()).thenReturn(20);
        Estimacion estimacion = new Estimacion(5.0f, stock);

        // Act
        estimacion.fechaFinStock();

        // Assert (verificación de comportamiento del mock)
        verify(stock, times(1)).getCantidad();
    }

    @Test
    @DisplayName("fechaFinStock: no invoca otros métodos de Stock innecesariamente")
    void testFechaFinStock_noInvocaMetodosInnecesarios() {
        // Arrange
        when(stock.getCantidad()).thenReturn(15);
        Estimacion estimacion = new Estimacion(3.0f, stock);

        // Act
        estimacion.fechaFinStock();

        // Assert: solo se debe haber invocado getCantidad, nada más
        verify(stock, times(1)).getCantidad();
        verifyNoMoreInteractions(stock);
    }

    @Test
    @DisplayName("fechaFinStock: stock con cantidad 0 da fecha igual a hoy")
    void testFechaFinStock_cantidadCero() {
        // Arrange: 0 / 5.0 = 0 días -> la fecha estimada es hoy mismo
        when(stock.getCantidad()).thenReturn(0);
        Estimacion estimacion = new Estimacion(5.0f, stock);
        Instant ahora = Instant.now();

        // Act
        Instant resultado = estimacion.fechaFinStock();

        // Assert: 0 días sumados a ahora => debe ser casi igual a ahora (mismo día)
        assertTrue(resultado.isAfter(ahora.minus(1, ChronoUnit.SECONDS)),"Con cantidad 0, la fecha de fin es hoy");
        assertTrue(resultado.isBefore(ahora.plus(1, ChronoUnit.DAYS)), "Con cantidad 0, la fecha de fin no debe ser mañana");
    }
}
