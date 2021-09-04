package Graph_short_path;

import java.util.*;
import java.io.*;
// �ִܰ�� : ���ͽ�Ʈ�� 1753�� ���� 
// ���� ��ȣ�� 1���� �����Ѵ�.
// ���� �ٽ��� ���� ��ΰ� ª�� �������� �۾��� �����ϴ°� ����. �׷��ٸ� �� �����󿡼��� ������ ���� �߸��� dp�� ���� ���ð��̴�.
/**
 * �����... ��Ÿ�ӿ����� �߻��߰� �Ƹ� �޸� �ʰ��� ���̴�. �ٸ� ���� ������ ����.
 * ó������ �ƹ� ���� ���� ������ķ� ���� Ǯ�����µ� �޸� �ʰ��� ���Խ��ϴ�.
 * ������ ������ �ִ� 2�������� ��������� ����ٸ� �ִ� 2�ﰳ�� int �迭�� �Ҵ��ϱ� ������ �翬�� �޸𸮰� �����ϴ�.
 * �׷��� ��������Ʈ�� ��������� ����Ͽ����ϴ�.
 * **/
public class p1__Dijkstra {
	// (1��V��20,000, 1��E��300,000) 
	private static int V, E; 				//��������, ��������
	private static ArrayList< ArrayList<Node> > list;  //������ ����Ǿ��ִ� ����
	private static int INF = 100_000_000;   //���ڿ��� _�� ���� �� �ְ� �� �ǹ̴� 100,000,000 ���� �ǹ̴�.
	private static int[] dist;				//������ ������������ �ش� �������� �ּ� �Ÿ�
	private static StringBuilder sb;
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
					pq.add(new Node(next.index, dist[next.index]));	//�̷��� ��ȭ�� �Ͼ� �������� ��� ����ְ�, ���� �Ÿ��� ���� ª���ͺ��� ������ �ȴ�. 
					//���� ���⼭ �����ߴ� ����... ���� �� ���� ���� �߿��� ���� ª���� �����ϴ°��� �ϰ�... �� ������ �ٽ� �����ؼ� ���ο� ������ �� ������ �ٽ� ������ ���������߿����� �Ǵ��ϴ��� �˾Ҵ�.
					// �װ��� �ƴ� �� ���� ���� ª�� �Ÿ��� �����°��� ����ؼ� ã�Ƽ� ���ฦ �ذ��� ���̾���. 
				}
			}
		}/**---------------------------------------------------------------------**/
		
		// ���⼭ �����Ѱ��� �츮�� �����ϰ� ���� ������ ������ ��� �����ϴ°��� �ƴϴ�. �ż������� ���� ª������ ã�ư��°��̴�. �׸��� ���� �湮�� �����ٸ� �װ� �и��� ���� ª�� ���¸� ������ ��������̴�. 
		// �� ������ ���� pq�� ���� �������� ��� ���� ���� �����ִ� ���� �������� �����ִ°��̴�. �� ���� ���� ���� �濡 ���ؼ��� pq�� ���� ������ �ʰ�,
		// pq�� ���� ���� ��� ���� �����Ҽ��ִ� ���⼺�� �����ְ� �켱���� ť�� �����ִ� ���� ���� ª������ �������ִ� Ȥ�� �� ���� �湮�� �ؼ� �湮�� �����ߴٸ�
		// ����k_start�������� ���� ª�� ��ζ�� �ں��Ҽ��ִ�. �ݵ�� k_start���� �����ؼ� ������� ����̱� �����̱⵵ �ϴ�.
		/**while(!next_q.isEmpty()) {
			Node now = q.poll();	//�湮
			//System.out.println(now.end); ���� �湮�� ���
			int start = now.end;	// ���� �� ���� ������� �ȴ�.
			for(Node i : list[start]) {	//���� �������� �����ؼ� �̾����� ������ ���� �־��ش�.   
				pq.add(i);	//�켱���� ť�̱⿡ ����ġ�� ���� ������� ����. 
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
		/**-----**/
		dijkstra(K_start);
		print(dist);
		//System.out.println("----");

		bw.write(sb.toString());
		bw.flush();

	}//==========================================================
}
