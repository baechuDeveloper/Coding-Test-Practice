package DFS_BFS;
import java.util.*;
import java.io.*;
//2667�� ������ȣ���̱�
public class p3 {
	static int N;
	static int[][] arr, visit;
	static int[][] cmd = { {-1,0}, {0,1}, {1,0}, {0,-1} };
	static int order = 1; //���� ��ȣ
	//============================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) 
				arr[i][j] = str.charAt(j)-'0';	//0�� ����Ʈ�� ����, 1�� ����Ʈ�� ����
		}
		visit = new int[N][N];	//0�� �湮���� ����, �׿��� �� �湮�� ��
		
		List<Integer> list = new ArrayList<>();
		// N�� �ִ� 25�̴� ������ Ȯ���� ����
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++) 
				if(arr[i][j]==1 && visit[i][j]==0) { // �� ���� ���� 
					list.add( BFS(i, j) );		
					order++;
				}
		
		list.sort(null);
		bw.write(list.size()+"\n");
		for(int i=0; i<list.size(); i++) 
			bw.write(list.get(i)+"\n");
		bw.flush();
	}//============================================
	static int BFS(int n, int m) {	//�湮�ϸ� üũ���ֵ���
		int count = 1;	//�ڱ� �ڽ����� ����Ʈ ����
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
