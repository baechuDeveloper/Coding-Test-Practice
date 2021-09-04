package Graph_short_path;

import java.util.*;
import java.io.*;
// 특정한 최단 경로 : 백준 1504번
// 시간초과 아무리 800개여도 간선의 개수는 매우 많으며 역시 시간적 비용이 크다. 
/**
 * V의 개수가 최대 800개 이기에 이 문제에 대해서는 다익스트라로 반드시 접근을 하지 않아도 될듯하다. 동시에 해당 문제에 접근 하려면 DFS를 활용한 풀이를 해야할것이다.
 * 기존 다익스트라 같은 형태와 달리 이 문제는 1번 정점에서 N번 정점까지 최단경로중 V1과 V2를 포함하는 경로의 길이를 제시하는 형태이다.
 * **/
public class p2__shortest_path_but_timeover {
	// (2 ≤ V ≤ 800, 0 ≤ E ≤ 200,000) 
	private static int V, E;			//정점개수, 간선개수 
	private static ArrayList< ArrayList<Node> > list;  //간선이 저장되어있는 형태
	private static boolean[] visit;
	private static int v1, v2; //지나야하는 정점 v1, v2, 
	private static int min = Integer.MAX_VALUE;
	//==========================================================
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

	public static void DFS(int now, int dist) {
		if(now == V) {	//마지막 정점 V에 해당하는가 
			if(visit[v1] && visit[v2] && min>dist) {
				min = dist;
				return;
			}
		}
		
		for(Node next : list.get(now)) {
			if(visit[next.index] == false) {
				visit[next.index] = true;
				DFS(next.index, dist + next.weight);
				visit[next.index] = false;
			}
		}
	}//==========================================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		/**input**/
		V = Integer.parseInt(st.nextToken());	//정점의 개수
		E = Integer.parseInt(st.nextToken());	//간선의 개수
	
		list = new ArrayList< ArrayList<Node> > ();
		visit = new boolean[V+1];
		for(int i=1; i<=V; i++)
			list.add( new ArrayList<Node>() ); 
		
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());	
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add( new Node(end, weight) );	// 즉 해당 리스트 배열이 표현하는건 리스트 인덱스가 시작점, 그 배열에 들어있는 원소가 정점이 되어서 표현된다. 
			// 다만 같은 U와  V를 잇는 다른 간선이라도 이후에 우선순위 큐에 들어가서 더 작은 가중치부터 처리되고 out되어서 중복처리가 진행된다.		
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		// 반드시 지나쳐야 하는 정점 두개.
		/**-----**/
	
		DFS(1, 0);

		bw.write(min+"");
		bw.flush();

	}//==========================================================
}
