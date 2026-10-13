package pe.edu.upeu.project_final.test;

import pe.edu.upeu.project_final.implementacion.ClienteDaoImpl;
import pe.edu.upeu.project_final.interfaces.IClienteDao;
import pe.edu.upeu.project_final.modelo.Cliente;

import java.util.List;

public class TestCliente {
    static IClienteDao cl = new ClienteDaoImpl();

    public static void listar() {
        List<Cliente> lista = cl.listAll();
        if (lista != null && !lista.isEmpty()) {
            for (Cliente cliente : lista) {
                System.out.println("ID_CLIENTE:" + cliente.getIdcliente());
                System.out.println("NOMBRE:" + cliente.getNombre());
                System.out.println("APELLIDOS:" + cliente.getApellidos());
                System.out.println("DNI:" + cliente.getDni());
                System.out.println("EMAIL:" + cliente.getEmail());
                System.out.println("GENERO:" + cliente.getGenero());
            }
        } else {
            System.out.println("no hay registros");
        }
    }
    public static void insertar(){
        Cliente c = new Cliente();
        c.setNombre("Louis Aljandro");
        c.setApellidos("Atto Ib");
        c.setDni("6152126");
        c.setEmail("luis.atto@eaportal.org");
        c.setGenero("M");
        boolean result = cl.insert(c);
        if (result){
            System.out.println("insert success");
        }else{
            System.out.println("insert error");
        }
    }

    public static void eliminar(){
        Cliente c = new Cliente();
        c.setIdcliente(2);
        boolean result = cl.delete(c);
        if (result){
            System.out.println("delete success");
        }else{
            System.out.println("delete error");
        }
    }


    public static void main(String[] args) {
        System.out.println("************");
        listar();
        System.out.println("************");
        insertar();
    }


}
