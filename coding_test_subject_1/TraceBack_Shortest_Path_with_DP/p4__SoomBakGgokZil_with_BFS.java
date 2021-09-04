package TraceBack_Shortest_Path_with_DP;
import java.io.*;
import java.util.*;

/*==========================================================================
 * 시간을 줄인 가장 큰 이유는 출력을 stack으로 하지 않고 바로 배열을 만들어서 담아내는 과정만 해서이다. stack으로 하는게 1100ms였고, 하지 않고 배열로만 작업한것은 240ms였다.
 * Arrays.fill을 해도 시간의 큰 차이는 없었다. BFS를 해서 맨처음 들어온 것은 시간상 최소가 맞다. 그치만 내가 다시 불필요하게 원래조건문을 추가해보니 시간적으로 차이는 없었다.
  
 * BFS를 이용했기에 그 다음 같은 번호로 들어올 애들은 항상 이전의 들어온것보다 시간이 더 크거나 같을 것이다. 따라서 처음 들어온것이 항상 최소를 유지해준다.
 * 만약 Arrays.fill을 하지 않을려면 내가 원래 -1로 판단한것을 0일때 아무것도 들어가지 않았다고 해야하므로 모든 sec를 1씩 늘린상태로 업데이트와 판단을 하고 다 끝나면 sec-1을 해서 답을 내준다.
  
 * Node를 만들지 않아도 된다. 사실 판단을 하고 바로 업데이트를 해주는 부분이 있기에 새로운 업데이트가 나오면 q에 들어가서 다시한번 q에 나올때 시행을 해볼수있다.
 * 그래서 Node의 sec와 상관없이 판단은 계속 가능하고, 시행도 sec와 상관이 없다.
 ===========================================================================*/

//13913번 - 숨바꼭질 4  (264ms) -(Node없이는 240ms, 다만 서버상황에 따라 계속 달라진다.)
public class p4__SoomBakGgokZil_with_BFS {
    static int N, K;            // 0 <= N,K <= 100_000
    static int[] dp, before;    // N에서부터 i까지 몇초 걸리는지, i번으로 오기전의 노드가 무엇인지.
    static Queue<Node> q;
    static int MAX = 100_001;
    //======================================================
    static class Node{
        int idx, sec;
        Node(int a, int b){
            idx=a; sec=b;
        }
    }//======================================================
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        /* 수빈이는 +1, -1, 2배로 이동할수가 있다. */
        dp = new int[MAX+1];
        before = new int[MAX+1];
        Arrays.fill(dp, -1);
        Arrays.fill(before, -1);
        dp[N] = 0;
        before[N] = Integer.MAX_VALUE;  //처음 시작인 곳은 자기 이외에는 돌아갈 곳이 없다.
        q = new LinkedList<>();
        q.add(new Node(N, 0));
        boolean check = false;
        
        //BFS로 동작하면서 그 내용을 메모이제이션해서 DP로 이용. 이 DP로 경로추적에 사용
        while(!q.isEmpty()) {
            Node now_node = q.poll();
            int now = now_node.idx;
            int sec = now_node.sec;
            if(now == K) break; //사실 이 조건문은 맨 처음에 now가 K일때만 걸려진다. 그외에는 저 아래 조건문으로 빠져나온다.

            int[] cal = {now-1, now+1, now*2};
            for(int i=0; i<3; i++){
                int next = cal[i];

                if(0<=next && next<=MAX){
                    if(dp[next]==-1 || sec+1<dp[next]){   //처음 들어가거나, 그 뒤에건 사실 불필요한 조건문. 이전보다 더 좋게 들어갈 수 있을 때(대신 1초차이도 결국 똑같아지니 패스)
                        dp[next] = sec+1;       //이곳에서 업데이트를 이루어지므로 저기 q에 빠져나올때 검사를 또 안해도 된다. 업데이트하고 판단을 이곳에서 다 해주고 걸러준다.
                        before[next] = now;
                        q.add(new Node(next, dp[next]));
                        if(next == K) {
                        	check = true;
                        	break;    //이미 업데이트 했으니 q에 들어오고 빠져나오지 않아도 끝났다. BFS라서 sec가 가장 낮은것부터 큰대로 q에 들어가게 된다. 최선이 나온다.
                        }
                    }
                }
            }
            if(check) break;
        }

        //출력
        int count = dp[K];        //원래 0부터 시작인데 1부터 시작해서 원래 시작의 맞추기 위해 -1을 해준다.
        int[] answer = new int[count+1];    //경로
        int end = K;
        for(int i=count; i>=0; i--) {
            answer[i] = end;
            end = before[end];
        }
        StringBuilder sb = new StringBuilder();
        for(int i:answer)
            sb.append(i+" ");
        bw.write(count+"\n");         //개수 출력
        bw.write(sb.toString()); //경로 출력
        bw.flush();


    }//======================================================
}
