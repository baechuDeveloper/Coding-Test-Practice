package Graph_short_path;

import java.util.*;
import java.io.*;
// 최단경로 : 다익스트라 1753번 백준 
// 정점 번호는 1부터 시작한다.
// 가장 핵심은 가장 경로가 짧은 간선부터 작업을 시작하는게 좋다. 그렇다면 내 구조상에서도 순서에 따른 잘못된 dp의 값이 나올것이다.
/**
 * 참고로... 런타임에러가 발생했고 아마 메모리 초과일 것이다. 다른 분의 조언을 보자.
 * 처음에는 아무 생각 없이 인접행렬로 만들어서 풀었었는데 메모리 초과가 나왔습니다.
 * 정점의 갯수가 최대 2만개여서 인접행렬을 만든다면 최대 2억개의 int 배열을 할당하기 때문에 당연히 메모리가 터집니다.
 * 그래서 인접리스트로 만들었더니 통과하였습니다.
 * **/
public class p1__Dijkstra {
	// (1≤V≤20,000, 1≤E≤300,000) 
	private static int V, E; 				//정점개수, 간선개수
	private static ArrayList< ArrayList<Node> > list;  //간선이 저장되어있는 형태
	private static int INF = 100_000_000;   //숫자에서 _을 넣을 수 있고 이 의미는 100,000,000 같은 의미다.
	private static int[] dist;				//시작한 정점에서부터 해당 정점까지 최소 거리
	private static StringBuilder sb;
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
	public static void dijkstra(int k_start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(); //해당 간선에서 우선순위를 보여주는 큐
		boolean[] fin = new boolean[V+1];	//해당 정점은 끝남.
		dist = new int[V+1]; //K_start 정점부터 해당 번호까지 최소 거리.

		Arrays.fill(dist, INF);
		dist[k_start] = 0;
		pq.add(new Node(k_start, 0));
		
		while(!pq.isEmpty()) {
			Node now_node = pq.poll();
			int now = now_node.index;		//현재 방문한 정점
			
			if(fin[now] == true) continue;
			fin[now] = true;
			
			for(Node next : list.get(now)) {
				if(dist[next.index] > dist[now]+next.weight) {
					dist[next.index] = dist[now]+next.weight;
					pq.add(new Node(next.index, dist[next.index]));	//이렇게 변화가 일어 났던것을 모두 집어넣고, 그중 거리가 제일 짧은것부터 나오게 된다. 
					//내가 여기서 착각했던 것이... 나는 그 다음 정점 중에서 가장 짧은걸 선택하는것을 하고... 그 다음은 다시 리셋해서 새로운 정점과 그 정점이 다시 나가는 정점끼리중에서만 판단하는줄 알았다.
					// 그것이 아닌 매 순간 가장 짧은 거리를 가지는것을 계속해서 찾아서 진행를 해가는 것이었다. 
				}
			}
		}/**---------------------------------------------------------------------**/
		
		// 여기서 착각한것이 우리가 진행하고 나서 뽑혀준 간선은 모두 리셋하는것이 아니다. 매순간마다 가장 짧은것을 찾아가는것이다. 그리고 만약 방문이 끝났다면 그건 분명히 가장 짧은 형태를 끝내고 사라진것이다. 
		// 그 이유는 내가 pq에 집어 넣은것은 모두 이제 내가 갈수있는 길의 선택지를 보여주는것이다. 즉 아직 갈수 없는 길에 대해서는 pq에 아직 들어오지 않고,
		// pq에 들어온 것은 모두 현재 진행할수있는 방향성을 보여주고 우선순위 큐라서 갈수있는 길중 가장 짧은것을 제시해주니 혹시 그 길을 방문을 해서 방문이 성공했다면
		// 시작k_start에서부터 가장 짧은 경로라고 자부할수있다. 반드시 k_start부터 시작해서 만들어진 경로이기 떄문이기도 하다.
		/**while(!next_q.isEmpty()) {
			Node now = q.poll();	//방문
			//System.out.println(now.end); 현재 방문한 노드
			int start = now.end;	// 이제 그 점이 출발점이 된다.
			for(Node i : list[start]) {	//현재 정점부터 시작해서 이어지는 간선을 전부 넣어준다.   
				pq.add(i);	//우선순위 큐이기에 가중치가 작은 순서대로 들어간다. 
			}
			int min_weight = Integer.MAX_VALUE;
			int index = 0;
			while(!pq.isEmpty()) {
				Node next = pq.poll();
				if(dist[next.end] > dist[start]+next.weight) {
					dist[next.end] = dist[start]+next.weight;
					if( min_weight > dist[next.end] ) {
						min_weight = dist[next.end];
						index = next.end;
					}
				}
			}
			if(index!=0) {
				next_q.add(new Node(index, min_weight));
			}
		}**/
	}//==========================================================
	public static void print(int[] arr) {
		sb = new StringBuilder();
		for(int i=1; i<=V; i++)
			if(arr[i]==INF) 
				sb.append("INF\n");
			else 
				sb.append(arr[i]+"\n");

	}//==========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		/**input**/
		V = Integer.parseInt(st.nextToken());	//정점의 개수
		E = Integer.parseInt(st.nextToken());	//간선의 개수
		int K_start = Integer.parseInt(br.readLine()); //시작 정점 (1<= K <= V)
		
		list = new ArrayList< ArrayList<Node> >();
		for(int i=0; i<=V; i++)
			list.add( new ArrayList<Node>() );
		
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());	
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add( new Node(end, weight) ); // 즉 해당 리스트 배열이 표현하는건 리스트 인덱스가 시작점, 그 배열에 들어있는 원소가 정점이 되어서 표현된다. 
			// 다만 같은 U와  V를 잇는 다른 간선이라도 이후에 우선순위 큐에 들어가서 더 작은 가중치부터 처리되고 out되어서 중복처리가 진행된다.		
		}
		/**-----**/
		dijkstra(K_start);
		print(dist);
		//System.out.println("----");

		bw.write(sb.toString());
		bw.flush();

	}//==========================================================
}
