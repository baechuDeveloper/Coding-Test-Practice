package Tree_DP;
import java.util.*;
import java.io.*;

/**
 * �� ������ ���� ���� Ʈ������ ���� �� �ϳ��� �󸮾���͸� ������ �ǵ��� �Ϸ��� � Ȥ�� ��� �׷��� ����� �ɱ�.
 * ���� ���غ�
 * */

// 2533�� - ��ȸ������(SNS)
public class MySNS {
	static int N;
	static int[][] dp;
	static ArrayList< ArrayList<Integer> > childList;
	//=======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		for(int i=0; i<=N; i++)
			Arrays.fill(dp[i], -1);
		childList = new ArrayList<>();
		for(int i=0; i<=N; i++) 
			childList.add(new ArrayList<>());
	
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			childList.get(a).add(b);
			childList.get(b).add(a);
		}
		
		//int case_X = solve(-1, 1, 0, -1);	//1����带 �󸮷� ���Ծ��ϰ� �湮�Ѵ�. 
		//int case_O = solve(-1, 1, 1, -1);	//1����带 �󸮷� �����ϰ� �湮�Ѵ�. 
		
	}//=======================================================
	
	/*now�� ����Ʈ���� ��Ʈ�� �ؼ� ���ư��� �����̴�.*/
	static int solve(int prev, int now, int state, int prev_state) {
		
		if(dp[now][state]!=-1) 
			return dp[now][state];
		
		if(state==1)
			dp[now][state] = 1;	//�̰��� �󸮾���ͷ� �غ��� �����غ���.
		else
			dp[now][state] = 0;	//�ư��� �󸮾���ͷ� �����ʰ� �����غ���.
		
		int count = 0;
		ArrayList<Integer> childNode = childList.get(now);
		for(int next:childNode) {
			if(next==prev)	//�ڽ��� �θ�δ� ���� ������ 
				continue;
			
			if(state==1) 
				dp[now][state] += solve(now, prev, 0, state);	//���� �༮�� �󸮾���Ͱ� �ɼ�����. 
			else 
				dp[now][state] += Math.min(solve(now, prev, 0, state), 
						solve(now, prev, 1, state)); //���� �༮�� �󸮾���ͷ� �ϴ°� ������ �ƴ��� ����.
			count++;
		}
		 
		if(count==0 && prev_state==0)
				dp[now][state] = 1;
		
		return dp[now][state];
	}//=======================================================

}
