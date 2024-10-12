package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

	public void establecerEnlace(Arista arista) {
		comprobarLimiteProbabilidad(arista);
		this.grafo.agregarArista(arista);
	}

	public void eliminarEnlace(int i, int j) {
		this.grafo.eliminarArista(i, j);
	}

	public void modificarEnlace(Arista arista) {
		comprobarLimiteProbabilidad(arista);
		modificarEnlace(arista);
	}

	private void comprobarLimiteProbabilidad(Arista arista) {
		if(arista.getPeso()>=100) {
			throw new IllegalArgumentException("la probabilidad debe de estar entre 0 y 100");
		}
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

	private void cargarArchivo(File file) throws IOException {
		try(FileReader fr = new FileReader(file);Scanner sc = new Scanner(fr);) {
			int i = 0;
            while(grafo.tamanio() < i && sc.hasNext()){
                int origen = Integer.parseInt(sc.nextLine());
                int destino = Integer.parseInt(sc.nextLine()) ;
                int probabilidad = Integer.parseInt(sc.nextLine());    
                
                establecerEnlace(new Arista(origen,destino,probabilidad));
                
                sc.nextLine();
                i++;
            }
        } catch (IOException e) {
        	throw new IOException("No se pudo cargar el archivo");
        }
    }
}
