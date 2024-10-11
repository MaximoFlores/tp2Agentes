package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
		this.grafo.agregarArista(arista);
	}
	
	public void eliminarEnlace(int i, int j) {
		this.grafo.eliminarArista(i, j);
	}

	public void modificarEnlace(Arista ar) {
		modificarEnlace(ar);
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
	
	public LinkedList<Integer> ejecutarBFS() {
		if(grafoAGM == null) {
			throw new RuntimeException("Para correr BFS primero debe de ejecutar algun algoritmo");
		}	
		return (LinkedList<Integer>) BFS.getOrdenBFS().clone();
	}
	
	
}
