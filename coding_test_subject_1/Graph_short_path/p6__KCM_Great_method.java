package Graph_short_path;
import java.util.*;
import java.io.*;
// 10217번 - KCM Travel
/**==============================================================================
 * 이전 방법과 동일한데 방문 횟수를 줄이는 한가지를 바꾸었다. visit[i]는 i도시를 방문했을 때, 나온 비용이다.  
 * 이 방법으로 8316ms에서 1592ms로 매우 크게 줄어들었다. 
 * 물론 정답은 100,000ms까지 허용해주긴해도, 우선순위큐에서 Node가 정렬되는 걸 생각하여 더 좋은 조건문이 나왔다.
 * 그리고 이전 코드처럼 자기보다 높은 비용중 자기보다 최단시간이 안좋은걸 일일이 대입해주지 않아도 되어서 시간이 줄어드는 효과가 생겨진다. 
 * 즉, 일일이 대입해주는 것 '대신에' 그 비슷한 효과를 이런 아래 조건을 바꾸는 것으로 더욱 효율이 좋았다. 
 ===============================================================================**/
// 1번은 인천도시, N번은 LA 
// 인천에서 LA로 가는 가장 짧은 소요시간을 구해주세요.
// 다익스트라를 사용하면서 DP를 활용한다. 이 DP는 2차원 배열로 [도시번호][현재 돈] 기존 다익스트라에서 1차원으로 활용한 dist배열을 2차원으로 둔다.

public class p6__KCM_Great_method  {
	
	static int[] visit;
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
			visit = new int[N+1];
			Arrays.fill(visit, INF);
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
				if(visit[now_city] < now_cost) continue;	//해당 도시로 방문해온것이 있는데, 지금 방문한 비용이 훨씬 크다면 다음 방문을 본다.
				visit[now_city] = now_cost; 	
				/**------------------------------------------------------------------------------------------
				 * 이걸 생각해볼것이 우선순위큐에서 어떤 한 도시를 처음으로 방문한 것이라면 그 도시로 가는 최단 시간을 기록했다는 것을 의미한다.
				 * 그 이후로 우선순위큐에서 해당 도시를 방문하는 것들은 시간을 기준으로 볼때 점점 안좋은 시간순으로 들어오게 된다. 
				 * 이 점이 굉장히 중요하다 우선순위 큐에서 나오는 순서는 시간이 가장 짧은 순으로 나오고 같은 도시에 대해서도 들어있는 큐에서 짧은대로 먼저 나오게된다.
				 * 그러므로 이점을 살려서 어차피 이미 방문한 도시였다면 내 이전의 방문했던 시간은 나보다 훨씬 좋게 방문했을 것이다. 그러니 내가 이곳에 방문이 되려면 적어도 비용적인 면에서 더 우수한 면이 있어야 함을 알수있다.
				 * 다시말해 다시 이 도시를 방문을 했다는 것은 이미 이전 방문으로 문제가 있다(혹은 그 이전의 경로부터 문제가 있다)라는 말이 되어, 시간이 줄어들지 않고 같거나 높은 것은 감수하고 다시 이곳을 방문하게 해줄 방안이다.
				 * 비용마저 더 높다면 더 이상 진행을 하지 않아도 이전보다 더 좋을수가 없으니 다음 큐로 넘어간다. 이렇게 하면 내가 처음에 작성한 코드보다 더 빨리 체크가 되고, 각 더 높은 비용을 대입해주지 않아도 되어서 시간이 엄청 줄어들었다. 
				
				 * 참고로 조건이 있다고 전부 그리디한게 아니다. 이건 더 좋을수가 없으니 미리 차단한거지 눈앞에 보이는 이득만 쫒는 격이 아니다. 
				 * 다만, 다익스트라는 일종의 그리디 알고리즘 측면이 있고 최소를 찾아주는 보장이 되어가는 형태로 잡혀진다. 
				 * 그리디라는 개념에 사로잡히지 않아도 된다. 무슨 말이냐면 DP든 그리디이든 뭐든지 그 형태가 고정되어있는게 아니다. 
				 * 유연하게 그 개념을 받아드려야한다 마치 그리디한 측면이 있구나 최소의 코스트와 시간부터 보도록 했으니깐.
				 * DP의 개념을 이용해 보는구나 방문했던 도시를 통해서 다음 도시의 내용을 더해가거나 이용해가는구나. 
				 * 이렇게 이러한 개념이 들어있는 구성을 가지면 좋겠다. 그리디이든 DP는 정해진 법칙이 아닌 개념적인 내용이며 접근의 시야를 정해볼수있는 도움을 주는 방법이다.
				 * 서로가 혼용이 될수도있고, 내가 생각하기에 문제에 따라 더 큰 개념이랑 작은 개념일수있는거다. 무조건 이건 DP만 있어야 한다가 아니며 
				 * DP에 그리디한 조건으로 접근해서 좀더 시간을 단축해보고 이 점이 사실 최소를 보장해주니 좋겠구나 라고 문제를 접근하면 가장 베스트한 사고방식이며 최고의 생각이다.
				 -------------------------------------------------------------------------------------------**/
				/**------------------------------------------------------------------------------------------
				 * 아래 이 조건문도 하지 않아도 되는게 이미 위에 조건문이 더 상위호환이다. 똑같은 코스트로도 방문한다면 위에서 걸러진다. 
				 * 또 만약에 위에 조건이 없다면, 아래 조건에서는 걸러지지 않는다. 같은 도시라도 다른(높거나 작은) 비용으로 들어왔을테니 계속 진행이 되어 더 많은 작업이 된다. 
				 * if(dp[now_city][now_cost] < now_time) continue;	 //이곳에 방문했던 적이 있어서 그때 왔을때 시간보다 지금 시간이 더 크다면 이미 예전이 더 좋은거라서 다음 도시. 
				 -------------------------------------------------------------------------------------------**/
				/**------------------------------------------------------------------------------------------
				 * 그리고 방문을 할때 갱신을 하지않아도 된다. 만약 아래 for문안에서 갱신을 해주지 않는다면 메모리 초과가 난다. 사실 다익스트라 구조에서도 저기에서 갱신을 한다. 
				 * 아 만약에 중복된 도시와 비용 티켓(시간만 다른)이 있는데, 저 안에 조건문으로 들어올때, 갱신을 안했다면 같은 도시와 같은 비용에 대해 최단 시간을 줄수있는 티켓만 들어가는게 아닌  모든 티켓이 들어가기에 더 많은 작업과 공간이 필요해지네.
				 * 따라서 저 안에서 갱신을 '반드시' 해주어야 한다. 다익스트라 구조에서도 저기에서 갱신을 하는게 당연하면 당연했지만 정말 중요했다.
				 * dp[now_city][now_cost] = now_time;	//방문을 했다면 이 도시를 방문한 시간으로 갱신해준다.
				-------------------------------------------------------------------------------------------**/
				for(Node next : list.get(now_city)) {	//앞으로 방문 할만한 도시를 살펴봄
					int next_city = next.end;				//다음에 갈 도시
					int next_cost = now_cost + next.cost;	//다음에 이 도시를 간다면 드는 비용
					int next_time = now_time+ next.time;	//다음에 이 도시를 간다면 드는 시간
					
					if(next_cost > M) continue;	//다음에 갈 도시의 비용이 전체 비용보다 크면 넘어가자.
					
					if(dp[next_city][next_cost] > next_time) {	//다음에 이 도시를 같은비용으로 갈때 원래 시간보다 단축이 된다면.
						dp[next_city][next_cost] = next_time;
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