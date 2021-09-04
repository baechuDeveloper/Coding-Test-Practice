package MST__Minimm_Spanning_Tree;
import java.util.*;
import java.io.*;

/* ===========================================
 * ���ڸ��� ����� �ּҺ���� ���϶�. �� Ʈ���� ����� �ּҺ���� ������. 
 * �� �ڼ����� �׷������� ���� Ʈ���μ�. �ּҽ��д�Ʈ���� ���ض�.
 =============================================*/
//4386�� - ���ڸ� �����
public class p3__Constellation {
	/*Ŭ���� ����*/
	static int N;			 //���� ����
	static Star[] star;
	static PriorityQueue<Edge> pq; //������ �켱���� ť
	static int[] group;
	//======================================================
	/*��(����) �ڷᱸ�� - �־��� ������ ������ �ְ� �츮���� ������ ���� �˾Ƽ� ���ؼ� MST�� �������Ѵ�. ��� ���������� 100�� �����̴�.*/
	static class Star {
		float x, y;	//���� ��ǥ
		Star(float x, float y){
			this.x=x; this.y=y;
		}
	}//======================================================
	/*���� �ڷᱸ��*/
	static class Edge implements Comparable<Edge>{
		int idx1, idx2;
		float weight; //���� ��ȣ��� ����ġ
		Edge(int a, int b, float w){
			idx1=a; idx2=b; weight=w;
		}
		public int compareTo(Edge o) {
			return (weight < o.weight? -1 : 1);	//���� ���� �� �۾�? �׷��� ������������ �������� ����... �ƴϸ� ���������� ����
		}
	}//======================================================
	/*main�Լ�*/
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		star = new Star[N+1];	//���� ��ȣ�� 1���� �����ϵ��� �غ���.
		pq = new PriorityQueue<>();
		//���� ������ŭ ���� ��ǥ�� �شٰ� ��.
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			float x = Float.parseFloat(st.nextToken());
			float y = Float.parseFloat(st.nextToken());
			star[i] = new Star(x, y);
		}
		for(int i=1; i<=N; i++) {
			Star A = star[i];
			for(int j=i+1; j<=N; j++) {
				Star B = star[j];
				float weight = (float)Math.sqrt( Math.pow(A.x-B.x, 2) + Math.pow(A.y-B.y, 2) );	
				pq.add(new Edge(i, j, weight));
			}
		}
		group = new int[N+1];
		Arrays.fill(group, -1);
		float result=0;
		int count=0;
		while(true) {
			Edge edge = pq.poll();
			if( merge(edge.idx1, edge.idx2) ) {
				result += edge.weight;
				if(++count == N-1)
					break;
			}
		}
		
		System.out.printf("%.2f", result);
	}//======================================================
	static int find(int a) {
		if(group[a]==-1) 
			return a;
		return group[a] = find(group[a]);
	}//======================================================
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//======================================================
}
