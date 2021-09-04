package Dynamic_Programing_2;

// 백준 - RGB거리 - 1149번 
// https://www.acmicpc.net/problem/1149

/**================================================================
 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 
 1)  1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 2)  N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.		
 3)  i번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.  (2 ≤ i ≤ N-1) 
 	(즉 양옆의 집의 색은 서로 달라야 한다.)
 ==================================================================**/

import java.util.StringTokenizer;
import java.io.*;
// dp방법을 '투 포인터' 알고리즘의 특성에서 '고정된 사이즈'를 가진  '슬라이딩 윈도우' 방식으로 적용해봄 내가.
public class RGB_Slide_Window {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		//----------------------------------------------------
		int N = Integer.parseInt(br.readLine());
		int[][] rgb = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) 
				rgb[i][j] = Integer.parseInt(st.nextToken());
		}
		//----------------------------------------------------
		int[][] dp = new int[2][3]; //슬라이딩 윈도우 방식
		
		for(int i=0; i<3; i++) 
			dp[0][i] = rgb[0][i];
				
		for(int i=1; i<N; i++) {
			
			//현재의 i번째 집을 빨강색으로 칠한 경우
			dp[1][0] = rgb[i][0] + Math.min(dp[0][1], dp[0][2]);
			
			//현재의 i번째 집을 초록색으로 칠한 경우
			dp[1][1] = rgb[i][1] + Math.min(dp[0][0], dp[0][2]);
			
			//현재의 i번째 집을 파란색으로 칠한 경우
			dp[1][2] = rgb[i][2] + Math.min(dp[0][0], dp[0][1]);
			
			for(int j=0; j<3; j++)
				dp[0][j] = dp[1][j];
		}
		
		int min = Math.min(dp[1][0], dp[1][1]);
		min = Math.min(dp[1][2], min);
		//----------------------------------------------------
		bw.write(min+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
