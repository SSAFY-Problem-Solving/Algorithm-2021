package day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1992 { // 쿼드 트리

  static int[][] map;
  static StringBuilder sb;

  public static void main(String[] args) throws NumberFormatException, IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    map = new int[N][N];

    for (int i = 0; i < N; i++) {
      String[] line = br.readLine().split("");
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(line[j]);
      }
    }

    if (isOne(0, 0, N))
      System.out.println(map[0][0]);
    else {
      sb = new StringBuilder();
      QuadTree(0, 0, N);
      System.out.println(sb.toString());
    }
  }

  private static void QuadTree(int r, int c, int n) {
    if (n == 1) { // 크기가 1이면
      sb.append(map[r][c]);
      return;
    }
    sb.append("(");

    // 크기가 2^x이면
    for (int i = r; i < (r + n); i += n / 2) {
      for (int j = c; j < (c + n); j += n / 2) {
        if (!isOne(i, j, n / 2))
          QuadTree(i, j, n / 2);
        else
          sb.append(map[i][j]);
      }
    }
    sb.append(")");
  }

  private static boolean isOne(int r, int c, int n) {
    int prev = map[r][c];
    for (int i = r; i < (r + n); i++) {
      for (int j = c; j < (c + n); j++) {
        if (map[i][j] == prev) {
          prev = map[i][j];
        } else {
          return false;
        }
      }
    }
    return true;
  }

}
