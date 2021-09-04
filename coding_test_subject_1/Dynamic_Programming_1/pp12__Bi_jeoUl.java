package Dynamic_Programming_1;

import java.util.*;
import java.io.*;

// 2629�� - �������� 
public class pp12__Bi_jeoUl {

	static int chu_N, ball_N;	//���� ����, ���� ����
	static int[] chu_W, ball_W;	//���� ����, ���� ����
	static boolean[][] dp;		//[�� ó������ �ش� �߱��� ��������� �ǹ�][�׋��� �����ߴ� ���� ����� ��]�� �����ϴ��� �ƴ���.
	//=================================================
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//dp��ü ����
		dp = new boolean[31][15001];
		
		//�� �Է�
		chu_N = Integer.parseInt(br.readLine());
		chu_W = new int[chu_N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=chu_N; i++) chu_W[i] = Integer.parseInt(st.nextToken());
		
		//���� �Է�
		ball_N = Integer.parseInt(br.readLine());
		ball_W = new int[ball_N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=ball_N; i++) ball_W[i] = Integer.parseInt(st.nextToken());
		
		//��� ���� Ȯ�� 
		find_dp(0, 0);
		
		//dp[chu_N][ball_W[i]]�� chu_N�� ���� ��� ����� ������ �ش� ball_W[i]�� �����ϴ��� �ľ��ϸ� �ȴ�.
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=ball_N; i++) {
			if(ball_W[i] > 15000) sb.append("N ");
			else if(dp[chu_N][ball_W[i]]) sb.append("Y ");
			else sb.append("N ");
		}
		
		System.out.println(sb.toString());
	}//=================================================
	
	//idx�� ���� ����غ� ���� ��ȣ�� ���ϸ�, weight�� ���� ������ ���̸� ���ϰ� �ȴ�. ���ʰ� ������ ������ �߰� �ö󰡴� ǥ���� �̷��� ��Ÿ�� �� �ִµ�, 
	//������ �����̵� �������̵� ������� �ϴ� �����̶�� �ΰڴ�. ���ʿ��� �ش� �߿� ���� +�� �ϸ� ���ʿ� ���ƺ���. �ƹ��͵� ���ϸ� ���ʿ� �ƹ��͵� �� �ø���. -�� �ϸ� �����ʿ� �÷��д�. �̷� ǥ���� �ȴ�.
	//������ ���� -�� �صξ ���� ���̸� ���� �����̴� ���� ������ ���־�� �Ѵ�. 
	//�̷��� ����, �ƹ��͵�, �������� ��ͷ� ��� �����ϸ� ���ʿ��� ������ ���� �����ʿ� ���� �ͱ��� �� ���̻��� ��� ������ �����ϰ� ������ �Ǹ�, �ߺ��� ����� ��� dp�� ������ �ؼ� ��귮�� �����̳��� �پ���. 
	//��� 3^30��ŭ�� ����� �ȳ��ð��̴�. �ߺ������ ���ϱ⵵ �ϰ�, ���밪�� ǥ���ϱ⵵ �ϰ�, boolean�� ���� ǥ���̴� ����� ���� 30 * 15000�� ����� ��� �������� �����̶�� ����.
	static void find_dp (int idx, int weight) {
		//�̹� ���غ��Ҵٸ� �ٷ� �Ѿ��.
		if(dp[idx][weight]) return;
		
		//�ش� ���� �������� ����� weight(����)�� ����
		dp[idx][weight] = true;
		
		//������ �߱��� �۾��� ����ģ�Ÿ� �� �� �۾��� ����.
		if(idx == chu_N) return;
		
		find_dp(idx+1, weight + chu_W[idx+1]);			 //���� ���￡ ���� ���ڴ�.
		find_dp(idx+1, weight);							 //�� �߸� ��𿡵� ���� �ʰڴ�.
		find_dp(idx+1, Math.abs(weight - chu_W[idx+1])); //������ ���￡ ���� ���ڴ�.
	}//=================================================
}
