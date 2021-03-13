package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1987 {
	static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static int r, c, maxDist, alphaNum;
	static int[][] map;
	static boolean[] alpha;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		alpha = new boolean[26];
		
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j) - 'A'; // 0 ~ 25(A ~ Z)
				if (!alpha[map[i][j]]) {
					alpha[map[i][j]] = true;
					alphaNum++;
				}
			}
		}
		Arrays.fill(alpha, false);
		maxDist = Integer.MIN_VALUE;
		dfs(0, 0, 1);
		System.out.println(maxDist);
	}
	
	private static void dfs(int x, int y, int dist) {
		alpha[map[x][y]] = true;
		if(maxDist < dist)
			maxDist = dist;
		if(maxDist == alphaNum)
			return;
		
		for(int d=0; d<4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			
			if(!isRange(nx, ny)) continue;
			if(alpha[map[nx][ny]]) continue;
			
			dfs(nx, ny, dist + 1);
			alpha[map[nx][ny]] = false;
		}
		
	}
	
	private static boolean isRange(int nx, int ny) {
		return nx>=0 && nx<r && ny>=0 && ny<c;
	}

}
