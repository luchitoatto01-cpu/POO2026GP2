package pe.edu.upeu.project_final.interfaces;

import pe.edu.upeu.project_final.modelo.Habitacion;

import java.util.List;

public interface IHabitacionDao {
    boolean insert(Habitacion h);

    boolean update(Habitacion h);

    Habitacion searchById(int id);

    boolean delete(Habitacion h);

    List<Habitacion> listAll();

    Habitacion searchByNumero(String numero);
}
