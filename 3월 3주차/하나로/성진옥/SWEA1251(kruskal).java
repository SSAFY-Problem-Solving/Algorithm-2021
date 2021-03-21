package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N1251_kruskal {
	static int N;
	static PriorityQueue<Edge> pq;
	static long[][] p;
	static int[] root;
	static double E;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			p = new long[2][N];
			root = new int[N];
			
			StringTokenizer st;
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					p[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			E = Double.parseDouble(br.readLine());
			
			pq = new PriorityQueue<>((o1, o2) -> {
				if(o1.w == o2.w) return 0;
				else if(o1.w > o2.w) return 1;
				else return -1;
			});
			
			setDistance();
			
			System.out.println("#" + tc + " " + kruskal());
		}
	}
	
	private static long kruskal() {
		int edgeCnt = 0;
		double weightSum = 0;
		
		while(edgeCnt < N-1) {
			Edge cur = pq.poll();
			
			if(union(cur.s, cur.e)) {
				edgeCnt++;
				weightSum += cur.w * E;
			}
		}
		
		return Math.round(weightSum);
	}

	private static void setDistance() {
		for(int i = 0; i < N; i++) {
			root[i] = i;
			for(int j = i+1; j < N; j++) {
				long dis = (p[0][i] - p[0][j])*(p[0][i] - p[0][j]) + (p[1][i] - p[1][j])*(p[1][i] - p[1][j]);
				pq.add(new Edge(i, j, dis));
			}
		}
	}
	
	private static boolean union(int a, int b) {
		int rA = findRoot(a);
		int rB = findRoot(b);
		
		if(rA == rB) return false;
		else {
			root[rB] = rA;
			return true;
		}
	}
	
	private static int findRoot(int a) {
		if(a != root[a]) {
			root[a] = findRoot(root[a]);
		}
		return root[a];
	}
	
	private static class Edge{
		int s, e;
		long w;
		
		Edge(int s, int e, long w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}
