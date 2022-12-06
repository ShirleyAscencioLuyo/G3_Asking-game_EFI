
package dao;

//Realizamos el contrado entre ICRUD
public interface ICRUD<T> { 
    public void registrar(T t) throws Exception;
    public void modificar(T t) throws Exception;
    public void eliminar(int codigo) throws Exception;
      
}
