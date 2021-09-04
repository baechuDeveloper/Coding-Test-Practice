package Dynamic_Programing_2;
import java.util.*;
import java.io.*;

// 2293번 동전 1
/**================================================================
 * 첫 번째 동전만 사용하여 각 k값 마다 가능한 경우의 수를 찾는다.
 * 첫 번째~ 두 번째 동전만 사용하였을 때, 각 k값 마다 가능한 경우의 수를 찾는다. 이 때, 첫 번째 동전만 사용해서 구했던 경우의 수를 활용한다.
 * 모든 동전을 사용할 때 까지 반복한다. 
 * C(i) : i번째 코인의 가치
 * D(i,k) : i번째 코인까지 사용하여 k를 만들 수 있는 경우의 수
  
  	점화식
 * D(i,k) = | D(i-1,k) 					if C(i)>k
 * 			| D(i-1,k) + D(i, k-C(i))  	if C(i)<=k
 * 
 * 두 조건문에 항상 이전에 k의 값을 구한 경우의 수를 이어온다. D(i-1,k) (현재의 코인을 포함시키지 않은 상태의 경우)
 * 그리고 만약 자신도 이 k값에 기여할 수 있는 coin이 되었다면
 * 자기자신 coin인 C(i)를 '한 개 포함'시키기 위해! 같은 동전 조건(즉, 같은 동전 종류 상태 i)에서 이를 매꾸기 위해 k-C(i)한 값을 만들어내는 경우를 더하면
 * 자기 자신을 포함시킨 경우를 만들 수 있다. k-C(i)의 값의 경우는 같은 조건에서 이전에 구해 놓은 상태이다.
 * 해당 D(i,k-C(i))의 경우의 수와 자기 자신을 포함안하고 이전에 확실히 기록된 D(i-1,k)의 경우의 수를 더하면 
 * D(i,k) i라는 코인까지 포함시킨 조건에서 k값을 만드는 경우의 수를 완성시킬수 있다. 
 * 
	  k  0 1 2 3 4 5 6 7 8 9 10
	  	 ----------------------
	c[0] 1 0 0 0 0 0 0 0 0 0 0 
 	c[1] 1 1 1 1 1 1 1 1 1 1 1
	c[2] 1 1 2 2 3 3 4 4 5 5 6 
	c[3] 1 1 2 2 3 4 4 6 7 8 10
 ===================================================================**/

public class p1__Coin1{

	static int N, K; 	// 동전의 종류 개수와 동전으로 만들어야하는 값	 
	static int[] coin;	// 동전 종류
	static int[][] dp;
	//=========================================================
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N+1];
		for(int i=1; i<=N; i++) 
			coin[i] = Integer.parseInt(br.readLine());
		dp = new int[N+1][K+1];	//[N+1]은 각 코인을 사용한 것을 말합니다. 정렬이 안되어도 상관 없다.
								//[K+1]은 만들어야하는 값을 뜻합니다. 최대 K인 값까지 해서 접근을 합니다. 
		for(int i=0; i<=N; i++) 
			dp[i][0] = 1;		//0번째 인덱스는 자기자신에 대한 덧셈으로 해당 0번째 값이 1로 존재해서 더해지면 자기 자신을 더하는 효과로 이해하면된다.
								//예를 들어 k=5에서 c[3]의 값은 5라면 5-5 = 0에 해당하는 인덱스의 dp값을 더해서 새로운 dp를 완성하는데 이건 자기 자신만 추가하는 경우리므로 1을 더해준다.
		for(int i=1; i<=N; i++) {
			int now_C = coin[i];
			for(int k=1; k<=K; k++) {
				if(now_C > k) 
					dp[i][k] = dp[i-1][k];
				else //now_C <= k
					dp[i][k] = dp[i-1][k] + dp[i][k-now_C];		
			}
		}
		
		System.out.println(dp[N][K]);
	}//=========================================================
}
