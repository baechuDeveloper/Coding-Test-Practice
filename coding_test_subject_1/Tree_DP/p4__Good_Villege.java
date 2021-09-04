package Tree_DP;

import java.util.*;
import java.io.*;
/** ================================================================
 * '트리의 독립집합'과 같은 문제
 * 대신 재귀함수 호출을 포함한다,안한다로 2번을 해야하는 경우를 
 * 한 번의 재귀함수 호출로 양쪽의 경우를 처리 할수있게 해주었다. 
 * 이렇게 한 경우가 DP적인 의미에서 더 맨 아래부터 차근차근 쌓아올려지고  [1]과 [2]를 따로 호출하지않아서
 * DP적으로 한번 갱신하고 끝낸것을 더 한눈에 보이게 해준다. 
 * 즉 자기보다 위의 상황을 필요없이 서브트리만 집중하여 끝낸다.(이점이 문제를 작게 나눈 경우) \
 * 그 위의 노드는 또 자기만의 서브트리만 신경쓰면 되며 
 * 해당 밑의 2개의 결과(포함한다/안한다)중 더 나은 것을 자신의 상황에 비교해서 더 나은걸 갱신해주는 DP적인 측면이 더욱 살아난다.
 * 그리고 계속 2개의 결과를 위로 보내고 우리는 그 2개를 통해 또 갱신을 이어간다. DP의 정석으로 보게된다.
 ===================================================================*/

// 1949번 - 우수 마을
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
		//마을입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			vile[i] = Integer.parseInt(st.nextToken());
		//간선입력
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
		int case_X = dp[1][0];	//1인 노드가 우수마을이 아닐때 시작한 경우
		int case_O = dp[1][1];	//1인 노드가 우수마을로 할때 시작한 경우
		
		System.out.println(Math.max(case_X, case_O));
	}//==========================================================
	
	/*now를 서브트리의 루트로 해서 나아가는 형태이다.*/
	static void DFS_SubTree(int now) {
		/*now를 루트로 하는 서브트리 방문*/
		visit[now] = true;
		dp[now][0] = 0;			//이곳을 우수마을로 생각 안한 경우
		dp[now][1] = vile[now]; //이곳을 우수마을로 생각 한 경우
		
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
