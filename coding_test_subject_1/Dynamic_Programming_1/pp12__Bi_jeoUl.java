package Dynamic_Programming_1;

import java.util.*;
import java.io.*;

// 2629번 - 양팔저울 
public class pp12__Bi_jeoUl {

	static int chu_N, ball_N;	//추의 개수, 볼의 개수
	static int[] chu_W, ball_W;	//추의 무게, 볼의 무게
	static boolean[][] dp;		//[맨 처음부터 해당 추까지 고려했음을 의미][그떄의 가능했던 무게 경우의 값]이 존재하는지 아닌지.
	//=================================================
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//dp객체 생성
		dp = new boolean[31][15001];
		
		//추 입력
		chu_N = Integer.parseInt(br.readLine());
		chu_W = new int[chu_N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=chu_N; i++) chu_W[i] = Integer.parseInt(st.nextToken());
		
		//구슬 입력
		ball_N = Integer.parseInt(br.readLine());
		ball_W = new int[ball_N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=ball_N; i++) ball_W[i] = Integer.parseInt(st.nextToken());
		
		//모든 조합 확인 
		find_dp(0, 0);
		
		//dp[chu_N][ball_W[i]]로 chu_N개 까지 모두 고려한 상태의 해당 ball_W[i]가 존재하는지 파악하면 된다.
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=ball_N; i++) {
			if(ball_W[i] > 15000) sb.append("N ");
			else if(dp[chu_N][ball_W[i]]) sb.append("Y ");
			else sb.append("N ");
		}
		
		System.out.println(sb.toString());
	}//=================================================
	
	//idx는 현재 고려해볼 추의 번호를 뜻하며, weight는 양쪽 저울의 차이를 뜻하게 된다. 왼쪽과 오른쪽 저울의 추가 올라가는 표현을 이렇게 나타낼 수 있는데, 
	//기준이 왼쪽이든 오른쪽이든 상관없이 일단 왼쪽이라고 두겠다. 왼쪽에서 해당 추에 대해 +를 하면 왼쪽에 놓아본다. 아무것도 안하면 양쪽에 아무것도 안 올린다. -를 하면 오른쪽에 올려둔다. 이런 표현이 된다.
	//주의할 점은 -를 해두어도 그저 차이를 통한 진행이니 절대 값으로 봐주어야 한다. 
	//이렇게 왼쪽, 아무것도, 오른쪽을 재귀로 계속 진행하면 왼쪽에만 몰린것 부터 오른쪽에 몰린 것까지 그 사이사이 모든 조합이 가능하게 진행이 되며, 중복된 계산을 모두 dp에 저장을 해서 계산량이 조금이나마 줄어든다. 
	//적어도 3^30만큼의 계산은 안나올것이다. 중복계산을 피하기도 하고, 절대값만 표현하기도 하고, boolean에 대한 표현이니 결과만 보면 30 * 15000의 비슷한 계산 진행으로 갈것이라고 본다.
	static void find_dp (int idx, int weight) {
		//이미 구해보았다면 바로 넘어간다.
		if(dp[idx][weight]) return;
		
		//해당 구슬 개수까지 고려한 weight(차이)가 존재
		dp[idx][weight] = true;
		
		//마지막 추까지 작업을 끝마친거면 더 할 작업은 없다.
		if(idx == chu_N) return;
		
		find_dp(idx+1, weight + chu_W[idx+1]);			 //왼쪽 저울에 놓아 보겠다.
		find_dp(idx+1, weight);							 //이 추를 어디에도 놓지 않겠다.
		find_dp(idx+1, Math.abs(weight - chu_W[idx+1])); //오른쪽 저울에 놓아 보겠다.
	}//=================================================
}
