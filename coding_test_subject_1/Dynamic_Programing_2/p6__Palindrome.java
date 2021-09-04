package Dynamic_Programing_2;

import java.util.*;
import java.io.*;

// 팰린드롬 - 뒤집어도 같은 말이 되는 단어 - 백준 10942번
// 일일히 테스트하면 당연히 답이 나오겠지만 시간초과.
/**-------------------------------------------------
 * 홍준이는 자연수 N개를 칠판에 적는다. 그 다음, 명우에게 질문을 총 M번 한다.
 * 각 질문은 두 정수 S와 E로 나타낼 수 있다. (1 ≤ S ≤ E ≤ N) 
 * S번째 수부터 E번째 까지 수가 팰린드롬을 이루는지를 물어보며, 명우는 각 질문에 대해 팰린드롬이다 또는 아니다를 말해야 한다.
 * 	1 2 1 3 1 2 1 이라는 걸 칠판에 적고
 * S = 1, E = 3인 경우 1 2 1 은 팰린드롬이다.
 * S = 2, E = 5인 경우 2 1 3 1은 팰린드롬이 아니다.
 ---------------------------------------------------**/
/*
 * 길이마다의 펠린드롬인지 알려주도록 DP를 작성하도록 해본다. 
 * 
 * */
public class p6__Palindrome {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(); // 수많은 출력문에 도움이 됨.
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] board = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			board[i] = Integer.parseInt(st.nextToken());
		
		int ans_N = Integer.parseInt(br.readLine());
		int[][] ans = new int[ans_N][2];
		for(int i=0; i<ans_N; i++) {
			st = new StringTokenizer(br.readLine());
			ans[i][0] = Integer.parseInt(st.nextToken());
			ans[i][1] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] dp = new boolean[N+1][N+1];	//행은 길이에 대한 정보, 열은 각 길이에 첫번째에 해당하는 원소 번호이다. 
		
		//우선 길이가 1일때와 2일때 만들어두자. 
		for(int i=1; i<N; i++) {
			dp[1][i] = true;
			if(board[i]==board[i+1]) 
				dp[2][i] = true;
		}
		dp[1][N] = true;
		
		//나머지 길이에 대해서는 이제 밑에 만든것들에 대한 정보와 자신의 첫번째와 마지막 원소가 같은지만 비교해서 보면 된다.	
		/**-------------------------------------------
		 * 예를 들어 길이가 3일때 1번째원소를 시작하는 형태는 1 2 1 일것이다. 
		 * 그러면 첫번째원소와 마지막원소가 같은지만 확인하고 그안에 길이가 3-2한 1인 dp의 1+1번쨰원소를 가져간다.
		 * 
		 * 이걸 풀어놓자면 길이가 i일때 j번째 원소를 시작하는 형태는... 
		 * 첫번째  j와 마지막 j+(i-1)번쨰가 같은지 확인하고 길이가 (i-2)한 상태의 j+1번째 원소인 dp[i-2][j+1]이 펠린드롬을 만족하는 본다.
		 * 이 i는 3부터 시작하게된다. 
		 ---------------------------------------------**/
		for(int i=3; i<=N; i++) 	//길이 3부터
			for(int j=1; j<=N-(i-1); j++) 	//원소는 길이에 따라  첫번쨰로 선택가능한 범위가 바뀌며 N-(i-1)번째 까지임
				if( board[j]==board[j+(i-1)] && dp[i-2][j+1] ) 	//첫번쨰와 마지막 원소가 같은지 비교하고, 가운데 값이 펜림드롬인지 본다.
					dp[i][j] = true;
		
		// answer 풀이
		int len;	
		for(int i=0; i<ans_N; i++) {
			len = ans[i][1] - ans[i][0] + 1;	//  ans[i][0]<=   <=ans[i][1]
			if(dp[len][ans[i][0]] == true)
				sb.append(1+"\n");
			else
				sb.append(0+"\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
		/* 확인용 출력문
		for(int i=1; i<=N; i++) { for(int j=1; j<=N-(i-1); j++) { System.out.print(dp[i][j]+" "); }System.out.println(); }
		 */		
	}

}
