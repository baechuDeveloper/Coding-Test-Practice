package Dynamic_Programing_2;

import java.util.*;
import java.io.*;

// 백준 7579 - 앱
// 최소한의 비용으로  M 바이트 이상의 메모리를 추가로 확보

// 일반적인 투 포인터로 슬라이딩 윈도우방식을 이용하기에는 설령 정렬을 해도 그 사이사이의 값을 쓰지 않고 답이 나오는 경우도 있으므로 적용하기 어렵다고 본다.
// 사실 이 문제는 냅색문제라고 보면 될 것이다. 냅색의 기반한 dp를 슬라이딩 윈도우 방식을 유사하게 적용한 풀이이다.

/**--------------------------------------------------------------------------------
 * 이 방법은 long[] dp = new long[N+1] 을 통해 진행을 한다.
 * 이 dp는 각 인덱스가 cost를 뜻하며 dp의 값은 현재 cost에서 낼수있는 가장 큰 메모리를 뜻한다.
 * 그래서 첫번째 앱부터 마지막 앱까지 하나씩 진행을 해가면서.. dp 전체 코스트에 대해 각각 현재 앱을 이용해 더 큰 메모리를 낼지 일일히 비교를 해본다.
 * 한번의 앱이 끝나면 현재 dp는 각 cost에 대해서 자신이 가질수있는 최대의 메모리 크기를 가지고있는 상태이다.
 * 모든 앱이 끝나면 dp에는 cost에 따른 그 cost로 낼수있는 최대 메모리 크기를 가지고있다.
 * M이라는 메모리 이상을 줄수있는 최소의 cost를 낼려면 ... 
 * 가장 낮은 cost 0부터 하나씩 진행을 해가며 M을 넘는 메모리가 나오면 그 cost가 가장 작은 비용으로 M이상 메모리를 확보하게 된다.
 * --------------------------------------------------------------------------------**/
public class p5__App_Knapsack_mem {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 앱의 개수
		int M = Integer.parseInt(st.nextToken());	// 필요한 메모리양 
		int[] mem = new int[N];						// 각 앱의 사용된 메모리
		int[] cost = new int[N];					// 비활성화 했을 때 비용
		int allCost = 0;							// 앱을 다 사용할때 메모리 양
		
		StringTokenizer m_st = new StringTokenizer(br.readLine());
		StringTokenizer c_st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			mem[i] = Integer.parseInt(m_st.nextToken());
			cost[i] = Integer.parseInt(c_st.nextToken());
			allCost += cost[i];
		}
		long[] dp = new long[allCost+1];	// 각 dp의 번호는 cost에 따른 번호이며... 그 값은 코스트로 가질수있는 최대 메모리 양이다.
		
		/** 보통의 방법이 아니다. 가방문제에서 사용가능한 형태의 방식이다.**/ 
		for(int i=0; i<N; i++) 									// 각 앱에 대해 하나씩 cost에 따른 dp를 비교해준다. 
			for(int j=allCost; j>=cost[i]; j--) 				// dp의 마지막 번호부터 자신의 cost번호까지 진행을하게된다.
				dp[j] = Math.max(dp[j],  dp[j-cost[i]] + mem[i]);	
		 
	  /* ---------------------------------------------------------------------------
	   * 해당 cost에 대해 기존의 뽑아본 j비용의 메모리 dp[j]가 클지, 
	   * 아니면 지금의 앱 cost[i]비용의 mem[i]와 이 앱을 포함시키지 않은 j-cost[i]비용의 기존 dp[j-cost[i]]를 더한게 클지를 보며 더 큰 메모리값을 얻어둔다. 
	   * 계속 진행을하면서 현재의 앱의 비용까지 도달할때까지 각 cost에 대해 메모리가 최대가 되는 형태가 되도록 진행을 해간다. 
		----------------------------------------------------------------------------*/
		
		// 이제 cost에 대해 최대 값을 구해둔 dp[i]값이 M이상일때 비용이 최소인 경우에 멈추게 해준다.
		for(int i=0; i<=allCost; i++) {	// 낮은 코스트부터 그 메모리가 M을 넘을때까지 진행.
			if(dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}//===========================================================
}
