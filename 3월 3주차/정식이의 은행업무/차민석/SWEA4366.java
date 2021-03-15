package day0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
2진수 숫자 하나 변경 배열 1개
3진수 숫자 하나 변경 배열 1개
둘을 Map 에 넣어서 count 가 2가 되는 숫자를 정답으로 한다.
 */
public class SWEA4366_정식이의은행업무 {

    static Map<Integer, Integer> map;
    static int[] binary;
    static int[] ternary;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++ ) {
            ans = 0;
            map = new HashMap<>();
            String s1 = br.readLine();
            String s2 = br.readLine();
            binary = new int[s1.length()];
            ternary = new int[s2.length()];
            for (int i = 0; i < s1.length(); i++) {
                binary[i] = s1.charAt(i) - '0';
            }
            for (int i = 0; i < s2.length(); i++) {
                ternary[i] = s2.charAt(i) - '0';
            }

            modifyBinary();

            checkTernary();

            sb.append("#" + tc + " " + ans + "\n");
        }
        System.out.println(sb);
    }

    private static void checkTernary() {
        for (int i = 0; i < ternary.length; i++) {
            int num = ternary[i];
            for (int j = 0; j < 3; j++) {
                if (j == num) {
                    continue;
                }
                ternary[i] = j;
                int sum = ternaryToInt();
                if (map.containsKey(sum)) {
                    ans = sum;
                    return;
                }
            }
            ternary[i] = num;
        }
    }

    private static int ternaryToInt() {
        int sum = 0;
        for (int i = 0; i < ternary.length; i++) {
            sum += Math.pow(3, ternary.length - 1 - i) * ternary[i];
        }
        return sum;
    }

    private static void modifyBinary() {
        for (int i = 0; i < binary.length; i++) {
            int num = binary[i];
            binary[i] = num == 1 ? 0 : 1;
            int sum = binaryToInt();
            map.put(sum, 1);
            binary[i] = num;
        }
    }

    private static int binaryToInt() {
        int sum = 0;
        for (int i = 0; i < binary.length; i++) {
            sum += Math.pow(2, binary.length - 1 - i) * binary[i];
        }
        return sum;
    }

}
