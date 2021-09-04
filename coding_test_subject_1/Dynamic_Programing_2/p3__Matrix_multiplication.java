package Dynamic_Programing_2;
import java.util.*;
import java.io.*;
// 11049번 - 행렬 곱셈 순서 . 손수 풀었다!
public class p3__Matrix_multiplication {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] mat = new int[N+1][2];	//순서대로 입력할수 있는 크기만 입력으로 주어진다. 
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			mat[i][0] = Integer.parseInt(st.nextToken());
			mat[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N+1][N+1];	
		/**----------------------------------------------------------------------------
		 * dp[1][4]가 ABCD에 대한 최적 곱이 들어있다. dp[1][1]은 A그자체라서 0, dp[1][2]은 AB의 행렬곱이 최적곱이 들어있다. 
		 * 물론 AB만 있으니 둘이 곱한 계산값만 들어있다. 
		 * dp[1][3]은 ABC에 대한 최적 곱이 들어있다. (AB)C와 A(BC)중 더 최소의 계산값이 나오는 걸 가져간다. 
		 * 인덱스는 1부터 사용
		 * 
		 * 점화식
		 * dp[a][c] = dp[a][b] + dp[b+1][c] + (mat[a][0]*mat[b][1]*mat[c][1]) ;
		 * 				(이때 b의 범위는 a <= b < c)  
		 -------------------------------------------------------------------------------**/

		//처음에 [1][2], [2][3], [3][4] 이렇게 .. 그다음은 [1][3] [2][4] 이렇게 범위를 늘리도록 	
		
		for(int bd=1; bd<N; bd++) {	//bd은 범위의 약자. 범위가 1인 것부터 [1][N]과 같이 N-1까지 범위를 늘려준다.  
			int head = (N-bd);	// 앞자리의 범위를 (N-bd)인 인덱스 까지이다.  
			for(int i=1; i<=head; i++) {	// dp[i][j]에서 앞자리를 맡는다. N-bd인 앞자리 까지이다. 
				int j=i+bd;					// 마지막 자리 인덱스
				for(int k=i; k<j; k++) {	// dp의 최적곱을 구하기위한 중간의 인덱스를 맡아준다. j-1자리까지 가져가면 된다.
					int temp = dp[i][k] + dp[k+1][j] +(mat[i][0]*mat[k][1]*mat[j][1]);
					if(dp[i][j]==0 || dp[i][j]>temp) 
						dp[i][j] = temp;
				}
			}
		}
		/*for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(dp[i][j]+" ");
			}System.out.println();
		}*/
		System.out.println(dp[1][N]);
	}

}
