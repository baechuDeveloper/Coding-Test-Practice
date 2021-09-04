package DFS_BFS;

import java.io.*;
import java.util.*;

// 2206�� - �� �μ��� �̵��ϱ�
// DFS�� �ܼ��ϰ� �����ؼ� �۾��غ��� �ð��ʰ��� ��Ÿ����. 
// ���� �μ��� ���� ���������� ���������� ���� ������ �߰��� �ּҰ� ���� ���ϹǷ� �� ���� ��츦 üũ�ؾ� �Ǽ� �ð��ʰ��� ��Ÿ����. 
/**
 * 0�� �̵��� �� �ִ� ��, 1�� �̵��� �� ���� ��. �츮�� �� ���� ��ܿ��� �� �Ʒ��� �ϴ����� �̵��ؾ߸� �Ѵ�.
 * **/
public class p2_Break_wall_by_DFS_fail {
	static boolean break_wall = false, find = false;
	static int N, M;
	static char[][] map;
	static int[][] walk;
	static boolean[][] visit;
	static int[][] cal = { {-1,0}, {0,1}, {1,0}, {0,-1} };	//��, ����, �Ʒ�, ��
	static List<Integer> list;
	static int INF = 1_000_001;
	//-------------------------------------
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];	//N�� M��		0�� ��� �� �� �ִ� ��, 1�� ��� ���ϴ� ��
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

	// BFS�� �۾��ؼ� ���ʷ� ���� ��찡 �ּҰ� �ȴ�. BFS�� ȣ���ҋ����� �̵��� �����ϴ� ���̴�. 
	// DFS�� �۾��Ѵٸ� ���� �μ��� ���� �� ������ �����ؼ� �۾��� �� ���� �ִ�. �ٸ� ������ �߰��� �־ �̰��� �ּҰ� �ȴٴ� ������ �����Ƿ� �� ���� ��쵵 �� �ʿ䵵 �ִ�.
	static void DFS(int n, int m, int count) {
		if(N==n && M==m) { //���������� �Դٸ� 
			list.add(count);
			return; 

		}
		boolean check = false;	//�� �湮���� ���� ���� ������� üũ.
		if(map[n][m]=='1') {	//�湮 �� ���� ���� ��, 			
			if(break_wall==false) {	//�ѹ� ���� �μ����� ����.
				check = true;		//���� �� �湮���� ���� ����ߴ�.
				break_wall = true;	//�̰����� �μ���.
			}
			else {
				return;				//���� �ν������� ���̻� �湮�� �� ������ ����. 
			}
		}
		//System.out.println(n+" "+m+" cnt:"+count+" break:"+break_wall+" wall:"+map[n][m]);
		visit[n][m] = true;	//�湮 

		for(int i=0; i<4; i++) {
			int t_n = n + cal[i][0];
			int t_m = m + cal[i][1];
			if(t_n>=0 && t_n<=N && t_m>=0 && t_m<=M && visit[t_n][t_m]==false) {
				DFS(t_n, t_m, count+1);
			}
		}
	
		visit[n][m] = false; //�湮 ��
		if(check == true)
			break_wall = false; //�̰��� ���� ����
	}//-------------------------------------
}
