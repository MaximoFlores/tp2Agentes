package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import grafo.Grafo;

public class BFS {
	
	private static List<Integer> L;
	private static boolean[] marcados;

	
	public static boolean esConexo(Grafo g) {
		if (g == null) {
			throw new IllegalArgumentException("El grafo no puede ser null.");
		}
		
		return g.tamanio() == 0 || alcanzables(g, 0).size() == g.tamanio();
	}

	public static Set<Integer> alcanzables(Grafo g, int origen) {
		Set<Integer> ret = new HashSet<Integer>();
		
		inicializarBusqueda(g, origen);
		
		while (!L.isEmpty()) {
			int i = seleccionarYMarcarVertice();
			ret.add(i);
			agregarVecinosNoMarcados(g, i);
			removerSeleccionado();
		}
		return ret;
	}

	private static void removerSeleccionado() {
		L.remove(0);
	}

	private static void agregarVecinosNoMarcados(Grafo g, int vertice) {		
		for (int vecino : g.getVecinos(vertice)) {
			if (!marcados[vecino] && !L.contains(vecino))
				L.add(vecino);
		}
	}

	private static int seleccionarYMarcarVertice() {
		int seleccionado = L.get(0);
		marcados[seleccionado] = true;
		return seleccionado;
	}

	private static void inicializarBusqueda(Grafo g, int origen) {
		L = new LinkedList<Integer>();
		marcados = new boolean[g.tamanio()];
		L.add(origen);
	}
	
	public static LinkedList<Integer> getOrdenBFS(){
		return  (LinkedList<Integer>) L;
	}
	



}
