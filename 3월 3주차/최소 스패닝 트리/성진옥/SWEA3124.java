package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N3124 {
	static int V, E;
	static int[] root;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			root = new int[V+1];
			for(int i = 1; i <= V; i++) root[i] = i;			
			
			pq = new PriorityQueue<Edge>((o1, o2) -> { 
				if(o1.w < o2.w) return -1;
				else if(o1.w > o2.w) return 1;
				else return 0;});
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
			
				pq.add(new Edge(s, e, w));
			}
			System.out.println("#" + tc + " " + kruskal());
		}
	}
	
	private static long kruskal() {
		int edgeCnt = 0;
		long weightSum = 0;
		
		while(edgeCnt < V-1) {
			Edge cur = pq.poll();
			
			if(union(cur.s, cur.e)) {
				weightSum += cur.w;
				edgeCnt++;
			}
		}
		return weightSum;
	}
	
	private static int findRoot(int node) {
		if(root[node] != node) {
			root[node] = findRoot(root[node]);
		}
		return root[node];
	}
	
	private static boolean union(int a, int b) {
		int rootA = findRoot(a);
		int rootB = findRoot(b);
		if(rootA == rootB) return false;
		else {
			root[rootB] = rootA;
			return true;
		}
	}
	
	private static class Edge{
		int s, e, w;
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}
