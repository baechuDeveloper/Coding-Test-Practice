package MST__Minimm_Spanning_Tree;
import java.util.*;
import java.io.*;

/* ========================================================
 * 황선자씨도 신이라고 푼다. 상관이 없다.
 * 새로 만들어야 할 정신적인 통로의 길이들이 합이 최소가 되게 만들어라.(여기서 앞으로 만들 통로의 길이를 구하면 되서, 이미 만들어진 통로의 길이는 더하지 않아도 된다.)
 * 즉, 모든 정점끼리 연결이 될수있는 건 트리이며, 통로의 전체 길이가 최소가 되려면 싸이클도 피해주면 된다.
 * (싸이클이 있으면 어느 정점 A에서 B로 가는 경우가 더 '짧은 형태'로 도달할지라도 전체적인 합에서는 평생 트리보다 못해진다 평생.) 
 * 트리로 만들때 전제 만드는 비용이 가장 적게 해주어라.(이미 만들어진 통로 비용을 제외시켜주고)
 * 비용은 거리. 좌표는 정수지만 거리는 실수값을 가질 수 있다.  
 * 
 * 그리고 float의 범위로 거리를 감당못하였으니 double로 바꾸어주었다.
 ==========================================================*/

// 1774번 - 우주신과의 교감
public class p4__Universe_God {
	static PriorityQueue<Turnel> pq;
	static God[] god;
	static int[] group;
	static int count = 0;   //현재까지 완성한 간선개수
	static double result=0;	//합의 값
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
		int N = Integer.parseInt(st.nextToken());	//우주신들의 개수, N<=1000
		int M = Integer.parseInt(st.nextToken());	//이미 연결된 신들과의 통로의 개수, M<=1000
		god = new God[N+1];
		group = new int[N+1];
		Arrays.fill(group, -1);
		//황선자씨를 포함하여 우주신들의 좌표
		for(int i=1; i<=N; i++) {	//각 i마다 신의 번호를 부여.
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			god[i] = new God(x, y);
		}
		//이미 연결된 통로 - 여기는 좌표가 아니라 신의 '번호'를 알려준다.
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if( merge(a,b) ) {
				if(++count == N-1) {	//여기서 문제가 있었는데 여기서 다 만들어질수도 있는거라서 해결할 수 있어야 한다. 
					System.out.println("0.00");
					return;
				}
			}
		}
		//각 터널을 만들어 우선순위큐에 넣어준다.
		pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			for(int j=i+1; j<=N; j++) {
				pq.add(new Turnel( i, j, makeDist(i, j) ));
			}
		}
		//크루스칼 알고리즘 
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
		a = find(a);	//자기의 우두머리 신 소환
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//========================================================
}
