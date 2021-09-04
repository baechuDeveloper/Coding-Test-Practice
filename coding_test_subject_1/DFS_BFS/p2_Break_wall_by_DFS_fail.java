package DFS_BFS;

import java.io.*;
import java.util.*;

// 2206번 - 벽 부수고 이동하기
// DFS로 단순하게 생각해서 작업해보니 시간초과가 나타났다. 
// 벽을 부수는 것을 직관적으로 생각했으나 역시 최초의 발견이 최소가 되지 못하므로 더 많은 경우를 체크해야 되서 시간초과가 나타난다. 
/**
 * 0은 이동할 수 있는 곳, 1은 이동할 수 없는 벽. 우리는 맨 왼쪽 상단에서 맨 아래쪽 하단으로 이동해야만 한다.
 * **/
public class p2_Break_wall_by_DFS_fail {
	static boolean break_wall = false, find = false;
	static int N, M;
	static char[][] map;
	static int[][] walk;
	static boolean[][] visit;
	static int[][] cal = { {-1,0}, {0,1}, {1,0}, {0,-1} };	//위, 오른, 아래, 왼
	static List<Integer> list;
	static int INF = 1_000_001;
	//-------------------------------------
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];	//N행 M열		0은 통과 할 수 있는 곳, 1은 통과 못하는 곳
		//walk = new int[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			//Arrays.fill(walk[i], INF);
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}System.out.println();
		}
		visit = new boolean[N][M];
		//walk[0][0] = 0;
		list = new ArrayList<>();
		N-=1; M-=1;
		System.out.println(N+" "+M);
		DFS(0, 0, 1);
		if(list.size()==0)
			System.out.println(-1);
		else {
			list.sort(null);
			System.out.println(list.get(0));
		}
	}//-------------------------------------

	// BFS로 작업해서 최초로 나온 경우가 최소가 된다. BFS를 호출할떄마다 이동을 시작하는 것이다. 
	// DFS로 작업한다면 벽을 부수는 것을 더 간단히 상정해서 작업을 할 수가 있다. 다만 최초의 발견이 있어도 이것이 최소가 된다는 보장이 없으므로 더 많은 경우도 볼 필요도 있다.
	static void DFS(int n, int m, int count) {
		if(N==n && M==m) { //도착지점에 왔다면 
			list.add(count);
			return; 

		}
		boolean check = false;	//이 방문에서 내가 벽을 사용한지 체크.
		if(map[n][m]=='1') {	//방문 한 곳이 벽일 때, 			
			if(break_wall==false) {	//한번 벽을 부순적이 없다.
				check = true;		//내가 이 방문에서 벽을 사용했다.
				break_wall = true;	//이곳에서 부순다.
			}
			else {
				return;				//벽을 부숴봤으니 더이상 방문을 할 권한이 없다. 
			}
		}
		//System.out.println(n+" "+m+" cnt:"+count+" break:"+break_wall+" wall:"+map[n][m]);
		visit[n][m] = true;	//방문 

		for(int i=0; i<4; i++) {
			int t_n = n + cal[i][0];
			int t_m = m + cal[i][1];
			if(t_n>=0 && t_n<=N && t_m>=0 && t_m<=M && visit[t_n][t_m]==false) {
				DFS(t_n, t_m, count+1);
			}
		}
	
		visit[n][m] = false; //방문 끝
		if(check == true)
			break_wall = false; //이곳을 빠져 나옴
	}//-------------------------------------
}
