package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

//GUI

public class Demo01 {
	
	//Registrar Usuarios
	
	public static void main(String[] args) {
		
		//1.ObtenerConexion = LLamar al persintence_unit
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		//2.Crear un manejador de entidades
		EntityManager manejador = fabrica.createEntityManager();
		
		//Proceso
		Usuario u = new Usuario();
		
		//Ojo!! si el proceso es un registrar, actualizar o eliminar se tiene que usar transaccion
		
		try {
			
			manejador.getTransaction().begin();
			manejador.persist(u);
			manejador.getTransaction().commit();
			
			System.out.println("Registro Ok!!!");
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getCause().getMessage());
		}
		manejador.close();
	}

}
