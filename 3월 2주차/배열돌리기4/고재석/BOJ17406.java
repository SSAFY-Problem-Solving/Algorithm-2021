import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17406 {
    /*
        solveArrVal(int[][] arr) : 배열의 값을 구하는 메소드
        cicle(Operation info) : r, c를 기준으로 양 옆으로 s 만큼의 정사각형을 오른쪽 회전하는 메소드
        permutation(int cnt) : 연산의 순서를 결정하는 메소드
     */

    static class Operation{
        int r;
        int c;
        int s;

        public Operation(){}

        public Operation(int r, int c, int s){
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static int N, M, K;
    static int[][] inputArr;
    static int[][] taskArr;

    static int minArrVal;

    static List<Operation> operationList;
    static int[] numbers;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        inputArr = new int[N][M];
        taskArr = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++){
                inputArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        operationList = new ArrayList<>();
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            operationList.add(new Operation(r, c, s));
        }

        minArrVal = Integer.MAX_VALUE;
        numbers = new int[K];
        visited = new boolean[K];
        permutation(0);

        System.out.println(minArrVal);
    }

    private static void permutation(int cnt){

        if(cnt == K) {
            // inputArr을 taskArr로 복사
            for(int i = 0; i < N; i++){
                System.arraycopy(inputArr[i], 0, taskArr[i], 0, M);
            }

            // taskArr을 순열에 따라 연산 수행
            for(int a = 0; a < numbers.length; a++){
                cicle(operationList.get(numbers[a]));
            }

            // 만들어진 taskArr의 값이 최소이면 최종 값 갱신
            minArrVal = Math.min(minArrVal, solveArrVal(taskArr));

            return;
        }

        for(int i = 0; i < K; i++){
            if(!visited[i]) {
                numbers[cnt] = i;
                visited[i] = true;
                permutation(cnt +  1);
                visited[i] = false;
            }
        }
    }

    private static void cicle(Operation info){
        int r = info.r;
        int c = info.c;
        int s = info.s;

        for(int i = 1; i <= s; i++){
            int cicleUnit = i * 2 + 1;

            // 왼쪽 상단 값
            int temp = taskArr[r - i][c - i];

            // 왼쪽 배열을 위로 올린다.
            for(int j = 0; j < cicleUnit - 1; j++){
                taskArr[r - i + j][c - i] = taskArr[r - i + j + 1][c - i];
            }

            // 아래 배열을 왼쪽으로 민다.
            for(int j = 0; j < cicleUnit - 1; j++){
                taskArr[r + i][c - i + j] = taskArr[r + i][c - i + j + 1];
            }

            // 오른쪽 배열을 아래로 내린다.
            for(int j = 0; j < cicleUnit - 1; j++){
                taskArr[r + i - j][c + i] = taskArr[r + i - j - 1][c + i];
            }

            // 위 배열을 오른쪽으로 민다.
            for(int j = 0; j < cicleUnit - 1; j++){
                taskArr[r - i][c + i - j] = taskArr[r - i][c + i - j - 1];
            }

            // 왼쪽 상단의 값을 오른쪽으로 한 칸 민 쪽에 넣어준다.
            taskArr[r - i][c - i + 1] = temp;
        }
    }

    private static int solveArrVal(int[][] arr){
        int arrVal = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++){
            int rowVal = 0;
            for(int j = 0; j < arr[i].length; j++){
                rowVal += arr[i][j];
            }
            arrVal = Math.min(arrVal, rowVal);
        }

        return arrVal;
    }

}
