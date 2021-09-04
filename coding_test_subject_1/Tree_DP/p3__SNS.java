package Tree_DP;
import java.util.*;
import java.io.*;

// 2533번 - 사회망서비스(SNS) 2568ms
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
		
		int case_X = solve(-1, 1, 0);	//1번노드를 얼리로 포함해서 방문한다.
		int case_O = solve(-1, 1, 1);	//1번노드를 얼리로 포함안하고 방문한다. 
		
		System.out.println(Math.min(case_X, case_O));
	}//=======================================================
	
	/*now를 서브트리의 루트로 해서 나아가는 형태이다.*/
	static int solve(int prev, int now, int state) {
		if(dp[now][state]!=-1) 
			return dp[now][state];
		
		dp[now][state] = state;	//1:이곳을 얼리어답터로 해보고 시작해보자. 혹은 0:이곳을 얼리어답터로 하지않고 시작해보자.

		ArrayList<Integer> childNode = childList.get(now);
		for(int next:childNode) {
			if(next==prev)	//자신의 부모로는 진행 안하지 
				continue;
			
			if(state==1) 
				dp[now][state] += Math.min( solve(now, next, 0), solve(now, next, 1) ); //다음 녀석은 얼리어답터이든 아니든 상관이 없다. 다만 더 작은값을 내주는 것을 고르면된다. 
			else 
				dp[now][state] += solve(now, next, 1); //다음 녀석이 전부 얼리어답터어야지만 통과된다.
		}
		 
		return dp[now][state];
	}//=======================================================

}
