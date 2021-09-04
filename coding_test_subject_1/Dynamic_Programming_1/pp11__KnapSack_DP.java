package Dynamic_Programming_1;
// https://st-lab.tistory.com/141
import java.util.*;
import java.io.*;
// 12865번 - 평범한 배낭
public class pp11__KnapSack_DP {

	static int N, K;
	static Integer[][] dp;
	static int[] W, V;
	//====================================================
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//물품의 수
		K = Integer.parseInt(st.nextToken());	//버틸 수 있는 무게
		dp = new Integer[N][K+1];				//[결과적으로 0부터 해당 물품 번호까지 쌓여있는 걸 의미][그 때의 버텨
		W = new int[N];
		V = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println( knapsack(N-1, K) ); 	// Top-Down방식으로 진행을 한다
	}//====================================================
	
	// i는 현재 살펴보고자 하는 물건의 '번호', k는 담아 낼수있는 '남은 무게량' 
	static int knapsack(int i, int k) {	
		// 범위 밖이라면 이렇게 친절하게 나가는 길을 알려줍니당.
		if(i < 0) return 0; 
		
		//탐색하지 않은 위치라면 Integer라서 가능. 아니면 모두 -1처럼 초기화 해서 작업해도 된다.
		if(dp[i][k] == null) {
			//현재 물건(i)을 추가로 못 담는 경우 (이전 i값 탐색),	재밌게도 Top-Down이라서 남은 무게량이 현재의 물건의 무게와 비교하는 조건을 갖는다. 
			if(W[i] > k) {
				dp[i][k] = knapsack(i-1, k);	//현재 물건은 포함 못하고 이전 물건으로 무게량을 그대로 가진채 넘어간다.
			}
			//현재 물건(i)을 담을 수 있는 경우
			else {
				// 현재 물건을 담지 않고 이전 i값으로 탐색, 아니면 현재 물건을 담고 남은 무게량을 갱신하고 가치합으로 늘려서 이전 i값으로 가게 된 상황으로 탐색. 결과적으로 큰 가치합인 걸 골라주면 된다.  
				dp[i][k] = Math.max( knapsack(i-1, k), V[i] + knapsack(i-1, k-W[i]) );	
			}
		}
		
		return dp[i][k];
	}//====================================================

}
