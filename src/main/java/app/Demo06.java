package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Usuario;

public class Demo06 {
	
	//Listar datos de Usuarios
	//Mostrando el tipo de Usuario
	
	public static void main(String[] args) {
		
		//1.ObtenerConexion = LLamar al persintence_unit
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		//2.Crear un manejador de entidades
		EntityManager manejador = fabrica.createEntityManager();
		
		// select * from tb_usuarios = list
		
		String jpql = "select p from Producto p";
		List<Producto> lstProducto = manejador.createQuery(jpql, Producto.class).getResultList();
		
		//Imprimir el listado
		for (Producto p : lstProducto) {
			System.out.println("Codigo...: " + p.getId_prod());
			System.out.println("descripcion...: " + p.getDes_prod());
			System.out.println("Stock...: " + p.getStk_prod());
			System.out.println("Precio...: " + p.getPre_prod());
			System.out.println("Categoria...: " + p.getObjCategoria().getDescripcion());
			System.out.println("Estado...: " + p.getEs_prod());
			System.out.println("Proveedor...: " + p.getObjProveedor().getNombre_rs());
			System.out.println("-------------------------------------------------");
		}
		
		manejador.close();
	}
}
