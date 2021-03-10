package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1992 {
	static int n;
	static char[][] map;
	static List<Character> ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		ans = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		check(0, 0, n);
		for (char ch : ans) {
			System.out.print(ch);
		}
		
	}
	
	private static void check(int r, int c, int size) {
		if(isQuadTree(r, c, size)) {
			ans.add(map[r][c]);
		} else {
			ans.add('(');
			int halfSize = size /2;
			check(r, c, halfSize); 						 // Area 1
			check(r, c + halfSize, halfSize);			 // Area 2
			check(r + halfSize, c, halfSize);			 // Area 3
			check(r + halfSize, c + halfSize, halfSize); // Area 4
			ans.add(')');
		}
	}
	
	private static boolean isQuadTree(int r, int c, int size) {
		int qt = map[r][c];
		for(int i=r; i< r+size; i++) {
			for(int j=c; j<c+size; j++) {
				if(qt != map[i][j])
					return false;
			}
		}
		return true;
	}
	
	private static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

}
