package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N17406 {
	static int N, M, K;
	static int[][] map, dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static boolean[] selected;
	static Status[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		list = new Status[K];
		selected = new boolean[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			list[i] = new Status(r, c, s);
		}
		
		System.out.println(perm(0, map));
	}
	
	private static int perm(int cnt, int[][] map) {
		if(cnt == K) {
			return calMin(map);
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < K; i++) {
			if(selected[i]) continue;
			
			selected[i] = true;
			min = Math.min(min, perm(cnt+1, rotateMap(map, i)));
			selected[i] = false;
		}
		return min;
	}

	private static int calMin(int[][] map) {
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum += map[i][j];
			}
			min = Math.min(min, sum);
		}
		return min;
	}
	
	private static int[][] rotateMap(int[][] map, int idx) {
		int[][] temp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		int r = list[idx].r;
		int c = list[idx].c;
		int s = list[idx].s;
		
		for(int i = s; i >= 1; i--) {
			rotateLine(r-i, c-i, i*2 + 1, temp);
		}
		
		return temp;
	}
	
	private static void rotateLine(int r, int c, int size, int[][] map) {
		int temp = map[r][c];
		
		for(int d = 0; d < 4; d++) {
			for(int s = 1; s < size; s++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				map[r][c] = map[nr][nc];
				
				r = nr;
				c = nc;
			}
		}
		
		map[r][c+1] = temp;
	}
	
	private static class Status{
		int r, c, s;
		Status(int r, int c, int s){
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
