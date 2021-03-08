package day0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 { // 탑

  static int N, maxIdx;
  static int[] tList;

  public static void main(String[] args) throws NumberFormatException, IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    maxIdx = 0;

    tList = new int[N + 1];
    tList[0] = 0;
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      tList[i] = Integer.parseInt(st.nextToken());
    }

    StringBuilder sb = new StringBuilder();
    Stack<Integer> stack = new Stack<Integer>();
    for (int i = 1; i < N + 1; i++) {
      int now = tList[i];

      if (i == 1) { // 첫 번째 인덱스
        stack.add(i);
        sb.append(0 + " ");
        maxIdx = i;
        continue;
      }

      int prev = stack.peek();
      if (tList[prev] < now) { // 직전 탑이 지금 탑보다 낮으면
        stack.pop();
        while (true) {
          if (stack.isEmpty()) {
            maxIdx = i;
            sb.append(0 + " ");
            break;
          }
          int max = stack.pop();
          if (tList[max] >= now) {
            stack.add(max);
            stack.add(i);
            maxIdx = max;
            sb.append(maxIdx + " ");
            break;
          }
        }
        stack.add(i);
      } else if (tList[prev] >= now) { // 직전 탑이 지금 탑보다 높으면
        maxIdx = i - 1;
        stack.add(i);
        sb.append(maxIdx + " ");
      }
    }
    System.out.println(sb.toString());
  }

}