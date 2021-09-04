package Graph_short_path;
import java.util.*;
import java.io.*;
// 10217�� - KCM Travel
/**==============================================================================
 * ����ġ�� 1�� �ƴϹǷ� DFS, BFS�� �ƴ� ���ͽ�Ʈ��, ���� ����, �÷��̵� �ͼ��� ����ؾ� �Ѵ�.
 * �� ��� -> ��� �̹Ƿ� ���ͽ�Ʈ��, �������带 ����ؾ� �Ѵ�.
 * ���� ����ġ�� ���� ������ ���ͽ�Ʈ�� ����ؾ��Ѵ�.
 * 
 * �ᱹ ���ͽ�Ʈ�� ����ؾ� �ϴµ� �� ������ ���ͽ�Ʈ�� + dp�� �̿��ؼ� Ǯ���Ѵ�. 
 * �̶� dp[i][j] = k�� �ִٸ�, i������ j������� �� �� �ִ� �ּ��� �ð� = k��� �����ϰ� Ǯ�̿� ����. 
 * �� 1�������� �ش� ���ÿ� ���� �ִܽð�����
 * 2�������� �������� �ش絵�÷� �ش������� ���� �ִܽð��̶�� ������ �˴ϴ�. 
 * ���ͽ�Ʈ��� DP������� �����ִ�. �ִܰ�δ� �������� �ִܰŸ��� ��Ÿ�� �� �ֱ� �����̴�. 
 * �ϳ��� �ִܰŸ��� ���� ��, �� �������� ���ߴ� �ִ� �Ÿ� ������ �״�� ����Ѵ�.
 ===============================================================================**/
// 1���� ��õ����, N���� LA 
// ��õ���� LA�� ���� ���� ª�� �ҿ�ð��� �����ּ���.
// ���ͽ�Ʈ�� ����ϸ鼭 DP�� Ȱ���Ѵ�. �� DP�� 2���� �迭�� [���ù�ȣ][���� ��] ���� ���ͽ�Ʈ�󿡼� 1�������� Ȱ���� dist�迭�� 2�������� �д�.

public class p6__KCM_travel  {
		
	static ArrayList< ArrayList<Node> > list;
	static int[][] dp;		//[i][j] - i�� ������ ���µ� j������� ���� ���� �ּ� �ð�.
	static int INF = 100_000;
	//========================================================
	static class Node implements Comparable<Node>{	//���ͽ�Ʈ�� ���� ���
		int end, cost, time;
		Node(int e, int c, int t){
			end = e; cost = c; time = t;
		}
		public int compareTo(Node o) {
			if(this.time == o.time) 
				return cost - o.cost;	//���� �ð��̸� ����� �������� ���������� ����
			return this.time - o.time;	//�ð��� ª������ ���������� ���� 
		}
	}//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//������ ��
			int M = Integer.parseInt(st.nextToken());	//�� �������
			int K = Integer.parseInt(st.nextToken());	//Ƽ�������� ��
			list = new ArrayList<>();
			dp = new int[N+1][M+1];
			for(int i=0; i<=N; i++) {
				list.add(new ArrayList<Node>());
				Arrays.fill(dp[i], INF);
			}
			//Ƽ������ �Է�
			for(int z=0; z<K; z++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());	//���۰���
				int v = Integer.parseInt(st.nextToken());	//��������
				int c = Integer.parseInt(st.nextToken());	//���
				int d = Integer.parseInt(st.nextToken());	//�ҿ�ð�
				list.get(u).add(new Node(v, c, d));
			}
			
			//���ͽ�Ʈ�� �˰��� ����
			int minTime = INF;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(1, 0, 0));
			dp[1][0] = 0;	//1�� ������ ���µ� 0 ������� ���� ���� �ּ� �ð�. ó���� �̷��� �̰��� �����ؼ� ������ ������.

			//pq�� ��ο� �귯�� ������ ������Ʈ �Ǿ��. �� �̰����� cost�� time�� �����Ǿ� �´�. 			
			while(!pq.isEmpty()) {	
				Node now = pq.poll(); //�湮
				int now_city = now.end;
				int now_cost = now.cost;
				int now_time = now.time;
				
				if(now_city == N) { minTime = now_time; break; } //LA���ÿ� ������ �ߴٸ� ����. �켱����ť�� �۾��Ͽ� N���� ���� �Ѱ��� ù���� ���� �ִ� �ð����� ���ϸ�, �ι����� �湮�� �Ҽ��ִٴ� ��ǿ� �԰��� ��뵵 ���� �������� ���� �湮���ֱ⿡ �´�.
				if(dp[now_city][now_cost] < now_time) continue;	 //�̰��� �湮�ߴ� ���� �־ �׶� ������ �ð����� ���� �ð��� �� ũ�ٸ� �̹� ������ �� �����Ŷ� ���� ����. 
				dp[now_city][now_cost] = now_time;	//�湮�� �ߴٸ� �� ���ø� �湮�� �ð����� �������ش�.
				
				for(Node next : list.get(now_city)) {	//������ �湮 �Ҹ��� ���ø� ���캽
					int next_city = next.end;				//������ �� ����
					int next_cost = now_cost + next.cost;	//������ �� ���ø� ���ٸ� ��� ���
					int next_time = now_time+ next.time;	//������ �� ���ø� ���ٸ� ��� �ð�
					
					if(next_cost > M) continue;	//������ �� ������ ����� ��ü ��뺸�� ũ�� �Ѿ��.
					
					if(dp[next_city][next_cost] > next_time) {	//������ �� ���ø� ����������� ���� ���� �ð����� ������ �ȴٸ�.
						
						//��¥ �̺κ��� ���� ������ ���ߴµ� �����صθ� ���� ���� �ִ�.
						//���ʿ��� push�� �������ؼ�. �ٵ� Great_method�� �� ���� ����� �ִ�. 
						for(int p_c = next_cost; p_c<=M; p_c++) {
							if(dp[next_city][p_c] > next_time)  //�ش絵�÷� ���µ� �ڱ⺸�� ���� �����߿��� ���غ� Ȥ�� ���غ� �ִܽð��� �ڱ⺸�� Ŀ�� �湮�ϰ� �ɶ� ������ �ν����ְ��Ѵ�. 
								dp[next_city][p_c] = next_time;	//"�Ӻ� �Ӻ� ���� ��뺸�� ���� ������� ������ ������ �ð��̸� �켱 ���� ������! �˸��ϴ�. ������ ������ ���� ���Բ��� �湮�Ͻø� �ð��� �����ٸ� �� ���� �۾��� �ʿ���� ����� ��������~""																							
						}
						pq.add(new Node(next_city, next_cost, next_time)); //�湮 �� ������ �ϳ��� �־��ش�.
					}
				}
			}
			//���
			if(minTime==INF) bw.write("Poor KCM\n");
			else bw.write(minTime+"\n");
		}//�ݺ��� ����
		
		bw.flush();
	}//========================================================
}