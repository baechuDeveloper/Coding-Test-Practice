package MST__Minimm_Spanning_Tree;

import java.io.*;
import java.util.*;
// 17472번 - 다리 만들기 2
public class p6__Make_Bridge_2 {
	//클래스 변수
	static int N, M;	//세로, 가로		1<= N, M <=10
	static int island_count = 0;	//2<= 섬의 개수 <=6
	static int[][] arr;				//섬의 형태	[x위치][y위치] = 해당 위치 섬의 번호가 나타날거임 
	static int[][] road_check;		//[섬의 번호][섬의 번호] = 섬과 섬 사이의 도로비용 값. 
	static PriorityQueue<Bridge> pq;
	static int[] group;
	static int[][] cal = { {0, -1}, {-1, 0}, {0, 1}, {1, 0} };
	static int INF = 100;
	//==================================================
	//간선 자료구조
	static class Bridge implements Comparable<Bridge>{
		int i1, i2, cost;
		Bridge(int a, int b, int c){
			i1=a; i2=b; cost=c;
		}
		public int compareTo(Bridge o) {
			return cost - o.cost;
		}
	}//==================================================
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
	//지도에서 발견된 섬에 번호를 지정
	static void find_island(int x, int y) {
		//BFS하게 4방향으로 퍼져나가게 해서 작업한다. 
		arr[x][y] = island_count;

		for(int i=0; i<4; i++) {
			int next_x = x+cal[i][0];
			int next_y = y+cal[i][1];
			if(0<=next_x && next_x<N && 0<=next_y && next_y<M) {
				if(arr[next_x][next_y]==0)
					find_island(next_x, next_y);
			}
		}
	}//==================================================
	//섬과 섬끼리 다리를 만들게 하는 작업
	static void make_bridge(int p1, int p2, int cost) {
		if(cost < road_check[p1][p2]) {	//[p2][p1]도 결국 [p1][p2]와 일심동체이니 하나만 체크해도 된다. 만약 이미 만들어져 있는데 더 길이가 줄었는지 확인할 수 있다.
			road_check[p1][p2] = cost;
			road_check[p2][p1] = cost;
			pq.add(new Bridge(p1, p2, cost));
		}
	}//==================================================
	//main함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];	//[세로][가로]
		for(int i=0; i<N; i++)
			Arrays.fill(arr[i], -1);

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				arr[i][j] += Integer.parseInt(st.nextToken());	
		}
		//for(int i=0; i<N; i++) { for(int j=0; j<M; j++) System.out.print(arr[i][j]+" "); System.out.println(); } System.out.println();

		//섬 정보를 입력 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0) {
					island_count++;	//새로운 섬을 발견했으니 개수를 늘려준다. 이 번호를 새로운 섬에 퍼트리면서 지정해주게된다.
					find_island(i, j);
				}
			}
		}
		//지정된 섬 출력
		//for(int i=0; i<N; i++) { for(int j=0; j<M; j++) System.out.print((arr[i][j]==-1?0:arr[i][j])+" "); System.out.println(); } System.out.println();

		//다리 간선 입력 
		road_check = new int[island_count+1][island_count+1];
		for(int i=0; i<=island_count; i++)
			Arrays.fill(road_check[i], INF);
		pq = new PriorityQueue<>();
		//가로 간선 먼저시작
		for(int i=0; i<N; i++) {
			int start_y=0;
			int start_color = arr[i][0];
			for(int j=0; j<M; j++) {
				int next_color = arr[i][j];
				if(next_color!=-1) {	//현재 어떤 섬에 있음 
					/**상황이 3가지 - 내가 아직 어떤 섬에서 시작을 안해서 처음으로 섬에 도착, 지금 내가 a라는 섬에 있는데 똑같이 a라는 섬에 위치중 하나, 내가 a라는 섬에 있는데 b라는 섬에 도착.*/
					//나는 이미 다른 색섬을 시작해서 온건데, 현재 가리키고있는곳이 새로운 색의 섬이네!? 간선발견인지 확인!
					if(start_color!=-1 && start_color!=next_color && j-(start_y+1) >= 2)  //간선발견
						make_bridge(start_color, next_color, j-(start_y+1));	
					start_color = next_color;	//처음 섬에 도착했을 때 갱신, 같은 섬에 있으면 갱신안해도 되지만 갱신해도 상관없으니 if조건 더 안붙일려고 갱신, 새로운 섬에 오면 갱신
					start_y=j;	//처음 섬에 도착했을떄도 갱신, 같은 섬에 있어도 더 오른쪽에 갈 수 있다면 갱신, 새로운 섬에 오면 갱신
				}
			}
		}
		//세로 간선 시작
		for(int j=0; j<M; j++) {
			int start_x=0;
			int start_color = arr[0][j];
			for(int i=0; i<N; i++) {
				int next_color = arr[i][j];
				if(next_color!=-1) {	//현재 어떤 섬에 있음 
					/**상황이 3가지 - 내가 아직 어떤 섬에서 시작을 안해서 처음로 섬에 도착, 지금 내가 a라는 섬에 있는데 똑같이 a라는 섬에 위치중 하나, 내가 a라는 섬에 있는데 b라는 섬에 도착.*/
					//나는 이미 다른 색섬을 시작해서 온건데, 현재 가리키고있는곳이 새로운 색의 섬이네!? 간선발견인지 확인!
					if(start_color!=-1 && start_color!=next_color && i-(start_x+1) >= 2)  //간선발견
						make_bridge(start_color, next_color,i-(start_x+1));	
					start_color = next_color;	//처음 섬에 도착했을 때 갱신, 같은 섬에 있으면 갱신안해도 되지만 갱신해도 상관없으니 if조건 더 안붙일려고 갱신, 새로운 섬에 오면 갱신
					start_x=i;	//처음 섬에 도착했을떄도 갱신, 같은 섬에 있어도 더 오른쪽에 갈 수 있다면 갱신, 새로운 섬에 오면 갱신
				}
			}
		}
		//간선출력
		//for(Bridge i : pq) System.out.println(i.i1+" "+i.i2+" "+i.cost); System.out.println();
		
		//크루스칼 알고리즘
		group = new int[island_count+1];
		Arrays.fill(group, -1);
		int sum=0, count=0;

		while(!pq.isEmpty()) {
			Bridge now = pq.poll();
			if( groupMerge(now.i1, now.i2) ) {
				sum += now.cost;
				if(++count == island_count-1) 
					break;			
			}
		}
		
		//모두 연결되어있는지 확인. groupFind를 통해서 현재 자신의 그룹 대표장이 누구인지 볼수잇다. 그중 어느 하나라도 다르다면 모두 연결되지 못한 것이다.
		int group_ex = groupFind(1);
		for(int i=2; i<=island_count; i++) {
			int test = groupFind(i);
			if(group_ex != test) {
				System.out.println(-1);
				return;
			}
		}
		
		System.out.println(sum);

	}//==================================================

}
