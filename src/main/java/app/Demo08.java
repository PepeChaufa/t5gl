package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import model.Usuario;

public class Demo08 {
	
	//Listado de los Usuarios segun el tipo de usuario(filtro)
	
	public static void main(String[] args) {
		
		//1.ObtenerConexion = LLamar al persintence_unit
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		//2.Crear un manejador de entidades
		EntityManager manejador = fabrica.createEntityManager();
		
		// select * from tb_usuarios where idtipo = ?
		
		String usuario = JOptionPane.showInputDialog("Ingrese Usuario: ");
		String pass = JOptionPane.showInputDialog("Ingrese Contraseña: ");
		
		String jpql = "select u from Usuario u where u.usr_usua = :xtip and u.cla_usua = :clave";
		try {
			
			Usuario u = manejador.createQuery(jpql, Usuario.class).setParameter("xtip", usuario).setParameter("clave", pass).getSingleResult();
			
			FrmManteProd m = new FrmManteProd();
			m.setVisible(true);
			
				
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuario o clave Incorrecta");
		}
		
		manejador.close();
	}
}
