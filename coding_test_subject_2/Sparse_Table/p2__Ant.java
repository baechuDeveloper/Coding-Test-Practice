package Sparse_Table;
import java.util.*;
import java.io.*;

/**
 * �켱 �߿��� ���� ��������� �ٸ������� ���� ����� �׻� �����ϴ� ��� ��. 
 * �׸��� �־��� ���� ������ n-1��� ������ �� �׷����� 'Ʈ������' �̴�.

 * ���⼭ ��������� 1�̶�� ������ ���ҷ��� �Ѵٸ�, 1�� Ʈ���� ��Ʈ�� �θ� �� ���̴�. 
 
 * cave_dist�� ���� �Ÿ���ŭ �̵����� �� �� �Ÿ����� �˷��ִµ�, �ƹ��� �ִ� ������ �ᱹ Ʈ���� ��Ʈ���� �������� �� ���� ������ �ְ� ������� ���� ������ ��� �� ���� ������ ���̴�. 
 * ������ ���� �������� ��Ʈ�� ������ �Ÿ��纸�� �۾����� �� ���� ��Ʈ���� �Ʒ��� ��尡 �ش� ������ �̵���ŭ�� ���μ� ������ �˷��ش�. 
 
 * �̷��� ���� �̿��ؼ� ���� �������� 28�̶�� �ִ������� �Ÿ����� 20�̶� �� ��, �ش� ���̴� ��Ʈ���� ����� ������ �� ������ �� �� �ִ�.
 * �ݴ�� ���� �������� 14��� �� ��, �ִ������� �Ÿ����� 20�� ��Ʈ���� �������� ���Ѵ�. �׷��ٸ� �� ������ �ٿ��� �Ÿ����� 14���� �۰ų� ���� ���� ã�´�.
 * ������� ���������� �ش� ������ �Ÿ����� 8�� ���� ã�Ҵ�. �׷��ٸ� '���� ������ ��ġ'���� �ش� ������ �Ÿ� 8��ŭ �̵��� �ؼ� '�ش� ������ ��ġk'�� �����ϰ�,
 * ���� �Ÿ��� 6��ŭ�� '�ش� ������ ��ġk'�� '������ ��ġk'�� �ξ��� ���� �Ͽ� ������ ������ ������ ����h��ŭ�� ����Ѵ�. �̶� �ش� ����h�� �̹� k��ŭ�� ������ ���������� 
 * ��� k���� Ŭ �� �� ����. �ִ������� 18�϶� k�� 14��� h�� 14���ٴ� ����� Ŭ ���� ���� �� �Ʒ����� ã�ư� ���� �Ÿ� 8�� �ش��ϴ� ���� �Ÿ����� ã���� �� ���̴�.
 
 * �Ѱ��� �� k�� ���� ���ø� ���� �ְڴ�. ������ ���� �ܼ��� �ش� ��ġ�� �����Ѵٴ� ���� ���������� �˾ƴ޶�. ������ Ǯ� ���� 256(k=8)�̶��� �� �������� ������ ��ġ�� 255�� �ش��ϴ� �Ÿ���� �̸� �˰��ִ�����.
 * �׷��� 255�� �����ҷ��� ��� �Ǵ��� �۵���� ���ຸ�ڴ�. �켱 128(k=7)�� �ش��ϴ� ���� �����ִ�. ���� ���� �� 127�̵ȴ�. �̴� ���� 128�ε� ������ �ȵǴ� 64(k=6)�� ������ �Ѵ�.
 * ���� 63�� ���� �̷��� ����Ͽ� 31...15...7...3...1 �̷��� �����Ͽ� ���� �� �� �ִ�. 
 * �� ������ �غ��� ���� ���캼 �������� �׻� ���������� �������� ���� ������ �����ϰ� �ȴ�. �̹� �׺��� ���ܰ�k=8���� �ȵǹǷ� �ٷ� �Ʒ��ܰ�k=7�� �̹� �ѹ� �ؼ� �� �ٽ� k=7�� �� �� �־��ٸ� 
 * k=8�� �Ǿ��� �״� ���̴�. "���⼭ ���ϰ��� �ϴ°� ������ ���� �������� ������ �ؼ� ���ϴ� ��ġ�� ����� ���� �� �� �ִٴ� ��"�̴�.    
 
 * ���� �������� ���� ���� �������� �Ѱ��ε� �츮�� Ǯ���� �Ѱ� ������ �������� ���� �� ���ִ� ���� ��ġ�� ������� ���� �˾ƺ��� ���̴�. 
 * �̵� ���������� �������� �Һ��ذ��� ��ġ�� �̵��ϰ� �� ���ο� ��ġ���� ������ �ִ� ����� ���� ������ ���̴�. ���� �������� �����ؼ� ��� ����k���� �����ϸ� �� ������ �ش��ϴ� 
 * ������ �����ϰ� �ٽ� �� ������ ����k���� ���� ���¿��� ã�ƺ��� �Ǵ� ���̴�. ���� �Ȱ��� ������ ������ ���� �������� ������ �ؼ� ���� �������� ������ �� �ִ� ���� ���� �ȴ�.
 
 * �� �ִ� �������� �����ؼ� �ٿ��� �ش�Ǵ� ���� �Ǹ� �̵��ϰ� �������� �и� ������ �������� ���� ������ ������ �����Ƿ�
 * �ٷ� �̾ �ش� ������ �Ʒ����� ������ �ϴ� �� ����� ��ҹ迭�� �ִ�� �����ϰ� ����� �� �ִ� ����ΰ� ����.
 **/

