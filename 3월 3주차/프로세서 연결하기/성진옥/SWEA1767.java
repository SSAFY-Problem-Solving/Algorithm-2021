package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N01 {
	static int N, maxCnt, minLength;
	static int[][] map, dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static List<Pos> cell;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			maxCnt = 0;
			minLength = Integer.MAX_VALUE;
			cell = new ArrayList<>();
			map = new int[N][N];
			
			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] == 1) {
						if(i != 0 && j != 0) {
							cell.add(new Pos(i, j));
						}else {
							maxCnt++;
						}
					}
				}
			}
			
			dfs(0, 0, 0);
			System.out.println("#" + tc + " " + minLength);
		}
	}
	
	private static void dfs(int idx, int cnt, int length) {
		if(idx == cell.size()) {
			if(maxCnt < cnt) {
				maxCnt = cnt;
				minLength = length;
			} else if(maxCnt == cnt && minLength > length) {
				minLength = length;
			}
			return ;
		}
		
		for(int d = 0; d < 4; d++) {
			int addLength = checkLength(idx, d);
			if(addLength > 0) {
				dfs(idx + 1, cnt + 1, length + addLength);
				cleanLength(idx, d);
			}
		}
		dfs(idx + 1, cnt, length);
	}
	
	private static int checkLength(int idx, int d) {
		Pos cur = cell.get(idx);
		int nr = cur.r, nc = cur.c;
		int length = 0;
		
		while(nr > 0 && nc > 0 && nr < N-1 && nc < N-1) {
			nr += dir[d][0];
			nc += dir[d][1];
			
			if(map[nr][nc] != 0) {
				cleanLength(idx, d);
				length = 0;
				break;
			}
			
			map[nr][nc] = idx+2;
			length++;
		}
		return length;
	}
	
	private static void cleanLength(int idx, int d) {
		Pos cur = cell.get(idx);
		int nr = cur.r, nc = cur.c;
		
		while(nr > 0 && nc > 0 && nr < N-1 && nc < N-1) {
			nr += dir[d][0];
			nc += dir[d][1];
			
			if(map[nr][nc] != idx+2) break;
			
			map[nr][nc] = 0;
		}
	}
	
	private static class Pos{
		int r, c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
