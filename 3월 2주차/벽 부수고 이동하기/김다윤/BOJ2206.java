package Boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

    static int N, M, ans;
    static char[][] map;
    static boolean[][][] visit;
    static Queue<Point> queue;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Point {
        int r, c, cnt;
        boolean breakable;
        Point(int r, int c, int cnt, boolean breakable) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.breakable = breakable;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;
        map = new char[N][M];
        visit = new boolean[N][M][2];
        queue = new LinkedList<>();

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        bfs();

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }

    private static void bfs() {

        queue.add(new Point(0, 0, 1, true));
        visit[0][0][0] = true;

        while(!queue.isEmpty()) {

            Point n = queue.poll();

            if(n.r == N-1 && n.c == M-1) {
                ans = Math.min(ans, n.cnt);
                return;
            }

            for(int dir=0; dir<4; dir++) {
                int nr = n.r + dr[dir];
                int nc = n.c + dc[dir];
                if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                if(map[nr][nc] == '1' && n.breakable && !visit[nr][nc][1]) { // 벽이라면,
                    visit[nr][nc][1] = true;
                    queue.add(new Point(nr, nc, n.cnt+1, false));
                }else if(map[nr][nc] == '0' && !visit[nr][nc][n.breakable ? 0 : 1]){ // 벽이 아니라면
                    visit[nr][nc][n.breakable ? 0 : 1] = true;
                    queue.add(new Point(nr, nc, n.cnt+1, n.breakable));
                }
            }

        }

    }

}
