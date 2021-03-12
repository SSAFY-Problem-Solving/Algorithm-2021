package day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17406 { // 배열 돌리기

  static int N, M, K, ans;
  static Calculate[] rList, order;
  static boolean[] isSelected;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    ans = Integer.MAX_VALUE;

    int[][] map = new int[N][M];
    rList = new Calculate[K];
    order = new Calculate[K];
    isSelected = new boolean[K];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int k = 0; k < K; k++) {
      st = new StringTokenizer(br.readLine(), " ");
      rList[k] = new Calculate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()));
    }

    perm(0, map);

    System.out.println(ans);
  }

  private static void perm(int cnt, int[][] map) {

    if (cnt == K) {
      int[][] temp = new int[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          temp[i][j] = map[i][j];
        }
      }
      ans = Math.min(ans, rotate(temp));
      return;
    }

    for (int n = 0; n < K; n++) {
      if (isSelected[n])
        continue;
      order[cnt] = rList[n];
      isSelected[n] = true;
      perm(cnt + 1, map);
      isSelected[n] = false;
    }
  }

  private static int rotate(int[][] map) {

    int prev, curr;

    for (int k = 0; k < K; k++) {
      int row = 0, col = 0;
      int ms = (order[k].r - order[k].s) - 1, ns = (order[k].c - order[k].s) - 1;
      int me = (order[k].r + order[k].s) - 1, ne = (order[k].c + order[k].s) - 1;
      int m = me - ms + 1, n = ne - ns + 1;
      int[][] temp = input(m, n, k, map);
      while (row < m && col < n) {
        if (row + 1 == m || col + 1 == n)
          break;

        prev = temp[row + 1][col];

        for (int i = col; i < n; i++) {
          curr = temp[row][i];
          temp[row][i] = prev;
          prev = curr;
        }
        row++;

        for (int i = row; i < m; i++) {
          curr = temp[i][n - 1];
          temp[i][n - 1] = prev;
          prev = curr;
        }
        n--;

        if (row < m) {
          for (int i = n - 1; i >= col; i--) {
            curr = temp[m - 1][i];
            temp[m - 1][i] = prev;
            prev = curr;
          }
        }
        m--;

        if (col < n) {
          for (int i = m - 1; i >= row; i--) {
            curr = temp[i][col];
            temp[i][col] = prev;
            prev = curr;
          }
        }
        col++;
      }

      // 돌린 배열 다시 적용
      int is = (order[k].r - order[k].s) - 1, js = (order[k].c - order[k].s) - 1;
      int a = me - ms + 1, b = ne - ns + 1;
      for (int i = 0; i < a; i++) {
        js = (order[k].c - order[k].s) - 1;
        for (int j = 0; j < b; j++) {
          map[is][js] = temp[i][j];
          js++;
        }
        is++;
      }
    }

    int minR = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      int nowR = 0;
      for (int j = 0; j < M; j++) {
        nowR += map[i][j];
      }
      minR = Math.min(minR, nowR);
    }

    return minR;
  }

  private static int[][] input(int m, int n, int k, int[][] map) {

    int ms = (order[k].r - order[k].s) - 1, ns = (order[k].c - order[k].s) - 1;
    int me = (order[k].r + order[k].s) - 1, ne = (order[k].c + order[k].s) - 1;
    int[][] temp = new int[m][n];
    int r = 0, c = 0;

    for (int i = ms; i <= me; i++) {
      r = 0;
      for (int j = ns; j <= ne; j++) {
        temp[c][r] = map[i][j];
        r++;
      }
      c++;
    }
    return temp;
  }

  static class Calculate {
    int r, c, s;

    Calculate(int r, int c, int s) {
      this.r = r;
      this.c = c;
      this.s = s;
    }
  }

}
