package Tree_DP;
import java.util.*;
import java.io.*;
// 15681번 - 트리와 쿼리
public class p1__Tree_and_Query {
    static int N, root, Q;
    static int[] count;
    static boolean[] visit;
    static ArrayList< ArrayList<Integer> > childList;
    //=======================================================
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       //정점개수
        root = Integer.parseInt(st.nextToken());    //루트가 되는 노드번호
        Q = Integer.parseInt(st.nextToken());       //질의 개수
        count = new int[N+1];
        visit = new boolean[N+1];
        childList = new ArrayList<>();
        for(int i=0; i<=N; i++)
            childList.add(new ArrayList<>());

        /*간선입력*/
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            childList.get(U).add(V);
            childList.get(V).add(U);
        }
        /*트리의 노드를 하나씩 방문하면서 서브트리에서 해당 노드가 루트일때 정점개수를 count배열에 저장을 해준다.*/
        visit[root] = true;
        makeTree(root);

        /*제시한 노드로 구성된 서브트리에서 노드의 개수(자기자신 포함)*/
        StringBuilder answer = new StringBuilder();
        for(int z=0; z<Q; z++){
            int subroot = Integer.parseInt(br.readLine());
            answer.append(count[subroot]+"\n");
        }
        bw.write(answer.toString());
        bw.flush();
    }//=======================================================
    /*트리를 방문하면서 하위트리에서 그 노드가 루트일 때 나오는 정점개수*/
    static int makeTree(int now) {
        ArrayList<Integer> childNode = childList.get(now);
        count[now] = 1;//처음 방문시 자신의 노드를 개수로 넣어준다.

        for(int next:childNode){
            if(!visit[next]) {
                visit[next] = true;
                count[now] += makeTree(next);
            }
        }

        return count[now];
    }//=======================================================
}
