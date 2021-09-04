package Graph_short_path;

import java.util.*;
import java.io.*;
// Ư���� �ִ� ��� : ���� 1504��
// �ð��ʰ� �ƹ��� 800������ ������ ������ �ſ� ������ ���� �ð��� ����� ũ��. 
/**
 * V�� ������ �ִ� 800�� �̱⿡ �� ������ ���ؼ��� ���ͽ�Ʈ��� �ݵ�� ������ ���� �ʾƵ� �ɵ��ϴ�. ���ÿ� �ش� ������ ���� �Ϸ��� DFS�� Ȱ���� Ǯ�̸� �ؾ��Ұ��̴�.
 * ���� ���ͽ�Ʈ�� ���� ���¿� �޸� �� ������ 1�� �������� N�� �������� �ִܰ���� V1�� V2�� �����ϴ� ����� ���̸� �����ϴ� �����̴�.
 * **/
public class p2__shortest_path_but_timeover {
	// (2 �� V �� 800, 0 �� E �� 200,000) 
	private static int V, E;			//��������, �������� 
	private static ArrayList< ArrayList<Node> > list;  //������ ����Ǿ��ִ� ����
	private static boolean[] visit;
	private static int v1, v2; //�������ϴ� ���� v1, v2, 
	private static int min = Integer.MAX_VALUE;
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

	public static void DFS(int now, int dist) {
		if(now == V) {	//������ ���� V�� �ش��ϴ°� 
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
		V = Integer.parseInt(st.nextToken());	//������ ����
		E = Integer.parseInt(st.nextToken());	//������ ����
	
		list = new ArrayList< ArrayList<Node> > ();
		visit = new boolean[V+1];
		for(int i=1; i<=V; i++)
			list.add( new ArrayList<Node>() ); 
		
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());	
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add( new Node(end, weight) );	// �� �ش� ����Ʈ �迭�� ǥ���ϴ°� ����Ʈ �ε����� ������, �� �迭�� ����ִ� ���Ұ� ������ �Ǿ ǥ���ȴ�. 
			// �ٸ� ���� U��  V�� �մ� �ٸ� �����̶� ���Ŀ� �켱���� ť�� ���� �� ���� ����ġ���� ó���ǰ� out�Ǿ �ߺ�ó���� ����ȴ�.		
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		// �ݵ�� �����ľ� �ϴ� ���� �ΰ�.
		/**-----**/
	
		DFS(1, 0);

		bw.write(min+"");
		bw.flush();

	}//==========================================================
}
