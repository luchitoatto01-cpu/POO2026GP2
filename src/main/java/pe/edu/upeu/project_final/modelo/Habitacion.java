package pe.edu.upeu.project_final.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Habitacion {
    private final IntegerProperty idhabitacion = new SimpleIntegerProperty();
    private final StringProperty numero = new SimpleStringProperty();
    private final IntegerProperty piso = new SimpleIntegerProperty();
    private final StringProperty tipo = new SimpleStringProperty();
    private final IntegerProperty capacidad = new SimpleIntegerProperty(1);
    private final DoubleProperty precioNoche = new SimpleDoubleProperty();
    private final StringProperty estado = new SimpleStringProperty("DISPONIBLE");

    public Habitacion() {
    }

    public Habitacion(int idhabitacion, String numero, int piso, String tipo,
                      int capacidad, double precioNoche, String estado) {
        setIdhabitacion(idhabitacion);
        setNumero(numero);
        setPiso(piso);
        setTipo(tipo);
        setCapacidad(capacidad);
        setPrecioNoche(precioNoche);
        setEstado(estado);
    }

    public final int getIdhabitacion() { return idhabitacion.get(); }
    public final void setIdhabitacion(int value) { idhabitacion.set(value); }
    public final IntegerProperty idhabitacionProperty() { return idhabitacion; }

    public final String getNumero() { return numero.get(); }
    public final void setNumero(String value) { numero.set(value); }
    public final StringProperty numeroProperty() { return numero; }

    public final int getPiso() { return piso.get(); }
    public final void setPiso(int value) { piso.set(value); }
    public final IntegerProperty pisoProperty() { return piso; }

    public final String getTipo() { return tipo.get(); }
    public final void setTipo(String value) { tipo.set(value); }
    public final StringProperty tipoProperty() { return tipo; }

    public final int getCapacidad() { return capacidad.get(); }
    public final void setCapacidad(int value) { capacidad.set(value); }
    public final IntegerProperty capacidadProperty() { return capacidad; }

    public final double getPrecioNoche() { return precioNoche.get(); }
    public final void setPrecioNoche(double value) { precioNoche.set(value); }
    public final DoubleProperty precioNocheProperty() { return precioNoche; }

    public final String getEstado() { return estado.get(); }
    public final void setEstado(String value) { estado.set(value); }
    public final StringProperty estadoProperty() { return estado; }
}
