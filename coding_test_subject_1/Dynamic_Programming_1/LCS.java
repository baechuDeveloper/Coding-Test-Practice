package Dynamic_Programming_1;

import java.io.*;
// 9251�� - LCS (Longest Common SubSequence)
public class LCS {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A = br.readLine();
		String B = br.readLine();
		int len_a = A.length();
		int len_b = B.length();
		int[][] dp = new int[len_a + 1][len_b + 1];
		/**--------------------------------------------------------------------
		 * ������ 1��°���� �Ѵٰ� �д�.
		 * dp[i][j]���� A�� i��°�� B�� j��°������ LCS�� ���̸� �־�� ���̴�. 
		  
		 * �񱳸� �ҋ��� �� ó�� 1��° �� i�� 1��°�� j���� ������ �ϴµ� 
		 
		 * i�� j�� ���ڰ� ������ �� �� ���� �����ϱ� ���� [i-1][j-1]�� ���±����� LCS�� ���̿��� +1�� ���ָ� [i][j]�� ������ �ȴ�.
		  
		 * ���� �� ���ڰ� �ٸ��ٸ� 
		 * i��° ���ڸ� ���Ծ��� [i-1][j]�� LCS�� ���¿��� j��°�� �߰��� ���� �ƹ��� ������� [i][j]�ΰ����� ������ų���ְ�
		 * j��° ���ڸ� ���Ծ��� [i][j-1]�� LCS�� ���¿��� i������ �߰��ص� �ƹ��� ������� [i][j]���� �����Ҽ��ִµ� 
		 * ���⼭ �� ���� LCS�� �����صξ��� ���� �ùٸ� ���Ű����� [i][j]�� �ݿ����ٰ��̴�.
		 
		 * ���� ��ȭ���� 
		 	dp[i][j] = dp[i-1][j-1] + 1
		 	dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
		 	
		 	dp[0][1] �̶�� �ϸ� A������ �ƹ��͵� ���� �����̸� B������ 1��° ���ڱ��� ��Ÿ���� ���̴�.
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
