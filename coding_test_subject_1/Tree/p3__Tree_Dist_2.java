package Tree;
import java.util.*;
import java.io.*;

// 1167�� - Ʈ���� ����
public class p3__Tree_Dist_2 {
    static int V, max=0;
    static ArrayList< ArrayList<Node> > childList;
    static boolean[] visit;
    //======================================================
    static class Node {
        int val, dist;
        Node(int v, int d){
            val=v; dist=d;
        }
    }//======================================================
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        visit = new boolean[V+1];
        childList = new ArrayList<>();
        for(int i=0; i<=V; i++)
            childList.add(new ArrayList<>() );

        //�� Ʈ���� ��� �Է�
        for(int i=0; i<V; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            while(true){
                int end = Integer.parseInt(st.nextToken());
                if(end==-1) break;
                int dist = Integer.parseInt(st.nextToken());
                childList.get(start).add(new Node(end, dist));
            }
        }

        // Ʈ���� ��Ʈ�� �������� �ʾҴµ�, ���⼭ ���� ���Ƿ� ���صε��� 1�� Ʈ���� ��Ʈ�� �ΰڴ�.
        visit[1] = true;
        DFS(1);
        System.out.println(max);
    }//======================================================

    static int DFS(int now) {
        ArrayList<Node> childNode = childList.get(now);
        int size = childNode.size();
        int[] childDist = new int[size];
        //System.out.println("���� "+now+"�� ������ "+size+"�� �Դϴ�.");

        int count = 0;
        for(int i=0; i<size; i++){
            Node next = childNode.get(i);
            if(visit[next.val] == false){
                //System.out.println(now+"���� "+next.val+"�� ���ϴ�. �Ÿ��� "+next.dist+" �Դϴ�.");
                visit[next.val] = true;
                childDist[i] = DFS(next.val) + next.dist;
                count++;
            }
        }

        //���� �� �����ִ� ���
        if(count==0) {
            //System.out.println(now+"���� 0�� �Դϴ�.");
            return 0;
        }

        Arrays.sort(childDist);
        int ret = childDist[size-1];
        if(count>=2)
            ret = childDist[size-1]+childDist[size-2];
        if(max < ret)
            max = ret;

        /*System.out.print(now+"���� "+childDist[size-1]+"�Դϴ�.");
        System.out.print(" ������ "+count+"�� �Դϴ�.");
        System.out.println(" max�� "+max+" �Դϴ�.");
        */
        return childDist[size-1];
    }//======================================================
}
