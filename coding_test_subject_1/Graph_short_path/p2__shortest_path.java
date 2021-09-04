package Graph_short_path;

import java.util.*;
import java.io.*;
// Ư���� �ִ� ��� : ���� 1504��
/** 
 * �Ѱ��� �����Ѱ� �̰� ���⼺�� ���� �׷���. ���� �� ���� ����. �ݵ�� �ѹ��� �����ľ��Ѵٴ� ���� ����... �⺻���� ���ͽ�Ʈ��� �ѹ��� ������ �̵��ϴ°� �´�.
 * �ٸ� ������ �ϰԵǴ� �� ��� ��� ������ �� �������Ѵٴ� ���뿡 �ѹ��� �����ľ��ϴ� ������ ���ٸ� ���ͽ�Ʈ�� 
 * ���� 1->v1 , v1->v2, v2->V �� ���� �Ϳ��� �� �� 3���� ���ͽ�Ʈ�󿡼��� �ߺ��� ������ 3���� ��ġ�� �ߺ��� ������ ���� �� �ְԵȴ�. 
 * �׷��� 1->v1, v1->v2, v2->V �� ���ؼ� �� 3���� ���ͽ�Ʈ�� ���� ���� ���̰��� ���� ���ذ���. 
 * �̰͵� 1-> v2, v2->v1, v1->V �� ���ؼ� �� 3���� ���ͽ�Ʈ�� ���� ���� ���̰��� ���� ���ذ���ȴ�. 
 * **/
public class p2__shortest_path {

	private static int V, E;			//��������, ��������   (2 �� V �� 800, 0 �� E �� 200,000)
	private static ArrayList< ArrayList<Node> > list;  //������ ����Ǿ��ִ� ����
	private static int INF = 200_000_000;
	//==========================================================
	// ��� �ڷᱸ��
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
	// ���ͽ�Ʈ�� �˰��� 
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
					// ���⼭ ���� ���� �����ϴ� Node�� weight�� �ǹ̾��ٰ� �ôµ�, �ƴϴ�
					// ���� �߿��ϴ�. pq���� �켱������ �Ǵ��ϴ� �����̴�. 
					// �׷��� 0������ֵ� �׳� now.weight���� ������� ���� ��Ȯ�� ���� �ڽ��� �ش� ������ ���� dist���� ���� �־���Ѵ�. 
				}
			}
		}
		return dist[end];
	}//==========================================================
	// �� ���� �ݵ�� ������ �ִ� ���
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
	// main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/**input**/
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	//������ ����
		E = Integer.parseInt(st.nextToken());	//������ ����
		
		list = new ArrayList< ArrayList<Node> > ();
		for(int i=0; i<=V; i++)
			list.add( new ArrayList<Node>() ); 
		
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());	
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add( new Node(end, weight) );	
			list.get(end).add( new Node(start, weight) );	//�����
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		// �ݵ�� �����ľ� �ϴ� ���� �ΰ�.
		/**-----**/
		
		System.out.println( apply_dijkstra(v1, v2)+"" );
		
	}//==========================================================
}
