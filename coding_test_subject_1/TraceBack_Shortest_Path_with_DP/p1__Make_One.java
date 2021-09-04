package TraceBack_Shortest_Path_with_DP;

import java.io.*;
/** 동적 계획법과 최단거리 역추적 - 지금까지는 최솟값, 최댓값, 최단거리만 찾았습니다. 이번에는 실제 최적해와 최단경로를 찾아 봅시다. */

/*==============================================================
 * 진행 가능한 연산은 3가지
   1) X가 3으로 나누어 떨어지면, 3으로 나눈다.
   2) X가 2로 나누어 떨어지면, 2로 나눈다.
   3) 1을 뺀다.

 * 정수 N이 주어졌을 때, 위와 같은 연산 3가지를 적절히 사용해서 '1'을 만들려고 한다.
 * 연산을 사용하는 횟수의 최솟값을 얻기위한 방법에 쓰여지는 값들을 순서대로 출력해보자.
 ==============================================================*/

/**============================================================
 * 내가 생각한 방법은 DP를 사용하겠다면 그 시작은 '1'에서 시작해서 위의 연산의 결과가 반대로 나와서 역으로 N에 도달하는 형식으로 진행 해보려한다.
 * 1) 3을 곱한다.
 * 2) 2를 곱한다.
 * 3) 1을 더한다.  
 =============================================================**/
// 12852번 - 1로 만들기 2
public class p1__Make_One {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		//반대로 생각해서 +1을 하거나, 2를 곱하거나, 3을 곱하는 연산을 해서 N에 도달하는것을 시작해보자.
		int[] dp = new int[N+1];    //dp에는 자신을 만든 녀석이 누구인지 들어있다.
		int[] cnt = new int[N+1];
		int now = 1;	//현재 now보다 작은 값에 대해서는 확정이 되어간다.
		while(true) {
			if(now == N) break;

			int[] cal = {now+1, 2*now, 3*now};
			for(int i=0; i<3; i++) {
				int next = cal[i];
				if(next <= N) {
					if(dp[next]==0 || cnt[now] < cnt[next]) {	//처음으로 들어온것 혹은 지금이 count의 수를 줄일 수 있다면(물론 count상 1차이는 업데이트 안해도 되지만 하도록 했다.) 
						dp[next] = now;  
						cnt[next] = 1 + cnt[now];
					}
				}
			}
			now++;
		}

		//구해놓은 dp로 역추적
		StringBuilder sb = new StringBuilder();
		int next = N;
		while(true) {
			sb.append(next+" ");
			next = dp[next];
			if(next==0)
				break;
		}
		bw.write(cnt[N]+"\n");
		bw.write(sb.toString());
		bw.flush();
	}
}

