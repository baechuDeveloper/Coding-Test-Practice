package Toplogical_Sort;
import java.io.*;
import java.util.*;
/**
 * 전형적인 정해진 순서대로 무언가를 진행할 때 쓰이기 좋은 위상행렬
 * DP(어쩌면 그저 메모이제이션)를 이용하는데 DP의 값을 확정 짓는 것은 indegree가 0인것이 큐에 빠져나오고 더이상 해당 값으로 등어오는 경우가 없으므로
 * 그 때의 dp값이 정해진다. 
 * 우리는 이렇게 값을 저장하고 다음 건물로 갈때 이 dp값의 현재 건물 비용을 비교하여 가장 작은 것으로 갱신해주면 된다.  
 * */
// 1005번 - ACM Craft
public class p3__ACM_Craft {
	
	static int N, K, W;
	static int[] devtime;
	static int[] indegree;
	static ArrayList< ArrayList<Integer> > edge;
	static Queue<Integer> q;
	static int[] dp;			//의미가 중요한데 건물들을 동시에 지을 수 있을때, 진행되는 시간에 맞추어 i건물을 완성하는데 걸리는 시간이다. 
								//즉, 초기부터 자신의 건물이 될때까지 '진행되고있는 시간'이 끝나는 시기(시점)를 말한다.
	//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//건물의 개수, 건물의 번호는 1번부터
			K = Integer.parseInt(st.nextToken());	//건물간의 건설순서규칙 개수
			devtime = new int[N+1];
			
			//건물 건설의 걸리는 시간을 넣어준다.
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) 
				devtime[i] = Integer.parseInt(st.nextToken());
			
			//건물순서 간선을 넣어준다.
			indegree = new int[N+1];
			edge = new ArrayList<>();
			for(int i=0; i<=N; i++)
				edge.add(new ArrayList<>());
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edge.get(from).add(to);
				indegree[to]++;
			}
			
			//건설을 완료해야할 건물 번호
			W = Integer.parseInt(br.readLine());
			dp = new int[N+1];
			
			/*위상정렬 알고리즘*/
			q = new LinkedList<>();
			for(int i=1; i<=N; i++) {
				if(indegree[i]==0) {
					dp[i] = devtime[i];	//처음에 들어오는건 자기 자신만 가져도 된다. 
					q.add(i);
				}
			}
			
			for(int cnt=1; cnt<=N; cnt++) {
				if(q.isEmpty()) 
					return;	//사실 모든 건물이 건설 가능하도록 주어지므로 이 조건문에 들어갈 일은 없다.
				
				int now = q.poll();
				
				if(now == W) {	//indegree가 0인 된 상태로 들어왔다는 것은 더이상 dp[W]를 갱신할 경우가 없음을 알수있다. 
					bw.write(dp[W]+"\n");
					break;
				}
				for(int next : edge.get(now)) {
					indegree[next]--;
					
					int temp = dp[now] + devtime[next];
					if(dp[next]<temp)
						dp[next] = temp;	//최소의 시간을 구하라는데 왜 더 큰 걸 넣어야 하느냐면! 건물을 동시에 지을수 있어서다. dp[next]를 갱신하는 것들은 모두 next를 가기위해서 반드시 거쳐야하는 건물들이 된다. 
											//따라서 모든 건물들을 다 커버해야하는데 이때 현재 진행하는 시간에 맞춰 동시에 건물들도 진행이 되는 상황이다.
											//이때 현재 시간에서 dp[next]를 갱신하는 것들 중 가장 시간이 많은 것을 고른다면 이것보다 시간이 적은 것들을 모두 포함해서 자기 건물까지 완성시키는 시간비용이 완성된다.  
											//그렇게하여 초기부터 '진행되고 있는 시간이 끝나는 시점'을 넣어야 하고  그러려면 이렇게 가장 오래 걸리는 경우를 한다면 그 건물을 완성하는데 '진행하는 시간이 끝나는 시점'이 된다.
					if(indegree[next]==0) 
						q.add(next);		//모든 재료가 준비 되었다면... 이 건물을 짓기 위한 비용을 첨가해서 최종 비용을 완성시킨다.					
				}
				
			}/*---위상정렬 알고리즘 종료---*/
		}
		bw.flush();

	}//========================================================

}
