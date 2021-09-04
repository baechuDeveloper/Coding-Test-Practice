package MST__Minimm_Spanning_Tree;
import java.util.*;
import java.io.*;

/* ===========================================
 * 별자리를 만드는 최소비용을 구하라. 즉 트리를 만드는 최소비용을 만들어라. 
 * 더 자세히는 그래프에서 만든 트리로서. 최소스패닝트리를 구해라.
 =============================================*/
//4386번 - 별자리 만들기
public class p3__Constellation {
	/*클래스 변수*/
	static int N;			 //별의 개수
	static Star[] star;
	static PriorityQueue<Edge> pq; //간선의 우선순위 큐
	static int[] group;
	//======================================================
	/*별(정점) 자료구조 - 주어진 문제는 정점만 주고 우리보고 간선에 대해 알아서 구해서 MST를 만들라고한다. 대신 정점개수는 100개 이하이다.*/
	static class Star {
		float x, y;	//별의 좌표
		Star(float x, float y){
			this.x=x; this.y=y;
		}
	}//======================================================
	/*간선 자료구조*/
	static class Edge implements Comparable<Edge>{
		int idx1, idx2;
		float weight; //별의 번호들과 가중치
		Edge(int a, int b, float w){
			idx1=a; idx2=b; weight=w;
		}
		public int compareTo(Edge o) {
			return (weight < o.weight? -1 : 1);	//뭐야 내가 더 작아? 그러면 오름차순에서 왼쪽으로 갈겡... 아니면 오른쪽으로 간다
		}
	}//======================================================
	/*main함수*/
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		star = new Star[N+1];	//별의 번호는 1부터 시작하도록 해보자.
		pq = new PriorityQueue<>();
		//별의 개수만큼 별의 좌표를 준다고 함.
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
