package Dynamic_Programing_2;

import java.util.*;
import java.io.*;
/**
 * �Ʒ� �ڵ�� Ž������� �ϳ��� ��Ʈ��ŷ ����̴�. DFS�� Ȱ���ؼ� ��Ʈ��ŷ�� �۾��ϸ� �� ��Ʈ��ŷ Ž�� ����� Ȱ���� Top-Down����� DP�� �̿��ϵ��� �Ѵ�.
 * '��������' ������ ��Ʈ��ŷ ����� �̿��� DFS����� '����'�� ��� �� ���빰�� ������ �޸������̼��� Ȱ���� ���̺��� ����ذ��� Top-Down������� 
 *  ���� ������� ��ġ���� �ٽ� ū ��ġ�� ������ ���̺��� ���빰�� ä��� �� �� ���빰�� ���� �ٽ� ������ Ǯ� �� �ְ� �Ѵ�. 
 *  �̷��� '���빰�� ���̺�'�� ä��� �̿��ϱ⸦ �ݺ��ϴ� ���°� DP��� ����̸�, �� ���⼺�� ��Ʈ��ŷ������ Ȱ���ؼ� ������ ���� �� �־���.  
  ��Ʈ��ŷ������� ������ ����
  visit[i] = true;
  DFS()
  visit[i] = false;
 *  �ϴ� ������ ���� ���� ��� DFS�� ��츦 ������ �غ��� ����� �ɼ������� �ʹ����� �ð��� �ɸ���.
 * 
 *  �׷��⿡ �� visit�� �� ȿ�������� dp[i] = -1�� ���� �湮���� �ʴ� �ɷ� �صΰ� -1�� �ƴϸ� �湮�ؼ� ��� ����� ������ ���°� �Ǿ� �� ���� �״�� ����Ѵ�.
 *  �̷��� �������� DFS�� ������ �ʰ� �ּ����� �ʿ��� ��θ� ���ؼ� ���� �����´�.
 *  ���� -1�� �ƴ� ���� �湮�� �ϰ� �Ǵ� ����°� DFS�� ������ �̹� �ش� ��ġ������ DFS�� ���� ���� �˼��ִ�. DFS�� ������ �����ؾ߸� �湮�ߴ����� �� �湮�ϰ� �Ǵ°��̰�
 *  �湮�� �ϰԵȰ����� �׻� �� ��ο��� �ʿ��� ����� ���� DFS�� ��� ������ ä����̱� �����̴�. 
 * **/
// 1570�� ���� ������ ��
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

	// ��� ����� ������ ������ �ʰ� �ڽ��� �̶����� �˾ƿ�. �ڽ����� �����ϴ� ��� ����� ��θ� �˷��ִ� ���°� �ȴ�. 
	// ���� �ڽ��� �����ִ� ����ε� �̹� ������ �湮�� �ߴٰ� �Ѵٸ� �츮�� ���� DFS�� ���� �ʰ� ��ٷ� �ش� ��ġ�� �������ִ� ����� ��츦 �ڽſ��� �����ָ� �ȴ�.
	public static int DFS(int row , int col) {
		if(row==R && col==C) {
		
			return 1;
		}
		//������0, �Ʒ���1, ����2, ����3
		dp[row][col] = 0; //�̹� �湮�� ������ 0���� ���ش�. 
		
		for(int i=0; i<4; i++) {
			int n_row = row+y[i];
			int n_col = col+x[i];
			
			if( (0<n_row && n_row<=R) && (0<n_col && n_col<=C) 
					&& arr[row][col]>arr[n_row][n_col] ) 
			{
				if(	dp[n_row][n_col]==-1)	//���� �湮�� �ѹ��� �غ����� ����. 
					dp[row][col] += DFS(n_row, n_col);	
				else
					dp[row][col] += dp[n_row][n_col];
			}
		}
		
		return dp[row][col];
	}//================================================================
}
