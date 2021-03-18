import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
    /*
    깊이 우선 탐색으로 풀어본다. (20 x 20이 최대이고 각 최대 깊이를 가봐야한다.)
    DFS에 visited 배열을 parameter로 넘겨준다.
     */

    static int R, C;
    static char[][] map;

    static int maxDepth;

    static int dy[] = {0, -1, 0, 1, 0};
    static int dx[] = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++){
            String input = br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = input.charAt(j);
            }
        }

        maxDepth = 0;

        boolean[] visited = new boolean[26];
        visited[map[0][0] - 'A'] = true;

        dfs(0, 0, 1, visited);

        System.out.println(maxDepth);

    }

    private static void dfs(int y, int x, int depth, boolean[] visited) {
        boolean isNext = false;

        for(int d = 1; d <= 4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || nx < 0 || ny >= R || nx >= C || visited[map[ny][nx] - 'A']){
                continue;
            }

            isNext = true;

            visited[map[ny][nx] - 'A'] = true;
            dfs(ny, nx, depth + 1, visited);
            visited[map[ny][nx] - 'A'] = false;
        }

        if(!isNext) {
            maxDepth = Math.max(depth, maxDepth);
        }
    }
}
