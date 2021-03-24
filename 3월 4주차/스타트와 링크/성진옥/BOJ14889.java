package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14889 {
	static int N;
	static int[][] map;
	static boolean[] select;	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		select = new boolean[N+1];
		
		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		select[1] = true;
		System.out.println(combination(1,1));
	}
	
	private static int combination(int idx, int cnt) {
		if(cnt == N/2) {
			return calS();
		}
		
		int min = Integer.MAX_VALUE;
		for(int next = idx+1; next <= N; next++) {
			select[next] = true;
			min = Math.min(min, combination(next, cnt+1));
			select[next] = false;
		}
		return min;
	}
	
	private static int calS() {
		boolean[] dir = { true, false };
		int[] sum = {0,0};
		
		for(int idx = 0; idx < 2; idx++) {
			for(int i = 1; i <= N; i++) {
				if(select[i] != dir[idx]) continue;
				
				for(int j = i+1; j <= N; j++) {
					if(select[j] != dir[idx]) continue;
					
					sum[idx] += map[i][j] + map[j][i];
				}
			}
		}
		return Math.abs(sum[0] - sum[1]);
	}
}
