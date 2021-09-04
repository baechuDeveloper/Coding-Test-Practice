package Tree;
import java.util.*;
import java.io.*;

// 1167번 - 트리의 지름
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

        //각 트리의 노드 입력
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

        // 트리의 루트는 정해지지 않았는데, 여기서 내가 임의로 정해두도록 1을 트리의 루트로 두겠다.
        visit[1] = true;
        DFS(1);
        System.out.println(max);
    }//======================================================

    static int DFS(int now) {
        ArrayList<Node> childNode = childList.get(now);
        int size = childNode.size();
        int[] childDist = new int[size];
        //System.out.println("현재 "+now+"번 개수는 "+size+"개 입니다.");

        int count = 0;
        for(int i=0; i<size; i++){
            Node next = childNode.get(i);
            if(visit[next.val] == false){
                //System.out.println(now+"에서 "+next.val+"로 들어갑니다. 거리는 "+next.dist+" 입니다.");
                visit[next.val] = true;
                childDist[i] = DFS(next.val) + next.dist;
                count++;
            }
        }

        //리턴 값 보내주는 경우
        if(count==0) {
            //System.out.println(now+"번은 0개 입니다.");
            return 0;
        }

        Arrays.sort(childDist);
        int ret = childDist[size-1];
        if(count>=2)
            ret = childDist[size-1]+childDist[size-2];
        if(max < ret)
            max = ret;

        /*System.out.print(now+"번은 "+childDist[size-1]+"입니다.");
        System.out.print(" 개수는 "+count+"개 입니다.");
        System.out.println(" max는 "+max+" 입니다.");
        */
        return childDist[size-1];
    }//======================================================
}
