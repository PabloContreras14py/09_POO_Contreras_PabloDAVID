package vallegrande.edu.pe.sistemaproductores.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3307/sistema_productores";

    private static final String USER = "root";

    private static final String PASSWORD = "123456";

    public static Connection conectar() {

        try {

            Connection conexion =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexión exitosa a MySQL");

            return conexion;

        } catch (SQLException e) {

            System.out.println("Error al conectar con MySQL");
            e.printStackTrace();

            return null;
        }
    }
}