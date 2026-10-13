
package pe.edu.upeu.project_final.test;

import pe.edu.upeu.project_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.project_final.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final.modelo.Habitacion;

import java.util.List;

public class TestHabitacion {

    static IHabitacionDao h = new HabitacionDaoImpl();

    public static void listar() {
        List<Habitacion> lista = h.listAll();

        if (lista != null && !lista.isEmpty()) {
            for (Habitacion habitacion : lista) {
                System.out.println("ID_HABITACION: " + habitacion.getIdhabitacion());
                System.out.println("NUMERO: " + habitacion.getNumero());
                System.out.println("PISO: " + habitacion.getPiso());
                System.out.println("TIPO: " + habitacion.getTipo());
                System.out.println("CAPACIDAD: " + habitacion.getCapacidad());
                System.out.println("PRECIO POR NOCHE: " + habitacion.getPrecioNoche());
                System.out.println("ESTADO: " + habitacion.getEstado());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("No hay registros");
        }
    }

    public static void insertar() {
        Habitacion h1 = new Habitacion();

        h1.setNumero("102");
        h1.setPiso(1);
        h1.setTipo("Doble");
        h1.setCapacidad(2);
        h1.setPrecioNoche(120.00);
        h1.setEstado("DISPONIBLE");

        boolean result = h.insert(h1);

        if (result) {
            System.out.println("Insert success");
        } else {
            System.out.println("Insert error");
        }
    }

    public static void eliminar() {
        Habitacion h1 = new Habitacion();

        h1.setIdhabitacion(2);

        boolean result = h.delete(h1);

        if (result) {
            System.out.println("Delete success");
        } else {
            System.out.println("Delete error");
        }
    }

    public static void main(String[] args) {
        System.out.println("========== LISTAR HABITACIONES ==========");
        listar();

        System.out.println("========== INSERTAR HABITACION ==========");
        insertar();

        System.out.println("========== LISTAR ACTUALIZADO ==========");
        listar();
    }
}
