package Dynamic_Programming_1;

import java.io.*;
// 9251번 - LCS (Longest Common SubSequence)
public class LCS {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A = br.readLine();
		String B = br.readLine();
		int len_a = A.length();
		int len_b = B.length();
		int[][] dp = new int[len_a + 1][len_b + 1];
		/**--------------------------------------------------------------------
		 * 시작은 1번째부터 한다고 둔다.
		 * dp[i][j]에는 A의 i번째와 B의 j번째까지의 LCS의 길이를 넣어둔 값이다. 
		  
		 * 비교를 할떄는 맨 처음 1번째 인 i와 1번째인 j부터 시작을 하는데 
		 
		 * i와 j의 문자가 같으면 딱 이 둘을 포함하기 전인 [i-1][j-1]인 상태까지의 LCS의 길이에서 +1를 해주면 [i][j]가 갱신이 된다.
		  
		 * 만약 두 문자가 다르다면 
		 * i번째 글자를 포함안한 [i-1][j]인 LCS의 상태에서 j번째는 추가한 것은 아무런 영향없이 [i][j]인것으로 발전시킬수있고
		 * j번째 글자를 포함안한 [i][j-1]인 LCS의 상태에서 i번쨰를 추가해도 아무런 영향없이 [i][j]으로 발전할수있는데 
		 * 여기서 더 많은 LCS를 포함해두었던 것이 올바른 갱신값으로 [i][j]를 반영해줄것이다.
		 
		 * 따라서 점화식은 
		 	dp[i][j] = dp[i-1][j-1] + 1
		 	dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
		 	
		 	dp[0][1] 이라고 하면 A에서는 아무것도 없는 상태이며 B에서는 1번째 문자까지 나타냈을 때이다.
		 ----------------------------------------------------------------------**/
		for(int i=1; i<=len_a; i++) {
			char a = A.charAt(i-1);
			for(int j=1; j<=len_b; j++) {
				char b = B.charAt(j-1);
				if(a==b) 
					dp[i][j] = dp[i-1][j-1] + 1;
				else 
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		System.out.println(dp[len_a][len_b]);
	}

}
