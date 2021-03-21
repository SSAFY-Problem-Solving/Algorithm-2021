package combinational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1247 {
	static int N;
	static boolean[] visit;
	static int[][] dis;
	static Pos[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			visit = new boolean[N+2];
			dis = new int[N+2][N+2];
			list = new Pos[N+2];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N+2; i++) {
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list[i] = new Pos(r, c);
			}
			
			calDis();
			System.out.println("#" + tc + " " + perm(0,0));
		}
	}
	
	private static int perm(int idx, int cnt) {
		if(cnt == N) {
			return dis[idx][1];
		}
		
		int min = Integer.MAX_VALUE;
		for(int next = 2; next < N+2; next++) {
			if(visit[next]) continue;
			
			visit[next] = true;
			min = Math.min(min, perm(next, cnt+1)+ dis[idx][next]);
			visit[next] = false;
		}
		return min;
	}
	
	private static void calDis() {
		for(int i = 0; i < N+2; i++) {
			for(int j = i+1; j < N+2; j++) {
				dis[i][j] = Math.abs(list[i].r - list[j].r) + Math.abs(list[i].c - list[j].c);
				dis[j][i] = dis[i][j];
			}
		}
	}
	
	private static class Pos{
		int r, c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
