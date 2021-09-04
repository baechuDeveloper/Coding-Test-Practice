package Tree_DP;
import java.util.*;
import java.io.*;

// 2533�� - ��ȸ������(SNS) 2568ms
public class p3__SNS {
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
		
		int case_X = solve(-1, 1, 0);	//1����带 �󸮷� �����ؼ� �湮�Ѵ�.
		int case_O = solve(-1, 1, 1);	//1����带 �󸮷� ���Ծ��ϰ� �湮�Ѵ�. 
		
		System.out.println(Math.min(case_X, case_O));
	}//=======================================================
	
	/*now�� ����Ʈ���� ��Ʈ�� �ؼ� ���ư��� �����̴�.*/
	static int solve(int prev, int now, int state) {
		if(dp[now][state]!=-1) 
			return dp[now][state];
		
		dp[now][state] = state;	//1:�̰��� �󸮾���ͷ� �غ��� �����غ���. Ȥ�� 0:�̰��� �󸮾���ͷ� �����ʰ� �����غ���.

		ArrayList<Integer> childNode = childList.get(now);
		for(int next:childNode) {
			if(next==prev)	//�ڽ��� �θ�δ� ���� ������ 
				continue;
			
			if(state==1) 
				dp[now][state] += Math.min( solve(now, next, 0), solve(now, next, 1) ); //���� �༮�� �󸮾�����̵� �ƴϵ� ����� ����. �ٸ� �� �������� ���ִ� ���� ����ȴ�. 
			else 
				dp[now][state] += solve(now, next, 1); //���� �༮�� ���� �󸮾���;������ ����ȴ�.
		}
		 
		return dp[now][state];
	}//=======================================================

}
