import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686 {
    /*
     NxN의 도시의 집에서 가장 가까운 치킨집과의 거리를 치킨거리라고 한다.
     도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
     두 좌표 사이의 거리는 |r1 - r2| + |c1 - c2| 이다.
     0 : 빈 칸, 1 : 집, 2 : 치킨집

     치킨 집을 M개 고른 경우에 도시의 치킨 거리의 최솟값을 출력

     집 리스트, 치킨 리스트 -> 치킨 집 M개 골라서 치킨 거리 (조합)
     */

    public static class Position{
        int y;
        int x;

        public Position(){}

        public Position(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static int N, M;

    static int cityChickenDistance;

    static List<Position> homeList;
    static List<Position> chickenList;

    static int[] selected;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        homeList = new ArrayList<>();
        chickenList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                int input = Integer.parseInt(st.nextToken());
                if(input == 0) {
                    continue;
                } else if(input == 1) {
                    homeList.add(new Position(i, j));
                } else if(input == 2) {
                    chickenList.add(new Position(i,j));
                }
            }
        }

        selected = new int[M];
        visited = new boolean[chickenList.size()];
        cityChickenDistance = Integer.MAX_VALUE;
        selectM(0, 0);

        System.out.println(cityChickenDistance);

    }

    public static void selectM(int cnt, int start){
        if(cnt == M){
            cityChickenDistance = Math.min(cityChickenDistance, calculateMinDistance());
            return;
        }

        for(int i = start; i < chickenList.size(); i++){
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            selected[cnt] = i;
            selectM(cnt + 1, i + 1);
            visited[i] = false;

        }
    }

    public static int calculateMinDistance(){

        int allChickenDistance = 0;

        for(int i = 0; i < homeList.size(); i++){
            Position currHome = homeList.get(i);

            int chickenDistance = Integer.MAX_VALUE;
            for(int j = 0; j < selected.length; j++){
                Position currChicken = chickenList.get(selected[j]);
                int calculatedDistance = Math.abs(currHome.y - currChicken.y) + Math.abs(currHome.x - currChicken.x);
                chickenDistance = Math.min(chickenDistance, calculatedDistance);
            }
            allChickenDistance += chickenDistance;
        }

        return allChickenDistance;
    }
}
