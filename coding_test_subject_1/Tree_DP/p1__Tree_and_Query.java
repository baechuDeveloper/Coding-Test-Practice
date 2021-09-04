package Tree_DP;
import java.util.*;
import java.io.*;
// 15681�� - Ʈ���� ����
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
        N = Integer.parseInt(st.nextToken());       //��������
        root = Integer.parseInt(st.nextToken());    //��Ʈ�� �Ǵ� ����ȣ
        Q = Integer.parseInt(st.nextToken());       //���� ����
        count = new int[N+1];
        visit = new boolean[N+1];
        childList = new ArrayList<>();
        for(int i=0; i<=N; i++)
            childList.add(new ArrayList<>());

        /*�����Է�*/
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            childList.get(U).add(V);
            childList.get(V).add(U);
        }
        /*Ʈ���� ��带 �ϳ��� �湮�ϸ鼭 ����Ʈ������ �ش� ��尡 ��Ʈ�϶� ���������� count�迭�� ������ ���ش�.*/
        visit[root] = true;
        makeTree(root);

        /*������ ���� ������ ����Ʈ������ ����� ����(�ڱ��ڽ� ����)*/
        StringBuilder answer = new StringBuilder();
        for(int z=0; z<Q; z++){
            int subroot = Integer.parseInt(br.readLine());
            answer.append(count[subroot]+"\n");
        }
        bw.write(answer.toString());
        bw.flush();
    }//=======================================================
    /*Ʈ���� �湮�ϸ鼭 ����Ʈ������ �� ��尡 ��Ʈ�� �� ������ ��������*/
    static int makeTree(int now) {
        ArrayList<Integer> childNode = childList.get(now);
        count[now] = 1;//ó�� �湮�� �ڽ��� ��带 ������ �־��ش�.

        for(int next:childNode){
            if(!visit[next]) {
                visit[next] = true;
                count[now] += makeTree(next);
            }
        }

        return count[now];
    }//=======================================================
}
