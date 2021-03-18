package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767 {
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int n, min, maxCore, minLen, coreSize;
	static int[][] map;
	static List<Core> cores;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			n = Integer.parseInt(br.readLine());

			min = 0;
			maxCore = 0;
			minLen = 0;

			map = new int[n][n];
			cores = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (!isBorder(i, j)) {
							cores.add(new Core(i, j));
						}
					}
				}
			}

			maxCore = -1;
			minLen = Integer.MAX_VALUE;
			coreSize = cores.size();
			dfs(0, 0, 0);

			System.out.println("#" + tc + " " + minLen);
		} // for tc end
	} // main end

	static void dfs(int core, int len, int depth) {

		if (core + coreSize - depth < maxCore)
			return;

		if (depth == coreSize) {
			if (maxCore < core) {
				maxCore = core;
				minLen = len;
			} else if (maxCore == core)
				if (minLen > len)
					minLen = len;
			return;
		}

		for (int d = 0; d < 4; d++) {
			
			int cnt = 0;
			int nr = cores.get(depth).r;
			int nc = cores.get(depth).c;
			
			while (true) {
				nr += dir[d][0];
				nc += dir[d][1];
				
				if (map[nr][nc] == 1) {
					cnt = 0;
					break;
				}
				cnt++;
				if (isBorder(nr, nc))
					break;
			}

			nr = cores.get(depth).r;
			nc = cores.get(depth).c;
			
			for (int i = 0; i < cnt; i++) {
				nr += dir[d][0];
				nc += dir[d][1];
				map[nr][nc] = 1;
			}
			
			if (cnt == 0)
				dfs(core, len, depth + 1);
			else {
				dfs(core + 1, len + cnt, depth + 1);
				
				nr = cores.get(depth).r;
				nc = cores.get(depth).c;

				for (int i = 0; i < cnt; i++) {
					nr += dir[d][0];
					nc += dir[d][1];
					map[nr][nc] = 0;
				}
			}
		} // for d end
	} // dfs end

	static boolean isBorder(int r, int c) {
		return r == 0 || r == n - 1 || c == 0 || c == n - 1;
	}

	static boolean isRange(int nr, int nc) {
		return nr >= 0 && nr < n && nc >= 0 && nc < n;
	}

	static class Core {
		int r, c;

		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}