package Tree_DP;
import java.util.*;
import java.io.*;
/*========================================================================================
 * 이전처럼 2개의 state에 따라 재귀적 함수를 호출하지않고 2개의 state를 통합해서 1개의 재귀함수호출로 줄인다.  
 * Arrays.fill은 시간상 정말로 미세한 차이가 있을뿐이다. 
 * 그러니 이거떄문에 시간초과날일은 없다고 본다. 리니어보다 크지는 않기에. 엄청엄청커서 조금이라도 줄일려면 모를까.
 * prev로 진행을 해도 되고 visit로 진행을 해도 시간상 차이가 별로 없다. 하는 역활은 서로 같기에 더 크기가 작은 boolean으로 해보았다.
 ==========================================================================================*/
// 2533번 - 사회망서비스(SNS)
public class p3__SNS_improve {
	static int N;
	static int[][] dp;
	static boolean[] visit;	//서브트리로서 방문을 해왔다. 다시보면 자신의 부모에게 가지 접근 못하게 해준다.
	static ArrayList< ArrayList<Integer> > childList;
	//=======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		visit = new boolean[N+1];
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
		
		DFS_subTree(1);	//1번노드로 루트로 시작한다.
		int case_X = dp[1][0];
		int case_O = dp[1][1];
		
		System.out.println(Math.min(case_X, case_O));
	}//=======================================================
	
	/*now를 서브트리의 루트로 해서 나아가는 형태이다.*/
	static void DFS_subTree(int now) {
		/*now를 루트로 하는 서브트리 방문*/
		visit[now] = true;
		dp[now][0] = 0;	//0 : 이곳을 얼리어답터로 하지않고 시작해볼 경우.
		dp[now][1] = 1;	//1 : 이곳을 얼리어답터로 하여 시작해볼 경우.
		
		ArrayList<Integer> childNode = childList.get(now);
		for(int next : childNode) {
			if(visit[next]) continue;
			
			DFS_subTree(next);	//여기서 dp[next][0]과 dp[next][1]이 갱신된다. 
			
			int next_X = dp[next][0];
			int next_O = dp[next][1];
			dp[now][0] += next_O;		//내가 얼리가 아니라서 옆 친구는 반드시 얼리친구가 있어야한다.
			dp[now][1] += Math.min(next_X, next_O);	//내가 얼리이니 친구가 얼리이든 아니든 상관없다. 더 작은 얼리를 가져온걸 가져간다.
		}
	}//=======================================================

}
