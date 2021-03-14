package day0314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
합집합 연산, 같은 집합에 포함되어 있는지 확인하는 연산
MST 냄새가 심하게 난다. => 살짝 변형시켜서 적용
[시간초과]
집합수 100만, 연산 수 10만 => 무조건 O(N) 으로 끝내야한다.
연산을 실행하면서 배열에 저장하지말고 그때그때 해보자
혹시 StringBuilder 에 append 하는 게 부담일 수 도 있을 것 같다
그냥 println 으로 해보자
=> union 로직에 문제가 있었다.
 */
public class BOJ1717_집합의표현 {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1. parent 배열 만들기
        parent = new int[N + 1]; // 0 ~ N
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 2. 연산 실행행
            if (flag == 0) {
                sum(a, b);
            } else {
                sb.append(isSum(a, b)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void sum(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        parent[rootB] = rootA;
    }

    private static String isSum(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        return rootA == rootB ? "YES" : "NO";
    }
}
