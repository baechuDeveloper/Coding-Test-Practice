package Lowest_Common_Ancestor;

import java.util.*;
import java.io.*;
// https://m.blog.naver.com/kks227/220820773477
// 11438�� - LCA 2
public class p2__LCA_2 {
	//Ʈ���� ��Ʈ�� 1�����
	static int N;	//N���� �������� �̷���� Ʈ��		2<= N <= 100_000		1������ �����ϴ� ���
	static int M;	//���� ����� ���� ������ ���� ����	1<= M <= 100_000
	static ArrayList<ArrayList<Integer>> childlist;
	static int[][] parent;
	static int[] depth;
	static int MAX_DIGIT = 18;
	//==========================================================
	//Ʈ�������� ����� �޼ҵ�
	static void makeTree(int root) {
		for(int child : childlist.get(root)) {
			if(depth[child]==-1) {
				parent[0][child] = root;
				depth[child] = depth[root]+1;
				makeTree(child);
			}
		}
	}//==========================================================
	//main�Լ�
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
			childlist.get(a).add(b);
			childlist.get(b).add(a);
		}
		parent = new int[MAX_DIGIT][N+1];
		depth = new int[N+1];
		for(int i=0; i<MAX_DIGIT; i++)
			Arrays.fill(parent[i], -1);
		Arrays.fill(depth, -1);
		depth[1] = 0;
		//�켱 Ʈ�������� �����ϰ� �غ��Ѵ�.
		makeTree(1);
		//��ҹ迭�� �ϼ��Ѵ�.
		for(int j=1; j<MAX_DIGIT; j++) {
			for(int i=1; i<=N; i++) {
				if(parent[j-1][i] != -1)
					parent[j][i] = parent[j-1][ parent[j-1][i] ];
			}
		}
		//������ ó���Ѵ�.
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
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
			 // System.out.println("���� : "+a+" "+b);
			 
			 // �������� ���ָ� a�� ���� �̵������ش�. �׷��� b�� depth�� �������ش�. 
			 for(int j=0; diff>0; j++) {
				 if(diff%2==1) a = parent[j][a];
				 diff/=2;
			 }
			 //System.out.println(depth[a]+" "+depth[b]);
			 
			 // a�� b�� ���� �ٸ��� '���ÿ�' ���� ���� ��ŭ �̵���Ų��. 
			 if(a != b) {
				 for(int k=MAX_DIGIT-1; k>=0; k--) {
					 //'��Ʈ���� ��'�� �����ϴ� �͸� ���������� ���� depth���� ���� �̵�Ƚ���� �ö����� �׻� depth�� ���� ����.
					 //���� ���� �̵��� ���� ��Ұ� ���� �ٸ��� �ϸ�... �׻� �츮�� ���������� �� ���� �ְ� '�������� �ٷ� �Ʒ� ĭ'�� ���Ͽ� ������ ��� �����̰Եȴ�.
					 //parent[k][a] != parent[k][b]��� ���� ���п� ���� �̵��� ���� ������ �������� k�� ���������� �����ϸ鼭 �ᱹ�� �� �ٷ� �� �ؿ� �ܰ���� �̵��� �Ǵ� ��ҹ迭 �����̴� ����� �ȴ�. 
					 //�׷��⿡ �ϴ� ���� ���� ������ �񱳸� �ؼ� �����Ѵ�. ���� �ش� ������ �Ű�ٸ� �ٷ� �� �Ʒ� �������� �ٽ� �����Ͽ� �� ������ �� �ִ°����� ������ �� �� �ְ� �ȴ�.
					 //�� ������ ��ҹ迭�� ���� �ö󰡴� ����̶�� ����� �� ����̶� ����. 
					 //�ű⿡ ������ �� �ѹ��̶� ���� ���� ������ ��� ���� ���� ������ '���� �ʴ´ٴ� ����'�� �ɶ� ���� �� �ܰ躰�� �ö󰡸� �� �ܰ�ȿ��� �� �ö� �� �ְԵǴ� ������.
					 //System.out.println(k+" a: "+a+" b: "+b);
					 if(parent[k][a]!=-1 && parent[k][a] != parent[k][b]) {
						 a = parent[k][a];
						 b = parent[k][b];
					 }
					 //�̷��� �ؼ� �־����� ��� �ö� �� �ִ� ���� '��Ʈ'�� ��ġ 1���̴�.	'-1'�� ��Ʈ�� �θ�� ���� ���̴�.
				 }
				 //�� �ٷ� �������� �� �Ʒ����� ���������� �ٷ� ���ܰ谡 ���� ����� ������ �ȴ�.
				 a = parent[0][a];
			 }
			 sb.append(a+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}//==========================================================

}
