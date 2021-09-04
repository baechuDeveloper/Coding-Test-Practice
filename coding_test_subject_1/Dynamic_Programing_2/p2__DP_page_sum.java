package Dynamic_Programing_2;

import java.util.*;
import java.io.*;

// ���� 11066 - ���� ��ġ��
// ��� ���� ��ġ�µ� �ʿ��� �ּ����� ����� ���ؿ��ÿ�.
// * ���� �� �ȳ������� ���ӵ� ������������ ��ĥ���� �ִ�.
/**
 * dp[i][j]�� page[i]���� page[j]���� �����ǿ� �ּҰ�ġ�� �����Ѵ�. (i�� j�� 1���� �����ϴ� �ε����� �д�.)
 * ������� {40, 30, 30, 50}�϶�, dp[1][2]��� page[1]�� page[2]�� 40�� 30�� ���� 70�̵ȴ�. (2�� �ۿ� ��� �׻� �ּҰ�ġ)
 * 
 * �� �̷��Ը� �Ѵٸ� �ܼ��� '��'�� ���ϴ� �����ΰ��̴�. 
 * dp[1][3]�� ���ҷ��� dp[1][1]�� dp[2][3]�� ���ѰͰ� dp[1][2]��dp[3][3]�� ���� ���� ���ϸ� �ȴ�.
 * dp[1][4]�� dp[1][1]+dp[2][4] -- dp[1][2]+dp[3][4] + dp[1][3]+dp[4][4]......
 * �̷��� ���� �츮�� �� ������ �Ǿ�� ���·� ��ư��� �Ѵ�.
 * 
 * �� dp[1][3] (30+30) + (30+30) + 40 �ΰ��̴�. �� ��Ȯ�� ���ٸ� (40 + 30 + 30) + 30 + 30 �̸�
 * �̴� 1��°���� 3��°������ �� �� dp[2][3]�� ���� �����̴�. 
 * �̷��� �����غ��ٸ� dp[1][1] ���� ���� ���� 0���� ġȯ�� �صδ°� �������̴�. �ܵ����� �ö��� ���������� Ī�ϴ°��̴�.
 * ���� �̸� ��ȭ������ Ǯ�ڸ� 
 * dp[1][3] = sum[3]-sum[0] + Math.min( dp[1][2], dp[2][3]);
 * dp[1][4] = sum[4]-sum[0] + Math.min( dp[1][3]+dp[4][4] , dp[1][2]+dp[3][4], dp[1][1]+dp[2][4] );
 * **/

public class p2__DP_page_sum {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test_N = Integer.parseInt(br.readLine());
		for(int z=0; z<test_N; z++) {

			int N = Integer.parseInt(br.readLine());
			int[] page = new int[N+1];					// �� �������� ũ�� �̸鼭 ���
			int[] sum = new int[N+1];
			int[][] dp = new int[N+1][N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				page[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + page[i];
			}//�Է¿Ϸ�
		
			//���̰� 2�ΰ��� �׻� �ּҰ�ġ�� �����Ǵ� �̸� ���صд�.
			for(int i=1; i<=N-1; i++) 
				dp[i][i+1] = page[i]+page[i+1];
			
			
			// ����� ������ [1][3],[2][4]...[N-2][N],[1][3],[2][4]...[N-2][N],...,[1][N-1],[2][N], [1][N]����
			// ������� ������ �ϴ� ū ������� for���� �����ذ��� �ȴ�. ������ ���ݸ�ŭ �����ϰ� 1,2,3�� ������ �ϸ� �� ������ min�� �����Ѵ�. 
			
			for(int len=2; len<=N-1; len++) {	 // [i][j]���� j-i�� ���� , �� ���� ����� ���� 
				for(int i=1; i<=N-len; i++) {    // N-len���� �ϸ� �ش� ������ ������ ���ҿ� �ش��ϴ� ù��° �ε����� �ȴ�.
					int j = i+len;
					int min = Integer.MAX_VALUE;
					for(int k=i; k<j; k++) { // �ּ� �� Ƚ����ŭ�� ���� min�� �˾ƺ���. �� len�� ����ŭ �񱳸� �غ���.
						min = Math.min( min, dp[i][k] + dp[k+1][j] );
					}
					dp[i][j] = (sum[j]-sum[i-1]) + min;
				}
			}

			System.out.println(dp[1][N]);
		}
	
	}//===========================================================
}
