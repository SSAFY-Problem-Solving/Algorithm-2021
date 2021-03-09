package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N15686 {
	static int N, M;
	static List<Pos> houseList, storeList;
	static int[][] dist;
	static int[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		houseList = new ArrayList<>();
		storeList = new ArrayList<>();
		selected = new int[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					houseList.add(new Pos(i, j));
				}else if(num == 2) {
					storeList.add(new Pos(i, j));
				}
			}
		}
		dist = new int[houseList.size()][storeList.size()];
		setDist();
		System.out.println(comb(0, 0));
	}
	
	private static void setDist() {
		for(int h = 0; h < houseList.size(); h++) {
			Pos house = houseList.get(h);
			for(int s = 0; s < storeList.size(); s++) {
				Pos store = storeList.get(s);
				dist[h][s] = Math.abs(house.r - store.r) + Math.abs(house.c - store.c);
			}
		}
	}
	
	private static int comb(int idx, int cnt) {
		int min = Integer.MAX_VALUE;
		
		if(cnt > 0) min = calDist(cnt);
		if(cnt == M || idx >= storeList.size()) return min;
		
		for(int nextIdx = idx; nextIdx < storeList.size(); nextIdx++) {
			selected[cnt] = nextIdx;
			min = Math.min(min, comb(nextIdx+1, cnt+1));
		}
				
		return min;
	}
	
	private static int calDist(int cnt) {
		int sum = 0;
		
		for(int h = 0; h < houseList.size(); h++) {
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < cnt; j++) {
				min = Math.min(min, dist[h][selected[j]]);
			}
			sum += min;
		}
		
		return sum;
	}
	
	private static class Pos{
		int r, c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
