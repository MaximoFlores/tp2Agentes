package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import bfs.BFS;
import grafo.Grafo;
import grafo.AGM;
import grafo.Arista;

public class Logica {

	Grafo grafo;
	Grafo grafoAGM;
	ArrayList<String> agentesNombres;

	public Logica(int tamanioGrafo) {
		this.grafo = new Grafo(tamanioGrafo);
		this.grafoAGM = null;
		this.agentesNombres = new ArrayList<>();// falta agregar nombres
	}

	public void añadirEspia(String nombreEsp) {
		this.grafo.agregarVertice();
		agentesNombres.add(nombreEsp);
	}

	public void eliminarEspia(int vert) {
		this.grafo.eliminarVertice(vert);
		agentesNombres.remove(vert);
	}

	public void establecerEnlace(int i, int j, int prob) {
		Arista ar = new Arista(i-1,j-1,prob);
		comprobarLimiteProbabilidad(ar);
		this.grafo.agregarArista(ar);
		System.out.println(ar.toString());
	}

	public void eliminarEnlace(int i, int j) {
		this.grafo.eliminarArista(i-1, j-1);
		System.out.println("arista:" + (i-1) + ", "+(j-1) + " eliminada");
	}

	private void comprobarLimiteProbabilidad(Arista arista) {
		if(arista.getPeso()>10) {
			throw new IllegalArgumentException("la probabilidad debe de estar entre 0 y 10");
		}
	}
	
	public int tamanioGrafo() {
		return grafo.tamanio();
	}

	public void ejecutarPrim() {
		verificarConexo();
		this.grafoAGM = AGM.Prim(this.grafo);
	}

	public void ejecutarKruskal() {
		verificarConexo();
		this.grafoAGM = AGM.Kruskal(this.grafo, this.grafo.getTodasLasAristas());
	}

	private void verificarConexo() {
		if(!BFS.esConexo(grafo)) {
			throw new RuntimeException("No se puede ejecutar ningun algoritmo si el grafo no es conexo");
		}
	}

	private void cargarEnlaces(File file) throws IOException {
		try(FileReader fr = new FileReader("src/model/agentes.txt");Scanner sc = new Scanner(fr);) {
			int i = 0;
            while(i < grafo.tamanio() && sc.hasNext()){
                int origen = Integer.parseInt(sc.nextLine());
                int destino = Integer.parseInt(sc.nextLine()) ;
                int probabilidad = Integer.parseInt(sc.nextLine());
                double coordX = Double.parseDouble(sc.nextLine());
                double coordY = Double.parseDouble(sc.nextLine());
                Coordinate coordAgente = new Coordinate(coordX, coordY);
                
                
                establecerEnlace(origen,destino,probabilidad);
                
                sc.nextLine();
                i++;
            }
        } catch (IOException e) {
        	throw new IOException("No se pudo cargar el archivo");
        }
    }
}
