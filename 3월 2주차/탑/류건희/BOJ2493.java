package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {

	/**
	 * 시간 복잡도 : O(n)
	 */		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 탑의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<int[]> stack = new Stack<>();

		for(int i=1; i<=n; i++) {
			 int height = Integer.parseInt(st.nextToken()); // 탑의 높이
			 while(!stack.isEmpty()) {
				 if(stack.peek()[1] >= height) { // 새로운(우측) 탑의 높이보다 이전(왼쪽) 탑의 높이가 크다면
					 System.out.print(stack.peek()[0] + " "); // 이전(왼쪽) 탑 idx 출력
					 break;
				 }
				 stack.pop();
			 }
			 if(stack.isEmpty()) {
				 System.out.print("0 ");
			 }
			 stack.push(new int[] {i, height}); // i: index, height : 탑의 높이
		}
	}
}
