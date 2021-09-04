package Tree;
import java.util.*;
import java.io.*;

/**-----------------------------------------------------------------------------
 * 그래프란 정점과 정점으로 잇는 간선들로 이루어진 집합이다.
 * 만약 간선 없이 있는 정점이 있다면 해당 정점은 다른 어떤 정점과는 '하나의 그래프'는 아니다.
 * 그 해당 정점이 하나의 그래프가 된다.
 * 따라서 그래프란 자신과 연결되어있는 정점의 집합이라고 보면 된다. 그래서 따로 떨어진 정점과는 같은 그래프가 아니다.
 * 또한 여기선 '그래프에서 싸이클이 없는 경우'인 '트리'라고도 언급해준다. 
 -----------------------------------------------------------------------------**/
/**----------------------------------
 * 트리라면 어느 노드를 집고 정상으로 두어도 트리가 된다.
 *
 * https://www.crocus.co.kr/630
 ------------------------------------**/

// 4803번 - 트리
public class p6__is_it_Tree_or_Graph{
    static int n, m;
    static int tcase=0;
    //main함수
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n==0 && m==0) break;
            ArrayList< ArrayList<Integer> > childList = new ArrayList<>();
            for(int i=0; i<=n; i++)
                childList.add(new ArrayList<>());

            //간선 입력
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                childList.get(a).add(b);
                childList.get(b).add(a);
            }

            Queue<Integer> q = new LinkedList<>();
            boolean[] visit = new boolean[n+1];
            int cnt = 0;
            //하나의 노드 혹은 사이클에 대해 V, E를 알 수 있게 된다.
            /*노드를 하나씩 돌려보면서 최종 간선의 개수를 구해보는 형태이다.*/
            for(int i=1; i<=n; i++) {
                //방문한 적 없는 노드는 방문
                int edge = 0;
                int vertex = 0;
                if(visit[i] == false){
                    cnt++;  //우선 트리가 존재한다고 가정하고 진행
                    q.add(i);

                    /*여기서 해당 노드와 연결된 모든 노드를 찾아보고 그것을 하나의 트리로 정해주도록 이미 방문을 했음을 알려준다.*/
                    while(!q.isEmpty()) {
                        int here = q.poll();

                       if(visit[here])
                            continue;
                        vertex++;   //방문한 정점의 개수를 올려준다.
                        visit[here] = true;

                        //양방향 그래프의 간선이 추가된다.
                        int size = childList.get(here).size();
                        for(int j=0; j<size; j++){
                            edge++; //존재하는 간선의 개수만큼 올려준다.
                            q.add(childList.get(here).get(j));  //다음에 갈 정점을 알려준다.
                        }
                    }

                    //트리이기 위해서는 '간선/2 == 정점-1' 이어야 한다.
                    if(edge/2 != vertex-1)
                        cnt--;  //우리가 트리라고 생각한것이 사실 트리가 아니었다!
                }
            }

            if(cnt>=2)
                bw.write("Case "+(++tcase)+": A forest of "+cnt+" trees.\n");
            else if(cnt==1)
                bw.write("Case "+(++tcase)+": There is one tree.\n");
            else
                bw.write("Case "+(++tcase)+": No trees.\n");
        }
        bw.flush();
    }//==================================================
}
