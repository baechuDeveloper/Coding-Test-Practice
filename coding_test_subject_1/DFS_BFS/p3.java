package DFS_BFS;
import java.util.*;
import java.io.*;
//2667번 단지번호붙이기
public class p3 {
	static int N;
	static int[][] arr, visit;
	static int[][] cmd = { {-1,0}, {0,1}, {1,0}, {0,-1} };
	static int order = 1; //단지 번호
	//============================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) 
				arr[i][j] = str.charAt(j)-'0';	//0은 아파트가 없음, 1은 아파트가 존재
		}
		visit = new int[N][N];	//0은 방문하지 않음, 그외의 값 방문을 함
		
		List<Integer> list = new ArrayList<>();
		// N은 최대 25이니 일일히 확인이 가능
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++) 
				if(arr[i][j]==1 && visit[i][j]==0) { // 한 단지 구성 
					list.add( BFS(i, j) );		
					order++;
				}
		
		list.sort(null);
		bw.write(list.size()+"\n");
		for(int i=0; i<list.size(); i++) 
			bw.write(list.get(i)+"\n");
		bw.flush();
	}//============================================
	static int BFS(int n, int m) {	//방문하면 체크해주도록
		int count = 1;	//자기 자신포함 아파트 개수
		visit[n][m] = order;
		for(int i=0; i<4; i++) {
			int t_n = n + cmd[i][0];
			int t_m = m + cmd[i][1];
			if(t_n>=0 && t_n<N && t_m>=0 && t_m<N) {
				if(arr[t_n][t_m]==1 && visit[t_n][t_m]==0) {
					count += BFS(t_n, t_m);
				}
			}
		}
		
		return count;
	}//============================================
}
