package Graph_short_path;

import java.util.*;
import java.io.*;

// 9370�� - ��Ȯ�� ������
//��ǥ�� �������� �����ϴ� �ִܰ���߿� g-h�� �������� �ִܰ�ΰ� �ִ°�. ������ �ش� �������� ������������ �������ش�. 
public class p3__Unknown_Arrival {
	//Ŭ��������
	static int[][] arr;		 //[����a][����b] ������ �Ÿ�
	static ArrayList<ArrayList<Integer>> list;	//�������� ����� ������ ������ 
	static int[] dest, dist; //�������ĺ�, s������������ �ش� �ε������� �ּҰ� �� �Ÿ�(������ �� ��������)
	static boolean[] check;	 //�湮üũ
	static int INF = 10_000_000;
	//======================================================
	//������ ���Ѵ�.
	static class Node implements Comparable<Node>{
		int index, weight;	//index�� �ش� ������ ���մϴ�.
							//weight�� �ش� �������� ���Ŀ��� ���� ���ͺ� ����ġ�� ��
		Node(int i, int w){
			index=i; weight = w;
		}
		public int compareTo(Node o) {
			return weight - o.weight;	//���� ũ�� ������ ��� �� ���� �� ũ��. ���������� ������������ ����
		}
	}//======================================================
	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine());
		
		while(test_case-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());	//������ ���� //�����δ� �����Ǵ� ���� ��, �� ������ ������ ���Ѵ�.
			int m = Integer.parseInt(st.nextToken());	//���� ����
			int t = Integer.parseInt(st.nextToken());	//������ �ĺ� ����
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());	//��������
			int g = Integer.parseInt(st.nextToken());	//������.	�� �� ������ ���̿� 1�� �̻��� ������ �����Ѵ�. 
			int h = Integer.parseInt(st.nextToken());	//������. ������ ���̿��� ���ΰ� ���ƺ��� 1���̴�. 
														//�� ���δ� �ݵ�� �־�����, ������ �ĺ��� �� ��� 1���� ���ϴ� �ִܰ���� �Ϻ��̴�. 
			//�׷��� ���� ����
			list = new ArrayList<>();
			for(int i=0; i<=n; i++)
				list.add(new ArrayList<>());
			arr = new int[n+1][n+1];
			for(int x=0; x<arr.length; x++)
				Arrays.fill(arr[x], INF);
			for(int z=0; z<m; z++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				arr[a][b] = arr[b][a]= 2*d;			//�Ÿ��� 2�辿 �÷��� ¦���� �ξ���.
				list.get(a).add(b);
				list.get(b).add(a);
			}
			arr[h][g] = arr[g][h] = arr[h][g]-1;	//g�� h���̸� ������ ������ �Ÿ��� Ȧ���� �ξ���. �ƴϸ� float������ �ξ� �ش� ���� �Ҽ��� ���� �ִ´�.
			
			//�������� �ش��ϴ� �ĺ���
			dest = new int[t];	
			for(int z=0; z<t; z++) 
				dest[z] = Integer.parseInt(br.readLine());
			Arrays.sort(dest); //�̸� ������������ ������ �صξ� ���信�� �ٷιٷ� ������ ��.
			
			//�������� s���� �����Ÿ��� �ش��ϴ� �Ÿ� 
			dist = new int[n+1];
			Arrays.fill(dist, INF);	//���ͽ�Ʈ�� �����Ű���� ū ���� �������ش�.
			
			//�湮�ߴ��� üũ
			check = new boolean[n+1];
			
			/**���ͽ�Ʈ�� �˰��� ����**/
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(s, 0));
			dist[s] = 0;
			
			while(!pq.isEmpty()) {
				Node nowNode = pq.poll();
				int now = nowNode.index;
				if(check[now]) continue; //�̹� �湮�ߴٸ� �н� 
				check[now] = true;
				for(int next : list.get(now)) {
					if( check[next]==false && dist[next] > dist[now] + arr[now][next] ) {
						dist[next] = dist[now] + arr[now][next];
						pq.add( new Node(next, dist[next]) );	//������� ������ �Ÿ� ���� ���� ���� �� �켱�������� Node�� ���� �����Ͽ���. 
																//if���ǿ� dist[now] ��� nowNode.weight�� �ص� �Ǹ�, ���� �ѹ� ���ŵ� dist[now]�� �ι� �ٽ� ���� ������ ���ŵ� ���� ����. ���ͽ�Ʈ�� ������ 
					}
				}
			}/**�˰��� ��**/
			
			//���� ���
			for(int i : dest) {
				if(dist[i] % 2 == 1) //�ش� �������������� ���� �����ߴٸ� �� ���� s���������� �ּ��ΰ� �翬�ϴ�. �ű⿡ �� ���� Ȧ����� g�� h���̸� �ǳʿ� �� ���� �˼��ִ�. 
					sb.append(i+" ");
			}
			sb.append("\n");
		}//��ü �׽�Ʈ���̽� ����
		
		bw.write(sb.toString());
		bw.flush();
	}//======================================================
	
}
