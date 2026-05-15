package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import aplicacion.MaquinaExpendedora;

public class VPrincipal extends JFrame {
	
	aplicacion.FachadaAplicacion fa;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JScrollPane scrollPane;
	private JTable tablaMaquinas;
	
	private JButton btnEditar;

	/**
	 * Create the frame.
	 */
	public VPrincipal(aplicacion.FachadaAplicacion fa) {
		this.fa = fa;
		
		_initComponents();
	}
	
	private void _initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textID = new JTextField();
		textID.setColumns(10);
		
		scrollPane = new JScrollPane();
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAnadirPerformed(e);
			}
		});
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditarPerformed(e);
			}
		});
		btnEditar.setEnabled(false);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalirPerformed(e);
			}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscarPerformed(e);
			}
		});
		
		JLabel lblId = new JLabel("ID:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(lblId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textID, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBuscar))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAadir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
							.addComponent(btnGuardar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalir)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar)
						.addComponent(lblId))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAadir)
						.addComponent(btnEditar)
						.addComponent(btnSalir)
						.addComponent(btnGuardar))
					.addContainerGap())
		);
		
		tablaMaquinas = new JTable();
        tablaMaquinas.setModel(new ModeloTablaMaquinas());
		scrollPane.setViewportView(tablaMaquinas);
		contentPane.setLayout(gl_contentPane);
	    }
	
		private void btnBuscarPerformed(ActionEvent evt) {
			ModeloTablaMaquinas m;

	        m=(ModeloTablaMaquinas) tablaMaquinas.getModel();
	        List<MaquinaExpendedora> listado;
	        String filtro = textID.getText().isEmpty() ? "" : textID.getText();
	        listado = new ArrayList<MaquinaExpendedora>(fa.cargarMaquinas(filtro).values());
	        m.setFilas(listado);
	        if (m.getRowCount() > 0) {
	        	tablaMaquinas.setRowSelectionInterval(0, 0);
	        	btnEditar.setEnabled(true);
	        }
	        else btnEditar.setEnabled(false);
		}
		
		private void btnAnadirPerformed(ActionEvent evt) {
			VMaquina vmaq = new VMaquina(fa);
			vmaq.setVisible(true);
			vmaq.setAlwaysOnTop(true);
		}
		
		private void btnEditarPerformed(ActionEvent evt) {
			ModeloTablaMaquinas m = (ModeloTablaMaquinas) tablaMaquinas.getModel();
			
			VMaquina vmaq = new VMaquina(fa, m.obtenerMaquina(tablaMaquinas.getSelectedRow()));
			
			vmaq.setVisible(true);
			vmaq.setAlwaysOnTop(true);
		}
	
		private void btnSalirPerformed(ActionEvent evt) {
    		this.dispose();
		}
}