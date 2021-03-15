package combinational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N4012 {
	static int[][] map;
	static int minDis, N;
	static boolean[] select;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			select = new boolean[N];
			minDis = Integer.MAX_VALUE;
			
			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			select[0] = true;
			combination(1, 1);
						
			System.out.println("#" + tc + " " + minDis);
		}
	}
	
	private static void combination(int idx, int cnt) {
		if(cnt == N/2) {
			minDis = Math.min(minDis, calS());
			return;
		}else if(idx == N) return ;
		
		for(int i = idx; i < N; i++) {
			select[i] = true;
			combination(i+1, cnt+1);
			select[i] = false;
		}
	}
	
	private static int calS() {
		int[] sum = new int[2];
		boolean[] chk = {true, false};
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < N; j++) {
				if(select[j] != chk[i]) continue;
				
				for(int k = j+1; k < N; k++){
					if(select[k] != chk[i]) continue;
					
					sum[i] += map[j][k] + map[k][j];
				}
			}
		}
		return Math.abs(sum[0] - sum[1]);
	}
}
