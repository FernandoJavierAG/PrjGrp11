package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

import aplicacion.MaquinaExpendedora;
import aplicacion.Localizacion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VMaquina extends JFrame {
	
	aplicacion.FachadaAplicacion fa;
	MaquinaExpendedora maquina;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMarca;
	private JTextField textNSerie;
	private JTextField textAltitud;
	private JTextField textLatitud;
	private JTextField textLongitud;
	private JTextField textCapacidad;

	/**
	 * Create the frame.
	 */
	
	public void _postInit() {
		if(maquina != null) {
			textMarca.setText(maquina.getMarca());
			textNSerie.setText(maquina.getNumSerie());
			textLatitud.setText(String.valueOf(maquina.getLatitud()));
			textLongitud.setText(String.valueOf(maquina.getLongitud()));
			textAltitud.setText(String.valueOf(maquina.getAltitud()));
		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public VMaquina(aplicacion.FachadaAplicacion fa) {
		this.fa = fa;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalirPerformed(e);
			}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardarPerformed(e);
			}
		});
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		textAltitud = new JTextField();
		textAltitud.setColumns(10);
		
		textLatitud = new JTextField();
		textLatitud.setColumns(10);
		
		textLongitud = new JTextField();
		textLongitud.setColumns(10);
		
		JLabel lblAltitud = new JLabel("Altitud:");
		
		JLabel lblLatitud = new JLabel("Latitud:");
		
		JLabel lblLongitud = new JLabel("Longitud:");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textLatitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLatitud))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLongitud)
						.addComponent(textLongitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textAltitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAltitud))
					.addContainerGap(144, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblAltitud)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textAltitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLatitud)
								.addComponent(lblLongitud))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textLatitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textLongitud, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(23))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblMarca = new JLabel("Marca:");
		
		textMarca = new JTextField();
		textMarca.setColumns(10);
		
		textNSerie = new JTextField();
		textNSerie.setColumns(10);
		
		JLabel label = new JLabel("-");
		
		JLabel lblNmeroDeSerie = new JLabel("Número de Serie:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label))
						.addComponent(lblMarca))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNmeroDeSerie)
						.addComponent(textNSerie, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(166, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMarca)
						.addComponent(lblNmeroDeSerie))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(textNSerie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblProductos = new JLabel("Productos:");
		
		JLabel lblCapacidad = new JLabel("Capacidad:");
		
		textCapacidad = new JTextField();
		textCapacidad.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnGuardar)
							.addGap(6)
							.addComponent(btnSalir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProductos)
							.addGap(121)
							.addComponent(lblCapacidad)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textCapacidad, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductos)
						.addComponent(lblCapacidad)
						.addComponent(textCapacidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar)
						.addComponent(btnSalir))
					.addGap(7))
		);
		contentPane.setLayout(gl_contentPane);

		
		_postInit();
	} 
	
	public VMaquina(aplicacion.FachadaAplicacion fa, MaquinaExpendedora maquina) {
		this.maquina = maquina;
		this(fa);
	}
	
	public void guardarMaquina() {
		String newID = textMarca.getText() + "-" + textNSerie.getText();
		Integer newCapacidad = Integer.parseInt(textCapacidad.getText());
		Localizacion newLoc = new Localizacion(
				Float.parseFloat(textLatitud.getText()),
				Float.parseFloat(textLongitud.getText()), 
				Float.parseFloat(textAltitud.getText())
				);
		
		try {
		
		if(this.maquina == null)
			maquina = new MaquinaExpendedora(newID, newCapacidad, newLoc);
		else
			maquina.setID(newID);
			maquina.setCapacidad(newCapacidad);
			maquina.setLoc(newLoc);
		} catch(IllegalArgumentException error) {
			JOptionPane.showMessageDialog(this, error.getMessage()); 
		}
		
		fa.guardarMaquina(maquina);
	}
	
    private void btnGuardarPerformed(ActionEvent evt) {  
    	guardarMaquina();
    }
    
    private void btnSalirPerformed(ActionEvent evt) {  
        this.dispose();
    }

}
