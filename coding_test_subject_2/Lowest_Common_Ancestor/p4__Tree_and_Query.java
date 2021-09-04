package Lowest_Common_Ancestor;
import java.util.*;
import java.io.*;

// 13511�� - Ʈ���� ����2
public class p4__Tree_and_Query{

	static int N;			//���� ���� 1������ ����.	 2 <= N <= 100_000
	static int K;			//��������				 1 <= K <= 100_000
	static int[] depth;		//Ʈ���� ����			 Ŭ���� �� ���� �� ��Ʈ�� 0
	static int[][] parent;	//[����][������] = �̵��� ��
	static long[][] pcost;
	//static long[] dcost;	//���� ������ ��Ʈ���� ��� ���
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
				pcost[0][child]  = (long)cost;
				//dcost[child] = dcost[now] + cost;
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
		pcost  = new long[MAX_DIGIT][N+1];
		//dcost = new long[N+1];
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
				pcost[j][i] = pcost[j-1][i] + pcost[j-1][ side ];
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
			long cost_A=0, cost_B=0;	//������������ �ö󰡸鼭 �߻��� ����. �׸��� long�ڷ����̴�!
			int cnt_A=0, cnt_B=0;	//������������ �ö󰡱� ���� ���� �̵��� Ƚ��. ������.

			int diff = depth[a] - depth[b];	//�׻� depth[a] >= depth[b]�� �����ϱ⿡ �ڿ���
			for(int j=0; diff>0; j++) { // �������� ���ָ� a�� ���� �̵������ش�. �׷��� b�� depth�� �������ش�. 
				if(diff%2==1) {	
					cost_A += pcost[j][a];	//�� �Ʒ� ���� �߿��ϱ���
					cnt_A += Math.pow(2, j);
					a = parent[j][a];
				}
				diff/=2;
			}

			if(a != b) { // a�� b�� ���� �ٸ��� '���ÿ�' ���� ���� ��ŭ �̵���Ų��. 
				for(int k=MAX_DIGIT-1; k>=0; k--) {
					if(parent[k][a]!=1 && parent[k][b]!=1 && parent[k][a] != parent[k][b]) {
						cost_A += pcost[k][a];
						cost_B += pcost[k][b];	//���������� �� �Ʒ� ���� �߿��ϱ���
						cnt_A += Math.pow(2, k);
						cnt_B += Math.pow(2, k);
						a = parent[k][a];
						b = parent[k][b];
					}
				}

				cost_A += pcost[0][a];	//������ ������������ �ö󰡴� �� �ѹ��� �̵�.
				cost_B += pcost[0][b];
				cnt_A++; cnt_B++;
				a = parent[0][a];	//�������� 
			}
			//a���� ���������� �����ִ�. 
			if(change==true) {
				int t1 = U;  	 U = V;  		  V = t1;
				long t2 = cost_A; cost_A = cost_B; cost_B = t2;
				int t3 = cnt_A;  cnt_A = cnt_B;   cnt_B = t3;
			}

			if(cmd==1) {
				long sum = cost_A+cost_B;
				sb.append(sum+"\n");
			}
			else {
				int num = Integer.parseInt(st.nextToken());	//U���� �����ؼ� V�� ���ϴ� ����� num���� �ش��ϴ� ����
				// �� �� ���������� �Ѿ�� V������ �������� ��쵵 ������ �װ� �ǽ�����.
				// �������� �� ������ Ǯ �� �Ƹ� ���߿��� ������ ����� �Ǵ°� Ʈ������ �� �������� ���� �濡 ��� ������ �����ϴ� �� �˾ƺ��� �ɷ� �Ѿ���� �ְڴ�. 
				// �׷��� �ҷ��� �Ƹ� U���� ������������� V���� ����������� ����� �̵��� �ִ��� ���� �ɰ��̴�.

			 	num--; //�� ��ȣ�� U�� 1������ �����Ͽ� �۵�. �׷��� num-1�� ���¿��� �̰� ��������� ����� �̵��� �Ǵ��� ����.

				if(cnt_A>=num) { //���������� �Ѿ V�� ���� �ʴ´�.
					for(int j=0; num>0; j++) {
						if(num%2==1)
							U = parent[j][U];
						num/=2;
					}
					sb.append(U+"\n"); // (num-1)�� �̵��Ͽ� ������ ���� �˷��ش�.
				}
				else {	//���������� �Ѿ� V�� ����.
					int Vnum = cnt_B - (num - cnt_A); //V���� �ö󰡴� ������ ���ؼ�, V���� �־�� ����� �̵����� �ش� ��°�� �����ϴ� Ƚ���� ��Ÿ������ �˷��ش�.
					for(int j=0; Vnum>0; j++) {
						if(Vnum%2==1)
							V = parent[j][V];
						Vnum/=2;
					}
					sb.append(V+"\n"); // Vnum�� �̵��Ͽ� ������ ���� �˷��ش�.
				}
			}

		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================

}
