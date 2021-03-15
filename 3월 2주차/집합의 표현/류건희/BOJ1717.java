package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {
	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		n = Integer.parseInt(st.nextToken()); // n + 1개의 집합
		m = Integer.parseInt(st.nextToken()); // m개의 입력
		
		parent = new int[n+1];
		
		for(int i=0; i<=n; i++) {
			makeSet(i);
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int operator = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(operator == 0) {
				union(x, y);
			}else {
				boolean ans = isTogether(x, y);
				if(ans)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
		
	}
	
	private static boolean isTogether(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px != py)
			return false;
		else
			return true;
	}
	
	/**
	 * 유일한 멤버 x를 포함하는 새로운 집합을 생성하는 연산
	 */
	private static void makeSet(int x) {
		parent[x] = x; // 부모를 자신의 idx로 표기
	}
	
	/**
	 * x를 포함하는 집합(대표자)를 찾는 연산, 대표자 idx 리턴
	 */
	private static int findSet(int x) {
		if(parent[x] == x)
			return x;
		else { // x가 루트가 아닌 경우
			parent[x] = findSet(parent[x]); // path compression
			return parent[x];
		}
	}
	
	/**
	 * x와 y를 포함하는 두 집합을 통합하는 연산 
	 */
	private static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px != py) // 다른 집할일 때만 합치기, 같은 집합인데 합치면 StackOverflowError
			parent[py] = px;
	}
	
	
	
}
