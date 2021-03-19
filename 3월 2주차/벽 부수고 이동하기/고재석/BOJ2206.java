import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    /*
     BFS로 푼다
     큐에 넣을 클래스에 벽을 부쉈는지 여부를 같이 넣어서 처리한다.
     */

    public static class Position{
        int y;
        int x;
        int dist;
        boolean isBreak;

        public Position(){}
        public Position(int y, int x, int dist, boolean isBreak) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.isBreak = isBreak;
        }
    }

    static int N, M;
    static int[][] map;

    static int minDist;

    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
    static Queue<Position> queue;
    static int[][] distMapBreak;
    static int[][] distMapNoBreak;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            String input = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = input.charAt(j) - 48;
            }
        }

        distMapBreak = new int[N][M];
        distMapNoBreak = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(distMapBreak[i], Integer.MAX_VALUE);
            Arrays.fill(distMapNoBreak[i], Integer.MAX_VALUE);
        }

        minDist = Integer.MAX_VALUE;

        queue = new LinkedList<>();
        queue.add(new Position(0, 0, 1, false));

        while(!queue.isEmpty()) {
            Position curr = queue.poll();

            if(curr.y == N - 1 && curr.x == M - 1) {
                minDist = curr.dist;
                break;
            }

            for(int d = 1; d <= 4; d++){
                int ny = curr.y + dy[d];
                int nx = curr.x + dx[d];

                boolean nBreak = curr.isBreak;

                // 인덱스 초과하거나, 이미 한번 부쉈는데 또 벽이거나
                if(ny < 0 || nx < 0 || ny >= N || nx >= M || (nBreak && map[ny][nx] == 1)) {
                    continue;
                }

                if(map[ny][nx] == 1) {
                    nBreak = true;
                }

                if(nBreak && curr.isBreak) {
                    if(distMapBreak[ny][nx] <= curr.dist + 1) {
                        continue;
                    }
                    distMapBreak[ny][nx] = curr.dist + 1;
                }

                else if (!nBreak){
                    if(distMapNoBreak[ny][nx] <= curr.dist + 1) {
                        continue;
                    }
                    distMapNoBreak[ny][nx] = curr.dist + 1;
                }

                queue.add(new Position(ny, nx, curr.dist + 1, nBreak));
            }
        }

        System.out.println(minDist==Integer.MAX_VALUE?-1:minDist);
    }
}
