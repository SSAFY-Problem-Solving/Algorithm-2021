package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686 {
	static int n, m, cityMinDist, chNum;
	static int[][] map;
	static int[] selected;
	static List<Point> houses, chickens;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // n x n
		m = Integer.parseInt(st.nextToken()); // 최대 치킨집 개수
		
		map = new int[n][n];
		chickens = new ArrayList<>();
		houses = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					houses.add(new Point(i,j));
				}else if(map[i][j] == 2) {
					chickens.add(new Point(i,j));
				}
			}
		}
		
		selected = new int[m];
		cityMinDist = Integer.MAX_VALUE;
		chNum = chickens.size();
		
		comb(0, 0);
		System.out.println(cityMinDist);
	}
	
	private static void comb(int start, int depth) {
		if(depth == m) {
			calcDist();
			return;
		}
				
		for(int i=start; i<chNum; i++) {
			selected[depth] = i;
			comb(i+1, depth+1);
		}
	}
	
	private static void calcDist() {
		int sum = 0;
		int hSize = houses.size();
		for(int i=0; i<hSize; i++) {
			int minDist = Integer.MAX_VALUE;
			for(int j=0; j<m; j++) {
				minDist = Math.min(minDist, Math.abs(houses.get(i).r - chickens.get(selected[j]).r) 
						+ Math.abs(houses.get(i).c - chickens.get(selected[j]).c));
			}
			sum += minDist;
			if(sum>cityMinDist) return;
		}
		cityMinDist = Math.min(cityMinDist, sum);
	}
	
	private static class Point{
		int r,c ;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
