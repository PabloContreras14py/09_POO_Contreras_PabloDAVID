package vallegrande.edu.pe.sistemaproductores.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductorDAO {

    public List<Productor> listar() {
        List<Productor> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, dni, telefono, comunidad FROM productores";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Productor p = new Productor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDni(rs.getString("dni"));
                p.setTelefono(rs.getString("telefono"));
                p.setComunidad(rs.getString("comunidad"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productores: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public boolean registrar(Productor p) {
        String sql = "INSERT INTO productores (nombre, dni, telefono, comunidad) VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDni());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getComunidad());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar productor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Productor p) {
        String sql = "UPDATE productores SET nombre = ?, dni = ?, telefono = ?, comunidad = ? WHERE id = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDni());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getComunidad());
            ps.setInt(5, p.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar productor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM productores WHERE id = ?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar productor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}