package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA4366 {
	static char[] two = {'0', '1'}, three = {'0', '1', '2'};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			char[] binary = br.readLine().toCharArray();
			char[] ternary = br.readLine().toCharArray();

			int decimal = calc(binary, ternary);
			System.out.println("#" + tc + " " + decimal);
		}
	}
	
	private static int calc(char[] binary, char[] ternary) {
		for(int i=0; i<binary.length; i++) {
			for(int j=0; j<ternary.length; j++) {
				
				for(int k=0; k<2; k++) {
					char[] tempB = binary.clone();
					tempB[i] = two[k];
					
					int bDecimal = numToDecimal(tempB, 2);
					
					for(int h=0; h<3; h++) {
						char[] tempT = ternary.clone();
						tempT[j] = three[h];
						
						int tDecimal = numToDecimal(tempT, 3);
						
						if(bDecimal == tDecimal) {
							return bDecimal;
						}
					}
				}
			}
		}
		return 0;
	}

	private static int numToDecimal(char[] nums, int x) {
		int decimal = 0;

		for (int i = 0; i < nums.length; i++) {
			decimal = decimal * x + (nums[i] - '0');
		}
		return decimal;
	}

}
