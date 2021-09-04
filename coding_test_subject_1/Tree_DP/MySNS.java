package Tree_DP;
import java.util.*;
import java.io.*;

/**
 * 내 스스로 내본 문제 트리에서 옆에 단 하나의 얼리어답터만 있으면 되도록 하려면 몇개 혹은 어떤걸 그렇게 만들면 될까.
 * 아직 안해봄
 * */

// 2533번 - 사회망서비스(SNS)
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
		
		//int case_X = solve(-1, 1, 0, -1);	//1번노드를 얼리로 포함안하고 방문한다. 
		//int case_O = solve(-1, 1, 1, -1);	//1번노드를 얼리로 포함하고 방문한다. 
		
	}//=======================================================
	
	/*now를 서브트리의 루트로 해서 나아가는 형태이다.*/
	static int solve(int prev, int now, int state, int prev_state) {
		
		if(dp[now][state]!=-1) 
			return dp[now][state];
		
		if(state==1)
			dp[now][state] = 1;	//이곳을 얼리어답터로 해보고 시작해보자.
		else
			dp[now][state] = 0;	//아곳을 얼리어답터로 하지않고 시작해보자.
		
		int count = 0;
		ArrayList<Integer> childNode = childList.get(now);
		for(int next:childNode) {
			if(next==prev)	//자신의 부모로는 진행 안하지 
				continue;
			
			if(state==1) 
				dp[now][state] += solve(now, prev, 0, state);	//다음 녀석은 얼리어답터가 될수없다. 
			else 
				dp[now][state] += Math.min(solve(now, prev, 0, state), 
						solve(now, prev, 1, state)); //다음 녀석이 얼리어답터로 하는게 좋을지 아닐지 보자.
			count++;
		}
		 
		if(count==0 && prev_state==0)
				dp[now][state] = 1;
		
		return dp[now][state];
	}//=======================================================

}
