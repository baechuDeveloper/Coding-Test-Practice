package MST__Minimm_Spanning_Tree;

import java.io.*;
import java.util.*;
// 2887�� - �༺ �ͳ�
/* https://jellyinghead.tistory.com/84
 * �༺ ���� ��� ������ ���Ϸ��� �뷫 100,000^2�� �ð� ���⵵�� �ɸ��ϴ�. �ð��ʰ��� ������ �� �ִ� ũ���Դϴ�.
 * ���� ��� �༺ ���� �Ÿ��� ���� �ʿ�� �����ϴ�. ���� �༺���� ����� �༺���� �Ÿ��� ���ϸ� �˴ϴ�. ������ ����ġ�� x, y, z�� �ּ� ���Դϴ�. 
 * ��, � �༺�� �ּ� ����ġ�� ������ x, y, z���� ������ �� ��, ���� ���� ���Ͽ� ���� �� �ֽ��ϴ�.
 * ���ذ� ���� �ʴ´ٸ� �ڵ带 ���� �����Ͻñ� �ٶ��ϴ�.
 * */
public class p5__Planet_Turnel {
	//Ŭ��������
	static int N;	//�༺ ���� 	1<= N <=100_000
	static Planet[] p;				//�� �༺�� ������ ��Ƶ�.
	static ArrayList<Planet> sortx, sorty, sortz;	// ���� x,y,z�� �������� ������ �ص� ����Ʈ��. ��ü���� ���빰�� ���� ������ �� ������ �ٸ��� �Ǿ��ִ�.
	static PriorityQueue<Edge> pq;	//ũ�罺Į�� ���� �غ��ص� �켱���� ť
	static int[] group;				//ũ�罺Į�� ���Ͽ����ε带 ���� �迭
	//========================================================
	/**������ ��Ÿ���� �༺ Ŭ����*/
	static class Planet {
		int idx, x, y, z;
		Planet(int i, int a, int b, int c) {
			idx=i; x=a; y=b; z=c;
		}
	}/**========================================================*/
	/**���� Ŭ����*/
	static class Edge implements Comparable<Edge> {
		int p1, p2, cost;
		Edge(int a, int b, int c){
			p1=a; p2=b; cost=c;
		}
		public int compareTo(Edge o) {	
			return cost-o.cost;	//������������ ���� 
		}
	}/**========================================================*/
	//�༺���� cost�� �˷��ִ� �Լ�
	static int min_cost(Planet a, Planet b) {
		int abs_x = Math.abs(a.x-b.x);
		int abs_y = Math.abs(a.y-b.y);
		int abs_z = Math.abs(a.z-b.z);
		return Math.min( Math.min(abs_x, abs_y), abs_z);
	}//========================================================
	//���Ͽ����ε� Ž���Լ�
	static int groupFind(int a) {
		if(group[a] == -1) return a;
		return group[a] = groupFind(group[a]);
	}//========================================================
	//���Ͽ����ε� �����Լ�
	static boolean groupMerge(int a, int b) {
		a = groupFind(a);
		b = groupFind(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//========================================================
	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//�Է� 
		N = Integer.parseInt(br.readLine());
		p = new Planet[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			p[i] = new Planet(i, x, y, z);
		}

		//����
		sortx = new ArrayList<>( Arrays.asList(p) );
		sorty = new ArrayList<>( Arrays.asList(p) );
		sortz = new ArrayList<>( Arrays.asList(p) );
		Collections.sort(sortx, new Comparator<Planet>() {	//comparator�� ��� ���� �̿��ϴ� ����� �ʿ��� ����. comparator�� �� ������ compare�Լ��� ���� �ٸ��ָ� �ȴ�.
			public int compare(Planet a, Planet b) {
				return a.x - b.x;
			}
		});
		Collections.sort(sorty, new Comparator<Planet>() {
			public int compare(Planet a, Planet b) {
				return a.y - b.y;
			}
		});
		Collections.sort(sortz, new Comparator<Planet>() {
			public int compare(Planet a, Planet b) {
				return a.z - b.z;
			}
		});
		
		//ũ�罺Į�� ���� ������ ������ ��Ƶд�. �̶� x,y,z�� ������ ���¶�� i�� i+1 ���� ������ �Ÿ��� x,y,z �� ���� ����� �༺�� �Ÿ��� �˷��ְԵ˴ϴ�. �̷��� ���� ����� �༺�� �Ÿ��� �˷��ָ� �� �༺�� ���� x,y,z �߿� ���°� ���� �� ����غ� �ʿ䰡 �������ϴ�.
		pq = new PriorityQueue<>();
		Planet p1, p2;
		for(int i=0; i<N-1; i++) {
			p1 = sortx.get(i);
			p2 = sortx.get(i+1);
			pq.add( new Edge(p1.idx, p2.idx, min_cost(p1, p2)) );
			p1 = sorty.get(i);
			p2 = sorty.get(i+1);
			pq.add( new Edge(p1.idx, p2.idx, min_cost(p1, p2)) );
			p1 = sortz.get(i);
			p2 = sortz.get(i+1);
			pq.add( new Edge(p1.idx, p2.idx, min_cost(p1, p2)) );
		}
		
		//ũ�罺Į �˰��� ����
		group = new int[N];
		Arrays.fill(group, -1);
		long sum = 0;	//����Ʈ���� ���
		int count = 0;	//Ȯ���� ��������

		while(true) {
			Edge now = pq.poll();
			if( groupMerge(now.p1, now.p2) ) { //�ش� ������ ������ �Ǿ���, ���ÿ� ��������. 
				sum += now.cost;
				if(++count == N-1)	//��ü ���ϴ� ������ ������ŭ Ȯ���ߴٸ� ���Ḧ �Ѵ�.
					break;
			}
		}
		
		System.out.println(sum);
	}//========================================================

}
