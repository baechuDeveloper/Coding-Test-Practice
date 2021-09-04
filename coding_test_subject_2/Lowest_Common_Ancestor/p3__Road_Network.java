package Lowest_Common_Ancestor;
import java.util.*;
import java.io.*;

//��� ������ �ֿ��� �� ���ø� �����ϴ� ������ ��ΰ� �ְ�... �� ���� Ʈ������.

// 3176�� - ���� ��Ʈ��ũ
public class p3__Road_Network {

	static int N;		// ���� ����, 		 2 <= N <= 100_000	,	���ô� 1�� ���� ����.
	static int K;		// ���� ���� ����,	 1 <= K <= 100_000
	static ArrayList<ArrayList<Road>> roadlist;	//���� ���� ����
	static int[][] parent;		//[����][���۵���] = �̵��� ����
	static int[][] max, min;	//[����][���۵���]���� ���۵��ÿ��� �̵��� ���ñ��� �̵����� �� ���� max�� min�� ������ �����̾����� ���.
	static int[] depth;			//���� ������ ����, 0�� ��Ʈ
	static int MAX_DIGIT = 18;
	//=========================================================
	static class Road{
		int city, cost;
		Road(int i, int c){
			city=i; cost=c;
		}
	}//=========================================================
	static void makeTree(int now) {
		for(Road child : roadlist.get(now)) {
			int city = child.city;
			int cost = child.cost;
			if(parent[0][city]==0) {
				parent[0][city] = now;
				max[0][city] = cost;
				min[0][city] = cost;
				depth[city] = depth[now]+1;
				makeTree(city);
			}
		}
	}//=========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		roadlist = new ArrayList<>();
		for(int i=0; i<=N; i++)
			roadlist.add(new ArrayList<>());
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			roadlist.get(a).add(new Road(b, c));
			roadlist.get(b).add(new Road(a, c));
		}

		//��ҹ迭�� ���� �غ�ܰ�
		int root = 1; //���� ��Ʈ�� ���� �� ���� ������ ���� ������ �޶�����. �ٸ�, �츮�� ���Ϸ��°� ��� ��Ʈ�϶� ���������� �ƴ� ���������� 2���� ���������� �Ǵ� �߰������̴�.
					  //���� ��Ʈ�� ������ ��������ʰ� Ʈ���̴� ��� ������ ��Ʈ�� �ص� �Ǹ�, ���������� �߰������� ��Ȱ�� ���ָ� �ȴ�.
		parent = new int[MAX_DIGIT][N+1];
		max	   = new int[MAX_DIGIT][N+1];
		min    = new int[MAX_DIGIT][N+1];
		for(int i=0; i<MAX_DIGIT; i++)
			Arrays.fill(min[i], 1_000_001); 	//min�� ���� �����ؼ� ������ �ȵȰ� ���� �� �����Ѵ�. max�� ���������� ���� �����ؼ� �������� ���� ���´�.
		depth = new int[N+1];
		Arrays.fill(depth, -1);
		depth[1] = 0;
		parent[0][1] = -1;	//��Ʈ�� �θ�� ���� ������ �Ͽ� �̷��� �ξ���. 
		makeTree(root);
		
		//��ҹ迭 �ϼ���Ű��
		for(int j=1; j<MAX_DIGIT; j++) {
			for(int i=1; i<=N; i++) {
				int side = parent[j-1][i];
				if(side != -1) {	//�ſ� ��Ȯ�� ���. ļ. �̹� �� ���ǹ��� ���� �Ѱ��̴� ��� �������.
					parent[j][i] = parent[j-1][ side ];
					max[j][i] = Math.max(max[j-1][i] , max[j-1][ side ]);	//j�� �̵����� ��ó�� i���� j-1�� �̵����� �� ���� max�� j-1�� �̵��� side���� j-1�� �̵��� max�� �� ���� �� ���� �ȴ�.
					min[j][i] = Math.min(min[j-1][i] , min[j-1][ side ]);	
				}
				else {
					parent[j][i] = parent[j-1][i];	//�� �����δ� 0�� �̾��.
					max[j][i] = max[j-1][i];
					min[j][i] = min[j-1][i];
				}
			}
		}	
		
		//���� ������ �ذ��غ���
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			//������ depth[D] >= depth[E]�� �����ؼ� �����ϰ����Ѵ�. �ϳ��� �����ϴ°� �� ������. �׸��� depth�� Ŭ���� ���.
			if(depth[D] < depth[E]) {
				int temp = D;
				D = E;
				E = temp;
			}
			int diff = depth[D] - depth[E];	//�׻� depth[D] >= depth[E]�� ����
			int a = D, b = E;
			int max_A=0, max_B=0;
			int min_A=1_000_001, min_B=1_000_001;
			//System.out.println("����"+D+" "+E);
			// �������� ���ָ� a�� ���� �̵������ش�. �׷��� b�� depth�� �������ش�. 
			for(int j=0; diff>0; j++) {
				if(diff%2==1) {
					max_A = Math.max(max_A, max[j][a]);
					min_A = Math.min(min_A, min[j][a]);
					a = parent[j][a];		//�� �������� ���� �̵����� �Ű��ش�. ���࿡ �̰Ÿ��� �ϰ� max, min�ϸ� �������. �����̴�.
				}
				diff/=2;
			}
			//System.out.println("��ó�� "+max_A+" "+min_A+" "+max_B+" "+min_B);
			// a�� b�� ���� �ٸ��� '���ÿ�' ���� ���� ��ŭ �̵���Ų��. 
			if(a != b) {
				for(int k=MAX_DIGIT-1; k>=0; k--) {

					if(parent[k][a]!=-1 && parent[k][a] != parent[k][b]) {
						max_A = Math.max(max_A, max[k][a]);
						min_A = Math.min(min_A, min[k][a]);
						max_B = Math.max(max_B, max[k][b]);
						min_B = Math.min(min_B, min[k][b]);
						a = parent[k][a];
						b = parent[k][b];	//�� �������� ���� �̵����� �Ű��ش�. ���࿡ �̰Ÿ��� �ϰ� max, min�ϸ� �������. �����̴�.
						//System.out.println("���� ���� "+max_A+" "+min_A+" "+max_B+" "+min_B);
					}
					
				}
				
				max_A = Math.max(max_A, max[0][a]);
				min_A = Math.min(min_A, min[0][a]);
				max_B = Math.max(max_B, max[0][b]);
				min_B = Math.min(min_B, min[0][b]);
				a = parent[0][a];
				//System.out.println("������ "+max_A+" "+min_A+" "+max_B+" "+min_B);System.out.println();
			}
			//a���� �������� ������ ����ִ�.
			int all_max = Math.max(max_A, max_B);
			int all_min = Math.min(min_A, min_B);
			sb.append(all_min+" "+all_max+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		
	}//=========================================================

}
