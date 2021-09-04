package Bitmask_DP;

import java.util.*;
import java.io.*;

public class p4__bitmask_DP {

	static int INF = 200_001;
	static int N, complete;
	static int[][] work;
	static int[][] dp;
	//=================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		complete = (1<<N)-1;
		work = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) 
				work[i][j] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N+1][(1<<N)-1];
		for(int i=1; i<=N; i++)
			Arrays.fill(dp[i], INF);
		
		System.out.println(tsp(1, 0));	//점화식 기준에서 work[now][next]에 대해 now의 값이 실존 해야하며, 먼저 1은 언제나 가지고 있어서 단순히 1부터 시작하게 했다.	
										//대신 visit는 0인 이유는... visit는 i j 중에서 j에 해당하는 visit를 알려주는데 아직 정해져진 열은 없기에 어떤 일도 처리 안된 0부터 시작해서 차근히 알아가는 것이다.
										//visit를 통해서 각각 현재 처리하고 있는 일이 뭐뭐인지 마크가 되어간다. 
										//해당 tsp의 진행은 그저 '순서대로' 해당 사람번호를 진행하고, 너가 이 일을 맡아보면 어떨지 계속 조합을 짜나가는 것이다. 
										// 원래 tsp에서는 단순히 방문을 했다는 영역과 마지막으로 돌아간다를 정해주면 되는데 이때 visit를 미리 짜두고 진행하는 형태로 하였다. 
	}//=================================================

	static int tsp(int now, int visit) {	//node는 현재 방문한 도시 , visit는 현재의 방문증 tsp를 재귀로 호출 할수록 방문한 도시가 많아질것이다. 
		//만약 모든 지점을 방문 한 경우
		if(visit == complete) {	//방문증에 모든 도시가 적혀있음 1111 1111 1111 1111 이렇게 
			return 0;
		}

		//지금 방문한 곳이 이미 방문해서 계산이 끝난 경우
		if(dp[now][visit] != INF) 
			return dp[now][visit];	//이미 방문해서 계산이 끝난 결과를 내보내준다.

		//정상적으로 방문이 되었다면 다음 방문지를 골라본다. 
		for(int next=1; next<=N; next++) {
			if((visit & (1<<(next-1))) == 0) { // 아직 방문을 하지 않았다면 진행을 한다. 
				
				int next_visit = visit | (1<<(next-1));	//해당 번호의 도시를 방문 했다고 비트마스크에 추가해준다. 
				
				dp[now][visit] = Math.min(dp[now][visit], work[now][next] + tsp(now+1, next_visit));	//기존 tsp와 다르게 now+1로 해당 도시의 개수가 늘어남을 알려준다. 
			}
		} //맨 처음에 호출한 (0,1)은 결국 모든 return을 거치고 온 모든 경우의 값들 중 가장 최소를 가져가게 될 것이다.

		//최소의 결과를 가진 자신의 방문 결과를 호출해준곳에 보내준다.
		return dp[now][visit];
	}//=================================================

}
