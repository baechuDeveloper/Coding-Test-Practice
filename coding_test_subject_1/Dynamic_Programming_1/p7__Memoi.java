package Dynamic_Programming_1;

import java.io.*;
// 1463번 - 1로 만들기
public class p7__Memoi {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		if(N==1) { System.out.println(0); return; }
		dp[1] = 0;
		int t1, t2, t3;
		for(int i=2; i<=N; i++) {
			t3=Integer.MAX_VALUE; 
			t2=Integer.MAX_VALUE; 
			t1=Integer.MAX_VALUE;
			if(i%3==0) 
				t3 = dp[i/3]+1;
			if(i%2==0) 
				t2 = dp[i/2]+1;		
			t1 = dp[i-1]+1;
			
			dp[i] = Math.min(t1, Math.min(t2, t3));
		}
		System.out.println(dp[N]);
	}
}
