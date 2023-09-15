package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	
	//Listar datos de Usuarios
	//Mostrando el tipo de Usuario
	
	public static void main(String[] args) {
		
		//1.ObtenerConexion = LLamar al persintence_unit
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		//2.Crear un manejador de entidades
		EntityManager manejador = fabrica.createEntityManager();
		
		// select * from tb_usuarios = list
		
		String jpql = "select u from Usuario u";
		List<Usuario> lstUsuario = manejador.createQuery(jpql, Usuario.class).getResultList();
		
		//Imprimir el listado
		for (Usuario u : lstUsuario) {
			System.out.println("Codigo...: " + u.getCod_usua());
			System.out.println("Nombre...: " + u.getNom_usua() + " " + u.getApe_usua());
			System.out.println("Tipo...: " + u.getObjTipo().getDescripcion());
			System.out.println("-------------------------------------------------");
		}
		
		manejador.close();
	}
}
