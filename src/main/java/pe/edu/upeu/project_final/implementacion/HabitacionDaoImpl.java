package pe.edu.upeu.project_final.implementacion;

import pe.edu.upeu.project_final.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.utils.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDaoImpl implements IHabitacionDao {


    private Habitacion mapRow(ResultSet rs) throws SQLException {
        Habitacion h = new Habitacion();
        h.setIdhabitacion(rs.getInt("idhabitacion"));
        h.setNumero(rs.getString("numero"));
        h.setPiso(rs.getInt("piso"));
        h.setTipo(rs.getString("tipo"));
        h.setCapacidad(rs.getInt("capacidad"));
        h.setPrecioNoche(rs.getDouble("precio_noche"));
        h.setEstado(rs.getString("estado"));
        return h;
    }

    @Override
    public boolean insert(Habitacion h) {
        String query = "INSERT INTO habitacion(numero,piso,tipo,capacidad,precio_noche,estado) " +
                "VALUES(?,?,?,?,?,?);";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, h.getNumero());
            st.setInt(2, h.getPiso());
            st.setString(3, h.getTipo());
            st.setInt(4, h.getCapacidad());
            st.setDouble(5, h.getPrecioNoche());
            st.setString(6, h.getEstado());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar habitacion: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Habitacion h) {
        String query = "UPDATE habitacion SET numero=?, piso=?, tipo=?, capacidad=?, " +
                "precio_noche=?, estado=? WHERE idhabitacion=?;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, h.getNumero());
            st.setInt(2, h.getPiso());
            st.setString(3, h.getTipo());
            st.setInt(4, h.getCapacidad());
            st.setDouble(5, h.getPrecioNoche());
            st.setString(6, h.getEstado());
            st.setInt(7, h.getIdhabitacion());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar habitacion: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Habitacion searchById(int id) {
        String query = "SELECT * FROM habitacion WHERE idhabitacion=?;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar habitacion por id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Habitacion h) {
        String query = "DELETE FROM habitacion WHERE idhabitacion=?;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, h.getIdhabitacion());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar habitacion: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Habitacion> listAll() {
        List<Habitacion> lista = new ArrayList<>();
        String query = "SELECT * FROM habitacion ORDER BY piso, numero;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar habitaciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Habitacion searchByNumero(String numero) {
        String query = "SELECT * FROM habitacion WHERE numero=?;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, numero);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar habitacion por numero: " + e.getMessage());
        }
        return null;
    }
}
