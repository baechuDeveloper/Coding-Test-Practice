package Lowest_Common_Ancestor;

import java.util.*;
import java.io.*;

// 3584�� - ���� ����� ���� ����
public class p1__LCA_easy {
	
	static int N;	//N���� �������� �̷���� Ʈ��		2<= N <= 100_000		1������ �����ϴ� ���
	static int M;	//���� ����� ���� ������ ���� ����	1<= M <= 100_000
	static int root;
	static Set<Integer> addset, delset;	//��� ��Ʈ�� ã��;
	static ArrayList<ArrayList<Integer>> childlist;
	static int[][] parent;
	static int[] depth;
	static int MAX_DIGIT = 18;
	//==========================================================
	//Ʈ�������� ����� �޼ҵ�
	static void makeTree(int now) {
		for(int child : childlist.get(now)) {
			parent[0][child] = now;	//�θ�� �ڽ� ���踦 Ȯ���� �˷��༭ �ٷ� ������ �����ϴ�. Ʈ�������̱⿡ �ߺ��� �ڽ��� ����. 
			depth[child] = depth[now] +1;
			makeTree(child);
		}
	}//==========================================================
	//main�Լ�
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			
			N = Integer.parseInt(br.readLine());
			childlist = new ArrayList<>();
			for(int i=0; i<=N; i++)
				childlist.add(new ArrayList<>());
			addset = new HashSet<>();
			delset = new HashSet<>();
			for(int i=0; i<N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken()); addset.add(p);
				int c = Integer.parseInt(st.nextToken()); delset.add(c);
				childlist.get(p).add(c);
			}
			
			for(int i:delset) 
				addset.remove(i);
			root = (int)addset.toArray()[0];

			parent = new int[MAX_DIGIT][N+1];
			depth = new int[N+1];
			Arrays.fill(depth, -1);
			depth[root] = 0;
			
			//�켱 Ʈ�������� �����ϰ� �غ��Ѵ�.
			makeTree(root);
			
			//��ҹ迭�� �ϼ��Ѵ�.
			for(int j=1; j<MAX_DIGIT; j++) {
				for(int i=1; i<=N; i++) {
					if(parent[j-1][i] != -1)
						parent[j][i] = parent[j-1][ parent[j-1][i] ];
				}
			}
			//������ ó���Ѵ�.
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			//������ depth[a] >= depth[b]�� �����ؼ� �����ϰ����Ѵ�. �ϳ��� �����ϴ°� �� ������. �׸��� depth�� Ŭ���� ���.
			if(depth[a] < depth[b]) {
				int temp = a;
				a = b;
				b = temp;
			}
			int diff = depth[a] - depth[b];	//�׻� depth[a] >= depth[b]�� ����
	
			// �������� ���ָ� a�� ���� �̵������ش�. �׷��� b�� depth�� �������ش�. 
			for(int j=0; diff>0; j++) {
				if(diff%2==1) a = parent[j][a];
				diff/=2;
			}

			// a�� b�� ���� �ٸ��� '���ÿ�' ���� ���� ��ŭ �̵���Ų��. 
			if(a != b) {
				for(int k=MAX_DIGIT-1; k>=0; k--) {

					if(parent[k][a]!=-1 && parent[k][a] != parent[k][b]) {
						a = parent[k][a];
						b = parent[k][b];
					}

				}
				a = parent[0][a];
			}
			sb.append(a+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}//==========================================================

}
