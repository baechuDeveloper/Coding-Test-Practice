package Lowest_Common_Ancestor;
import java.util.*;
import java.io.*;

// 13511�� - Ʈ���� ����2
public class p4__Tree_and_Query_MORE {

	static int N;			//���� ���� 1������ ����.	 2 <= N <= 100_000
	static int K;			//��������				 1 <= K <= 100_000
	static int[] depth;		//Ʈ���� ����			 Ŭ���� �� ���� �� ��Ʈ�� 0
	static int[][] parent;	//[����][������] = �̵��� ��
	static long[] dcost;	//���� ������ ��Ʈ���� ��� ���
	static ArrayList<ArrayList<Node>> childlist;	//���� ����
	static int MAX_DIGIT = 18;
	//=======================================================
	static class Node{
		int i, cost;
		Node(int i, int c){
			this.i=i; cost=c;
		}
	}//=======================================================
	static void makeTree(int now) {
		for(Node node: childlist.get(now)) {
			int child = node.i;
			int cost = node.cost;
			if(parent[0][child]==0) {
				parent[0][child] = now;
				dcost[child] = dcost[now] + cost;
				depth[child] = depth[now]+1;
				makeTree(child);
			}
		}
	}//=======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		childlist = new ArrayList<>();
		for(int i=0; i<=N; i++)
			childlist.add(new ArrayList<>());
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			childlist.get(a).add(new Node(b, c));
			childlist.get(b).add(new Node(a, c));	
		}

		//��ҹ迭 �غ�
		parent = new int[MAX_DIGIT][N+1];
		dcost = new long[N+1];
		depth = new int[N+1];
		Arrays.fill(depth, -1);
		depth[1] = 0;
		parent[0][1] = 1;	//��� �ڱ⿡�� ������ ������ �ٸ� �����Ե� ����ߴ� �������� �ټ����ְ� 1�� ������ �����ϴ� ��� �̵����� ������ ��� ���� �ټ��ְ� �ȴ�.
		//Ʈ���ϼ�
		makeTree(1);	//��Ʈ�� 1�� �ξ��µ� �̷��� �� �� �־��� ������ a�� b������ ��θ� ���ϴµ� �־ �� �߰��� ��Ʈ 1�� ��ο� ���ԵǾ a���� ��Ʈ�� b���� ��Ʈ�� �ϴ� ����� ���������� ���ϴ� ������ε� ���� �� �ִ�.

		//��ҹ迭 �ϼ���Ű��.
		for(int j=1; j<MAX_DIGIT; j++) {
			for(int i=1; i<=N; i++) {
				int side = parent[j-1][i];
				parent[j][i] = parent[j-1][ side ];
			}
		}	
		//for(int j=0; j<MAX_DIGIT; j++) {for(int i=1; i<=N; i++) {System.out.println(i+"�� "+j+"�� �̵�:"+parent[j][i]+" ���:"+pcost[j][i]);}System.out.println();}

		//�������� �����ϱ�
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 1�� �� �� ������ ���, 2�� �� �����̿� �����ϴ� ���� �� ��° ���� ���ش޶�.
			int cmd = Integer.parseInt(st.nextToken());
			//a�� b������ ��Ʈ�� ���ԵǾ��ֵ� ���� ���������� ���ϴ� ������ �س� �� �ִ�.
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			//������ depth[U] >= depth[V]�� �����ؼ� �����ϰ����Ѵ�. �ϳ��� �����ϴ°� �� ������. �׸��� depth�� Ŭ���� ���.
			boolean change = false;
			if(depth[U] < depth[V]) {
				int temp = U;
				U = V;
				V = temp;
				change = true;	//�̰� �� üũ�ϴ� �� �����󿡼��� ������ U�� ���� �ִµ�, ���� ����� ���ϵ��� ������ ���̶� �ٽ� ���� �����ϰ��� üũ�Ѵ�. 
			}
			int a=U, b=V;

			int diff = depth[a] - depth[b];	//�׻� depth[a] >= depth[b]�� �����ϱ⿡ �ڿ���
			for(int j=0; diff>0; j++) { // �������� ���ָ� a�� ���� �̵������ش�. �׷��� b�� depth�� �������ش�. 
				if(diff%2==1) {	
					a = parent[j][a];
				}
				diff/=2;
			}

			if(a != b) { // a�� b�� ���� �ٸ��� '���ÿ�' ���� ���� ��ŭ �̵���Ų��. 
				for(int k=MAX_DIGIT-1; k>=0; k--) {
					if(parent[k][a]!=1 && parent[k][b]!=1 && parent[k][a] != parent[k][b]) {
						a = parent[k][a];
						b = parent[k][b];
					}
				}
				a = parent[0][a];	//�������� 
			}
			//a���� ���������� �����ִ�. 
			if(change==true) {
				int temp = U;  U = V;  V = temp;
			}

			if(cmd==1) {
				sb.append((dcost[U]+dcost[V]- 2*dcost[a])+"\n");
			}
			else {
				int num = Integer.parseInt(st.nextToken());	//U���� �����ؼ� V�� ���ϴ� ����� num���� �ش��ϴ� ����
				int cnt = 0;

				/* https://salepark.tistory.com/52
				 * �� ���� 2�� ������ ���� �����غ���
				 * u�� v�� ��� K��° ����� ��ȣ�� ����ϴ� �����ε�,
				 * K�� ������(?)�� ����Ǿ������� 2���� ���� ������.
				 * K��° ��尡 �ּҰ�������P�� ���ʿ� ���� ���� �����ʿ� ���� �� �̴�.
				 * �ٸ� �����ؼ�, u - �ּҰ������� - v�� ��ΰ� �����Ǿ� ���� ��,
				 * u-�ּҰ������� ���̿� ���� ���� �ּҰ������� - v���̿� ���� �� �ΰ����� ���� �� �ִ�.
				 * u - �ּҰ������� ���̿� ���� ��  u ���� K��°�� ã�ư��� �ȴ�.
				 * �̶� ���� K������ ã�ư� �� LCA�� ���� �� ����ߴ� dp���� �̿��ؼ� ã�ư��� �ȴ�.
				 * ���������� �ּҰ������� - v���̿� ���� ��, �켱 u - v�Ÿ����� K�� ����, v���� �ּҰ��������� �Ÿ��� �ȴ�.
				 * �̴� u - �ּҰ��������� �Ÿ��� ���ߴ� ��ó�� dp���� �̿��� ã�ư��� �ȴ�.*/
				if (num > depth[U] - depth[a] + 1) {
					num -= depth[U] - depth[a] + 1;
					num = (depth[V] - depth[a])- num;
					while (num != 0) {
						if (num % 2 == 1) {
							V = parent[cnt][V];
						}
						num /= 2;
						++cnt;
					}
					sb.append(V+"\n");
				}
				else {
					--num;
					while (num != 0) {
						if (num % 2 == 1) {
							U = parent[cnt][U];
						}
						num /= 2;
						++cnt;
					}
					sb.append(U+"\n");
				}
				
			}

		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================

}
