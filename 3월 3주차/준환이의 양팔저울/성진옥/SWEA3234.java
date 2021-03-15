package combinational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N3234 {
	static int N, sum;
	static int[] w;
	static boolean[] select;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			w = new int[N];
			select = new boolean[N];
			sum = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++){
				w[i] = Integer.parseInt(st.nextToken());
				sum += w[i];
			}
			
			System.out.println("#" + tc + " " + permutation(0,sum,0,0));
		}
	}
	
	private static int permutation(int cnt, int sum, int left, int right) {
		if(cnt == N) return 1;
		else if(left >= sum + right) {
			int ret = 1;
			for(int i = 1; i <= N - cnt; i++) {
				ret *= (i * 2);
			}			
			return ret;
		}
		
		int ret = 0;
		for(int i = 0; i < N; i++) {
			if(select[i]) continue;
			
			select[i] = true;
			ret += permutation(cnt+1, sum-w[i], left + w[i], right);
			if(left >= right + w[i]) {
				ret += permutation(cnt+1, sum-w[i], left, right + w[i]);
			}
			select[i] = false;
		}
		return ret;
	}
}
