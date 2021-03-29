package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA_3234 준환이의 양팔저울
public class SWEA3234 {
	static int n, cnt, median;
	static int[] weights;
	static boolean[] selected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			n = Integer.parseInt(br.readLine());
			
			weights = new int[n];
			selected = new boolean[n];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
			cnt = 0;
			median();
			perm(0, 0, 0);
			
			System.out.println("#" + tc + " " + cnt);
			
		}
		
	}
	
	private static void perm(int depth, int lSum, int rSum) {
		if(lSum > median) {
			
			int tCnt = 1;
			
			for(int i=0; i < n-depth; i++)
				tCnt *= 2;
			for(int i=2; i <= n-depth; i++)
				tCnt *= i;
			cnt += tCnt;
				
			return;
		}
		
		if(depth == n) {
			cnt += 1;
			return;
		}
		
		for(int i=0; i<n; i++) {
			
			if(selected[i]) continue;
			
			selected[i] = true;
			perm(depth + 1, lSum + weights[i], rSum);
			
			if(lSum >= rSum + weights[i])
				perm(depth + 1, lSum, rSum + weights[i]);
			
			selected[i] = false;
			
		}
		
	}
	
	private static void median() {
		double sum = 0;
		for (int weight : weights) {
			sum += weight;
		}
		
		median = (int) Math.ceil(sum / 2);
	}

}