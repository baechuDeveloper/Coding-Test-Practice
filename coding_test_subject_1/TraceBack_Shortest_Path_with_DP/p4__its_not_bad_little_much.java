package TraceBack_Shortest_Path_with_DP;

import java.io.*;
import java.util.*;

//13913번 - 숨바꼭질 4
public class p4__its_not_bad_little_much {
	static int N, K;
    static int[] dp, before;
    //======================================================
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        /* 수빈이는 +1, -1, 2배로 이동할수가 있다. */
        dp = new int[100_001];
        before = new int[100_001];
        Arrays.fill(dp, -1);
        Arrays.fill(before, -1);
        dp[N] = 0;
        before[N] = -1;

        //동생이 수빈이보다 뒤에 있다면 둘의 위치는 항상 0보다 크기에 -1로만 갈수있는데 이렇게 해두면 된다.
        //동생이 수빈이 보다 앞에 있을 때. 먼저 뒤에 있는 것들의 dp를 모두 채워 준다.
        for(int now=N; now>=1; now--) {
            dp[now-1] = dp[now]+1;
            before[now-1] = now;
        }
        //그리고 0부터 다시 dp를 채우는 작업을 시작해서 모든 경우를 다 정해두고 간다.
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
