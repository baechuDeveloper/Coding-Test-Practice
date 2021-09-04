package MST__Minimm_Spanning_Tree;

import java.io.*;
import java.util.*;

/**=================================================================
 * ũ�罺Į �˰������� �۵������ �̷����ϴ�. 
 * 1. �������� ����ġ ������ �������� �����ϰ� �������� �� ������Ʈ(�׷�)�� �ʱ�ȭ�Ѵ�.
 * 2. �������� �����鼭 ���� ������ ������ ������Ʈ�� ����Ǿ� ���� ������ ������ �̰� �����Ѵ�.
 * 3. ���� V-1���� ������ ��, �� ������� �������� �̷�� �׷����� MST�̴�. 
 * ������ �Ѵٰ� �Ҷ� ����ġ �������� �Ѵٴµ�, ��� �������� ���õȰ� �����ϴ�. ������������ �ص� �ǰ�, ������������ �ص� �˴ϴ�. 
 * ���п� ����� ���� �� �ִ� MST�� ������ �ϼ� �ֽ��ϴ�. ���� ����ġ�� ���� ���� ���Դϴ�.  
  
 * �׷��� ũ�罺Į�� �⺻�� '����'�� �߽����� �����ϴ� ���̴�. 
 * �����̱⿡ 2���� ������ ���� 2���� �������� ������ ������µ�, ũ�罺Į������ 2���� ������ ������ �ϳ��� ������ �������ָ� �˴ϴ�. 
 * �ʱ⿡�� �� ������ �ڽŸ��� �׷����� ����(Ȥ�� � �׷쿡�� ������ �ʾҴ� �ؼ� 0Ȥ��-1�� ���ϰ� ����)������, ���� ������ ���İ��� �������� �ٸ� �׷��� ������ �Ǿ� �ᱹ �ϳ��� �׷��� �ɶ����� �����ϸ� �˴ϴ�.
 * ������ ����ġ�� ���� ���� ���� ���� �Ŀ� ����Ŭ�� ������ �ʵ��� ��� ��带 �湮�� �� �ֵ��� ������ �ȴ�.
 ===================================================================**/
// 1197�� - �ּ� ���д� Ʈ�� 
public class p2__MST {
	/*Ŭ���� ����*/
	static PriorityQueue<Edge> pq;	// ����ġ�� ���� ������ ������� ������
	static int[] group;				// �� ������ ���ؼ� ������ �׷��� ������. ���Ͽ� ���ε� �迭�� ��.
	//=======================================================
	/*���� �ڷᱸ��*/  		//MST�� �ƴ� �ִܰ�ο����� ���ͽ�Ʈ��� ���� 'Node'�� ������ ���ؼ� �ڷᱸ���� ����� 2���� �������� ������ �����ߴ�.
	static class Edge implements Comparable<Edge>{
		int idx1, idx2, weight;
		Edge(int a, int b, int w){
			idx1=a; idx2=b; weight=w;
		}
		public int compareTo(Edge o) {	//���� ����ġ�� ���� �� ó���ϴ� ����� ������ ����. �׷��� ������� -1�̵� 1�� �൵�ȴ�.
			if(weight<o.weight) return -1;
			else return 1;
		}
	}//=======================================================
	/*���Ͽ� ���ε��� find����*/		
	static int groupFind(int a) {	
		if(group[a]==-1) return a;	//���� � �׷쿡�� ������ �ʾ����� �ڱ� �ڽ��� �������ش�. �׷� �ڱ� �ڽ����� �׷��� �����ȴ�. ���� �������� �����Ѱ� �̳༮�̶�� ���̶� ������ � ���̵� �̳༮�� �츮�� �������� �˷��ִ� ���̴�.
		return group[a] = groupFind(group[a]);	//��׷��̶� ���ߴٸ�(�ڱ��ڽŵ� ����) ���� �� �׷��� ���� ���� ������ ã���� ����Ѵ�.
		//(�� �׷��� �ϳ��� �׷��� ���� �����ְ� �����ִٸ� ���̻� �ٲ��� �ʾƵ� �ȴٰ� �����߾��� �ֳ��ϸ� �̷��� �ڽ��� ���� �׷쿡 ����, �� �׷��� ���� �� �ٸ� �׷��� �־ �̷��� �� ���ϰ��谡 �����ȴ�.
		//��� MST�� ��ġ�� �׷��� ���ϰ��谡 ���������� ��Ÿ���� �ȴ�.
		//�׸��� ���� ���� �ִ� �׷��� �ְ��� ������ �Ǹ�, ������ �׷��� �ٷ� �Ѵܰ� ���� �ٸ� �׷�������.. �� ������ �ش� �ְ��� ������� �Ǿ ��ΰ� �� �׷���忡�� ���ԵǾ��ٰ� �����ȴ�.)
	
		//������ ���⼭�� �ֱٿ� ���Ѵ�� ������ �������־ Ȥ���� �ݺ��Ǵ� ��������� �����̳��� ���̵��� ���־���. �׷��� �������� �¾Ƶ� ��� �׷��� �� �������� �ʰ� ������ ���� �ٿ��־���.
		//��� MST�� �������� ����׷����� ������ �ȵ� ������ �ٽ� find�� �ϸ� ���� ���� ���忡�� ���� ��������. ������ �����������̱� �����̴�.
	}//=======================================================
	/*���Ͽ� ���ε��� merge����*/
	static boolean groupMerge(int a, int b) {
		a = groupFind(a);
		b = groupFind(b);
		if(a==b) return false;	//������ �׷��� ���ٸ� ��ü���� �ʾƵ� �ȴٰ� �˷��ش�.
		group[b] = a;			//�̰��� �Դٸ� ���߿� �Ѹ��� �� ���� �׷��̶�� ���� �ȴ�. �׷��� �ϸ� �ϳ��� ���Եȴ�.
		return true;			//���� ���������� �˸���.
	}//=======================================================
	/*main�Լ�*/
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Edge(a, b, c));
		}

		/* ũ�罺Į �˰����� */
		group = new int[V+1];
		Arrays.fill(group, -1);
		int result=0, count=0;	//result : ����ġ��, count:���� ���� �� 
		
		while(true) {
			Edge edge = pq.poll(); 
			if( groupMerge(edge.idx1, edge.idx2) ) {
				result += edge.weight; 
				if(++count == V-1) //Ʈ���� ������Ҹ�ŭ ������ Ȯ���ߴٸ� ����.
					break;
			}
		}/*==ũ�罺Į �˰����� ����==*/
		
		for(int i=1; i<=V; i++)
			System.out.print(group[i]+" ");
		
		System.out.println(result);	
	}//=======================================================

}