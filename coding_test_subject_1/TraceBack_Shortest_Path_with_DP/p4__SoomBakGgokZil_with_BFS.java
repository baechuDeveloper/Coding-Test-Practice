package TraceBack_Shortest_Path_with_DP;
import java.io.*;
import java.util.*;

/*==========================================================================
 * �ð��� ���� ���� ū ������ ����� stack���� ���� �ʰ� �ٷ� �迭�� ���� ��Ƴ��� ������ �ؼ��̴�. stack���� �ϴ°� 1100ms����, ���� �ʰ� �迭�θ� �۾��Ѱ��� 240ms����.
 * Arrays.fill�� �ص� �ð��� ū ���̴� ������. BFS�� �ؼ� ��ó�� ���� ���� �ð��� �ּҰ� �´�. ��ġ�� ���� �ٽ� ���ʿ��ϰ� �������ǹ��� �߰��غ��� �ð������� ���̴� ������.
  
 * BFS�� �̿��߱⿡ �� ���� ���� ��ȣ�� ���� �ֵ��� �׻� ������ ���°ͺ��� �ð��� �� ũ�ų� ���� ���̴�. ���� ó�� ���°��� �׻� �ּҸ� �������ش�.
 * ���� Arrays.fill�� ���� �������� ���� ���� -1�� �Ǵ��Ѱ��� 0�϶� �ƹ��͵� ���� �ʾҴٰ� �ؾ��ϹǷ� ��� sec�� 1�� �ø����·� ������Ʈ�� �Ǵ��� �ϰ� �� ������ sec-1�� �ؼ� ���� ���ش�.
  
 * Node�� ������ �ʾƵ� �ȴ�. ��� �Ǵ��� �ϰ� �ٷ� ������Ʈ�� ���ִ� �κ��� �ֱ⿡ ���ο� ������Ʈ�� ������ q�� ���� �ٽ��ѹ� q�� ���ö� ������ �غ����ִ�.
 * �׷��� Node�� sec�� ������� �Ǵ��� ��� �����ϰ�, ���൵ sec�� ����� ����.
 ===========================================================================*/

//13913�� - ���ٲ��� 4  (264ms) -(Node���̴� 240ms, �ٸ� ������Ȳ�� ���� ��� �޶�����.)
public class p4__SoomBakGgokZil_with_BFS {
    static int N, K;            // 0 <= N,K <= 100_000
    static int[] dp, before;    // N�������� i���� ���� �ɸ�����, i������ �������� ��尡 ��������.
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
        /* �����̴� +1, -1, 2��� �̵��Ҽ��� �ִ�. */
        dp = new int[MAX+1];
        before = new int[MAX+1];
        Arrays.fill(dp, -1);
        Arrays.fill(before, -1);
        dp[N] = 0;
        before[N] = Integer.MAX_VALUE;  //ó�� ������ ���� �ڱ� �̿ܿ��� ���ư� ���� ����.
        q = new LinkedList<>();
        q.add(new Node(N, 0));
        boolean check = false;
        
        //BFS�� �����ϸ鼭 �� ������ �޸������̼��ؼ� DP�� �̿�. �� DP�� ��������� ���
        while(!q.isEmpty()) {
            Node now_node = q.poll();
            int now = now_node.idx;
            int sec = now_node.sec;
            if(now == K) break; //��� �� ���ǹ��� �� ó���� now�� K�϶��� �ɷ�����. �׿ܿ��� �� �Ʒ� ���ǹ����� �������´�.

            int[] cal = {now-1, now+1, now*2};
            for(int i=0; i<3; i++){
                int next = cal[i];

                if(0<=next && next<=MAX){
                    if(dp[next]==-1 || sec+1<dp[next]){   //ó�� ���ų�, �� �ڿ��� ��� ���ʿ��� ���ǹ�. �������� �� ���� �� �� ���� ��(��� 1�����̵� �ᱹ �Ȱ������� �н�)
                        dp[next] = sec+1;       //�̰����� ������Ʈ�� �̷�����Ƿ� ���� q�� �������ö� �˻縦 �� ���ص� �ȴ�. ������Ʈ�ϰ� �Ǵ��� �̰����� �� ���ְ� �ɷ��ش�.
                        before[next] = now;
                        q.add(new Node(next, dp[next]));
                        if(next == K) {
                        	check = true;
                        	break;    //�̹� ������Ʈ ������ q�� ������ ���������� �ʾƵ� ������. BFS�� sec�� ���� �����ͺ��� ū��� q�� ���� �ȴ�. �ּ��� ���´�.
                        }
                    }
                }
            }
            if(check) break;
        }

        //���
        int count = dp[K];        //���� 0���� �����ε� 1���� �����ؼ� ���� ������ ���߱� ���� -1�� ���ش�.
        int[] answer = new int[count+1];    //���
        int end = K;
        for(int i=count; i>=0; i--) {
            answer[i] = end;
            end = before[end];
        }
        StringBuilder sb = new StringBuilder();
        for(int i:answer)
            sb.append(i+" ");
        bw.write(count+"\n");         //���� ���
        bw.write(sb.toString()); //��� ���
        bw.flush();


    }//======================================================
}
