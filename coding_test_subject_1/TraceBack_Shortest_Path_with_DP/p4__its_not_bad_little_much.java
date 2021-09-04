package TraceBack_Shortest_Path_with_DP;

import java.io.*;
import java.util.*;

//13913�� - ���ٲ��� 4
public class p4__its_not_bad_little_much {
	static int N, K;
    static int[] dp, before;
    //======================================================
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        /* �����̴� +1, -1, 2��� �̵��Ҽ��� �ִ�. */
        dp = new int[100_001];
        before = new int[100_001];
        Arrays.fill(dp, -1);
        Arrays.fill(before, -1);
        dp[N] = 0;
        before[N] = -1;

        //������ �����̺��� �ڿ� �ִٸ� ���� ��ġ�� �׻� 0���� ũ�⿡ -1�θ� �����ִµ� �̷��� �صθ� �ȴ�.
        //������ ������ ���� �տ� ���� ��. ���� �ڿ� �ִ� �͵��� dp�� ��� ä�� �ش�.
        for(int now=N; now>=1; now--) {
            dp[now-1] = dp[now]+1;
            before[now-1] = now;
        }
        //�׸��� 0���� �ٽ� dp�� ä��� �۾��� �����ؼ� ��� ��츦 �� ���صΰ� ����.
        for(int now=0; now<=K; now++) {
            int[] cal = {now-1, now+1, now*2};

            for(int i=0; i<3; i++) {
                int next = cal[i];
                if (0<=next && next<=100_000) {
                    if(dp[next]==-1 || dp[now]<dp[next]){
                        dp[next] = dp[now]+1;
                        before[next] = now;
                    }
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        int count = 0;
        int end = K;
        stack.push(end);
        while(true) {
            if(end == N) break;
            else {
                end = before[end];
                stack.push(end);
                count++;
            }
        }

        System.out.println(count);
        while(!stack.isEmpty()){
            //bw.write(dp[stack.peek()]+"  ");
            System.out.print(stack.pop()+" ");
        }
        //System.out.println();
        //bw.flush();

    }//======================================================
}
