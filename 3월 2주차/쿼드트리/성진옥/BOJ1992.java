package divisionAndConquest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1992 {
	static int N;
	static char[][] video;
	static int[][] dir = {{0,0}, {0,1}, {1,0}, {1,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		video = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			video[i] = br.readLine().toCharArray();
		}
		
		System.out.println(translate(0,0,N));
	}
	
	private static String translate(int r, int c, int size) {
		if(size == 1) {
			if(video[r][c] == '1') return "1";
			else return "0";
		}
		
		String[] s = new String[4];
		for(int i = 0; i < 4; i++) {
			s[i] = translate(r + (size*dir[i][0])/2, c + (size*dir[i][1])/2, size/2);
		}
		
		boolean same = true;
		for(int i = 0; i < 4; i++) {
			if(!s[0].equals(s[i]) || s[i].length() > 1) same = false;
		}
		
		if(same) return s[0];
		else {
			String total = "(";
			for(int i = 0; i < 4; i++) {
				total += s[i];
			}
			return total + ")";
		}
	}
}
