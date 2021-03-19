import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA4012 {

    static int t, N;
    static int[][] synergyArr;

    static int tasteGap;

    static boolean[] selectedFood;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++){

            N = Integer.parseInt(br.readLine());

            synergyArr = new int[N][N];
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < N; j++){
                    synergyArr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            tasteGap = Integer.MAX_VALUE;
            selectedFood = new boolean[N];

            combination(0,0);

            System.out.println("#" + tc + " " + tasteGap);

        }
    }

    private static void combination(int start, int cnt){
        if(cnt == N / 2) {
            tasteGap = Math.min(calculateTasteGap(), tasteGap);
            return;
        }

        for(int i = start; i < N; i++){
            if(selectedFood[i]) continue;

            selectedFood[i] = true;
            combination(i + 1, cnt + 1);
            selectedFood[i] = false;
        }
    }

    private static int calculateTasteGap() {
        int gap = 0;

        int synergy1 = 0;
        int synergy2 = 0;
        for(int i = 0; i < N; i++){
            if(selectedFood[i]) {
                for(int j = i; j < N; j++){
                    if(selectedFood[j]) {
                        synergy1 += synergyArr[i][j] + synergyArr[j][i];
                    }
                }
            }

            else {
                if(!selectedFood[i]) {
                    for(int j = i; j < N; j++){
                        if(!selectedFood[j]) {
                            synergy2 += synergyArr[i][j] + synergyArr[j][i];
                        }
                    }
                }
            }
        }

        gap = Math.abs(synergy1 - synergy2);

        return gap;
    }
}
