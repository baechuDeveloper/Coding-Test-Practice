package DFS_BFS;
//2206�� - �� �μ��� �̵��ϱ�
import java.io.*;
import java.util.*;
//ó�� ��� walk�� �̿��ؼ� ��ø�� ���� ���������� �� ���� �ǵ��� ���� ���� �� �ִٰ� �� �� ������ �̴� ���� �μ��ٴ� Ư¡�� �������� ��ȿ�ϰ� �ۿ��Ѵ�.
//walk�� �� ũ�ٰ� �ص� ���� �μ��� ���� ���¿��� ���� �� ���� ������ �־��. �׷��ٰ� ������ �ִ� �ֵ��� ����־� �ٽ� ť�� ���� ������ walk�� ������ �ǹ̰� ���� ���°� �ȴ�.
//walk�� �����صθ� ���. [���� �� �վ�ô�/���ô�] ���� �����ؼ� �����ϴ°� �������͸� ����. �ٸ� �������� ������ �� �����ٰ� ���߿� ���� ���� ���·� walk[1] ������ ������ 
// [0]���� �������� [1]������ ���ο� ������ �ٽ� ����� �ȴ�. �ٸ� ���� �޸������̼� ȿ��ó�� �ߺ��� ���� ������ ���İ��� ȿ�������� Ǯ �� �־���.  
/*------------------------- �� ��
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
 * 1. ����ġ�� ���� �ִܰ�δ� ������ BFS�̴�. �ֳ��ϸ� ������ ��Ȳ���� DFS�� ó�� ���������� �װ��� ���� �ּҶ�� ������ ���� �����̴�. BFS�� ������ �ȴ�. 
 * �� ���������� ���� ĭ�� �湮�ϴ� ��� ���� �� �μ� ���� �� �����ϱ� ������ ���� �ν����� ���θ� �湮 �迭�� ����Ͽ� �μ� Ƚ���� �� ���� ���� �湮�ϴ� ����� �˴ϴ�. 
 * �׷��� �̴� ������ Ư�� ������ �� ���������� ���ϴ� �׸����̹Ƿ� �ٸ� �������� �Ժη� ����ؼ��� �� �˴ϴ�.
 * ------------------------------------------------------**/

// visit[][][] �� �̿��ؼ� [���� �� �վ�ô�/���ô�][��ǥn][��ǥm] ���� �ش� ���� �湮�ߴ��� üũ�Ѵ�. �� ���� ���� �վ �湮�غ��Ҵ���, �� �հ� �湮�غ��Ҵ��� 
// DFS�� �ܼ��ϰ� �����ؼ� �۾��غ��� �ð��ʰ��� ��Ÿ����. 
// ���� �μ��� ���� ���������� ���������� ���� ������ �߰��� �ּҰ� ���� ���ϹǷ� �� ���� ��츦 üũ�ؾ� �Ǽ� �ð��ʰ��� ��Ÿ����. 
/**---------------------------------------------------------------------------
 * 0�� �̵��� �� �ִ� ��, 1�� �̵��� �� ���� ��. �츮�� �� ���� ��ܿ��� �� �Ʒ��� �ϴ����� �̵��ؾ߸� �Ѵ�.
 * ---------------------------------------------------------------------------**/
