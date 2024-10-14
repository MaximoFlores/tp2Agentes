package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import model.Archivo;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToggleButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Vista {
	
	private JFrame frame;
	private Logica model;
	private JMapViewer mapa;
	private JTextField textFieldApellidoAgente;

	public Vista() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize()  {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1157, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}

		model = new Logica();

		JPanel contenedorPrincipal = new JPanel();
		contenedorPrincipal.setBorder(null);
		frame.getContentPane().add(contenedorPrincipal, BorderLayout.CENTER);
		contenedorPrincipal.setLayout(null);

		cargarMapa(contenedorPrincipal);

		cargarMenu(contenedorPrincipal);	
	}

	private void cargarMenu(JPanel contenedorPrincipal) {
		JPanel contenedorMenu = new JPanel();
		contenedorMenu.setBounds(0, 0, 278, 600);
		contenedorMenu.setBackground(new Color(0, 128, 192));
		contenedorMenu.setLayout(null);
		contenedorPrincipal.add(contenedorMenu);
		
		cargarModificacionDeAgentes(contenedorMenu);

		cargarBtnAgregarAgente(contenedorMenu);

		cargarEliminarAgente(contenedorMenu);

		botonesAGM(contenedorMenu);

		cargarSeparadores(contenedorMenu);

		cargarLblTiempoAGM(contenedorMenu);

		cargarLogoAgente(contenedorMenu);
		
		JLabel lblCoordXMap = new JLabel("000.00000");
		lblCoordXMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCoordXMap.setForeground(new Color(255, 255, 255));
		lblCoordXMap.setBackground(new Color(255, 255, 255));
		lblCoordXMap.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoordXMap.setBounds(35, 296, 100, 21);
		contenedorMenu.add(lblCoordXMap);
		
		JLabel lblCoordYMap = new JLabel("000.00000");
		lblCoordYMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCoordYMap.setForeground(new Color(255, 255, 255));
		lblCoordYMap.setBackground(new Color(255, 255, 255));
		lblCoordYMap.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoordYMap.setBounds(35, 326, 100, 21);
		contenedorMenu.add(lblCoordYMap);
		
		textFieldApellidoAgente = new JTextField("Introduce apellido");
		textFieldApellidoAgente.setFont(new Font("Tahoma", Font.ITALIC, 12));
		textFieldApellidoAgente.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textFieldApellidoAgente.getText().equals("Introduce apellido")) {
					textFieldApellidoAgente.setText("");
					textFieldApellidoAgente.setForeground(Color.black);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textFieldApellidoAgente.getText().isEmpty()) {
					textFieldApellidoAgente.setForeground(Color.gray);
					textFieldApellidoAgente.setText("Introduce apellido");
				}
			}
		});
		textFieldApellidoAgente.setBounds(147, 327, 121, 20);
		contenedorMenu.add(textFieldApellidoAgente);
		textFieldApellidoAgente.setColumns(10);
		
		JLabel lblCoordAclaration_1 = new JLabel("X: ");
		lblCoordAclaration_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCoordAclaration_1.setForeground(new Color(255, 255, 255));
		lblCoordAclaration_1.setBackground(new Color(255, 255, 255));
		lblCoordAclaration_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoordAclaration_1.setBounds(10, 296, 25, 21);
		contenedorMenu.add(lblCoordAclaration_1);
		
		JLabel lblCoordAclaration_2 = new JLabel("Y:");
		lblCoordAclaration_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCoordAclaration_2.setForeground(new Color(255, 255, 255));
		lblCoordAclaration_2.setBackground(new Color(255, 255, 255));
		lblCoordAclaration_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoordAclaration_2.setBounds(10, 326, 25, 21);
		contenedorMenu.add(lblCoordAclaration_2);
		
		JToggleButton tglbtnSeleccionarCoords = new JToggleButton("Elegir Coords");
		tglbtnSeleccionarCoords.setFont(new Font("Dialog", Font.BOLD, 12));
		tglbtnSeleccionarCoords.setFocusable(false);
		tglbtnSeleccionarCoords.setBounds(147, 296, 121, 20);
		contenedorMenu.add(tglbtnSeleccionarCoords);
	}

	private void cargarBtnAgregarAgente(JPanel contenedorMenu) {
		JButton btnAgregarAgente = new JButton("Agregar agente");
		btnAgregarAgente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregarAgente.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAgregarAgente.setFocusable(false);
		btnAgregarAgente.setBounds(10, 357, 260, 34);
		contenedorMenu.add(btnAgregarAgente);
	}

	private void cargarMapa(JPanel contenedorPrincipal) {
		JPanel contenedorMapa = new JPanel();
		contenedorMapa.setBounds(280, 0, 863, 600);
		contenedorMapa.setBackground(new Color(255, 0, 0));
		contenedorPrincipal.add(contenedorMapa);
		contenedorMapa.setLayout(new BorderLayout(0, 0));

		mapa = new JMapViewer();
		cargarMarcaMapaArchivo();
		contenedorMapa.add(mapa);
	}
	
	private void cargarMarcaMapaArchivo() {
		Archivo archivo = Archivo.getInstance();
		for (Coordinate coordenada : archivo.getCoords()) {
			MapMarker marker = new MapMarkerDot("AQUI", coordenada);
			mapa.addMapMarker(marker);
		}
	}

	private void cargarEliminarAgente(JPanel contenedorMenu) {
		JButton btnNewButton_1 = new JButton("Eliminar Agente");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 413, 198, 34);
		btnNewButton_1.setFocusable(false);
		contenedorMenu.add(btnNewButton_1);

		JSpinner spinnerVerticeEliminar = new JSpinner(new SpinnerNumberModel(0, 0, model.tamanioGrafo(), 1));
		spinnerVerticeEliminar.setBounds(218, 413, 48, 34);
		contenedorMenu.add(spinnerVerticeEliminar);
	}

	private void cargarLogoAgente(JPanel contenedorMenu) {
		JLabel lblLogo = new JLabel("Agentes");
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("Rockwell", Font.BOLD | Font.ITALIC, 48));
		lblLogo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFocusable(false);		
		lblLogo.setBounds(12, 10, 256, 60);
		contenedorMenu.add(lblLogo);
	}

	private void cargarSeparadores(JPanel contenedorMenu) {
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 80, 260, 2);
		contenedorMenu.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 284, 260, 2);
		contenedorMenu.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 401, 260, 2);
		contenedorMenu.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 457, 260, 2);
		contenedorMenu.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(12, 513, 260, 2);
		contenedorMenu.add(separator_5);
	}

	private void cargarModificacionDeAgentes(JPanel contenedorMenu) {
		String[] listaVert = new String[model.tamanioGrafo()];
		for (int i = 1; i <= model.tamanioGrafo(); i++) {
			listaVert[i-1] = "" + i;
		}

		JComboBox comboBVertice1 = new JComboBox(listaVert);
		comboBVertice1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBVertice1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBVertice1.setFocusable(false);
		comboBVertice1.setBounds(10, 92, 258, 42);
		contenedorMenu.add(comboBVertice1);

		JComboBox comboBVertice2 = new JComboBox(listaVert);
		comboBVertice2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBVertice2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBVertice2.setFocusable(false);
		comboBVertice2.setBounds(10, 188, 258, 42);
		contenedorMenu.add(comboBVertice2);

		JSpinner spinnerProbabilidad = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerProbabilidad.setBounds(221, 138, 49, 40);
		contenedorMenu.add(spinnerProbabilidad);

		JRadioButton btnRadiusAddLink = new JRadioButton("Añadir enlace");
		btnRadiusAddLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		btnRadiusRemoveLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		btnModEnlace.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
	}

	private void cargarLblTiempoAGM(JPanel contenedorMenu) {
		JLabel lblTiempoKruskal = new JLabel("");
		lblTiempoKruskal.setForeground(new Color(255, 255, 255));
		lblTiempoKruskal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempoKruskal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTiempoKruskal.setBounds(12, 525, 125, 65);
		lblTiempoKruskal.setFocusable(false);
		contenedorMenu.add(lblTiempoKruskal);

		JLabel lblTiempoPrim = new JLabel("");
		lblTiempoPrim.setForeground(new Color(255, 255, 255));
		lblTiempoPrim.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempoPrim.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTiempoPrim.setBounds(147, 525, 121, 65);
		lblTiempoPrim.setFocusable(false);
		contenedorMenu.add(lblTiempoPrim);
	}

	private void botonesAGM(JPanel contenedorMenu) {
		JButton btnGenerarPrim = new JButton("Prim");
		btnGenerarPrim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerarPrim.setFont(new Font("Dialog", Font.BOLD, 12));
		btnGenerarPrim.setBounds(147, 469, 123, 34);
		btnGenerarPrim.setFocusable(false);
		contenedorMenu.add(btnGenerarPrim);

		JButton btnGenerarKruskal = new JButton("Kruskal");
		btnGenerarKruskal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerarKruskal.setFont(new Font("Dialog", Font.BOLD, 12));
		btnGenerarKruskal.setBounds(10, 469, 125, 34);
		btnGenerarKruskal.setFocusable(false);
		contenedorMenu.add(btnGenerarKruskal);
	}
}