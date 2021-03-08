import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {

    public static class Tower{
        int height;
        int index;

        public Tower(){}

        public Tower(int height, int index){
            this.height = height;
            this.index = index;
        }
    }

    static int N;
    static Stack<Tower> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        stack = new Stack<>();

        int maxHeight = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
           int towerHeight = Integer.parseInt(st.nextToken());

            if(towerHeight > maxHeight) {
                maxHeight = towerHeight;
                stack.clear();
                stack.push(new Tower(maxHeight, i));
                System.out.print("0 ");
            }

            else {
                while(!stack.isEmpty()) {
                    Tower compareTower = stack.pop();

                    if(compareTower.height > towerHeight) {
                        stack.push(compareTower);
                        stack.push(new Tower(towerHeight, i));
                        System.out.print(compareTower.index + " ");
                        break;
                    }
                }
            }
        }
    }
}
