package Tree_DP;
import java.util.*;
import java.io.*;
/*========================================================================================
 * ����ó�� 2���� state�� ���� ����� �Լ��� ȣ�������ʰ� 2���� state�� �����ؼ� 1���� ����Լ�ȣ��� ���δ�.  
 * Arrays.fill�� �ð��� ������ �̼��� ���̰� �������̴�. 
 * �׷��� �̰ŋ����� �ð��ʰ������� ���ٰ� ����. ���Ͼ�� ũ���� �ʱ⿡. ��û��ûĿ�� �����̶� ���Ϸ��� �𸦱�.
 * prev�� ������ �ص� �ǰ� visit�� ������ �ص� �ð��� ���̰� ���� ����. �ϴ� ��Ȱ�� ���� ���⿡ �� ũ�Ⱑ ���� boolean���� �غ��Ҵ�.
 ==========================================================================================*/
// 2533�� - ��ȸ������(SNS)
public class p3__SNS_improve {
	static int N;
	static int[][] dp;
	static boolean[] visit;	//����Ʈ���μ� �湮�� �ؿԴ�. �ٽú��� �ڽ��� �θ𿡰� ���� ���� ���ϰ� ���ش�.
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
		
		DFS_subTree(1);	//1������ ��Ʈ�� �����Ѵ�.
		int case_X = dp[1][0];
		int case_O = dp[1][1];
		
		System.out.println(Math.min(case_X, case_O));
	}//=======================================================
	
	/*now�� ����Ʈ���� ��Ʈ�� �ؼ� ���ư��� �����̴�.*/
	static void DFS_subTree(int now) {
		/*now�� ��Ʈ�� �ϴ� ����Ʈ�� �湮*/
		visit[now] = true;
		dp[now][0] = 0;	//0 : �̰��� �󸮾���ͷ� �����ʰ� �����غ� ���.
		dp[now][1] = 1;	//1 : �̰��� �󸮾���ͷ� �Ͽ� �����غ� ���.
		
		ArrayList<Integer> childNode = childList.get(now);
		for(int next : childNode) {
			if(visit[next]) continue;
			
			DFS_subTree(next);	//���⼭ dp[next][0]�� dp[next][1]�� ���ŵȴ�. 
			
			int next_X = dp[next][0];
			int next_O = dp[next][1];
			dp[now][0] += next_O;		//���� �󸮰� �ƴ϶� �� ģ���� �ݵ�� ��ģ���� �־���Ѵ�.
			dp[now][1] += Math.min(next_X, next_O);	//���� ���̴� ģ���� ���̵� �ƴϵ� �������. �� ���� �󸮸� �����°� ��������.
		}
	}//=======================================================

}
