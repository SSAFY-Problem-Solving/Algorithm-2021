package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N2206 {
	static int N, M;
	static char[][] map;
	static int[][][] visit;
	static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][N];
		visit = new int[2][N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				visit[0][i][j] = Integer.MAX_VALUE;
				visit[1][i][j] = Integer.MAX_VALUE;
			}
		}
		
		bfs();
		
		if(visit[0][N-1][M-1] == Integer.MAX_VALUE && visit[1][N-1][M-1] == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(Math.min(visit[0][N-1][M-1], visit[1][N-1][M-1]));
		}
	}
	
	private static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0,0,0));
		visit[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dir[d][0];
				int nc = cur.c + dir[d][1];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				if(map[nr][nc] == '0' && visit[cur.hint][nr][nc] > visit[cur.hint][cur.r][cur.c] + 1) {
					visit[cur.hint][nr][nc] = visit[cur.hint][cur.r][cur.c] + 1;
					q.add(new Pos(nr, nc, cur.hint));
				}else if(map[nr][nc] == '1' && cur.hint == 0 && visit[1][nr][nc] > visit[0][cur.r][cur.c] + 1) {
					visit[1][nr][nc] = visit[0][cur.r][cur.c] + 1;
					q.add(new Pos(nr, nc, 1));
				}
			}
		}
	}
	
	private static class Pos{
		int r, c, hint;
		Pos(int r, int c, int hint){
			this.r = r;
			this.c = c;
			this.hint = hint;
		}
	}
}
