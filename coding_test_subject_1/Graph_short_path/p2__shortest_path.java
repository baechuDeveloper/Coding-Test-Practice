package Graph_short_path;

import java.util.*;
import java.io.*;
// 특정한 최단 경로 : 백준 1504번
/** 
 * 한가지 간과한게 이건 방향성이 없는 그래프. 또한 이 밀이 없다. 반드시 한번만 지나쳐야한다는 말이 없다... 기본적인 다익스트라는 한번의 정점만 이동하는게 맞다.
 * 다만 응용을 하게되는 이 경우 어느 지점을 꼭 지나야한다는 내용에 한번만 지나쳐야하는 조건이 없다면 다익스트라를 
 * 각각 1->v1 , v1->v2, v2->V 로 가는 것에서 저 각 3개의 다익스트라에서는 중복이 없지만 3개를 합치면 중복인 정점이 있을 수 있게된다. 
 * 그러니 1->v1, v1->v2, v2->V 에 대해서 각 3번의 다익스트라를 통해 나온 길이값을 각각 더해간다. 
 * 이것도 1-> v2, v2->v1, v1->V 에 대해서 각 3번의 다익스트라를 통해 나온 길이값을 각각 더해가면된다. 
 * **/
public class p2__shortest_path {

	private static int V, E;			//정점개수, 간선개수   (2 ≤ V ≤ 800, 0 ≤ E ≤ 200,000)
	private static ArrayList< ArrayList<Node> > list;  //간선이 저장되어있는 형태
	private static int INF = 200_000_000;
	//==========================================================
	// 노드 자료구조
	static class Node implements Comparable<Node>{	//간선이 아닌 정점이다.
		int index, weight;
		Node(int a, int b){
			index = a;
			weight = b;
		}
		public int compareTo(Node o) {
			if(weight<o.weight) return -1;
			else return 1;		
		}
	}//==========================================================
	// 다익스트라 알고리즘 
	public static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[V+1];
		boolean[] visit = new boolean[V+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now_node = pq.poll();
			int now = now_node.index;
			
			if(visit[now] == true)
				continue;
			visit[now] = true;
			
			for(Node next : list.get(now)) {
				if(visit[next.index] == false && dist[next.index] > dist[now]+next.weight) {
					dist[next.index] = dist[now] + next.weight;
					pq.add( new Node(next.index, dist[next.index]));	
					// 여기서 내가 새로 생성하는 Node의 weight를 의미없다고 봤는데, 아니다
					// 제일 중요하다. pq에서 우선순위를 판단하는 기준이다. 
					// 그러니 0을집어넣든 그냥 now.weight같이 집어넣지 말고 정확히 지금 자신이 해당 노드까지 얻어온 dist값을 집어 넣어야한다. 
				}
			}
		}
		return dist[end];
	}//==========================================================
	// 두 점을 반드시 지나는 최단 경로
	public static int apply_dijkstra(int v1, int v2) {
		
		// 1 -> ... -> v1 -> ... -> v2 -> ... -> V
		int case_1 = 0;
		case_1 += dijkstra(1, v1);
		case_1 += dijkstra(v1, v2);
		case_1 += dijkstra(v2, V);
		
		// 1 -> ... -> v2 -> ... -> v1 -> ... -> V
		int case_2 = 0;
		case_2 += dijkstra(1, v2);
		case_2 += dijkstra(v2, v1);
		case_2 += dijkstra(v1, V);
		
		if(case_1>=INF && case_2>=INF) 
			return -1;
		else 
			return Math.min(case_1, case_2);
	}//==========================================================
	// main함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/**input**/
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	//정점의 개수
		E = Integer.parseInt(st.nextToken());	//간선의 개수
		
		list = new ArrayList< ArrayList<Node> > ();
		for(int i=0; i<=V; i++)
			list.add( new ArrayList<Node>() ); 
		
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());	
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add( new Node(end, weight) );	
			list.get(end).add( new Node(start, weight) );	//양방향
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		// 반드시 지나쳐야 하는 정점 두개.
		/**-----**/
		
		System.out.println( apply_dijkstra(v1, v2)+"" );
		
	}//==========================================================
}