// 14942�� - ����
public class p2__Ant {

	static int n;				//���� ����	1 <= n <= 100_000
	static int[] energy;			//�� �濡 �����ϴ� ���̰� �����ϰ� �ִ� ������ ��	(1�� ���̺��� n�� ���̱���) 
	static ArrayList<ArrayList<Edge>> childNode;	//������ �پ��ִ� ������ �˷���.
	static int[][] parent;		// [ ���� ][ �����ϴ� �� ] = �����ϴ� ������ ����� �̵��� ���� �� ������ ����
	static int[][] cave_dist;	// [ ���� ][ �����ϴ� �� ] = �����ϴ� ������ ����� �̵��� ���� �� ������ �Ÿ�
	static int MAX_DIGIT = 18;	// 2^17 = 1024 * 128 = �� 1000 * 128 = ��128_000 �̴�. n�� �ִ� 100_000�̴� Ŀ���� �ȴ�.
	//=======================================================
	//������ ���� ���� ���� �Ÿ��� �˷��ְ� �ϴ� �ڷᱸ��
	static class Edge{
		int next, cost;
		Edge(int b, int c){
			 next=b; cost=c;
		}
	}//=======================================================
	//Tree������ �� ����� �θ� ������ �˾Ƴ��� parent�迭�� �ϼ��Ѵ�.
	static void makeTree(int root) {
		ArrayList<Edge> childlist = childNode.get(root);
		for(Edge edge : childlist) {
			int child = edge.next;
			if(parent[0][child]==0) {	//ó�� �湮���� �� �� ���� ��尡 �ɰ���
				parent[0][child] = root;	//���� ���� �θ�� ����.
				cave_dist[0][child] = edge.cost;		
				makeTree(child);
			}
		}
	}//=======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		//������ �� �Է�
		n = Integer.parseInt(br.readLine());

		//�� ���̰� ���� ������ �Է�
		energy = new int[n+1];
		for(int i=1; i<=n; i++) 
			energy[i] = Integer.parseInt(br.readLine());

		//�� ������ ������ �Է�
		childNode = new ArrayList<>();
		for(int i=0; i<=n; i++)
			childNode.add(new ArrayList<>());
		for(int i=0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			childNode.get(a).add(new Edge(b, c));
			childNode.get(b).add(new Edge(a, c));
		}

		//�θ� ���� ���غ���. 
		parent = new int[MAX_DIGIT][n+1];
		cave_dist = new int[MAX_DIGIT][n+1];
		parent[0][1] = -1;	//Tree�۾��� ������ parent�� -1�� ��尡 1�� �����̸� �̰� ��Ʈ�̴�.
		makeTree(1);		//��Ʈ�� 1���� Ʈ�������� �θ� �˷���. �̴� ���ǿ��� Ʈ����� �־��⿡ ����⸸ �ϸ� ��.

