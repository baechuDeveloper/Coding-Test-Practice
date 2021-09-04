package Divide_and_Conquer;

import java.util.*;
import java.io.*;


public class p2__Perma_little_def {
	static long P = 1_000_000_007;
	static long[][] dp;
	//====================================================
	
	
	//�̰� ������ �̿��� ����ε� n�� ũ�Ⱑ �ſ�Ŀ���� �Ǹ� �׸�ŭ �޸� ������ ��û ���� �ø��� �Ǿ heap�� �޸� �ʰ��� �߻��Ѵ�. 
	static long combi_dp_fail(int n, int r) {
		if( n==r || r==0 ) 
			dp[n][r] = 1;
		else if(dp[n][r]==0) 
			dp[n][r] = ( combi_dp_fail(n-1, r-1) + combi_dp_fail(n-1, r) ) % P;		
		return dp[n][r];
	}//====================================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		dp = new long[n+1][r+1];;
	}//====================================================
	
}
