package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1987 {
	static int N, M;
	static boolean[] visit = new boolean[27];
	static char[][] map;
	static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		visit[map[0][0] - 'A'] = true;
		System.out.println(dfs(0,0,0));
	}
	
	private static int dfs(int r, int c, int cnt) {
		int max = 1;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
			
			if(visit[map[nr][nc] - 'A']) continue;
			
			visit[map[nr][nc] - 'A'] = true;
			max = Math.max(max, dfs(nr, nc, cnt+1) + 1);			
			visit[map[nr][nc] - 'A'] = false;
		}
		
		return max;
	}
}
