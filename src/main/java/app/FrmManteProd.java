package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox cboProveedor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 102, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(246, 74, 102, 14);
		contentPane.add(lblProveedor);
		
		cboProveedor = new JComboBox();
		cboProveedor.setBounds(307, 70, 106, 22);
		contentPane.add(cboProveedor);
		
		listado();
		llenaComboCategoria();
		llenaComboProveedor();
	}

	void llenaComboCategoria() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager manejador = fabrica.createEntityManager();
		
		String jpql = "select c from Categoria c";
		List<Categoria> lstCategoria = manejador.createQuery(jpql, Categoria.class).getResultList();
		
		cboCategorias.addItem("Seleccione");
		for (Categoria c : lstCategoria) {
			cboCategorias.addItem(c.getDescripcion());
		}
		
		manejador.close();
		
	}
	
	void llenaComboProveedor() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager manejador = fabrica.createEntityManager();
		
		String jpql = "select p from Proveedor p";
		List<Proveedor> lstProveedor = manejador.createQuery(jpql, Proveedor.class).getResultList();
		
		cboProveedor.addItem("Seleccione");
		for (Proveedor p : lstProveedor) {
			cboProveedor.addItem(p.getNombre_rs());
		}
		
		manejador.close();
		
	}
	
	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager manejador = fabrica.createEntityManager();
		
		String jpql = "select p from Producto p";
		List<Producto> lstProducto = manejador.createQuery(jpql, Producto.class).getResultList();
		
		for (Producto p : lstProducto) {
			Imprimir("Codigo...: " + p.getId_prod());
			Imprimir("descripcion...: " + p.getDes_prod());
			Imprimir("Stock...: " + p.getStk_prod());
			Imprimir("Precio...: " + p.getPre_prod());
			Imprimir("Categoria...: " + p.getObjCategoria().getDescripcion());
			Imprimir("Estado...: " + p.getEs_prod());
			Imprimir("Proveedor...: " + p.getObjProveedor().getNombre_rs());
			Imprimir("-------------------------------------------------");
		}
		
		manejador.close();
		
	}
	
	void Imprimir(String Texto) {
		txtSalida.append(Texto + "\n");
	}
	
	void registrar() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager manejador = fabrica.createEntityManager();
		
		String codigo = txtCodigo.getText();
		String descripcion = txtDescripcion.getText();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int categoria = cboCategorias.getSelectedIndex();
		int Proveedor = cboProveedor.getSelectedIndex();
		
		Producto p = new Producto();
		
		p.setId_prod(codigo);
		p.setDes_prod(descripcion);
		p.setStk_prod(stock);
		p.setPre_prod(precio);
		p.setIdcategoria(categoria);
		p.setEs_prod(1);
		p.setIdproveedor(Proveedor);

		
		try {
			
			manejador.getTransaction().begin();
			manejador.persist(p);
			manejador.getTransaction().commit();
			
			JOptionPane.showMessageDialog(this, "Registro Ok!!!");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e.getCause().getMessage());
		}
		manejador.close();
		listado();

	}
}
