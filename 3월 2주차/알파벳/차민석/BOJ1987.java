package day0314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
같은 알파벳이 적힌 칸을 두 번 지날 수 없다. => 알파벳 26개 배열을 만들기?
죄측상단에서 최대 몇 칸을 지날 수 있는지 => 배열의 알파벳을 얼마나 visit 했는지 확인
비트마스크 고민
 */
public class BOJ1987_알파벳 {

    static int R, C, ans;
    static char[][] map;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ans = 0;

        map = new char[R][];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 삽입 후 재귀 돌리기
        recur(0, 0, 1 << map[0][0] - 'A');

        System.out.println(ans);
    }

    private static void recur(int r, int c, int num) {

        ans = Math.max(Integer.bitCount(num), ans);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C || (num & (1 << map[nr][nc] - 'A')) != 0) {
                continue;
            }
            // 삽입 후 재귀 돌리기
            recur(nr, nc, num | (1 << map[nr][nc] - 'A'));
        }
    }
}
