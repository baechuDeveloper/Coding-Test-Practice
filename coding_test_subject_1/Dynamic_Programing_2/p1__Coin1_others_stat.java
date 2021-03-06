package Dynamic_Programing_2;

// 백준 - 동전1 - 2293번
// https://www.acmicpc.net/problem/2293

/**==================================================
 * n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 
 * 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 
 * 그 경우의 수를 구하시오. 각각의 동전은 몇 개라도 사용할 수 있다.
 * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 * 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 
 * 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 
 * 동전의 가치는 100,000보다 작거나 같은 자연수이다.
 ===================================================**/

/**===========================================================================
 * 1원을 만드는 방법은 1원 동전을 사용하는 1가지 경우밖에 없다.
 * 2원을 만드는 방법은 1원 동전 2개를 사용하거나 2원 동전 1개를 사용하는 두 가지 경우가 있다. 
 * 이것은 1원을 만드는 경우의 수에 2원 동전 하나만 사용하는 한 가지 경우를 추가하는 것과 같다.
 * 5원을 만드는 방법은 5원(1개), 1원(3개) + 2원(1개), 1원(1개) + 2원(2개), 1원(5개)로 총 네 가지 경우가 있다. 
 * 이것은 4원을 만드는 경우의 수에 5원 동전 하나만 사용하는 한 가지 경우를 추가하는 것과 같다.
 ----------------------------------------------------------------------------
 *좀 더 자세히 이해하기 위해 dp 배열의 변화를 살펴보자
 idx 0   1   2   3   4   5   6   7   8   9   10  (k)
 -----------------------------------------------
 dp  1   0   0   0   0   0   0   0   0   0   0   (초기화)
 dp  1   1   1   1   1   1   1   1   1   1   1   (1만 사용하여 k를 만들 수 있는 경우의 수)
 dp  1   1   2   2   3   3   4   4   5   5   6   (1과 2를 사용하여 k를 만들 수 있는 경우의 수)
 dp  1   1   2   2   3   4   5   6   7   8   10  (1, 2, 5를 모두 사용하여 k를 만들 수 있는 경우의 수)
 -----------------------------------------------
 * 5원 = 0원 + 5원 (coins[i] = 5일때)
 * 5원 = 3원 + 2원 (coins[i] = 2일때)
 * 5원 = 4원 + 1원 (coins[i] = 1일때)
...

 * dp[j] = dp[j](기존의 동전 종류를 이용해 k를 만드는 경우의 수) + dp[j - coins[i]](새 동전 종류 사용하는 경우 추가)
 * 					coin[i]를 제외하고 만든 경우 				coin[i]을 포함시켜 만든 경우 
 =============================================================================**/

/**---------------------------------------------------------------------------------------------------------- 
 * 1원, 2원, 3원이라는 경우를 주어져서 10원을 만들라고 하면...
 * 1원과 2원과 3원으로 만들라고 할때, 기존 1원과 2원으로 만든 것에 맨 처음부터 k=3부터 목표인 k=10까지 3원을 하나씩 추가하는 것으로 dp의 경우를 늘린다. 
 * 예시로 3원은 dp[3]부터 의미를 가지며 dp[3]의 원래 값과 3원을 하나 추가할때 새로히 k=3이 되도록  dp[0](0원을 만드는 현재의 경우)는 3원 1개를 추가한 방법의 개수를 알려주게 된다.
 * 그렇게 dp[3]은 3원을 포함하여 새로히 바뀌고... 그다음 dp[4]는 기존의 dp[4]의 원래 값과 dp[1]을 만드는 현재의 경우(여기에 3원만 더해지면 4원을 만드는 경우와 동일하다)와 함께 새로히 바꾸어지고
 * 그렇게 흘러 dp[6]은 기존의 dp[6]의 경우와 dp[3]을 만드는 현재의 경우, 즉 dp[3]이 이미 3원에 대해 반영을 한 상태이며. 그렇기에 dp[3]에 또 한번의 3원이 더해지는 것이 3원을 만드는 경우를 모두 적어준것이 된다.

 * dp[6]은 이렇게 기존의 것과 우리가 적용한 것에 대해 또 한번 3원의 경우를 추가하게되어... 
 * 새로운 6원을 만드는데 있어서 '3원을 전부 제외하고 6원을 만든 기존 경우'와 6-3한 '3원을 만드는데있어서 현재의 경우'를 서로 더하면 되고.
 * 이 6-3한 '3원을 만드는데 있어서 현재의 경우'자체는 말그대로 3원을 만드는 경우이지만 이 3원을 만드는 원소의 조합 경우의 수는 해당 각 원소의 집합마다 추가로 3원을 더하는 것이고
 * 결국 원소만 각각 하나씩 추가된것은 전체 경우의수도 동일하기에 dp[3]의 값이  6원에 대한 방법의 일부가 된다.       
 * 
 * dp[7]은 3원을 제외하고 기존의 dp[7]과 
 * 7-3한 4원을 만드는 현재의 방법을 더한것과 같다. 이 dp[4]를 만드는 현재의 경우의 각 원소의 조합에 3원을 추가하는것은 경우의 수가 바뀌지는 않기때문이다. 
 * 
 * dp[0]은 1로 해주어야 하는건 새 동전을 추가할때 자기 자신의 경우 하나만 더한 걸 표현하고자 dp[0]를 만드는 경우과 자기 자신 1개만 이용한 것을 뜻하기 때문이다.  
 ==============================================================================================================**/

import java.util.StringTokenizer;
import java.io.*;

public class p1__Coin1_others_stat {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		//---------------------------------------
		int N = Integer.parseInt(st.nextToken());	// 전체 동전 개수
		int k = Integer.parseInt(st.nextToken());	// 원하는 목표 값
		
		int temp = 0;
		int[] coin = new int[N];					// 동전의 종류
	
		for(int i=0; i<N; i++) {					// 동전 입력
			temp = Integer.parseInt(br.readLine());
			if(temp <= 10000) 
				coin[i] = temp; 
		}
		//---------------------------------------
		int[] dp = new int[10001];	// 0번 부터 10000번 인덱스까지 전부 이용할수 있도록 10001로 설정해두었다. 물론 k까지의 범위만 우리는 이용하게 된다.
		dp[0] = 1;					// 자기 자신 하나만을 더하는 경우를 위해서 1로 미리 설정해둔다. dp[0]는 고정 될것이다.
		
		for(int i=0; i<N; i++) {			// dp시작
			for(int j=coin[i]; j<=k; j++) 
				dp[j] += dp[j - coin[i]];	// 위에 참조
			
			for(int z=1; z<=k; z++ ) System.out.print(dp[z]+" "); System.out.println();	//단순 현재 dp상태 보여주는 출력문 
		}
		//----------------------------------------
		bw.write(dp[k]+"");		//dp[k]를 계속 누적하면서 마지막 dp[k]까지 얻어온 결과가 정답이다.
		bw.flush();
		bw.close();
		br.close();
	}

}//===================================================================================

/* Input 값 

3 10
1
2
5
 
 * */
