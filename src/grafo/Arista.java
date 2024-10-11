package grafo;

public class Arista implements Comparable<Arista> {

	private final int origen;
	private final int destino;
	private final int peso;

	public Arista(int i, int j, int peso) {
		verificarParametros(i,j,peso);
		this.origen = i;
		this.destino = j;
		this.peso = peso;
	}

	public int getOrigen() {
		return origen;
	}

	public int getDestino() {
		return destino;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return "{d: " + destino + ", p: " + peso + "}";
	}

	public int compareTo(Arista other) {
		return this.peso - other.peso;
	}

	private void verificarParametros(int val1, int val2, int val3) {
		if (val1 < 0 || val2 < 0 || val3 < 0) {
			throw new IllegalArgumentException("El parametro pasado no puede ser negativo: " + val1 + ", " + val2 + ", " + val3);
		}
	}
}

