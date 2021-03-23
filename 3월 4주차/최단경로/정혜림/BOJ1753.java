package day0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 { // 최단 경로

  static int V, E, K;
  static List<Edge>[] graph;
  static StringBuilder sb;
  static boolean[] visit;
  static int[] distance;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(br.readLine());

    graph = new ArrayList[V + 1];

    for (int g = 1; g <= V; g++) {
      graph[g] = new ArrayList<>();
    }

    visit = new boolean[V + 1];
    distance = new int[V + 1];

    Arrays.fill(distance, Integer.MAX_VALUE);

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine(), " ");

      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      graph[s].add(new Edge(e, w));
    }

    PriorityQueue<Edge> pQueue = new PriorityQueue<>();
    distance[K] = 0;
    pQueue.offer(new Edge(K, 0));

    while (!pQueue.isEmpty()) {
      int now = pQueue.poll().num;

      if (visit[now])
        continue;

      visit[now] = true;

      for (Edge e : graph[now]) {
        if (distance[e.num] > distance[now] + e.dist) {
          distance[e.num] = distance[now] + e.dist;
          pQueue.offer(new Edge(e.num, distance[e.num]));
        }
      }
    }

    sb = new StringBuilder();
    for (int i = 1; i <= V; i++) {
      sb.append((distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]) + "\n");
    }

    System.out.println(sb.toString());
  }

  static class Edge implements Comparable<Edge> {
    int num, dist;

    Edge(int num, int dist) {
      this.num = num;
      this.dist = dist;
    }

    @Override
    public int compareTo(Edge o) {
      return this.dist - o.dist;
    }
  }
}