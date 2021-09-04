package Graph_short_path;
import java.util.*;
import java.io.*;

//�� ���� �������θ� ����־ ���� ���������� ��θ� �����ְԵȴ�. ���ͽ�Ʈ��� 
public class Show_Path_dijkstra {

		private static int V, E; 				//��������, ��������
		private static ArrayList< ArrayList<Node> > list;  //������ ����Ǿ��ִ� ����
		private static int INF = 100_000_000;   //���ڿ��� _�� ���� �� �ְ� �� �ǹ̴� 100,000,000 ���� �ǹ̴�.
		private static int[] dist;				//������ ������������ �ش� �������� �ּ� �Ÿ�
		private static StringBuilder sb;
		private static int[] path;	//��θ� ���ؼ�
		//==========================================================
		static class Node implements Comparable<Node>{	//������ �ƴ� �����̴�.
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
			PriorityQueue<Node> pq = new PriorityQueue<>(); //�ش� �������� �켱������ �����ִ� ť
			boolean[] fin = new boolean[V+1];	//�ش� ������ ����.
			dist = new int[V+1]; //K_start �������� �ش� ��ȣ���� �ּ� �Ÿ�.

			Arrays.fill(dist, INF);
			dist[k_start] = 0;
			pq.add(new Node(k_start, 0));
			
			while(!pq.isEmpty()) {
				Node now_node = pq.poll();
				int now = now_node.index;		//���� �湮�� ����
				
				if(fin[now] == true) continue;
				fin[now] = true;
				
				for(Node next : list.get(now)) {
					if(dist[next.index] > dist[now]+next.weight) {
						dist[next.index] = dist[now]+next.weight;
						path[next.index] = now;	//�߰��� �κ�
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
			V = Integer.parseInt(st.nextToken());	//������ ����
			E = Integer.parseInt(st.nextToken());	//������ ����
			int K_start = Integer.parseInt(br.readLine()); //���� ���� (1<= K <= V)
			
			list = new ArrayList< ArrayList<Node> >();
			for(int i=0; i<=V; i++)
				list.add( new ArrayList<Node>() );
			
			for(int i=1; i<=E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());	
				int weight = Integer.parseInt(st.nextToken());
				list.get(start).add( new Node(end, weight) ); // �� �ش� ����Ʈ �迭�� ǥ���ϴ°� ����Ʈ �ε����� ������, �� �迭�� ����ִ� ���Ұ� ������ �Ǿ ǥ���ȴ�. 
				// �ٸ� ���� U��  V�� �մ� �ٸ� �����̶� ���Ŀ� �켱���� ť�� ���� �� ���� ����ġ���� ó���ǰ� out�Ǿ �ߺ�ó���� ����ȴ�.		
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
			//���������� �� ������ ���� �Ǿ Ǯ�̰� �ȴ�. 
			System.out.print(end+"�� �̷� ��� ");
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