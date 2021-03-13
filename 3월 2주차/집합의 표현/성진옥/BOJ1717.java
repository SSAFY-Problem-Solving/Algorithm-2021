package set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1717 {
	static int N, M;
	static int[] root;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		
		set();
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(t == 0) {
				union(a, b);
			}else {
				if(findRoot(a) == findRoot(b)) System.out.println("YES");
				else System.out.println("NO");
			}
		}
	}
	
	private static void set() {
		for(int i = 1; i <= N; i++) {
			root[i] = i;
		}
	}
	
	private static void union(int a, int b) {
		root[findRoot(b)] = findRoot(a);
	}
	
	private static int findRoot(int n) {
		if(n != root[n]) {
			root[n] = findRoot(root[n]);
		}
		return root[n];
	}
}
