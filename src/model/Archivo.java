package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafo.Arista;

public class Archivo {
	private ArrayList<String> apellidoAgentes;
	private ArrayList<Coordinate> coordenadasParaMapa;
	private ArrayList<Arista> aristasArchivo;
	private int tamGrafo;
	private static Archivo instance;

	private Archivo() {
		this.coordenadasParaMapa = new ArrayList<Coordinate>();
		this.aristasArchivo = new ArrayList<Arista>();
		this.apellidoAgentes = new ArrayList<String>();
		try {
			cargarEnlaces();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Archivo getInstance() {
		if(instance == null) {
			instance = new Archivo();
		}
		return instance;
	}

	public ArrayList<Arista> getAristas(){
		return this.aristasArchivo;
	}

	public int getTamGrafo() {
		return this.tamGrafo;
	}

	public ArrayList<Coordinate> getCoords(){
		return this.coordenadasParaMapa;
	}

	private void cargarEnlaces() throws IOException {
		try(FileReader fr = new FileReader("src/model/agentes.txt");Scanner sc = new Scanner(fr)) {
			saltarInstruccionesArchivo(sc);

			int i = 0;
			while(i < this.tamGrafo && sc.hasNext()){
				int origen = devolverNumeroInt(sc);
				int destino = devolverNumeroInt(sc);
				int probabilidad = devolverNumeroInt(sc);
				double coordX = devolverNumeroDouble(sc);
				double coordY = devolverNumeroDouble(sc);
				coordenadasParaMapa.add(new Coordinate(coordX, coordY));

				aristasArchivo.add(new Arista(origen,destino,probabilidad));

				sc.nextLine();
				i++;
			}
		} catch (IOException e) {
			throw new IOException("No se pudo cargar el archivo");
		}
	}

	private int devolverNumeroInt(Scanner sc) {
		String linea = sc.nextLine();
		return Integer.parseInt(linea);
	}

	private double devolverNumeroDouble(Scanner sc) {
		String linea = sc.nextLine();
		return Double.parseDouble(linea);
	}

	private void saltarInstruccionesArchivo(Scanner sc) {
		for (int i = 0; i < 6; i++) {
			sc.nextLine();
		}
		this.tamGrafo = devolverNumeroInt(sc);
		sc.nextLine();
		sc.nextLine();
		cargarApellidoAgentes(sc);
	}
	private void cargarApellidoAgentes(Scanner sc) {
		String cadApellidos = (String) sc.nextLine();
		String apellido = "";

		for (int i = 0; i < cadApellidos.length(); i++) {
			if(cadApellidos.indexOf(i) == ',') {
				this.apellidoAgentes.add(apellido);
				apellido = "";
			}else {
				apellido += cadApellidos.indexOf(i);
			}
		}
		sc.nextLine();
	}
}