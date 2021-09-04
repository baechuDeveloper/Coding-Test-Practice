package Dynamic_Programing_2;

import java.util.*;
import java.io.*;

// �Ӹ���� - ����� ���� ���� �Ǵ� �ܾ� - ���� 10942��
// ������ �׽�Ʈ�ϸ� �翬�� ���� ���������� �ð��ʰ�.
/**-------------------------------------------------
 * ȫ���̴� �ڿ��� N���� ĥ�ǿ� ���´�. �� ����, ��쿡�� ������ �� M�� �Ѵ�.
 * �� ������ �� ���� S�� E�� ��Ÿ�� �� �ִ�. (1 �� S �� E �� N) 
 * S��° ������ E��° ���� ���� �Ӹ������ �̷������ �����, ���� �� ������ ���� �Ӹ�����̴� �Ǵ� �ƴϴٸ� ���ؾ� �Ѵ�.
 * 	1 2 1 3 1 2 1 �̶�� �� ĥ�ǿ� ����
 * S = 1, E = 3�� ��� 1 2 1 �� �Ӹ�����̴�.
 * S = 2, E = 5�� ��� 2 1 3 1�� �Ӹ������ �ƴϴ�.
 ---------------------------------------------------**/
/*
 * ���̸����� �縰������� �˷��ֵ��� DP�� �ۼ��ϵ��� �غ���. 
 * 
 * */
public class p6__Palindrome {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(); // ������ ��¹��� ������ ��.
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
		
		boolean[][] dp = new boolean[N+1][N+1];	//���� ���̿� ���� ����, ���� �� ���̿� ù��°�� �ش��ϴ� ���� ��ȣ�̴�. 
		
		//�켱 ���̰� 1�϶��� 2�϶� ��������. 
		for(int i=1; i<N; i++) {
			dp[1][i] = true;
			if(board[i]==board[i+1]) 
				dp[2][i] = true;
		}
		dp[1][N] = true;
		
		//������ ���̿� ���ؼ��� ���� �ؿ� ����͵鿡 ���� ������ �ڽ��� ù��°�� ������ ���Ұ� �������� ���ؼ� ���� �ȴ�.	
		/**-------------------------------------------
		 * ���� ��� ���̰� 3�϶� 1��°���Ҹ� �����ϴ� ���´� 1 2 1 �ϰ��̴�. 
		 * �׷��� ù��°���ҿ� ���������Ұ� �������� Ȯ���ϰ� �׾ȿ� ���̰� 3-2�� 1�� dp�� 1+1�������Ҹ� ��������.
		 * 
		 * �̰� Ǯ����ڸ� ���̰� i�϶� j��° ���Ҹ� �����ϴ� ���´�... 
		 * ù��°  j�� ������ j+(i-1)������ ������ Ȯ���ϰ� ���̰� (i-2)�� ������ j+1��° ������ dp[i-2][j+1]�� �縰����� �����ϴ� ����.
		 * �� i�� 3���� �����ϰԵȴ�. 
		 ---------------------------------------------**/
		for(int i=3; i<=N; i++) 	//���� 3����
			for(int j=1; j<=N-(i-1); j++) 	//���Ҵ� ���̿� ����  ù������ ���ð����� ������ �ٲ�� N-(i-1)��° ������
				if( board[j]==board[j+(i-1)] && dp[i-2][j+1] ) 	//ù������ ������ ���Ұ� ������ ���ϰ�, ��� ���� �渲������� ����.
					dp[i][j] = true;
		
		// answer Ǯ��
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
		
		/* Ȯ�ο� ��¹�
		for(int i=1; i<=N; i++) { for(int j=1; j<=N-(i-1); j++) { System.out.print(dp[i][j]+" "); }System.out.println(); }
		 */		
	}

}
