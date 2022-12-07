package controlador;

import Vista.UsuarioV;
import dao.UsuarioImpl;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;

public class UsuarioC {

    UsuarioImpl dao;
    Usuario usuario;
    Usuario vista;
    List<Usuario> listado;

    public UsuarioC() {
        dao = new UsuarioImpl();
        usuario = new Usuario();
    }

    public void registrar() {
        try {
            obtenerDatos();
            dao.registrar(usuario);
        } catch (Exception e) {
            System.out.println("Error en UsuarioC/registrar : " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            usuario.setIdusu(UsuarioV.idUsuario);
            obtenerDatos();
            dao.modificar(usuario);
        } catch (Exception e) {
            System.out.println("Error en UsuarioC/modificar : " + e.getMessage());
        }
    }

    public void eliminar() {
        try {
            usuario.setIdusu(UsuarioV.idUsuario);
            obtenerDatos();
            dao.eliminar(usuario.getIdusu());
        } catch (Exception e) {
            System.out.println("Error en UsuarioC/eliminar  : " + e.getMessage());
        }
    }

    public void listar(DefaultTableModel modelo, Integer tipo, String dato) {
        try {
            dao.listar(modelo, tipo, dato);
        } catch (Exception e) {
            System.out.println("Error en Listar Usuarios : " + e.getMessage());
        }
    }

    public void obtenerDatos() {
        usuario.setNombre(UsuarioV.txtNombre.getText());
        usuario.setApellido(UsuarioV.txtApellido.getText());
        usuario.setCelular(UsuarioV.txtCelular.getText());
        usuario.setFechaNacimiento(UsuarioV.jdFechaDeNacimiento.getDate());
    }
}
