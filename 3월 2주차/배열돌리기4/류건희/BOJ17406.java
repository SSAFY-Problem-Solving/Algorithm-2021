package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17406 {
	static int n, m, k, minSum;
	static int[][] originalMap;
	static int[] selected;
	static boolean[] checked;
	static Condition[] conditions;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()); // 회전 연산의 개수

		originalMap = new int[n][m];
		checked = new boolean[k];
		selected = new int[k];
		conditions = new Condition[k];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		minSum = Integer.MAX_VALUE;

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			conditions[i] = new Condition(r, c, s);
		}
//		printMap(originalMap);
		perm(0);
		System.out.println(minSum);
	}

	private static void perm(int depth) {
		if (depth == k) {
			int map[][] = copyArr(originalMap);
			for (int idx : selected) {
				rotate(conditions[idx].r, conditions[idx].c, conditions[idx].s, map);
			}
//			printMap(map);
			calcMin(map);
			return;
		}

		for (int i = 0; i < k; i++) {
			if (!checked[i]) {
				selected[depth] = i;
				checked[i] = true;
				perm(depth + 1);
				checked[i] = false;
			}
		}
	}

	/**
	 * 가장 왼쪽 윗 칸 : (r-s, c-s) 가장 오른쪽 아랫 칸 : (r+s, c+s)
	 * 
	 */
	private static void rotate(int r, int c, int s, int[][] map) {
		int sr = r - s;
		int sc = c - s;
		int er = r + s;
		int ec = c + s;

		for (int i = 0; i < s; i++) {
			int start = map[sr + i][sc + i];
			for (int j = sr + i; j < er - i; j++)
				map[j][sc + i] = map[j + 1][sc + i];

			for (int j = sc + i; j < ec - i; j++)
				map[er - i][j] = map[er - i][j + 1];

			for (int j = er - i; j > sr + i; j--)
				map[j][ec - i] = map[j - 1][ec - i];

			for (int j = ec - i; j > sc + i + 1; j--)
				map[sr + i][j] = map[sr + i][j - 1];

			map[sr + i][sc + i + 1] = start;
		}

	}

	private static int[][] copyArr(int[][] map) {
		int[][] temp = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	private static void calcMin(int[][] map) {
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < m; j++) {
				sum += map[i][j];
			}
			minSum = Math.min(minSum, sum);
		}
	}

	static void printMap(int[][] map) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}System.out.println("---------------------------------");
	}

	private static class Condition {
		int r, c, s;

		public Condition(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
