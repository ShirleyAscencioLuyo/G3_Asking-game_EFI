package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import service.UtilSQL;

public class UsuarioImpl extends Conexion implements ICRUD<Usuario> {

    static int cantRegistros = 0;
//Establecer su funcionamiento 

    @Override
    public void registrar(Usuario t) throws Exception {
        System.out.println("REGISTRANDO....");

        String sql = "insert into usuario (nomusu, apeusu,celusu, fecnacusu) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getApellido());
            ps.setString(3, t.getCelular());
            ps.setDate(4, UtilSQL.convert(t.getFechaNacimiento()));
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en UsuarioImpl -> registar: " + e.getMessage());

        }
    }

    @Override
    public void modificar(Usuario t) throws Exception {
        String sql = "update usuario set nomusu=?, apeusu=?, celusu=?, fecnacusu=? where idusu=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);          
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getApellido());
            ps.setString(3, t.getCelular());
            ps.setDate(4, UtilSQL.convert(t.getFechaNacimiento()));
            ps.setInt(5, t.getIdusu());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en UsuarioImpl -> modificar : " + e.getMessage());

        }
    }

    @Override
    public void eliminar(int codigo) throws Exception {
        String sql = "delete from usuario where idusu=" + codigo;
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en UsuarioImpl/eliminar: " + e.getMessage());
        }

    }

    public void listar(DefaultTableModel modeloTabla, Integer tipo, String dato) throws Exception {
        // Tipo: 1-> Todos, 2->nombre, 3->apellido, 4->celular, 5->fecha Nacimiento
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "select * from usuario";
                break;
            case 2:
                sql = "select * from usuario where nomusu like '%" + dato + "%'";
                break;
            case 3:
                sql = "select * from usuario where apeusu like '%" + dato + "%'";
                break;
            case 4:
                sql = "select * from usuario where celusu like '%" + dato + "%'";
                break;
            case 5:
                sql = "select * from usuario where fecnacusu like '%" + dato + "%'";
                break;
        }

        String datos[] = new String[5];
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            cantRegistros = 0;
            while (rs.next()) {
                for (int i = 0; i < 5; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                modeloTabla.addRow(datos);
                cantRegistros++; // cantRegistros = cantRegistros + 1
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en UsuarioImpl/listar " + e.getMessage());
        }
    }

}
