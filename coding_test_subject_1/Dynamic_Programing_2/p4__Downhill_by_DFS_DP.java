package Dynamic_Programing_2;

import java.util.*;
import java.io.*;
/**
 * 아래 코드는 탐색기법중 하나인 백트래킹 방법이다. DFS를 활용해서 백트래킹을 작업하며 이 백트래킹 탐색 기법을 활용해 Top-Down방식의 DP를 이용하도록 한다.
 * '내리막길' 문제는 백트래킹 기법을 이용한 DFS방식의 '형태'를 잡고 그 내용물의 정보를 메모이제이션을 활용한 테이블을 기록해가는 Top-Down방식으로 
 *  작은 결과값의 뭉치들이 다시 큰 뭉치를 만들어가며 테이블의 내용물을 채우고 또 그 내용물을 통해 다시 문제를 풀어갈 수 있게 한다. 
 *  이렇게 '내용물의 테이블'을 채우고 이용하기를 반복하는 형태가 DP라는 기법이며, 그 방향성은 백트래킹구조를 활용해서 진행을 도울 수 있었다.  
  백트래킹기법으로 진행을 도움
  visit[i] = true;
  DFS()
  visit[i] = false;
 *  하는 구조로 가는 것은 모든 DFS의 경우를 일일히 해보는 방법이 될수있지만 너무많은 시간이 걸린다.
 * 
 *  그렇기에 이 visit를 더 효울적으로 dp[i] = -1로 아직 방문하지 않는 걸로 해두고 -1이 아니면 방문해서 어떠한 결과를 가져온 형태가 되어 그 값을 그대로 사용한다.
 *  이러면 여러번의 DFS를 돌리지 않고도 최소한의 필요한 경로를 구해서 값을 가져온다.
 *  또한 -1이 아닌 값을 방문을 하게 되는 경우라는건 DFS의 구조상 이미 해당 위치에서의 DFS는 끝난 것을 알수있다. DFS로 끝까지 도달해야만 방문했던곳을 또 방문하게 되는것이고
 *  방문을 하게된곳에는 항상 그 경로에서 필요한 경우의 수를 DFS로 모두 끝마쳐 채운것이기 때문이다. 
 * **/
// 1570번 백준 내리막 길
public class p4__Downhill_by_DFS_DP {

	private static int R, C;	//row, col
	private static int[][] arr, dp;
	private static int[] x = {1, 0, -1, 0};
	private static int[] y = {0, 1, 0, -1};
	//================================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R+1][C+1];
		dp = new int[R+1][C+1];
		
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(DFS(1,1));
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.print(dp[i][j]+" ");
			}System.out.println();
		}
		
	}//================================================================

	// 모든 경로의 행위를 가지는 않고 자신이 이때가지 알아온. 자신으로 시작하는 모든 경우의 경로를 알려주는 행태가 된다. 
	// 만약 자신이 갈수있는 경로인데 이미 누군가 방문을 했다고 한다면 우리는 그쪽 DFS를 하지 않고 곧바로 해당 위치가 제공해주는 경로의 경우를 자신에게 더해주면 된다.
	public static int DFS(int row , int col) {
		if(row==R && col==C) {
		
			return 1;
		}
		//오른쪽0, 아래쪽1, 왼쪽2, 위쪽3
		dp[row][col] = 0; //이미 방문을 했으니 0으로 해준다. 
		
		for(int i=0; i<4; i++) {
			int n_row = row+y[i];
			int n_col = col+x[i];
			
			if( (0<n_row && n_row<=R) && (0<n_col && n_col<=C) 
					&& arr[row][col]>arr[n_row][n_col] ) 
			{
				if(	dp[n_row][n_col]==-1)	//아직 방문을 한번도 해본적이 없다. 
					dp[row][col] += DFS(n_row, n_col);	
				else
					dp[row][col] += dp[n_row][n_col];
			}
		}
		
		return dp[row][col];
	}//================================================================
}
