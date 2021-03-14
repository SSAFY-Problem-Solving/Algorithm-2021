package day0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
벽을 최대 하나 부수고 이동할 수 있다
최단 경로 구하기
해당 칸에서 벽을 부쉈을 때, 안 부쉈을 때 2가지 경우의 최단 경로 수를 저장하는 배열이 필요
한 칸 이동할 때마다 d가 추가되므로 d가 작을 때만 이동하게 하면 visit 배열은 필요없다
벽을 부순 경우에는 벽이 있는 경우 진행이 불가능하다.
벽을 아직 안 부순 경우 벽이 있는 경우에도 진행이 가능하고 대신 벽을 부쉈다는 체크를 한다.
 */
public class BOJ2206_벽부수고이동하기 {

    static int N, M, ans;
    static char[][] map;
    static Queue<Point> queue;
    static int[][][] memo; // 벽 부수지않으면 [][][0], 한번 부수면 [][][1]
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static class Point {
        int r, c, d;
        boolean isBreak;

        public Point(int r, int c, int d, boolean isBreak) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.isBreak = isBreak;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        queue = new LinkedList<>();
        memo = new int[N][M][2];
        initMemo();

        simulate();

        System.out.println(ans);
    }

    private static void initMemo() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 2; k++) {
                    memo[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private static void simulate() {
        queue.add(new Point(0, 0, 1, false));
        memo[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int idx = now.isBreak ? 1 : 0;

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || now.d + 1 >= memo[nr][nc][idx]) {
                    continue;
                }

                if (map[nr][nc] == '1') { // 현재 위치가 벽이라면
                    if (now.isBreak) {
                        continue;
                    } else {
                        queue.add(new Point(nr, nc, now.d + 1, true));
                        memo[nr][nc][1] = Math.min(memo[nr][nc][1], now.d + 1);
                    }
                } else { // 벽이 아니면
                    queue.add(new Point(nr, nc, now.d + 1, now.isBreak));
                    memo[nr][nc][idx] = now.d + 1;
                }
            }
        }

        int tempAns = Math.min(memo[N - 1][M - 1][0], memo[N - 1][M - 1][1]);
        ans = tempAns == Integer.MAX_VALUE ? -1 : tempAns;
    }

}