public class p2_Break_wall_by_BFS_Amazing { 
	static int N, M;
	static char[][] map;
	static int[][][] walk;	//[���� �� �վ�ô�/���ô�][��ǥn][��ǥm]
	static int[][] cal = { {-1,0}, {0,1}, {1,0}, {0,-1} };	//��, ����, �Ʒ�, ��
	//static int[][] walk2;	//�ʱ⿡ ������ ���. �̰� öȸ�Ǿ���
	//static boolean[][][] visit; // [���� �� �վ�ô�/���ô�][��ǥn][��ǥm]  �� ���� ���� ���� ���·� �湮�غ��Ҵ���, ���� �� �հ� �湮�غ��Ҵ���
	static boolean success = false;
	static Set<Integer> set;
	static int INF = 1_000_001;
	//=========================================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];	//N�� M��		0�� ��� �� �� �ִ� ��, 1�� ��� ���ϴ� ��
		//walk = new int[N][M];
		walk = new int[2][N][M];	//[0]�� ���� �ȶհ� �� ����, [1]�� ���� �հ� �� ����.
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
	//�湮 �̷��� �˷��ִ� ��� �ڷᱸ��
	static class Node{
		int n, m, count;
		boolean break_p;	//���� �ڱ� ������ �μ� ������ �ִٸ� ���⿡ true�� ��� �ް� �´�. 
		Node(int a, int b, int c, boolean d) { n=a; m=b; count=c; break_p=d; }
	}//=========================================================================
	// BFS�� �۾��ؼ� ���ʷ� ���� ��찡 �ּҰ� �ȴ�. BFS�� ȣ���ҋ����� �̵��� �����ϴ� ���̴�. 
	static void BFS() {

		set = new HashSet<>();
		Queue<Node> q = new LinkedList<>();
		q.add( new Node(0, 0, 1, false) );
		int n, m, count;
		boolean break_p;

		while(!q.isEmpty()) {
			//�̷� ���·� �湮�� �غ���.
			Node now = q.poll();
			n=now.n; m=now.m; count=now.count; break_p=now.break_p;
			int v=0; if(break_p) v=1;

			if(n==N && m==M) {
				set.add(count);
				success = true;
				break;
			}
			//���� �������� �湮 ����
			int t_n, t_m;
			for(int i=0; i<4; i++) {
				t_n = n + cal[i][0];
				t_m = m + cal[i][1];
				if( t_n>=0 && t_n<=N && t_m>=0 && t_m<=M ) {
					int temp = map[t_n][t_m]-'0';	//������ ������ ���� �ִ���
					if(temp==1) {	//������ �� ���� ���� �ִ�.
						if(break_p==true) {	//���� ���� ���� �����̴�. 
							continue;
						}
						else {
							//���� �༮���� ���� �հ� �� �������� �˷��ش�.
							q.add( new Node(t_n, t_m, count+1, true) );
							walk[1][t_n][t_m] = count+1;
						}
					}
					else if(walk[v][t_n][t_m] > count+1) { // ������ �� ���� ���� ����. temp�� 1�� �� ���� �̰��� �� �� ����.
															// else�� �Ʒ� if�� �Ŷ� else if�ΰŶ� ���� �ӵ��� ���̰� ������ �������� �������� �޶���. �� ��� ���� ���� ����.
						q.add( new Node(t_n, t_m, count+1, break_p) );
						walk[v][t_n][t_m] = count+1;
					}
				}
			}

		}//�ݺ��� ����-------------------

	}//=========================================================================
	/*
	static void BFS_fail() {

		set = new HashSet<>();
		Queue<Node> q = new LinkedList<>();
		q.add( new Node(0, 0, 1, false) );
		int n, m, count;
		boolean break_p;

		while(!q.isEmpty()) {
			//�̷� ���·� �湮�� �غ���.
			Node now = q.poll();
			n=now.n; m=now.m; count=now.count; break_p=now.break_p;

			if(n==N && m==M) {
				set.add(count);
				success = true;
				break;
			}
			//���� ���� ���·� �湮�Ѱ� �������� ���� ���� count��� �湮�� �ص� �� ���� ����� �°��̹Ƿ� �����ص� �ȴ�.
			if(walk[n][m] < count)
				continue;
			//�� ������ �湮�̸� ������ �ص� �ȴ�.
			else {
				if(map[n][m]=='1') {
					if(break_p==true) 
						continue;
					else {
						now.break_p = true;
						break_p=now.break_p;
					}
				}
				//���� �������� �湮 ����
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

		}//�ݺ��� ����-------------------

	}//=========================================================================
	 */
}
