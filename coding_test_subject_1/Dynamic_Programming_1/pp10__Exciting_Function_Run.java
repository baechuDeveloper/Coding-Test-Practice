package Dynamic_Programming_1;
import java.util.*;
import java.io.*;
// 1003번 - 신나는 함수 실행
public class pp10__Exciting_Function_Run {

	static int[][][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		dp = new int[100][100][100];
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a==-1 && b==-1 && c==-1) break;
			
			sb.append( "w("+a+", "+b+", "+c+") = " + w(a, b, c)+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		
	}//==========================================================
	// dp를 이용 즉, memoization을 이용해주자. dp의 근본중 하나는 이렇게 재귀를 이용할때 memoi를 깃들인 이 형태가 근본 dp이다.
	// memo를 하게된다면 엄청난 중복계산을 피할수 있다는 것 만으로 매우 시간을 단축할수있다. 일렬의 계산만을 하게 해준다.
	static int w(int a, int b, int c) {
		if(a<=0 || b<=0 || c<=0) {
			
			return 1;
		}
		else if(a>20 || b>20 || c>20) {
			if(dp[a][b][c] == 0) 
				dp[a][b][c] = w(20, 20, 20);
			return dp[a][b][c];
		}
		else if(a < b && b < c) {
			if(dp[a][b][c] == 0) 
				dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
			return dp[a][b][c];
		}
		else {
			if(dp[a][b][c] == 0) 
				dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
			return dp[a][b][c];
		}
	}//==========================================================

}
