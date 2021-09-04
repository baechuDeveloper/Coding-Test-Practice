package Dynamic_Programing_2;

import java.util.*;
import java.io.*;

// 백준 11066 - 파일 합치기
// 모든 장을 합치는데 필요한 최소한의 비용을 구해오시오.
// * 설명에 잘 안나왔지만 연속된 페이지끼리만 합칠수가 있다.
/**
 * dp[i][j]는 page[i]부터 page[j]까지 누적되온 최소가치를 저장한다. (i와 j는 1부터 시작하는 인덱스로 둔다.)
 * 예를들어 {40, 30, 30, 50}일때, dp[1][2]라면 page[1]과 page[2]인 40과 30의 합인 70이된다. (2개 밖에 없어서 항상 최소가치)
 * 
 * 단 이렇게만 한다면 단순히 '합'만 구하는 형태인것이다. 
 * dp[1][3]을 구할려면 dp[1][1]과 dp[2][3]을 더한것과 dp[1][2]와dp[3][3]을 더한 값을 비교하면 된다.
 * dp[1][4]는 dp[1][1]+dp[2][4] -- dp[1][2]+dp[3][4] + dp[1][3]+dp[4][4]......
 * 이렇게 말고 우리는 더 누적이 되어가는 형태로 잡아가야 한다.
 * 
 * 즉 dp[1][3] (30+30) + (30+30) + 40 인것이다. 더 정확히 본다면 (40 + 30 + 30) + 30 + 30 이며
 * 이는 1번째부터 3번째까지의 합 에 dp[2][3]을 더한 형태이다. 
 * 이렇게 전개해본다면 dp[1][1] 같은 경우는 값을 0으로 치환을 해두는게 맞을것이다. 단독으로 올때는 예외적으로 칭하는것이다.
 * 따라서 이를 점화식으로 풀자면 
 * dp[1][3] = sum[3]-sum[0] + Math.min( dp[1][2], dp[2][3]);
 * dp[1][4] = sum[4]-sum[0] + Math.min( dp[1][3]+dp[4][4] , dp[1][2]+dp[3][4], dp[1][1]+dp[2][4] );
 * **/

public class p2__DP_page_sum {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test_N = Integer.parseInt(br.readLine());
		for(int z=0; z<test_N; z++) {

			int N = Integer.parseInt(br.readLine());
			int[] page = new int[N+1];					// 각 페이지의 크기 이면서 비용
			int[] sum = new int[N+1];
			int[][] dp = new int[N+1][N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				page[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + page[i];
			}//입력완료
		
			//길이가 2인것은 항상 최소가치로 누적되니 미리 정해둔다.
			for(int i=1; i<=N-1; i++) 
				dp[i][i+1] = page[i]+page[i+1];
			
			
			// 만드는 순서는 [1][3],[2][4]...[N-2][N],[1][3],[2][4]...[N-2][N],...,[1][N-1],[2][N], [1][N]까지
			// 순서대로 진행을 하는 큰 범위대로 for문을 정의해가면 된다. 일정한 간격만큼 진행하고 1,2,3씩 진행을 하며 그 곳에서 min을 측정한다. 
			
			for(int len=2; len<=N-1; len++) {	 // [i][j]에서 j-i의 차이 , 즉 길이 비슷한 느낌 
				for(int i=1; i<=N-len; i++) {    // N-len까지 하면 해당 길이의 마지막 원소에 해당하는 첫번째 인덱스가 된다.
					int j = i+len;
					int min = Integer.MAX_VALUE;
					for(int k=i; k<j; k++) { // 최소 이 횟수만큼을 통해 min을 알아본다. 즉 len의 수만큼 비교를 해본다.
						min = Math.min( min, dp[i][k] + dp[k+1][j] );
					}
					dp[i][j] = (sum[j]-sum[i-1]) + min;
				}
			}

			System.out.println(dp[1][N]);
		}
	
	}//===========================================================
}
