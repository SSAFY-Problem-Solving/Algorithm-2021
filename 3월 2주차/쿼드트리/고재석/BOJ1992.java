import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
    /*
    메소드를 하나 만들어보자. 이 메소드는 재귀하는데,
    재귀에 들어가기 전에 sb에 ( 를 붙이고 나올 때 )를 붙인다.
    ( 붙이기 전에 인덱스 범위 안에 모두 0인지 1인지를 보고 다 0이거나 1이면 재귀안들어간다.
     */

    static int N;
    static int[][] video;

    static StringBuilder quadTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        video = new int[N][N];
        for(int i = 0; i < N; i++){
            String input = br.readLine();
            for(int j = 0; j < N; j++){
                video[i][j] = input.charAt(j) - 48;
            }
        }

        quadTree = new StringBuilder();
        makeQuadTree(video);

        System.out.println(quadTree);
    }

    public static void makeQuadTree(int[][] quadVideo){

        // 입력받은 비디오 조각을 전체 같은 값인지 확인해서 같은 갑이면 append
        // 아닌경우에는 다시 4개로 나누어서 재귀
        int initVal = quadVideo[0][0];
        int videoLen = quadVideo.length;
        boolean allMatched = true;
        for(int i = 0; i < videoLen; i++){
            for(int j = 0; j < videoLen; j++){
                if(quadVideo[i][j] != initVal) {
                    allMatched = false;
                    break;
                }
            }
            if(!allMatched) {
                break;
            }
        }

        if(allMatched) {
            quadTree.append(initVal);
            return;
        }

        // 입력받은 배열을 4개로 나눈다.
        int[][][] devidedVideo = new int[4][videoLen / 2][videoLen / 2];


        for(int i = 0; i < videoLen / 2; i++){
            System.arraycopy(quadVideo[i], 0, devidedVideo[0][i], 0, videoLen / 2);
            System.arraycopy(quadVideo[i], videoLen / 2, devidedVideo[1][i], 0, videoLen / 2);
        }
        for(int i = videoLen / 2; i < videoLen; i++){
            System.arraycopy(quadVideo[i], 0, devidedVideo[2][i - videoLen / 2], 0, videoLen / 2);
            System.arraycopy(quadVideo[i], videoLen / 2, devidedVideo[3][i - videoLen / 2], 0, videoLen / 2);
        }

        // 재귀 들어가기 전에 괄호 넣고 끝나면 괄호 넣는다.
        // 4개의 재귀 들어간다.
        quadTree.append("(");
        makeQuadTree(devidedVideo[0]);
        makeQuadTree(devidedVideo[1]);
        makeQuadTree(devidedVideo[2]);
        makeQuadTree(devidedVideo[3]);
        quadTree.append(")");
    }
}