		//��ü ��ҹ迭�� ����Ѵ�. 
		for(int j=1; j<MAX_DIGIT; j++) {
			for(int i=1; i<=n; i++) {
				if( parent[j-1][i] != -1 ) { //�迭�� ���� -1�̸� �ȵǴ� �͵� �ְ�, �̹� �߰����� -1 �Ѿ�� ����� �ǹ��ε� �׷��ٸ� �� ���� ���ص� �ȴ�. 
					parent[j][i] = parent[ j-1 ][ parent[j-1][i] ];	//i���� j-1�� �̵��� ��. �� ������ �ٽ� j-1���� �̵��� �ϸ�. 2��j-1�� ���� 2��j-1�� ���� 2��j���� �Ǵ°��̴�.
					cave_dist[j][i] = cave_dist[j-1][i] + cave_dist[j-1][ parent[j-1][i] ];
					//2^(j-1)�� ���� �� �ڸ����� �� �Ÿ��� �̵��� ������ j-1�� �� �Ÿ��� ���ϰ� �ȴ�. j-1�� �� ������ ���� ���صξ���. 
					//���� i���� �����ؼ��� j-1���� �Ÿ��� j-1�� ������ �� ������ �ٽ� j-1���� �Ѱ� ���Ӱ� ������ ���� �Ÿ��� ���̴�.
				}
				//parent�� ������ ��� ������ ��ȣ�� �����ȿ��� ��� �̷������ �ȴ�. ���� ���ܰ� �ٷ� -1�̴�. �׸��� 0�� �����ȿ� ���� �� �� ����. �ƹ��� �R�R���ص� 0�� ����Ű�� ���� �ƹ��� �����.
				//-1�� �θ�� ó�� �а��� �ٷ� 1�������̴�. ���ʴ�� ����Ǵ� �������� �Ǹ��� 1�������� �θ� -1�̸� �����ε� ��� ����� ������ �ǵ� ������ ���� ������Ű�� �Ѵ�.
				//�׸��� ��Ʈ�� ���� ����� ����(���ǻ�A)�� �� �������� 1���� ����Ű�� �θ� -1�� �ǰ� �ȴ�. �׷��� �� ����� ����A�� �� ������ ����� ������ ������ ���� �����ϰԵȴ�.
				//�̷��� ������ ��Ʈ���� �� �������� �� ����(���ǻ�B)�� �����ϴٰ� ����Ű�� �ɰ��� A���� �� �ϳ��� �ɰ��ε�, 
				//A�� �����״ٸ�  ��������� 1����尡 �� ������ ������ŭ�� �����ϰų��� A�� ��尡 �� �����ؼ� ���� ��� -1�� ����Ű�� ������ �����ϰ� �Ǿ� �� B�鵵 -1�� �����ϰ� �� ���ķδ� ������ ���� �����ϵ��� �ϸ�ȴ�.
				//�̸� ���� ���� ���鿡�Ե� ���������鼭 '��Ʈ�� �Ѿ�� ������ŭ�� �Ÿ����� �Դٸ� ���ʴ�� ������ �ؿͼ� ��Ʈ�� �ش��ϴ� �Ÿ���ŭ�� �˷��ְ�'
				//�����ε� �� ���� ��� ���ӵǾ� �����ϰ� ��������. 
				//���� �����ε� -1���� ��� �����ش�.
				else {	
					//���ʴ�� ����Ǵ� ������ ���� -1�� �ᱹ '���� ���°� ��Ʈ�� �ش��ϴ� ��ġ'���� �˷��ִ� ���°� �ȴ�. ���� ������ �ش��ϴ� ��ġ�� ������ �Ƶ��� ���� �ö󰥰� ������ �� else���п� �Ѿ ���¿����� -1�� �Ÿ��� �����߰�, 
					//���� if�������� ��Ʈ�� �����ϴ� �Ÿ���ŭ�� ����ϰ� -1�� ������ �Ѵ� ��ġ�� �ƴ� ��Ȯ�ϰ� �������� ���� �ݵ�� �����ϱ⿡ ������ ������ �ȴ�. �׸��� ��Ȯ�� �����ϰ� �������� �ش� ���̴� else���п� ū ������ �Ǿ ������ �ȴ�.
					//�̸��� ��� �� �� k��ŭ �������� �ش� ��忡���� �׸�ŭ�� k�� ��� ��ó���� ���°�, �� ���� ��ó���� ������ ��Ʈ�� �����ѰŶ�� ��Ʈ�� ������ �縸ŭ�� �������ֱ⶧���̴�.  
					parent[j][i] = -1;
					cave_dist[j][i] = cave_dist[j-1][i];
				}
			}
		}
		
		//1�������� �׻� 1�������̴� 
		sb.append(1+"\n");
		//�� ���̵��� ������ �� �ִ� ������ ��ġ�� ã�´�.
		for(int ant=2; ant<=n; ant++) {
			//System.out.println("���� ���� : "+ant);
			int now = ant; //���� ���̰� ��ġ�� ����ȣ
			int now_energy = energy[now];	//���� �����ִ� ������ ������
			
			for(int k=MAX_DIGIT-1; k>=0; k--) {
				int need_energy = cave_dist[k][now]; //���� ������ �����ϱ����� �ʿ��� ������
				//System.out.print("��ġ : "+now+" ���� : "+now_energy+" �ʿ� : "+need_energy);
				//���� ������ŭ�� ���� �����ִ� �������� ������ �� ���� �� ���´�.
				if(need_energy <= now_energy) {
					now_energy -= need_energy;	//�ش� ������ �����Ҹ�ŭ�� �������� �Һ��Ѵ�. 
					now = parent[k][now];	//�ش� ������ŭ �̵��ؼ� ��ġ�� ���� �����ؼ� ����
				}
				if(now_energy<=0  || now==-1) {	//�̹� �������� �� ���ų�, ��Ʈ�� �ش��ϴ� ��ҷ� �����ߴٸ� ���̻� ���� �� �ʿ䰡 ����.
					if(now==-1) now=1;
					break;	
				}
			}
			//now�� �������� �����ִ°��� �ᱹ ���� �ִ� �������� �̵��� �� �ִ� ���� ���� ��ġ�� �ȴ�. 
			sb.append(now+"\n");
		}
		bw.write(sb.toString());
		bw.flush();

	}//=======================================================

}