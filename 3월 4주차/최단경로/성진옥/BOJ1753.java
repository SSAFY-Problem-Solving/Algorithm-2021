
package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N1753 {
	static int V, E, K;
	static List<Pos> graph[];
	static int[] dis;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		graph = new LinkedList[V+1];
		dis = new int[V+1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new LinkedList<>();
			dis[i] = -1;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[s].add(new Pos(e, w));
		}
		
		dis[K] = 0;
		setDis(K);
		
		for(int i = 1; i <= V; i++) {
			if(dis[i] == -1) System.out.println("INF");
			else System.out.println(dis[i]);
		}
	}
	
	private static void setDis(int K) {
		PriorityQueue<Pos> q = new PriorityQueue<>((o1, o2) -> {return o1.w - o2.w;});
		q.add(new Pos(K, 0));
		dis[K] = 0;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(Pos next : graph[cur.node]) {
				if(dis[next.node] != -1 && dis[next.node] <= dis[cur.node] + next.w) 
					continue;
				dis[next.node] = dis[cur.node] + next.w;
				q.add(new Pos(next.node, dis[next.node]));
			}
		}
	}
	
	private static class Pos{
		int node, w;
		Pos(int node, int w){
			this.node = node;
			this.w = w;
		}
	}
}
