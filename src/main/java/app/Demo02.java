package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	
	//Actualizar datos de Usuarios
	
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
			manejador.merge(u);
			manejador.getTransaction().commit();
			
			System.out.println("Actualizacion Ok!!!");
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getCause().getMessage());
		}
		manejador.close();
	}
}
