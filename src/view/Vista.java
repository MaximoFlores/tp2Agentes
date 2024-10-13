package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;


import org.openstreetmap.gui.jmapviewer.JMapViewer;


import model.Logica;

import javax.swing.JButton;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Vista {

	private JFrame frame;
	private Logica model;

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1157, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		model = new Logica(10);

		JPanel contenedorPrincipal = new JPanel();
		contenedorPrincipal.setBorder(null);
		frame.getContentPane().add(contenedorPrincipal, BorderLayout.CENTER);
		contenedorPrincipal.setLayout(null);

		JPanel contenedorMenu = new JPanel();
		contenedorMenu.setBounds(1, 0, 278, 600);
		contenedorMenu.setBackground(new Color(0, 128, 192));
		contenedorMenu.setLayout(null);
		contenedorPrincipal.add(contenedorMenu);


		String[] listaVert = new String[model.tamanioGrafo()];
		for (int i = 1; i <= model.tamanioGrafo(); i++) {
			listaVert[i-1] = "" + i;
		}
		
		JComboBox comboBVertice1 = new JComboBox(listaVert);
		comboBVertice1.setBounds(12, 92, 258, 42);
		contenedorMenu.add(comboBVertice1);

		JComboBox comboBVertice2 = new JComboBox(listaVert);
		comboBVertice2.setBounds(12, 188, 258, 42);
		contenedorMenu.add(comboBVertice2);


		JSpinner spinnerProbabilidad = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerProbabilidad.setBounds(221, 138, 49, 40);
		contenedorMenu.add(spinnerProbabilidad);

		JRadioButton btnRadiusAddLink = new JRadioButton("Añadir enlace");
		btnRadiusAddLink.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRadiusAddLink.setForeground(new Color(255, 255, 255));
		btnRadiusAddLink.setSelected(true);
		btnRadiusAddLink.setFocusable(false);
		btnRadiusAddLink.setOpaque(false);
		btnRadiusAddLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spinnerProbabilidad.setEnabled(btnRadiusAddLink.isSelected());
			}
		});
		btnRadiusAddLink.setBounds(34, 140, 123, 21);
		contenedorMenu.add(btnRadiusAddLink);

		JRadioButton btnRadiusRemoveLink = new JRadioButton("Eliminar enlace");
		btnRadiusRemoveLink.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRadiusRemoveLink.setForeground(new Color(255, 255, 255));
		btnRadiusRemoveLink.setOpaque(false);
		btnRadiusRemoveLink.setFocusable(false);
		btnRadiusRemoveLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spinnerProbabilidad.setEnabled(!btnRadiusRemoveLink.isSelected());
			}
		});
		btnRadiusRemoveLink.setBounds(34, 161, 123, 21);
		contenedorMenu.add(btnRadiusRemoveLink);

		ButtonGroup bg = new ButtonGroup();
		bg.add(btnRadiusRemoveLink);
		bg.add(btnRadiusAddLink);

		JButton btnModEnlace = new JButton("Modificar Enlace");
		btnModEnlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cadVert1 = (String) comboBVertice1.getSelectedItem();
				int vert1 = Integer.parseInt(cadVert1);	
				
				String cadVert2 = (String) comboBVertice2.getSelectedItem();
				int vert2 = Integer.parseInt(cadVert2);		
				
				int probabilidad = (Integer) spinnerProbabilidad.getValue();
				
				if(btnRadiusAddLink.isSelected()) {	
					model.establecerEnlace(vert1, vert2, probabilidad);
				}else {
					model.eliminarEnlace(vert1, vert2);
				}
			}
		});
		btnModEnlace.setFont(new Font("Dialog", Font.BOLD, 12));
		btnModEnlace.setBounds(30, 240, 230, 34);
		contenedorMenu.add(btnModEnlace);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 80, 260, 2);
		contenedorMenu.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 284, 260, 2);
		contenedorMenu.add(separator);

		JButton btnAgregarAgente = new JButton("Agregar agente");
		btnAgregarAgente.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAgregarAgente.setBounds(12, 296, 260, 34);
		contenedorMenu.add(btnAgregarAgente);

		JButton btnNewButton_1 = new JButton("Eliminar Agente");
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton_1.setBounds(12, 352, 198, 34);
		contenedorMenu.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Prim");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(147, 408, 123, 34);
		contenedorMenu.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Kruskal");
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton_2.setBounds(12, 408, 125, 34);
		contenedorMenu.add(btnNewButton_2);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 340, 260, 2);
		contenedorMenu.add(separator_2);

		JSpinner spinnerVerticeEliminar = new JSpinner(new SpinnerNumberModel(0, 0, model.tamanioGrafo(), 1));
		spinnerVerticeEliminar.setBounds(220, 352, 48, 34);
		contenedorMenu.add(spinnerVerticeEliminar);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 396, 260, 2);
		contenedorMenu.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(12, 452, 260, 2);
		contenedorMenu.add(separator_4);
		
		JLabel lblTiempoKruskal = new JLabel("");
		lblTiempoKruskal.setForeground(new Color(255, 255, 255));
		lblTiempoKruskal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempoKruskal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTiempoKruskal.setBounds(12, 464, 125, 75);
		contenedorMenu.add(lblTiempoKruskal);
		
		JLabel lblTiempoPrim = new JLabel("");
		lblTiempoPrim.setForeground(new Color(255, 255, 255));
		lblTiempoPrim.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempoPrim.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTiempoPrim.setBounds(147, 464, 121, 75);
		contenedorMenu.add(lblTiempoPrim);
		
		JLabel lblLogo = new JLabel("Agentes");
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 48));
		lblLogo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(null);
		lblLogo.setBounds(12, 10, 256, 60);
		contenedorMenu.add(lblLogo);

		JPanel contenedorMapa = new JPanel();
		contenedorMapa.setBounds(284, 0, 859, 600);
		contenedorMapa.setBackground(new Color(255, 0, 0));
		contenedorPrincipal.add(contenedorMapa);
		contenedorMapa.setLayout(new BorderLayout(0, 0));

		generarMapa(contenedorMapa);
	}

	public void generarMapa(JPanel container) {
		JMapViewer mapa = new JMapViewer();
		container.add(mapa);
	}
}
