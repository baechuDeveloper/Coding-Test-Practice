package Tree_DP;

import java.util.*;
import java.io.*;
/** ================================================================
 * 'Ʈ���� ��������'�� ���� ����
 * ��� ����Լ� ȣ���� �����Ѵ�,���Ѵٷ� 2���� �ؾ��ϴ� ��츦 
 * �� ���� ����Լ� ȣ��� ������ ��츦 ó�� �Ҽ��ְ� ���־���. 
 * �̷��� �� ��찡 DP���� �ǹ̿��� �� �� �Ʒ����� �������� �׾ƿ÷�����  [1]�� [2]�� ���� ȣ�������ʾƼ�
 * DP������ �ѹ� �����ϰ� �������� �� �Ѵ��� ���̰� ���ش�. 
 * �� �ڱ⺸�� ���� ��Ȳ�� �ʿ���� ����Ʈ���� �����Ͽ� ������.(������ ������ �۰� ���� ���) \
 * �� ���� ���� �� �ڱ⸸�� ����Ʈ���� �Ű澲�� �Ǹ� 
 * �ش� ���� 2���� ���(�����Ѵ�/���Ѵ�)�� �� ���� ���� �ڽ��� ��Ȳ�� ���ؼ� �� ������ �������ִ� DP���� ������ ���� ��Ƴ���.
 * �׸��� ��� 2���� ����� ���� ������ �츮�� �� 2���� ���� �� ������ �̾��. DP�� �������� ���Եȴ�.
 ===================================================================*/

// 1949�� - ��� ����
public class p4__Good_Villege {
	static int N;
	static int[] vile;
	static int[][] dp;
	static boolean[] visit;
	static ArrayList< ArrayList<Integer> > childList;
	//==========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		vile = new int[N+1];
		dp = new int[N+1][2];
		visit = new boolean[N+1];
		//�����Է�
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			vile[i] = Integer.parseInt(st.nextToken());
		//�����Է�
		childList = new ArrayList<>();
		for(int i=0; i<=N; i++)
			childList.add(new ArrayList<>());
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			childList.get(a).add(b);
			childList.get(b).add(a);
		}
		
		DFS_SubTree(1);
		int case_X = dp[1][0];	//1�� ��尡 ��������� �ƴҶ� ������ ���
		int case_O = dp[1][1];	//1�� ��尡 ��������� �Ҷ� ������ ���
		
		System.out.println(Math.max(case_X, case_O));
	}//==========================================================
	
	/*now�� ����Ʈ���� ��Ʈ�� �ؼ� ���ư��� �����̴�.*/
	static void DFS_SubTree(int now) {
		/*now�� ��Ʈ�� �ϴ� ����Ʈ�� �湮*/
		visit[now] = true;
		dp[now][0] = 0;			//�̰��� ��������� ���� ���� ���
		dp[now][1] = vile[now]; //�̰��� ��������� ���� �� ���
		
		ArrayList<Integer> childNode = childList.get(now);
		for(int next : childNode) {
			if(visit[next]) continue;
			
			DFS_SubTree(next);
			int next_X = dp[next][0];
			int next_O = dp[next][1];
			
			dp[now][0] += Math.max(next_X, next_O);
			dp[now][1] += next_X;		
		}
	}//==========================================================

}
