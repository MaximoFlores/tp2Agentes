package bfs;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import grafo.Arista;
import grafo.Grafo;


public class BFSTest2 {

	@Test(expected=IllegalArgumentException.class)
	public void grafoNullTest() {
		BFS.esConexo(null);
	}

	@Test
	public void grafoVacioTest() {
		assertTrue(BFS.esConexo(new Grafo(0)));
	}
	
	@Test
	public void grafoUnVerticeTest() {
		assertTrue(BFS.esConexo(new Grafo(1)));
	}
	
	@Test
	public void grafoDosVerticesAisladosTest() {
		assertFalse(BFS.esConexo(new Grafo(2)));
	}
	
	@Test
	public void grafoDosVerticesConexoTest() {
		Grafo g = new Grafo(2);
		g.agregarArista(new Arista(0,1,2));
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void grafoInconexoTest() {
		Grafo g = inicializarGrafoInconexo();
		
		assertFalse(BFS.esConexo(g));
	}
	
	@Test
	public void grafoConexoTest() {
		Grafo g = inicializarGrafoInconexo();
		g.agregarArista(new Arista(4,6,2));
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesInconexoTest() {
		Grafo g = inicializarGrafoInconexo();
		
		int[] esperado = {0, 1, 2, 3, 4};
		AssertAlcanzable.iguales(esperado, BFS.alcanzables(g, 0));
	}
	
	private Grafo inicializarGrafoInconexo() {
		Grafo g = new Grafo(7);
		g.agregarArista(new Arista(0,1,5));
		g.agregarArista(new Arista(0,2,6));
		g.agregarArista(new Arista(1,3,4));
		g.agregarArista(new Arista(2,4,8));
		g.agregarArista(new Arista(3,4,2));
		g.agregarArista(new Arista(5,6,9));
		
		return g;
		
		
	}

}
