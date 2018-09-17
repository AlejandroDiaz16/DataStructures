package Graphs;

public class Edge {
	int w;
	Object toVertex;
	
	
	public Edge(int w, Object toVertex) {
		super();
		this.w = w;
		this.toVertex = toVertex;
	}


	public Edge(Object toVertex) {
		super();
		this.toVertex = toVertex;
	}
	
	@Override
	public String toString() {
		return "Vertice: "+toVertex+" Valor:"+w;
	}
	
	
	
	
	
}
