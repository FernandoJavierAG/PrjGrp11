public class Stock {

    // Atributos
    private Producto producto;
    private MaquinaExpendedora maquina;
    private int cantidadActual;
    private int cantidadMaxima;

    // Constructor vacío
    public Stock() {
    }

    // Constructor completo
    public Stock(Producto producto, MaquinaExpendedora maquina, int cantidadActual, int cantidadMaxima) {
        this.producto = producto;
        this.maquina = maquina;
        this.cantidadActual = cantidadActual;
        this.cantidadMaxima = cantidadMaxima;
    }

    // Getters y Setters

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public MaquinaExpendedora getMaquina() {
        return maquina;
    }

    public void setMaquina(MaquinaExpendedora maquina) {
        this.maquina = maquina;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }
}
