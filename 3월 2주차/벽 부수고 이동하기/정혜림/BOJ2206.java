package day0318;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 { // 벽 부수고 이동하기

  static int N, M, ans;
  static int[][] map, visit;
  static int[] di = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
  static int[] dj = { 0, 1, 0, -1 };

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    visit = new int[N][M];

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = line.charAt(j) - '0';
        visit[i][j] = Integer.MAX_VALUE;
      }
    }

    ans = Integer.MAX_VALUE;
    getLen();

    System.out.println(ans);
  }

  private static void getLen() {

    Queue<Point> queue = new LinkedList<Point>();
    queue.offer(new Point(0, 0, 1, 0));
    visit[0][0] = 1;

    while (!queue.isEmpty()) {
      Point now = queue.poll();

      if (now.i == N - 1 && now.j == M - 1) {
        ans = now.dist;
        return;
      }

      for (int d = 0; d < 4; d++) {
        int ni = now.i + di[d];
        int nj = now.j + dj[d];

        if (ni < 0 || ni >= N || nj < 0 || nj >= M)
          continue;
        if (visit[ni][nj] <= now.bCnt)
          continue;

        if (map[ni][nj] == 0) {
          visit[ni][nj] = now.bCnt;
          queue.offer(new Point(ni, nj, now.dist + 1, now.bCnt));
        } else {
          if (now.bCnt == 0) {
            visit[ni][nj] = now.bCnt + 1;
            queue.offer(new Point(ni, nj, now.dist + 1, now.bCnt + 1));
          }
        }
      }
    } // while end
    ans = -1;
  }

  static class Point {
    int i, j, dist, bCnt;

    Point(int i, int j, int dist, int bCnt) {
      this.i = i;
      this.j = j;
      this.dist = dist;
      this.bCnt = bCnt;
    }

    @Override
    public String toString() {
      return "Point [i=" + i + ", j=" + j + ", bCnt=" + bCnt + ", dist=" + dist + "]";
    }
  }

}