package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class PanelMapa extends JPanel {

	private BufferedImage mapa;
	private int ancho, alto;
	private ArrayList<Point> puntos;
	private Image iconoAgente;
	private boolean a;


	public PanelMapa() {
		puntos = new ArrayList<Point>();
		this.setLayout(null);
		
		try {
			// Cargar la imagen (coloca la ruta correcta de tu archivo de imagen)
			mapa = ImageIO.read(new File("src/view/russiaMap.png"));
			iconoAgente = new ImageIcon("src/view/detective.png").getImage();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void agregarIcono(Point p) {
		puntos.add(p);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		ancho = this.getWidth();
		alto = this.getHeight();

		// Dibujar la imagen del mapa con la transformación
		g.drawImage(mapa, 0, 0 ,ancho, alto, null);
		
		for (Point point : puntos) {
			g.drawImage(iconoAgente, point.x, point.y, null);
		}
	}

}

