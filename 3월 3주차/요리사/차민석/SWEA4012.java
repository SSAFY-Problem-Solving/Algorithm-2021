package day0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
단순한 조합 문제
1. A: nCn/2, B: nCn/2
2. n/2C2를 해서 시너지 계산
3. 시너지 차이 ans 저장
 */
public class SWEA4012_요리사 {

    static int N, ans;
    static int[][] map;
    static int[] personA;
    static int[] personB;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            personA = new int[N / 2];
            personB = new int[N / 2];
            check = new boolean[N];
            comb(0, 0);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void comb(int idx, int start) {
        if (idx == N / 2) {
            calculateSynergy();
            return;
        }

        for (int i = start; i < N; i++) {
            personA[idx] = i;
            comb(idx + 1, i + 1);
        }
    }

    private static void calculateSynergy() {
        initPersonB();

        int synergyA = calSynergy(personA);
        int synergyB = calSynergy(personB);

        int tempAns = Math.abs(synergyA - synergyB);
        ans = Math.min(tempAns, ans);
    }

    private static int calSynergy(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                sum += map[arr[i]][arr[j]] + map[arr[j]][arr[i]];
            }
        }
        return sum;
    }

    private static void initPersonB() {
        Arrays.fill(check, false);
        for (int n : personA) {
            check[n] = true;
        }

        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                personB[idx++] = i;
            }
        }
    }

}
