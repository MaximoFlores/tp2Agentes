package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Grafo {

	ArrayList<HashSet<Arista>> vecinos;

	public Grafo(int tamanio) {
		this.vecinos = new ArrayList<>();
		for (int i = 0; i < tamanio; i++) {
			this.vecinos.add(new HashSet<>());
		}
	}

	public void agregarArista(int i, int j, int peso) {
		this.vecinos.get(i).add(new Arista(i, j, peso));
		this.vecinos.get(j).add(new Arista(j, i, peso));
	}

	public void agregarArista(Arista arista) {
		this.vecinos.get(arista.getOrigen()).add(arista);
		this.vecinos.get(arista.getDestino()).add(new Arista(arista.getDestino(), arista.getOrigen(), arista.getPeso()));
	}

	public void mostrarGrafo() {
		for (int i = 0; i < vecinos.size(); i++) {
			System.out.print("Vertice: " + i + ", vecinos: ");
			for (Arista arista : vecinos.get(i)) {
				System.out.print(arista.toString() + ", ");
			}
			System.out.println("");
		}
	}

	public HashSet<Arista> getVecinos(int vert) {
		return this.vecinos.get(vert);
	}

	public int tamanio() {
		return this.vecinos.size();
	}

	public List<Arista> getTodasLasAristas(){
		List<Arista> ret = new LinkedList<>();
		for (HashSet<Arista> vecino : this.vecinos) {
			ret.addAll(vecino);
		}
		return ret;
	}
}
