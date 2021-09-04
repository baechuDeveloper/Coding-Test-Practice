package DFS_BFS;
//2206번 - 벽 부수고 이동하기
import java.io.*;
import java.util.*;
//처음 방식 walk를 이용해서 중첩을 통해 유망한지를 본 것은 의도는 좋고 줄일 수 있다고 볼 수 있지만 이는 벽을 부순다는 특징이 없을때만 유효하게 작용한다.
//walk가 더 크다고 해도 벽을 부수지 않은 상태에서 왔을 때 아직 포텐이 있어서다. 그렇다고 포텐이 있는 애들을 살려주어 다시 큐에 넣은 행위는 walk를 구현한 의미가 없는 상태가 된다.
//walk를 구분해두면 어떨까. [벽을 안 뚫어봤다/뚧어봤다] 으로 구분해서 적용하는건 괜찮을것만 같다. 다만 적었듯이 벽없이 잘 나가다가 도중에 벽을 뚫은 상태로 walk[1] 내용을 적으면 
// [0]에서 적힌곳이 [1]에서는 새로운 곳으로 다시 열어보게 된다. 다만 역시 메모이제이션 효과처럼 중복이 적은 연산을 거쳐가며 효율적으로 풀 수 있었다.  
/*------------------------- 반 례
6 3
000
110
000
011
111
000

7 3
010
000
110
100
001
011
000
-----------------------------------------------*/

/**------------------------------------------------------
 * 1. 가중치가 없는 최단경로는 무조건 BFS이다. 왜냐하면 동일한 상황에서 DFS는 처음 도착했을때 그것이 가장 최소라는 보장이 없기 떄문이다. BFS는 보장이 된다. 
 * 이 문제에서는 같은 칸에 방문하는 경우 벽을 안 부순 것이 더 유리하기 때문에 벽을 부쉈는지 여부를 방문 배열에 기록하여 부순 횟수가 더 적을 때만 방문하는 방법도 됩니다. 
 * 그러나 이는 문제의 특성 때문에 이 문제에서만 통하는 그리디이므로 다른 문제에도 함부로 사용해서는 안 됩니다.
 * ------------------------------------------------------**/

// visit[][][] 를 이용해서 [벽을 안 뚫어봤다/뚧어봤다][좌표n][좌표m] 으로 해당 곳을 방문했는지 체크한다. 이 곳을 벽을 뚫어서 방문해보았는지, 안 뚫고 방문해보았는지 
// DFS로 단순하게 생각해서 작업해보니 시간초과가 나타났다. 
// 벽을 부수는 것을 직관적으로 생각했으나 역시 최초의 발견이 최소가 되지 못하므로 더 많은 경우를 체크해야 되서 시간초과가 나타난다. 
/**---------------------------------------------------------------------------
 * 0은 이동할 수 있는 곳, 1은 이동할 수 없는 벽. 우리는 맨 왼쪽 상단에서 맨 아래쪽 하단으로 이동해야만 한다.
 * ---------------------------------------------------------------------------**/
