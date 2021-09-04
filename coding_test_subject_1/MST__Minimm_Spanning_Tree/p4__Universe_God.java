package MST__Minimm_Spanning_Tree;
import java.util.*;
import java.io.*;

/* ========================================================
 * Ȳ���ھ��� ���̶�� Ǭ��. ����� ����.
 * ���� ������ �� �������� ����� ���̵��� ���� �ּҰ� �ǰ� ������.(���⼭ ������ ���� ����� ���̸� ���ϸ� �Ǽ�, �̹� ������� ����� ���̴� ������ �ʾƵ� �ȴ�.)
 * ��, ��� �������� ������ �ɼ��ִ� �� Ʈ���̸�, ����� ��ü ���̰� �ּҰ� �Ƿ��� ����Ŭ�� �����ָ� �ȴ�.
 * (����Ŭ�� ������ ��� ���� A���� B�� ���� ��찡 �� 'ª�� ����'�� ���������� ��ü���� �տ����� ��� Ʈ������ �������� ���.) 
 * Ʈ���� ���鶧 ���� ����� ����� ���� ���� ���־��.(�̹� ������� ��� ����� ���ܽ����ְ�)
 * ����� �Ÿ�. ��ǥ�� �������� �Ÿ��� �Ǽ����� ���� �� �ִ�.  
 * 
 * �׸��� float�� ������ �Ÿ��� ������Ͽ����� double�� �ٲپ��־���.
 ==========================================================*/

// 1774�� - ���ֽŰ��� ����
public class p4__Universe_God {
	static PriorityQueue<Turnel> pq;
	static God[] god;
	static int[] group;
	static int count = 0;   //������� �ϼ��� ��������
	static double result=0;	//���� ��
	//========================================================
	static class God {
		int x, y;
		God(int x, int y){
			this.x=x; this.y=y;
		}
	}//========================================================
	static class Turnel implements Comparable<Turnel> {
		int idx1, idx2;
		double dist;
		Turnel(int a, int b, double distance){
			idx1=a; idx2=b; dist=distance;
		}
		public int compareTo(Turnel o) {
			return (dist<o.dist ? -1 : 1);
		}
	}//========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//���ֽŵ��� ����, N<=1000
		int M = Integer.parseInt(st.nextToken());	//�̹� ����� �ŵ���� ����� ����, M<=1000
		god = new God[N+1];
		group = new int[N+1];
		Arrays.fill(group, -1);
		//Ȳ���ھ��� �����Ͽ� ���ֽŵ��� ��ǥ
		for(int i=1; i<=N; i++) {	//�� i���� ���� ��ȣ�� �ο�.
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			god[i] = new God(x, y);
		}
		//�̹� ����� ��� - ����� ��ǥ�� �ƴ϶� ���� '��ȣ'�� �˷��ش�.
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if( merge(a,b) ) {
				if(++count == N-1) {	//���⼭ ������ �־��µ� ���⼭ �� ����������� �ִ°Ŷ� �ذ��� �� �־�� �Ѵ�. 
					System.out.println("0.00");
					return;
				}
			}
		}
		//�� �ͳ��� ����� �켱����ť�� �־��ش�.
		pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			for(int j=i+1; j<=N; j++) {
				pq.add(new Turnel( i, j, makeDist(i, j) ));
			}
		}
		//ũ�罺Į �˰��� 
		while(true) {
			Turnel now = pq.poll();
			if( merge(now.idx1, now.idx2) ) {
				result += now.dist;
				if(++count == N-1) {
					break;
				}
			}
		}
		System.out.printf("%.2f", result);
	}//========================================================
	static double makeDist(int a, int b) {
		return Math.sqrt( Math.pow(god[a].x-god[b].x, 2) + Math.pow(god[a].y-god[b].y, 2) );
	}//========================================================
	static int find(int a) {
		if(group[a]==-1) return a;
		return group[a] = find(group[a]);
	}//========================================================
	static boolean merge(int a, int b) {
		a = find(a);	//�ڱ��� ��θӸ� �� ��ȯ
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//========================================================
}
