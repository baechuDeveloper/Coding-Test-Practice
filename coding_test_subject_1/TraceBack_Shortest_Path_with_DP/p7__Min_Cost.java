package TraceBack_Shortest_Path_with_DP;

import java.util.*;
import java.io.*;

// 11779번 - 최소비용 구하기 2
public class p7__Min_Cost {
	//클래스변수
	static int N, M; //도시 개수, 버스 개수		1<=n<=1000	 1<=m<=100_000
	static int start, end;
	static ArrayList<ArrayList<Integer>> list;	//간선에 대해 바로 사용하게 해줄 값
	static int[][] cost;						//따로 비용에 대해서 준비해둔 값.
	static int[] prev;							// [현재 위치] = 현재 위치로 최소로 가기 위한 바로 이전 위치. 다익스트라로 맨 처음 갱신된 값이 가장 최소로 가게 하는 위치가 된다.
	static int[] dist;							// 다익스트라를 위한 시작점에서 해당 점까지 가는 최소비용 
	static boolean[] visit;
	static int INF = 100_000_000;
	//=========================================================
	//다익스트라 알고리즘을 위한 정점
	static class Node implements Comparable<Node> {
		int city, sum_cost;
		Node(int city, int sum_cost){
			this.city = city; this.sum_cost = sum_cost;
		}
		public int compareTo(Node o) {
			return sum_cost - o.sum_cost;	//오름차순으로 비용이 낮을수록 왼쪽에 치우치게 됨
		}
	}//=========================================================
	//main함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//입력
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int i=0; i<=N; i++)
			list.add(new ArrayList<>());
		cost = new int[N+1][N+1];
		prev = new int[N+1];
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		visit = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	//시작점
			int b = Integer.parseInt(st.nextToken());	//도착점	
			int c = Integer.parseInt(st.nextToken());	//비용
			//아직 한번도 갱신이 안된거면 간선을 추가하고 비용이 낮은걸로 갱신한다.
			if(cost[a][b] == 0) {		
				list.get(a).add(b);
				cost[a][b] = c;
			}
			//더 비용이 낮은 걸로 대체한다.
			else if(c < cost[a][b]) {
				cost[a][b] = c;
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end   = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add( new Node(start, 0) );
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.city;
			int sum_cost = node.sum_cost;
			if(visit[now]) continue;
			visit[now] = true;
			
			for(int next : list.get(now)) {
				if(dist[next] > dist[now] + cost[now][next]) {
					dist[next] = dist[now] + cost[now][next];
					prev[next] = now;
					pq.add( new Node(next, sum_cost+cost[now][next]) );
				}
			}
		}
		/* 이렇게하면 한번 방문할때까지 계속 집어넣는 현상이 나오는데 다익스트라로 하면 원래 도중에 끊길수 있어서 도움이 되는 것이다.
		  while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.city;
			int sum_cost = node.sum_cost;
			
			if(prev[now]!=0) continue;
			prev[now] = node.prev_city;
			
			for(int next : list.get(now)) {
				if(prev[next]==0 ) {
					pq.add( new Node(next, sum_cost+cost[now][next], now) );
				}
			}
		}*/
		
		Deque<Integer> stack = new ArrayDeque<>();
		int trace = end;
		int count = 0;
		int sum	= 0;
		while(trace != 0) {
			stack.push(trace);
			sum += cost[prev[trace]][trace];
			trace = prev[trace];
			count++;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(sum+"\n");
		sb.append(count+"\n");
		while(!stack.isEmpty())
			sb.append(stack.pop()+" ");
		bw.write(sb.toString());
		bw.flush();
	}//=========================================================

}
