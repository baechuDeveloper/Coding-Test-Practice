package TraceBack_Shortest_Path_with_DP;

import java.util.*;
import java.io.*;
/*------------
ABCDEF
QAWCDF

FGHABCDE
ABCDEFGH
--------------*/

/* =======================================================================
 * 해당 경로도 구해본다. 이때 만들어둔 dp배열을 통해 맨 뒤에부터 시작해서 
 * 가장 큰 개수부터 작은 수로 가는 방향으로 i와 j에 대해서 그 직사각형을 좁히는 형태로 접근합니다.
 =========================================================================*/
//9252번 - LCS 2
public class p3__LCS_perfect_trace {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String A = br.readLine();
		String B = br.readLine();
		int len_A = A.length();
		int len_B = B.length();
		int[][] dp = new int[len_A+1][len_B+1]; // index 0에 해당하는 부분도 초기 더하는 부분에 쓰이게 된다.

		for(int i=1; i<=len_A; i++){
			char a = A.charAt(i-1);
			for(int j=1; j<=len_B; j++){
				char b = B.charAt(j-1);
				if(a==b)
					dp[i][j] = dp[i-1][j-1] + 1;
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}

		/*for(int i=1; i<=len_A; i++) {
			for (int j = 1; j <= len_B; j++)
				System.out.print(dp[i][j] + " ");
			System.out.println();
		}*/

		Stack<Character> stack = new Stack<>();
		int count = dp[len_A][len_B];
		int A_cut = len_A;
		int B_cut = len_B;

		// 별짓 다해봤네. 구해놓은 dp배열을 통해 맨 뒤에부터 시작해서 가장 큰 개수부터 작은 수로 가는 방향으로 i와 j에 대해서 그 직사각형을 좁히는 형태로 접근합니다.
		while(count>0) {
			boolean check = false;
			for (int i = A_cut; i > 0; i--) {
				for (int j = B_cut; j > 0; j--) {
					if(dp[i][j]==count && dp[i-1][j]==count-1 && dp[i][j-1]==count-1) { //왼쪽 위족이 한개씩 작다면 당연히 대각선에서 오게된 경우다.
						//System.out.println(i+" "+j);
						stack.push(A.charAt(i-1));
						count--;
						A_cut = i-1;
						B_cut = j-1;
						check = true;
						break;
					}
				}
				if(check) break;
			}
		}
		//출력
		bw.write(dp[len_A][len_B]+"\n");
		while(!stack.isEmpty())
			bw.write(stack.pop()+"");
		bw.flush();

	}//======================================================
}