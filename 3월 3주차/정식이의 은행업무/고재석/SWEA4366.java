import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA4366 {
    /*
    브루트 포스로 풀어보자.
    문자열 2진수를 int형 십진수로 바꾸는 메소드
    문자열 3진수를 int형 십진수로 바꾸는 메소드
     */

    static int tc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tc = Integer.parseInt(br.readLine());
        for(int t = 1; t <= tc; t++){

            StringBuilder binary = new StringBuilder();
            StringBuilder ternary = new StringBuilder();

            binary.append(br.readLine());
            ternary.append(br.readLine());

            int sendingCost = Integer.MAX_VALUE;

            for(int i = 0; i < binary.length(); i++){
                char tempBinary = binary.charAt(i);
                for(int j = 0; j < 2; j++){
                    if(tempBinary == j) {
                        continue;
                    }
                    binary.setCharAt(i, Character.forDigit(j, 10));
                    int bDecimal = binaryToDecimal(binary.toString());

                    for(int k = 0; k < ternary.length(); k++){
                        char tempTernary = ternary.charAt(k);
                        for(int l = 0; l < 3; l++){
                            if(tempTernary == l) {
                                continue;
                            }
                            ternary.setCharAt(k, Character.forDigit(l, 10));
                            int tDecimal = ternaryToDecimal(ternary.toString());
                            if(bDecimal == tDecimal) {
                                sendingCost = bDecimal;
                                break;
                            }

                        }
                        ternary.setCharAt(k, tempTernary);
                        if(sendingCost != Integer.MAX_VALUE) {
                            break;
                        }
                    }
                    if(sendingCost != Integer.MAX_VALUE) {
                        break;
                    }
                }
                binary.setCharAt(i, tempBinary);
                if(sendingCost != Integer.MAX_VALUE) {
                    break;
                }
            }

            System.out.println("#" + t + " " + sendingCost);
        }
    }

    private static int binaryToDecimal(String binary){
        int result = 0;

        for(int i = binary.length() - 1; i >= 0; i--){
            result += (binary.charAt(i) - '0') * Math.pow(2, binary.length() - 1 - i);
        }

        return result;
    }

    private static int ternaryToDecimal(String ternary){
        int result = 0;

        for(int i = 0; i < ternary.length(); i++){
            result += (ternary.charAt(i) - '0') * Math.pow(3, ternary.length() - 1 - i);
        }

        return result;
    }
}
