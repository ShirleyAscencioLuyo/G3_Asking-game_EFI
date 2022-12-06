package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection cnx = null;

    public static Connection conectar() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String user = "root";
            String pass = "admin";
            String url = "jdbc:mysql://localhost/BDRegistro";
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pass);

        } catch (Exception o) {
            System.out.println("Error en la conexion " + o);
        }
        return cnx;
    }

    public static void main(String[] args) throws Exception {
        Conexion.conectar();

//este metodo me retonar una variable tipo connection cnx 
        if (cnx != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("Sin Conexion");
        }
    }
}
