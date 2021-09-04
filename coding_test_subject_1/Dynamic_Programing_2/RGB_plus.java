package Dynamic_Programing_2;

import java.util.StringTokenizer;
import java.io.*;
// 추가적으로 path정보까지 더할 수 있도록 해둠.
// dp에 접근할때 dp를 부분을 작게두며 이동하는 형태인 슬라이딩 윈도우 방식
public class RGB_plus {

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
		String[][] path = new String[2][3];
		char[] color = {'R', 'G', 'B'};
		for(int i=0; i<3; i++) {
			dp[0][i] = rgb[0][i];
			path[0][i] = new String(color[i]+" ");
		}

		for(int i=1; i<N; i++) {
			//현재의 i번째 집을 각 색으로 칠한 경우
			for(int j=0; j<3; j++) {
				int idx1 = (j+1)%3;
				int idx2 = (j+2)%3;
				
				if(dp[0][idx1] < dp[0][idx2]) {
					dp[1][j] = rgb[i][j] + dp[0][idx1];
					path[1][j] = path[0][idx1] + color[j]+" ";
				}
				else {
					dp[1][j] = rgb[i][j] + dp[0][idx2];
					path[1][j] = path[0][idx2] + color[j]+" ";
				}
			}
			// 다음 작업을 이어가도록.
			for(int z=0; z<3; z++) {
				dp[0][z] = dp[1][z];
				path[0][z] = path[1][z];
			}
		}
		//----------------------------------------------------
		int index = -1;
		if(dp[1][0]<dp[1][1]) {
			if(dp[1][0]<dp[1][2]) index = 0;
			else index = 2;
		}
		else {
			if(dp[1][1]<dp[1][2]) index = 1;
			else index = 2;
		}
		//----------------------------------------------------
		bw.write(dp[1][index]+"\n");
		bw.write("색은 "+ path[1][index]);
		bw.flush();
		bw.close();
		br.close();
	}

}