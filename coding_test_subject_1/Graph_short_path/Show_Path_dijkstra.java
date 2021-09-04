package Graph_short_path;
import java.util.*;
import java.io.*;

//한 지점 기준으로만 잡고있어서 시작 지점에서의 경로만 보여주게된다. 다익스트라는 
public class Show_Path_dijkstra {

		private static int V, E; 				//정점개수, 간선개수
		private static ArrayList< ArrayList<Node> > list;  //간선이 저장되어있는 형태
		private static int INF = 100_000_000;   //숫자에서 _을 넣을 수 있고 이 의미는 100,000,000 같은 의미다.
		private static int[] dist;				//시작한 정점에서부터 해당 정점까지 최소 거리
		private static StringBuilder sb;
		private static int[] path;	//경로를 위해서
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
						path[next.index] = now;	//추가한 부분
						pq.add(new Node(next.index, dist[next.index]));	
					}
				}
			}
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
			path = new int[V+1];
			/**-----**/
			dijkstra(K_start);
			print(dist);
			for(int i=1; i<=V; i++)
				pathView(i);
			bw.write(sb.toString());
			bw.flush();

		}//==========================================================
		public static void pathView(int end) {
			//시작지점은 한 곳으로 지정 되어서 풀이가 된다. 
			System.out.print(end+"는 이런 경로 ");
			Stack<Integer> stack = new Stack<>();
			if(path[end]==0) {
				System.out.print("INF");
			}
			else {
				stack.push(end);
				while(true) {
					int temp = path[end];
					if(path[temp]==0) {
						stack.push(1);
						break;
					}
					else {
						stack.push(temp);
						end = temp;
					}
				}
			}
			while(!stack.isEmpty()) {
				System.out.print(stack.pop()+" ");
			}System.out.println("\n----");
			
		}
}