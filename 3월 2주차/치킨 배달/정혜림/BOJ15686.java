package day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686 { // 치킨 배달
  static int N, M, ans;
  static Point[] list; // 조합 리스트 배열
  static ArrayList<Point> hList, cList; // 집, 치킨가게 리스트

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    ans = Integer.MAX_VALUE;

    hList = new ArrayList<Point>();
    cList = new ArrayList<Point>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < N; j++) {
        int now = Integer.parseInt(st.nextToken());
        if (now == 1)
          hList.add(new Point(i, j));
        if (now == 2)
          cList.add(new Point(i, j));
      }
    }

    list = new Point[M];
    comb(0, 0);
    System.out.println(ans);
  }

  private static void comb(int start, int cnt) {

    if (cnt == M) {
      chickenLen(list);
      return;
    }

    for (int i = start; i < cList.size(); i++) {
      list[cnt] = cList.get(i);
      comb(i + 1, cnt + 1);
    }
  }

  private static void chickenLen(Point[] cStores) {

    int nowCL = 0; // 현재 순열에서 나올 수 있는 최소 치킨거리
    for (int i = 0; i < hList.size(); i++) {
      int minCL = Integer.MAX_VALUE; // 각 집에서 가장 가까운 치킨집과의 거리
      Point house = hList.get(i);
      for (int m = 0; m < cStores.length; m++) {
        Point cStore = cStores[m];

        // 현재 집에서 가장 가까운 치킨집과의 치킨거리를 minCL에 저장
        minCL = Math.min(minCL, (Math.abs(house.i - cStore.i) + Math.abs(house.j - cStore.j)));
      }
      nowCL += minCL;
    }
    ans = Math.min(ans, nowCL);
  }

  static class Point {
    int i, j;

    Point(int i, int j) {
      this.i = i;
      this.j = j;
    }

    @Override
    public String toString() {
      return "Point [i=" + i + ", j=" + j + "]";
    }
  }
}
