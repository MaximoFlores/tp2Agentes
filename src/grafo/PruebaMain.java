package grafo;

public class PruebaMain {
	public static void main(String[] args) {
		Grafo g = new Grafo(6);
		g.agregarArista(0, 1, 1);
		g.agregarArista(0, 2, 2);
		g.agregarArista(1, 2, 2);
		g.agregarArista(1, 3, 3);
		g.agregarArista(2, 4, 4);
		g.agregarArista(2, 3, 3);
		g.agregarArista(3, 5, 6);
		g.agregarArista(4, 5, 5);

		System.out.println("Grafo Viejo: ");
		g.mostrarGrafo();
		System.out.println("");
		//        System.out.println("Prim: ");
		//        Grafo gp = AGM.Prim(g);
		//        System.out.println("");
		//        gp.mostrarGrafo();
		//        System.out.println("");
		System.out.println("Kruskal: ");
		Grafo gp2 = AGM.Kruskal(g, g.getTodasLasAristas());
		gp2.mostrarGrafo();
	}
}
