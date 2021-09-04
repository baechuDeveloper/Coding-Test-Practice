package Graph_short_path;
import java.util.*;
import java.io.*;
// 10217번 - KCM Travel
/**==============================================================================
 * 가중치가 1이 아니므로 DFS, BFS가 아닌 다익스트라, 벨만 포드, 플로이드 와샬을 사용해야 한다.
 * 한 노드 -> 노드 이므로 다익스트라, 벨만포드를 사용해야 한다.
 * 음의 가중치는 없기 때문에 다익스트라를 사용해야한다.
 * 
 * 결국 다익스트라를 사용해야 하는데 이 문제는 다익스트라 + dp를 이용해서 풀이한다. 
 * 이때 dp[i][j] = k가 있다면, i노드까지 j비용으로 갈 수 있는 최소의 시간 = k라고 생각하고 풀이에 들어간다. 
 * 즉 1차원적인 해당 도시에 가는 최단시간에서
 * 2차원적인 생각에서 해당도시로 해당비용으로 갈때 최단시간이라는 정보가 됩니다. 
 * 다익스트라는 DP문제라고 볼수있다. 최단경로는 여러개의 최단거리로 나타낼 수 있기 때문이다. 
 * 하나의 최단거리를 구할 때, 그 이전까지 구했던 최단 거리 정보를 그대로 사용한다.
 ===============================================================================**/
// 1번은 인천도시, N번은 LA 
// 인천에서 LA로 가는 가장 짧은 소요시간을 구해주세요.
// 다익스트라를 사용하면서 DP를 활용한다. 이 DP는 2차원 배열로 [도시번호][현재 돈] 기존 다익스트라에서 1차원으로 활용한 dist배열을 2차원으로 둔다.

public class p6__KCM_travel  {
		
	static ArrayList< ArrayList<Node> > list;
	static int[][] dp;		//[i][j] - i번 노드까지 가는데 j비용으로 갔을 때의 최소 시간.
	static int INF = 100_000;
	//========================================================
	static class Node implements Comparable<Node>{	//다익스트라를 위한 노드
		int end, cost, time;
		Node(int e, int c, int t){
			end = e; cost = c; time = t;
		}
		public int compareTo(Node o) {
			if(this.time == o.time) 
				return cost - o.cost;	//같은 시간이면 비용이 작을수록 오름차순의 왼쪽
			return this.time - o.time;	//시간이 짧을수록 오름차순의 왼쪽 
		}
	}//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//공항의 수
			int M = Integer.parseInt(st.nextToken());	//총 지원비용
			int K = Integer.parseInt(st.nextToken());	//티켓정보의 수
			list = new ArrayList<>();
			dp = new int[N+1][M+1];
			for(int i=0; i<=N; i++) {
				list.add(new ArrayList<Node>());
				Arrays.fill(dp[i], INF);
			}
			//티켓정보 입력
			for(int z=0; z<K; z++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());	//시작공항
				int v = Integer.parseInt(st.nextToken());	//도착공항
				int c = Integer.parseInt(st.nextToken());	//비용
				int d = Integer.parseInt(st.nextToken());	//소요시간
				list.get(u).add(new Node(v, c, d));
			}
			
			//다익스트라 알고리즘 시작
			int minTime = INF;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(1, 0, 0));
			dp[1][0] = 0;	//1번 노드까지 가는데 0 비용으로 갔을 때의 최소 시간. 처름은 이렇게 이곳에 도착해서 시작한 것으로.

			//pq에 경로에 흘러간 정보가 업데이트 되어간다. 즉 이곳에만 cost와 time이 누적되어 온다. 			
			while(!pq.isEmpty()) {	
				Node now = pq.poll(); //방문
				int now_city = now.end;
				int now_cost = now.cost;
				int now_time = now.time;
				
				if(now_city == N) { minTime = now_time; break; } //LA도시에 도착을 했다면 종료. 우선순위큐로 작업하여 N까지 도달 한것은 첫번쨰 가장 최단 시간임을 말하며, 두번쨰로 방문을 할수있다는 사실에 입각해 비용도 가장 낮은것을 먼저 방문해주기에 맞다.
				if(dp[now_city][now_cost] < now_time) continue;	 //이곳에 방문했던 적이 있어서 그때 왔을때 시간보다 지금 시간이 더 크다면 이미 예전이 더 좋은거라서 다음 도시. 
				dp[now_city][now_cost] = now_time;	//방문을 했다면 이 도시를 방문한 시간으로 갱신해준다.
				
				for(Node next : list.get(now_city)) {	//앞으로 방문 할만한 도시를 살펴봄
					int next_city = next.end;				//다음에 갈 도시
					int next_cost = now_cost + next.cost;	//다음에 이 도시를 간다면 드는 비용
					int next_time = now_time+ next.time;	//다음에 이 도시를 간다면 드는 시간
					
					if(next_cost > M) continue;	//다음에 갈 도시의 비용이 전체 비용보다 크면 넘어가자.
					
					if(dp[next_city][next_cost] > next_time) {	//다음에 이 도시를 같은비용으로 갈때 원래 시간보다 단축이 된다면.
						
						//진짜 이부분은 내가 생각을 못했는데 인지해두면 좋을 수도 있다.
						//불필요한 push를 막기위해서. 근데 Great_method에 더 좋은 방법이 있다. 
						for(int p_c = next_cost; p_c<=M; p_c++) {
							if(dp[next_city][p_c] > next_time)  //해당도시로 오는데 자기보다 높은 비용들중에서 구해본 혹은 구해볼 최단시간이 자기보다 커서 방문하게 될때 정보로 인식해주게한다. 
								dp[next_city][p_c] = next_time;	//"속보 속보 지금 비용보다 높은 사람들중 나보다 안좋은 시간이면 우선 제가 왔음을! 알립니다. 다음에 저보다 높은 비용님께서 방문하시면 시간이 안좋다면 더 많은 작업이 필요없이 당신은 빠지세용~""																							
						}
						pq.add(new Node(next_city, next_cost, next_time)); //방문 할 도시중 하나로 넣어준다.
					}
				}
			}
			//출력
			if(minTime==INF) bw.write("Poor KCM\n");
			else bw.write(minTime+"\n");
		}//반복문 종료
		
		bw.flush();
	}//========================================================
}