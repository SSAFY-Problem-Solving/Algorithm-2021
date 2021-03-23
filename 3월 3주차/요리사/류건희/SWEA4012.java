package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// SWEA_4012 요리사
public class SWEA4012 {
	static int n, minDiff;
	static int[][] map;
	static boolean[] selected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {

			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			minDiff = Integer.MAX_VALUE;
			selected = new boolean[n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			calcTastes();
			comb(0, 0);

			System.out.println("#" + tc + " " + minDiff);

		}

	}

	private static void comb(int depth, int start) {
		if (depth == n / 2) {
			minDiff = Math.min(minDiff, calcTastes());
			return;
		}

		for (int i = start; i < n; i++) {
			selected[i] = true;
			comb(depth + 1, i + 1);
			selected[i] = false;
		}

	}

	private static int calcTastes() {
		int diff = 0, tasteA = 0, tasteB = 0;

		for (int i = 0; i < n; i++) {
			if (selected[i]) {
				for (int j = i + 1; j < n; j++) {
					if (selected[j]) {
						tasteA += map[i][j] + map[j][i];
					}
				}
			} else {
				if (!selected[i]) {
					for (int j = i + 1; j < n; j++) {
						if (!selected[j]) {
							tasteB += map[i][j] + map[j][i];
						}
					}
				}
			}
		}

		diff = Math.abs(tasteA - tasteB);

		return diff;
	}

}
