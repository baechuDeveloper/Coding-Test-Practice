package MST__Minimm_Spanning_Tree;

import java.io.*;
import java.util.*;
// 17472�� - �ٸ� ����� 2
public class p6__Make_Bridge_2 {
	//Ŭ���� ����
	static int N, M;	//����, ����		1<= N, M <=10
	static int island_count = 0;	//2<= ���� ���� <=6
	static int[][] arr;				//���� ����	[x��ġ][y��ġ] = �ش� ��ġ ���� ��ȣ�� ��Ÿ������ 
	static int[][] road_check;		//[���� ��ȣ][���� ��ȣ] = ���� �� ������ ���κ�� ��. 
	static PriorityQueue<Bridge> pq;
	static int[] group;
	static int[][] cal = { {0, -1}, {-1, 0}, {0, 1}, {1, 0} };
	static int INF = 100;
	//==================================================
	//���� �ڷᱸ��
	static class Bridge implements Comparable<Bridge>{
		int i1, i2, cost;
		Bridge(int a, int b, int c){
			i1=a; i2=b; cost=c;
		}
		public int compareTo(Bridge o) {
			return cost - o.cost;
		}
	}//==================================================
	//���Ͽ����ε� Ž���Լ�
	static int groupFind(int a) {
		if(group[a] == -1) return a;
		return group[a] = groupFind(group[a]);
	}//========================================================
	//���Ͽ����ε� �����Լ�
	static boolean groupMerge(int a, int b) {
		a = groupFind(a);
		b = groupFind(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//========================================================
	//�������� �߰ߵ� ���� ��ȣ�� ����
	static void find_island(int x, int y) {
		//BFS�ϰ� 4�������� ���������� �ؼ� �۾��Ѵ�. 
		arr[x][y] = island_count;

		for(int i=0; i<4; i++) {
			int next_x = x+cal[i][0];
			int next_y = y+cal[i][1];
			if(0<=next_x && next_x<N && 0<=next_y && next_y<M) {
				if(arr[next_x][next_y]==0)
					find_island(next_x, next_y);
			}
		}
	}//==================================================
	//���� ������ �ٸ��� ����� �ϴ� �۾�
	static void make_bridge(int p1, int p2, int cost) {
		if(cost < road_check[p1][p2]) {	//[p2][p1]�� �ᱹ [p1][p2]�� �Ͻɵ�ü�̴� �ϳ��� üũ�ص� �ȴ�. ���� �̹� ������� �ִµ� �� ���̰� �پ����� Ȯ���� �� �ִ�.
			road_check[p1][p2] = cost;
			road_check[p2][p1] = cost;
			pq.add(new Bridge(p1, p2, cost));
		}
	}//==================================================
	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];	//[����][����]
		for(int i=0; i<N; i++)
			Arrays.fill(arr[i], -1);

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				arr[i][j] += Integer.parseInt(st.nextToken());	
		}
		//for(int i=0; i<N; i++) { for(int j=0; j<M; j++) System.out.print(arr[i][j]+" "); System.out.println(); } System.out.println();

		//�� ������ �Է� 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0) {
					island_count++;	//���ο� ���� �߰������� ������ �÷��ش�. �� ��ȣ�� ���ο� ���� ��Ʈ���鼭 �������ְԵȴ�.
					find_island(i, j);
				}
			}
		}
		//������ �� ���
		//for(int i=0; i<N; i++) { for(int j=0; j<M; j++) System.out.print((arr[i][j]==-1?0:arr[i][j])+" "); System.out.println(); } System.out.println();

		//�ٸ� ���� �Է� 
		road_check = new int[island_count+1][island_count+1];
		for(int i=0; i<=island_count; i++)
			Arrays.fill(road_check[i], INF);
		pq = new PriorityQueue<>();
		//���� ���� ��������
		for(int i=0; i<N; i++) {
			int start_y=0;
			int start_color = arr[i][0];
			for(int j=0; j<M; j++) {
				int next_color = arr[i][j];
				if(next_color!=-1) {	//���� � ���� ���� 
					/**��Ȳ�� 3���� - ���� ���� � ������ ������ ���ؼ� ó������ ���� ����, ���� ���� a��� ���� �ִµ� �Ȱ��� a��� ���� ��ġ�� �ϳ�, ���� a��� ���� �ִµ� b��� ���� ����.*/
					//���� �̹� �ٸ� ������ �����ؼ� �°ǵ�, ���� ����Ű���ִ°��� ���ο� ���� ���̳�!? �����߰����� Ȯ��!
					if(start_color!=-1 && start_color!=next_color && j-(start_y+1) >= 2)  //�����߰�
						make_bridge(start_color, next_color, j-(start_y+1));	
					start_color = next_color;	//ó�� ���� �������� �� ����, ���� ���� ������ ���ž��ص� ������ �����ص� ��������� if���� �� �Ⱥ��Ϸ��� ����, ���ο� ���� ���� ����
					start_y=j;	//ó�� ���� ������������ ����, ���� ���� �־ �� �����ʿ� �� �� �ִٸ� ����, ���ο� ���� ���� ����
				}
			}
		}
		//���� ���� ����
		for(int j=0; j<M; j++) {
			int start_x=0;
			int start_color = arr[0][j];
			for(int i=0; i<N; i++) {
				int next_color = arr[i][j];
				if(next_color!=-1) {	//���� � ���� ���� 
					/**��Ȳ�� 3���� - ���� ���� � ������ ������ ���ؼ� ó���� ���� ����, ���� ���� a��� ���� �ִµ� �Ȱ��� a��� ���� ��ġ�� �ϳ�, ���� a��� ���� �ִµ� b��� ���� ����.*/
					//���� �̹� �ٸ� ������ �����ؼ� �°ǵ�, ���� ����Ű���ִ°��� ���ο� ���� ���̳�!? �����߰����� Ȯ��!
					if(start_color!=-1 && start_color!=next_color && i-(start_x+1) >= 2)  //�����߰�
						make_bridge(start_color, next_color,i-(start_x+1));	
					start_color = next_color;	//ó�� ���� �������� �� ����, ���� ���� ������ ���ž��ص� ������ �����ص� ��������� if���� �� �Ⱥ��Ϸ��� ����, ���ο� ���� ���� ����
					start_x=i;	//ó�� ���� ������������ ����, ���� ���� �־ �� �����ʿ� �� �� �ִٸ� ����, ���ο� ���� ���� ����
				}
			}
		}
		//�������
		//for(Bridge i : pq) System.out.println(i.i1+" "+i.i2+" "+i.cost); System.out.println();
		
		//ũ�罺Į �˰���
		group = new int[island_count+1];
		Arrays.fill(group, -1);
		int sum=0, count=0;

		while(!pq.isEmpty()) {
			Bridge now = pq.poll();
			if( groupMerge(now.i1, now.i2) ) {
				sum += now.cost;
				if(++count == island_count-1) 
					break;			
			}
		}
		
		//��� ����Ǿ��ִ��� Ȯ��. groupFind�� ���ؼ� ���� �ڽ��� �׷� ��ǥ���� �������� �����մ�. ���� ��� �ϳ��� �ٸ��ٸ� ��� ������� ���� ���̴�.
		int group_ex = groupFind(1);
		for(int i=2; i<=island_count; i++) {
			int test = groupFind(i);
			if(group_ex != test) {
				System.out.println(-1);
				return;
			}
		}
		
		System.out.println(sum);

	}//==================================================

}
