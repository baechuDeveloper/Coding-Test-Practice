package Bitmask_DP;

import java.util.*;
import java.io.*;

public class p4__bitmask_DP {

	static int INF = 200_001;
	static int N, complete;
	static int[][] work;
	static int[][] dp;
	//=================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		complete = (1<<N)-1;
		work = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) 
				work[i][j] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N+1][(1<<N)-1];
		for(int i=1; i<=N; i++)
			Arrays.fill(dp[i], INF);
		
		System.out.println(tsp(1, 0));	//��ȭ�� ���ؿ��� work[now][next]�� ���� now�� ���� ���� �ؾ��ϸ�, ���� 1�� ������ ������ �־ �ܼ��� 1���� �����ϰ� �ߴ�.	
										//��� visit�� 0�� ������... visit�� i j �߿��� j�� �ش��ϴ� visit�� �˷��ִµ� ���� �������� ���� ���⿡ � �ϵ� ó�� �ȵ� 0���� �����ؼ� ������ �˾ư��� ���̴�.
										//visit�� ���ؼ� ���� ���� ó���ϰ� �ִ� ���� �������� ��ũ�� �Ǿ��. 
										//�ش� tsp�� ������ ���� '�������' �ش� �����ȣ�� �����ϰ�, �ʰ� �� ���� �þƺ��� ��� ��� ������ ¥������ ���̴�. 
										// ���� tsp������ �ܼ��� �湮�� �ߴٴ� ������ ���������� ���ư��ٸ� �����ָ� �Ǵµ� �̶� visit�� �̸� ¥�ΰ� �����ϴ� ���·� �Ͽ���. 
	}//=================================================

	static int tsp(int now, int visit) {	//node�� ���� �湮�� ���� , visit�� ������ �湮�� tsp�� ��ͷ� ȣ�� �Ҽ��� �湮�� ���ð� ���������̴�. 
		//���� ��� ������ �湮 �� ���
		if(visit == complete) {	//�湮���� ��� ���ð� �������� 1111 1111 1111 1111 �̷��� 
			return 0;
		}

		//���� �湮�� ���� �̹� �湮�ؼ� ����� ���� ���
		if(dp[now][visit] != INF) 
			return dp[now][visit];	//�̹� �湮�ؼ� ����� ���� ����� �������ش�.

		//���������� �湮�� �Ǿ��ٸ� ���� �湮���� ��󺻴�. 
		for(int next=1; next<=N; next++) {
			if((visit & (1<<(next-1))) == 0) { // ���� �湮�� ���� �ʾҴٸ� ������ �Ѵ�. 
				
				int next_visit = visit | (1<<(next-1));	//�ش� ��ȣ�� ���ø� �湮 �ߴٰ� ��Ʈ����ũ�� �߰����ش�. 
				
				dp[now][visit] = Math.min(dp[now][visit], work[now][next] + tsp(now+1, next_visit));	//���� tsp�� �ٸ��� now+1�� �ش� ������ ������ �þ�� �˷��ش�. 
			}
		} //�� ó���� ȣ���� (0,1)�� �ᱹ ��� return�� ��ġ�� �� ��� ����� ���� �� ���� �ּҸ� �������� �� ���̴�.

		//�ּ��� ����� ���� �ڽ��� �湮 ����� ȣ�����ذ��� �����ش�.
		return dp[now][visit];
	}//=================================================

}
