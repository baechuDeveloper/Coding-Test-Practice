package Tree;
import java.io.*;
import java.util.*;
/**
 * DFS 방법을 이용해서 작업을 했고, 가장 밑의 하위노드부터 부분트리의 루트로 지정하면서 이녀석이 루트일때 만들수있는 최대거리를 구해가면서
 * max를 갱신해준다. 또한 이게 현재의 트리의 루트에서 루트의 하위 끝단 노드로 가는 가장 긴 길이를 자신을 호출한 윗 노드에게 넘겨준다.
 * 그럼 그 윗노드를 루트로 시작하는 부분트리에서 또 자신이 루트일때 하위 끝단 노드로 가는 가장 긴 길이를 구해서 자신을 호출한 윗 노드에게 넘겨준다.
 * 이렇게 max 갱신과 현재 루트에서 끝단 하위노드까지 거리중 가장 긴 것을 보내주는것을 1번루트까지 진행을 하면 된다.
 *
 * max를 갱신하는 방법은 다음과 같다.
 * 만약 루트에서 자식노드가 1개이면 max에서는 그 노드에 대해서 보내주면 되고, 2개이상 일때 가장 긴 길이 2개'만' 보내주면된다.
 * 루트가 완성된 그림을 보면 알겠지만 현재의 루트를 중간으로 해서 하위 끝단 노드와 다른 하위 끝단 노드를 이어주는게 가장 긴 거리를 만들수있는 방법이다.
 * 다시 말하면 트리의 끝단(위든 아래든 끝이 있어야함)에서 다른 끝단의 길이를 구하는 것이 가장 긴 거리를 구하는 것이고, 개수가 1개이면 자기 자신이 끝단이라고 가정해서 1개일떄를 보내주면 된다.

 * 위까지가 max를 갱신하는 방법이고 자기 자신의 부모 노드에게 넘겨줄 떄는 간단히도 가장 긴 거리 하나만 넘겨주면 된다. 트리 그림을 보면 알겠지만
 * 결국 끝단을 선택하는 문제인데, 어느 끝단으로 주는게 더 긴지를 보고 가장 긴 것을 넘겨주는 것이다.
 * **/
// 1967번 - 트리의 지름
public class p2__Tree_Dist {
    static int N, max=0;
    static ArrayList< ArrayList<Node> > childList;
    //=================================================
    static class Node {
        int val, dist;
        Node(int a, int b){
            val=a;dist=b;
        }
    }//=================================================
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //노드 개수

        childList = new ArrayList<>(); //루트 번호는 1이다.
        for(int i=0; i<=N; i++)
            childList.add(new ArrayList<Node>() );

        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            childList.get(parent).add(new Node(child, dist));
        }

        dfs(1);
        System.out.println(max);
    }//=================================================
    // 작업 시작
    static int dfs(int now) {
        ArrayList<Node> childNode = childList.get(now);
        int count = childNode.size();
        if(count==0) {
            //System.out.println(now+"번은 0입니다.");
            return 0;
        }

        int [] childDist = new int[count];
        for(int i=0; i<count; i++){
            Node next = childNode.get(i);
            childDist[i] = dfs(next.val)+next.dist;
        }

        //가장 큰 값을 리턴해준다. 또한 이 노드를 head로 적용해서 만들수있는 최대거리를 구해준다.
        Arrays.sort(childDist);
        int ret = childDist[0];
        if(count >= 2)
            ret = childDist[count-1]+childDist[count-2];

        if(max<ret)
            max=ret;
        //System.out.print(now+"번은 "+childDist[count-1]+"입니다.");
        //System.out.print(" 개수는 "+count+"개 입니다.");
        //System.out.println(" max는 "+max+" 입니다.");
        return childDist[count-1];
    }//=================================================
}
