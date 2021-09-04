package TraceBack_Shortest_Path_with_DP;

import java.util.*;
import java.io.*;

// 11779�� - �ּҺ�� ���ϱ� 2
public class p7__Min_Cost {
	//Ŭ��������
	static int N, M; //���� ����, ���� ����		1<=n<=1000	 1<=m<=100_000
	static int start, end;
	static ArrayList<ArrayList<Integer>> list;	//������ ���� �ٷ� ����ϰ� ���� ��
	static int[][] cost;						//���� ��뿡 ���ؼ� �غ��ص� ��.
	static int[] prev;							// [���� ��ġ] = ���� ��ġ�� �ּҷ� ���� ���� �ٷ� ���� ��ġ. ���ͽ�Ʈ��� �� ó�� ���ŵ� ���� ���� �ּҷ� ���� �ϴ� ��ġ�� �ȴ�.
	static int[] dist;							// ���ͽ�Ʈ�� ���� ���������� �ش� ������ ���� �ּҺ�� 
	static boolean[] visit;
	static int INF = 100_000_000;
	//=========================================================
	//���ͽ�Ʈ�� �˰����� ���� ����
	static class Node implements Comparable<Node> {
		int city, sum_cost;
		Node(int city, int sum_cost){
			this.city = city; this.sum_cost = sum_cost;
		}
		public int compareTo(Node o) {
			return sum_cost - o.sum_cost;	//������������ ����� �������� ���ʿ� ġ��ġ�� ��
		}
	}//=========================================================
	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//�Է�
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
			int a = Integer.parseInt(st.nextToken());	//������
			int b = Integer.parseInt(st.nextToken());	//������	
			int c = Integer.parseInt(st.nextToken());	//���
			//���� �ѹ��� ������ �ȵȰŸ� ������ �߰��ϰ� ����� �����ɷ� �����Ѵ�.
			if(cost[a][b] == 0) {		
				list.get(a).add(b);
				cost[a][b] = c;
			}
			//�� ����� ���� �ɷ� ��ü�Ѵ�.
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
		/* �̷����ϸ� �ѹ� �湮�Ҷ����� ��� ����ִ� ������ �����µ� ���ͽ�Ʈ��� �ϸ� ���� ���߿� ����� �־ ������ �Ǵ� ���̴�.
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
