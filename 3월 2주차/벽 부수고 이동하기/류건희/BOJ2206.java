package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int n, m, minDist;
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m][2];
		
		for (int i = 0; i < n; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = ch[j] - '0';
			}
		}
		
		bfs();
		if(minDist == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(minDist);
	}
	
	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 1, 0));
		visited[0][0][0] = true;
		minDist = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dist = temp.dist;
			int cnt = temp.cnt;
			
			if(r == n-1 && c == m-1)
				minDist = Math.min(minDist, dist);
			
			for(int d=0; d<4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(!isRange(nr, nc)) continue;
				if(visited[nr][nc][cnt]) continue;
				
				if(map[nr][nc] == 0) {
					q.add(new Point(nr, nc, dist + 1, cnt));	
					visited[nr][nc][cnt] = true;
				}
				
				if(map[nr][nc] == 1 && cnt == 0) {
					q.add(new Point(nr, nc, dist + 1, cnt + 1));	
					visited[nr][nc][cnt] = true;
				}
				
			}
			
			
		}
		
	}
	
	private static boolean isRange(int nr, int nc) {
		return nr>=0 && nr<n && nc>=0 && nc< m;
	}
	
	private static class Point{
		int r, c, dist, cnt;

		public Point(int r, int c, int dist, int cnt) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.cnt = cnt;
		}
	}
}
