package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class N2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] height = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] answer = new int[N];
		Stack<Integer> s = new Stack<>();
		s.push(N-1);
		
		for(int i = N-2; i >= 0; i--) {
			while(!s.empty()) {
				if(height[i] > height[s.peek()]) {
					int idx = s.pop();
					answer[idx] = i+1;
				}else {
					s.push(i);
					break;
				}
			}
			if(s.empty()) {
				s.push(i);
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}
}
