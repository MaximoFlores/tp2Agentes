package grafo;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class AGM {

	public static Grafo Prim(Grafo g) {
		HashSet<Integer> vertices = new HashSet<>();
		Grafo nuevoGrafo = new Grafo(g.tamanio());
		PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingInt(Arista::getPeso));
		vertices.add(0);
		pq.addAll(g.getAristasVecinos(0));

		while (vertices.size() < g.tamanio()) {
			Arista aristaPesoMin = pq.poll();
			if (aristaPesoMin != null && !vertices.contains(aristaPesoMin.getDestino())) {

				nuevoGrafo.agregarArista(aristaPesoMin);
				vertices.add(aristaPesoMin.getDestino());

				for (Arista arista : g.getAristasVecinos(aristaPesoMin.getDestino())) {
					if (!vertices.contains(arista.getDestino())) {
						pq.offer(arista);
					}
				}
			}
		}
		return nuevoGrafo;
	}

	public static Grafo Kruskal(Grafo g, List<Arista> aristas) {
		Collections.sort(aristas);
		Grafo nuevoGrafo = new Grafo(g.tamanio());
		UnionFind uf = new UnionFind(g.tamanio());

		for (Arista arista : aristas) {
			int vert1 = arista.getOrigen();
			int vert2 = arista.getDestino();
			if (uf.find(vert1) != uf.find(vert2)) {
				uf.union(vert1, vert2);
				nuevoGrafo.agregarArista(arista);
			}
		}
		return nuevoGrafo;
	}

}
