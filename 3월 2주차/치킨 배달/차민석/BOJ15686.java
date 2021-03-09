package day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
치킨 집 중 M개 고르고, 치킨 거리가 최소가 되도록!
단순히 생각해보면 map 이 필요없어 보인다
 */
public class BOJ15686_치킨배달 {

    static int N, M, ans;
    // 조합용
    static int[] select;
    // 리스트
    static List<Point> chickens;
    static List<Point> houses;

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        chickens = new ArrayList<>();
        houses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    houses.add(new Point(i, j));
                } else if (n == 2) {
                    chickens.add(new Point(i, j));
                }
            }
        }

        select = new int[M];

        comb(0, 0);

        System.out.println(ans);
    }

    private static void comb(int idx, int start) {
        if (idx == M) {
            calcDist();
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            select[idx] = i;
            comb(idx + 1, i + 1);
        }

    }

    private static void calcDist() {
        int tempAns = 0;
        for (Point house : houses) {
            int r1 = house.r;
            int c1 = house.c;
            int dist = Integer.MAX_VALUE;

            // 1. 각 집당 가장 짧은 치킨 거리 체크
            for (int idx : select) {
                int r2 = chickens.get(idx).r;
                int c2 = chickens.get(idx).c;

                int tempDist = Math.abs(r1 - r2) + Math.abs(c1 - c2);
                dist = Math.min(tempDist, dist);
            }

            // 2. 가장 짧은 치킨 거리를 더한다
            tempAns += dist;
        }

        ans = Math.min(tempAns, ans);
    }
}
