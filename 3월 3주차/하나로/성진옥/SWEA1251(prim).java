package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N1251_prim {
	static int N;
	static long[][] p;
	static double E;
	static List<Node>[] graph;
	static boolean[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			p = new long[2][N];
			
			graph = new LinkedList[N];
			select = new boolean[N];
			
			StringTokenizer st;
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					p[i][j] = Integer.parseInt(st.nextToken());
					
					graph[j] = new LinkedList<>();
				}
			}
			
			E = Double.parseDouble(br.readLine());
			
			setDistance();
			
			System.out.println("#" + tc + " " + kruskal());
		}
	}
	
	private static long kruskal() {
		int nodeCnt = 0;
		double weightSum = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.w == o2.w) return 0;
			else if(o1.w > o2.w) return 1;
			else return -1;
		});
		
		pq.add(new Node(0,0));
		while(nodeCnt < N) {
			Node cur = pq.poll();
			
			if(select[cur.node]) continue;
			
			select[cur.node] = true;
			nodeCnt++;
			weightSum += cur.w * E;

			for(Node next: graph[cur.node]) {
				if(select[next.node]) continue;
				
				pq.add(new Node(next.node, next.w));
			}
		}
		return Math.round(weightSum);
	}

	private static void setDistance() {
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				long dis = (p[0][i] - p[0][j])*(p[0][i] - p[0][j]) + (p[1][i] - p[1][j])*(p[1][i] - p[1][j]);
				graph[i].add(new Node(j, dis));
				graph[j].add(new Node(i, dis));
			}
		}
	}
	
	private static class Node{
		int node;
		long w;
		
		Node(int node, long w){
			this.node = node;
			this.w = w;
		}
	}
}
