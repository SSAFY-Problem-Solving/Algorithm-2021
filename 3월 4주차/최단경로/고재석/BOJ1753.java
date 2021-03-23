import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1753 {
    /*
    최단 경로를 구하는 다익스트라 알고리즘을 활용해보자.
     */
    static class Edge{
        int end;
        int weight;

        public Edge(){}

        public Edge(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }

    static int V, E, K;
    static List<Edge>[] edgeList;

    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        edgeList = new List[V + 1];
        for(int i = 1; i <= V; i++){
            edgeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine()," ");
            int startVertex = Integer.parseInt(st.nextToken());
            int endVertex = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[startVertex].add(new Edge(endVertex, weight));
        }

        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        visited = new boolean[V + 1];

        dijkstra(K);

        for(int i = 1; i <= V; i++){
            System.out.println(K == i ? 0 : (distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]));
        }
    }

    private static void dijkstra(int startVertex) {

        // 초기 시작점은 일단 방문했다.
        visited[startVertex] = true;
        distance[startVertex] = 0;

        // 초기 시작점에서부터 이어지는 모든 정점의 거리를 distance 배열에 입력
        for(int i = 0; i < edgeList[startVertex].size(); i++){
            Edge curr = edgeList[startVertex].get(i);
            distance[curr.end] = Math.min(distance[curr.end], curr.weight);
        }

        // 모든 정점을 방문할 때까지 시작점 이후의 정점을 하나씩 선택
        boolean isStop = false;
        while(!isStop){

            // 아직 방문하지 않았으며 현재 정점에서 거리가 최소인 정점을 하나 선택
            int minDistance = Integer.MAX_VALUE;
            int minVertex = -1;
            for(int i = 1; i <= V; i++){
                if(!visited[i] && minDistance > distance[i]) {
                    minDistance = distance[i];
                    minVertex = i;
                }
            }

            // 방문하지 않은 곳이 있지만 초기 시작점에서 경로가 생길 수 없는 경우 멈춘다.
            if(minVertex == -1) {
                break;
            }

            // 선택된 정점을 방문
            visited[minVertex] = true;

            // 선택된 정점에 인접한 미방문 정점에 대해 거리 갱신
            for(int i = 0; i < edgeList[minVertex].size(); i++){
                Edge curr = edgeList[minVertex].get(i);
                if(!visited[curr.end]) {
                    distance[curr.end] = Math.min(distance[curr.end], distance[minVertex] + curr.weight);
                }
            }

            // 모든 정점을 방문했으면 isStop을 true로 바꾼다.
            for(int i = 1; i <= V; i++){
                if (!visited[i]) break;
                if (i == V) isStop = true;
            }
        }
    }
}
