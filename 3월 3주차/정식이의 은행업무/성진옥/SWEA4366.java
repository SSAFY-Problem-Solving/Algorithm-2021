package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N4366 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			char[] number2 = br.readLine().toCharArray();
			char[] number3 = br.readLine().toCharArray();
						
			System.out.println("#" + tc + " " + findNumber(number2, number3));
		}
	}
	
	private static int findNumber(char[] number2, char[] number3) {
		for(int idx2 = 0; idx2 < number2.length; idx2++) {
			for(int n = 0; n < 2; n++) {
				if(number2[idx2] - '0' == n) continue;
				
				char temp2 = number2[idx2];
				number2[idx2] = (char)('0' + n);
				
				int num2 = changeNumber(2, number2);
				
				for(int idx3 = 0; idx3 < number3.length; idx3++) {
					for(int n3 = 0; n3 < 3; n3++) {
						if(number3[idx3] - '0' == n3) continue;
						
						char temp3 = number3[idx3];
						number3[idx3] = (char)('0' + n3);
												
						if(num2 == changeNumber(3, number3)) return num2;
						
						number3[idx3] = temp3;
					}
				}
				number2[idx2] = temp2;
			}
		}
		return 0;
	}
	
	private static int changeNumber(int type, char[] number) {
		int n = 0, start = 1;
		
		for(int i = number.length-1; i >= 0; i--) {
			n += (number[i] - '0') * start;
			start *= type;
		}
		return n;
	}
}
