package Dynamic_Programing_2;
import java.util.*;
import java.io.*;
// 11049�� - ��� ���� ���� . �ռ� Ǯ����!
public class p3__Matrix_multiplication {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] mat = new int[N+1][2];	//������� �Է��Ҽ� �ִ� ũ�⸸ �Է����� �־�����. 
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			mat[i][0] = Integer.parseInt(st.nextToken());
			mat[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N+1][N+1];	
		/**----------------------------------------------------------------------------
		 * dp[1][4]�� ABCD�� ���� ���� ���� ����ִ�. dp[1][1]�� A����ü�� 0, dp[1][2]�� AB�� ��İ��� �������� ����ִ�. 
		 * ���� AB�� ������ ���� ���� ��갪�� ����ִ�. 
		 * dp[1][3]�� ABC�� ���� ���� ���� ����ִ�. (AB)C�� A(BC)�� �� �ּ��� ��갪�� ������ �� ��������. 
		 * �ε����� 1���� ���
		 * 
		 * ��ȭ��
		 * dp[a][c] = dp[a][b] + dp[b+1][c] + (mat[a][0]*mat[b][1]*mat[c][1]) ;
		 * 				(�̶� b�� ������ a <= b < c)  
		 -------------------------------------------------------------------------------**/

		//ó���� [1][2], [2][3], [3][4] �̷��� .. �״����� [1][3] [2][4] �̷��� ������ �ø����� 	
		
		for(int bd=1; bd<N; bd++) {	//bd�� ������ ����. ������ 1�� �ͺ��� [1][N]�� ���� N-1���� ������ �÷��ش�.  
			int head = (N-bd);	// ���ڸ��� ������ (N-bd)�� �ε��� �����̴�.  
			for(int i=1; i<=head; i++) {	// dp[i][j]���� ���ڸ��� �ô´�. N-bd�� ���ڸ� �����̴�. 
				int j=i+bd;					// ������ �ڸ� �ε���
				for(int k=i; k<j; k++) {	// dp�� �������� ���ϱ����� �߰��� �ε����� �þ��ش�. j-1�ڸ����� �������� �ȴ�.
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
