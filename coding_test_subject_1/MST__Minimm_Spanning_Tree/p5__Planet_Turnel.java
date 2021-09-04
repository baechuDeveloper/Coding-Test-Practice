package MST__Minimm_Spanning_Tree;

import java.io.*;
import java.util.*;
// 2887번 - 행성 터널
/* https://jellyinghead.tistory.com/84
 * 행성 간의 모든 간선을 구하려면 대략 100,000^2의 시간 복잡도가 걸립니다. 시간초과를 예상할 수 있는 크기입니다.
 * 굳이 모든 행성 간의 거리를 구할 필요는 없습니다. 현재 행성에서 가까운 행성간의 거리만 구하면 됩니다. 간선의 가중치는 x, y, z의 최소 값입니다. 
 * 즉, 어떤 행성의 최소 가중치의 간선은 x, y, z별로 정렬한 후 앞, 뒤의 값을 비교하여 구할 수 있습니다.
 * 이해가 되지 않는다면 코드를 보며 이해하시길 바랍니다.
 * */
public class p5__Planet_Turnel {
	//클래스변수
	static int N;	//행성 개수 	1<= N <=100_000
	static Planet[] p;				//각 행성의 정보를 담아둠.
	static ArrayList<Planet> sortx, sorty, sortz;	// 각각 x,y,z를 기준으로 정렬을 해둔 리스트들. 전체적인 내용물은 서로 같지만 그 순서가 다르게 되어있다.
	static PriorityQueue<Edge> pq;	//크루스칼을 위해 준비해둔 우선순위 큐
	static int[] group;				//크루스칼의 유니온파인드를 위한 배열
	//========================================================
	/**정점을 나타내는 행성 클래스*/
	static class Planet {
		int idx, x, y, z;
		Planet(int i, int a, int b, int c) {
			idx=i; x=a; y=b; z=c;
		}
	}/**========================================================*/
	/**간선 클래스*/
	static class Edge implements Comparable<Edge> {
		int p1, p2, cost;
		Edge(int a, int b, int c){
			p1=a; p2=b; cost=c;
		}
		public int compareTo(Edge o) {	
			return cost-o.cost;	//오름차순으로 정렬 
		}
	}/**========================================================*/
	//행성간의 cost를 알려주는 함수
	static int min_cost(Planet a, Planet b) {
		int abs_x = Math.abs(a.x-b.x);
		int abs_y = Math.abs(a.y-b.y);
		int abs_z = Math.abs(a.z-b.z);
		return Math.min( Math.min(abs_x, abs_y), abs_z);
	}//========================================================
	//유니온파인드 탐색함수
	static int groupFind(int a) {
		if(group[a] == -1) return a;
		return group[a] = groupFind(group[a]);
	}//========================================================
	//유니온파인드 머지함수
	static boolean groupMerge(int a, int b) {
		a = groupFind(a);
		b = groupFind(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//========================================================
	//main함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력 
		N = Integer.parseInt(br.readLine());
		p = new Planet[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			p[i] = new Planet(i, x, y, z);
		}

		//정렬
		sortx = new ArrayList<>( Arrays.asList(p) );
		sorty = new ArrayList<>( Arrays.asList(p) );
		sortz = new ArrayList<>( Arrays.asList(p) );
		Collections.sort(sortx, new Comparator<Planet>() {	//comparator를 즉시 만들어서 이용하는 방법이 필요한 순간. comparator에 들어갈 내용을 compare함수를 통해 꾸며주면 된다.
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
		
		//크루스칼을 위해 간선의 정보를 담아둔다. 이때 x,y,z로 정렬한 상태라면 i와 i+1 간의 사이의 거리가 x,y,z 중 가장 가까운 행성의 거리를 알려주게됩니다. 이렇게 가장 가까운 행성의 거리를 알려주면 그 행성에 대해 x,y,z 중에 나온것 마다 더 고려해볼 필요가 없어집니다.
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
		
		//크루스칼 알고리즘 시작
		group = new int[N];
		Arrays.fill(group, -1);
		long sum = 0;	//신장트리의 비용
		int count = 0;	//확보한 간선개수

		while(true) {
			Edge now = pq.poll();
			if( groupMerge(now.p1, now.p2) ) { //해당 간선은 간택이 되었고, 동시에 합쳐진다. 
				sum += now.cost;
				if(++count == N-1)	//전체 원하는 간선의 개수만큼 확보했다면 종료를 한다.
					break;
			}
		}
		
		System.out.println(sum);
	}//========================================================

}
