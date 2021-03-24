import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1786 {
    /*
    문자열 패턴 매칭 알고리즘인 KMP 알고리즘을 구현한다.
     */

    static String T, P;

    static int cntMatched;
    static Queue<Integer> ansPosition;

    static int[] fail;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = br.readLine();
        P = br.readLine();

        fail = new int[P.length()];
        makeFail(P.toCharArray());

        cntMatched = 0;
        ansPosition = new LinkedList<>();
        compareStr(T.toCharArray(), P.toCharArray());

        System.out.println(cntMatched);
        while(!ansPosition.isEmpty()) System.out.print(ansPosition.poll() + " ");
    }

    // 패턴 문자열의 접두사와 접미사를 겹치는 부분을 체크해서 불일치 시 얼마만큼 이동하면 되는지 기록하는 메소드
    private static void makeFail(char[] pattern){

        // 접두사 인덱스
        int j = 0;

        // 패턴에서 i 인덱스 이하의 문자열을 접미사로 본다
        for(int i = 1; i < pattern.length; i++){

            // 접미사와 접두사의 인덱스의 값이 일치하지 않으면 이전 인덱스의 fail함수 값을 다시 본다.
            while( j > 0 && pattern[i] != pattern[j] ) j = fail[j - 1];

            // 접미사와 접두사의 인덱스 값이 일치하면 다음 값으로 넘어간다.
            if(pattern[i] == pattern[j]) fail[i] = ++j;
        }
    }

    // 전체 문자열과 패턴 문자열을 비교해서 매치되는 경우를 기록할 메소드
    private static void compareStr(char[] text, char[] pattern){

        // 패턴 문자열 인덱스
        int j = 0;

        // 전체 문자열 인덱스
        for(int i = 0; i < text.length; i++){

            // 만약 두 문자열 인덱스의 값이 다르면 실패함수의 값으로 패턴 인덱스를 바꾼다.
            while(j > 0 && text[i] != pattern[j]) j = fail[j - 1];

            // 만약 두 문자열 인덱스의 값이 같으면
            // i랑 j 한 칸씩 더 가서 본다.
            // 만약 최종 값이라면 최종 결과 갱신하고 i도 초기 인덱스에서 한 칸만 옆으로 옮긴다.
            if(text[i] == pattern[j]) {
                if(j == pattern.length - 1) {
                    cntMatched++;
                    ansPosition.add(i - pattern.length + 2);
                    j = fail[j];
                }
                else j++;
            }
        }
    }
}
