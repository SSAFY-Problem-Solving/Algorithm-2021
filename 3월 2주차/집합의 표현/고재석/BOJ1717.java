import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {
    /*
    대표 집합을 반환하는 배열을 만든다.
    그래서 같은지 본다.
     */

    static int n, m;

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for(int i = 0; i < n + 1; i++){
            parent[i] = i;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(operation == 0) {
                parent[find(b)] = parent[find(a)];
            }

            else if(operation == 1) {
                System.out.println(find(a) == find(b) ? "YES" : "NO");
            }
        }
    }

    private static int find(int element){
        if (element == parent[element]) return element;
        else {
            parent[element] = parent[parent[element]];
            return find(parent[element]);
        }
    }

}
