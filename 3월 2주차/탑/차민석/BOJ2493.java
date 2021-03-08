package day0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
N = 50만 => N^2 = 250,000,000,000 = 2500억 => O(N) 한번에 끝내야 한다
탑 개수 N개, 왼쪽으로 레이저 발사
6 9 5 7 4
각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내기
1. 왼쪽으로 발사하니까 마지막 인덱스 타워부터 0번 인덱스로 확인
2. 다음 타워가 내 높이보다 클 경우 그 타워 번호를 입력해야한다.
3. [9, 6, 5, 7] 이 상황에서는 [0, 1, 2, 1] 이 나와야한다.
4. 루프 1번에 끝내야 하므로 스택사용. 인덱스 저장하기
극단적인 경우 [ 1억, 1, 2, ... 50만]
1번 인덱스까지 50만회
0번 인덱스에서 50만회 => 100만회 ( 50만 * 50만 은 아니다 + 연산 )
스택에서 확인한 후 빼기 때문에 스택에 MAX 50만 개 들어가면 최대 50만 번을 더 확인하고 끝
O(2*N)
 */
public class BOJ2493 {

    static int N;
    static int[] towers, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        towers = new int[N];
        ans = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            // 1. stack 에 탑이 들어 있는 경우 맨 위 인덱스에 속하는 높이와 비교 후
            // 높이가 같거나 크면 꺼내서 ans 의 해당 인덱스 부분에 현재 i 저장
            // 스택 안의 높이가 더 큰 경우 stop
            while (!stack.isEmpty()) {
                int prevHeight = towers[stack.peek()];
                int curHeight = towers[i];

                if (curHeight >= prevHeight) {
                    int ansIdx = stack.pop();
                    ans[ansIdx] = i + 1;
                } else {
                    break;
                }
            }

            // 2. 현재 인덱스를 스택에 저장
            stack.push(i);

        }

        while (!stack.isEmpty()) {
            ans[stack.pop()] = 0;
        }

        StringBuilder sb = new StringBuilder();
        for (int n : ans) {
            sb.append(n + " ");
        }
        System.out.println(sb);
    }
}