public class p2_Break_wall_by_BFS_Amazing { 
	static int N, M;
	static char[][] map;
	static int[][][] walk;	//[벽을 안 뚫어봤다/뚧어봤다][좌표n][좌표m]
	static int[][] cal = { {-1,0}, {0,1}, {1,0}, {0,-1} };	//위, 오른, 아래, 왼
	//static int[][] walk2;	//초기에 생각한 방식. 이건 철회되었다
	//static boolean[][][] visit; // [벽을 안 뚫어봤다/뚧어봤다][좌표n][좌표m]  이 곳을 벽을 뚫은 상태로 방문해보았는지, 벽을 안 뚫고 방문해보았는지
	static boolean success = false;
	static Set<Integer> set;
	static int INF = 1_000_001;
	//=========================================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];	//N행 M열		0은 통과 할 수 있는 곳, 1은 통과 못하는 곳
		//walk = new int[N][M];
		walk = new int[2][N][M];	//[0]은 벽을 안뚫고 온 상태, [1]은 벽을 뚫고 온 상태.
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			Arrays.fill(walk[0][i], INF);
			Arrays.fill(walk[1][i], INF);
			//Arrays.fill(walk2[i], INF);
		}

		N-=1; M-=1;
		BFS();

		if(success==false) System.out.println(-1);
		else System.out.println(set.toArray()[0]);
		/*
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<M+1; j++) {
				if(walk[0][i][j] == INF) System.out.print(0+" ");
				else System.out.print(walk[0][i][j]+" ");
			}System.out.println();
		}
		System.out.println();
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<M+1; j++) {
				if(walk[1][i][j] == INF) System.out.print(0+" ");
				else System.out.print(walk[1][i][j]+" ");
			}System.out.println();
		}
		 */
	}//=========================================================================
	//방문 이력을 알려주는 노드 자료구조
	static class Node{
		int n, m, count;
		boolean break_p;	//만약 자기 이전에 부순 흔적이 있다면 여기에 true를 계속 달고 온다. 
		Node(int a, int b, int c, boolean d) { n=a; m=b; count=c; break_p=d; }
	}//=========================================================================
	// BFS로 작업해서 최초로 나온 경우가 최소가 된다. BFS를 호출할떄마다 이동을 시작하는 것이다. 
	static void BFS() {

		set = new HashSet<>();
		Queue<Node> q = new LinkedList<>();
		q.add( new Node(0, 0, 1, false) );
		int n, m, count;
		boolean break_p;

		while(!q.isEmpty()) {
			//이런 상태로 방문을 해볼게.
			Node now = q.poll();
			n=now.n; m=now.m; count=now.count; break_p=now.break_p;
			int v=0; if(break_p) v=1;

			if(n==N && m==M) {
				set.add(count);
				success = true;
				break;
			}
			//이제 정상적인 방문 시작
			int t_n, t_m;
			for(int i=0; i<4; i++) {
				t_n = n + cal[i][0];
				t_m = m + cal[i][1];
				if( t_n>=0 && t_n<=N && t_m>=0 && t_m<=M ) {
					int temp = map[t_n][t_m]-'0';	//다음에 갈곳이 벽이 있는지
					if(temp==1) {	//다음에 갈 곳이 벽이 있다.
						if(break_p==true) {	//내가 벽을 뚫은 상태이다. 
							continue;
						}
						else {
							//다음 녀석에게 벽을 뚫고 온 상태임을 알려준다.
							q.add( new Node(t_n, t_m, count+1, true) );
							walk[1][t_n][t_m] = count+1;
						}
					}
					else if(walk[v][t_n][t_m] > count+1) { // 다음에 갈 곳에 벽이 없다. temp가 1인 건 절대 이곳에 올 수 없다.
															// else에 아래 if인 거랑 else if인거랑 연산 속도의 차이가 있을까 돌려보니 때떄마다 달랐다. 즉 상관 없이 서로 같다.
						q.add( new Node(t_n, t_m, count+1, break_p) );
						walk[v][t_n][t_m] = count+1;
					}
				}
			}

		}//반복문 종료-------------------

	}//=========================================================================
	/*
	static void BFS_fail() {

		set = new HashSet<>();
		Queue<Node> q = new LinkedList<>();
		q.add( new Node(0, 0, 1, false) );
		int n, m, count;
		boolean break_p;

		while(!q.isEmpty()) {
			//이런 상태로 방문을 해볼게.
			Node now = q.poll();
			n=now.n; m=now.m; count=now.count; break_p=now.break_p;

			if(n==N && m==M) {
				set.add(count);
				success = true;
				break;
			}
			//만약 현재 상태로 방문한게 이전보다 좋지 못한 count라면 방문을 해도 더 나쁜 결과로 온것이므로 제외해도 된다.
			if(walk[n][m] < count)
				continue;
			//더 괜찮은 방문이면 진행을 해도 된다.
			else {
				if(map[n][m]=='1') {
					if(break_p==true) 
						continue;
					else {
						now.break_p = true;
						break_p=now.break_p;
					}
				}
				//이제 정상적인 방문 시작
				walk[n][m] = count;

				int t_n, t_m;
				for(int i=0; i<4; i++) {
					t_n = n + cal[i][0];
					t_m = m + cal[i][1];
					if( t_n>=0 && t_n<=N && t_m>=0 && t_m<=M && walk[t_n][t_m] > count+1) {
						q.add( new Node(t_n, t_m, count+1, break_p) );
					}
				}
			}

		}//반복문 종료-------------------

	}//=========================================================================
	 */
}
