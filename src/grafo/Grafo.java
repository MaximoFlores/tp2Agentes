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

	public void agregarArista(Arista arista) {
		this.vecinos.get(arista.getOrigen()).add(arista);
		Arista aristaInv = new Arista(arista.getDestino(), arista.getOrigen(), arista.getPeso());
		this.vecinos.get(arista.getDestino()).add(aristaInv);
	}

	public void eliminarArista(int i, int j) {
		this.vecinos.get(i).removeIf(Arista -> Arista.getDestino() == j);
		this.vecinos.get(j).removeIf(Arista -> Arista.getDestino() == i);
	}

	public void modArista(Arista ar) {
		eliminarArista(ar.getOrigen(), ar.getDestino());
		agregarArista(ar);
	}

	public void agregarVertice() {
		this.vecinos.add(new HashSet<Arista>());
	}
	
	public void eliminarVertice(int vert) {
		this.vecinos.remove(vert);
		for (HashSet<Arista> arista : vecinos) {
			arista.removeIf(Arista -> Arista.getDestino() == vert);
		}
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
	

	public HashSet<Arista> getAristasVecinos(int vert) {
		return this.vecinos.get(vert);
	}
	public HashSet<Integer> getVecinos(int vert){
		HashSet<Integer> listaVecinos = new HashSet<Integer>();
		for (Arista arista : this.vecinos.get(vert)) {
			listaVecinos.add(arista.getDestino());
		}
		return listaVecinos;
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
