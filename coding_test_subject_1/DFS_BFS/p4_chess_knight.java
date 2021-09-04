package DFS_BFS;
import java.util.*;
import java.io.*;

// 7562�� - ����Ʈ�� �̵�
/**
 * BFS�� ���������� ã�ư������� �غ���
 * */
public class p4_chess_knight {

	static int[][] chess;
	static int[][] cmd = { {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {-2,-1}, {-2,1}, {-1,2} };	//8���� ����
	//==============================================================
	static class Node{
		int x, y, count;
		Node(int a, int b, int c){
			x=a; y=b; count=c;
		}
	}//==============================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0) {
			// �����Է�
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int nowX   = Integer.parseInt(st.nextToken());
			int nowY   = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int destX  = Integer.parseInt(st.nextToken());
			int destY  = Integer.parseInt(st.nextToken());

			// ü���� �غ�
			chess = new int[N][N];
			for(int i=0; i<N; i++)
				Arrays.fill(chess[i], -1);
			Queue<Node> q = new LinkedList<>();
			q.add(new Node(nowX, nowY, 0));
			
			// BFS ����
			while(!q.isEmpty()) {
				Node now = q.poll();
				int x = now.x, y = now.y, count = now.count;
				if(chess[y][x] != -1) continue;	//�湮�� �غ� ���� ��ǻ� BFS���п� �ִ����� �ü��ִ� �Ÿ��� �°��̴�. �׷��� �湮���ٸ� ���̻� ������ ���ص� �ȴ�.
				if(x == destX && y == destY) {  //�������� ����
					chess[destY][destX] = count;
					break;
				}
				
				chess[y][x] = count; //�湮�� �غ�. 
				
				for(int i=0; i<8; i++) {
					int nextX = x+cmd[i][0];
					int nextY = y+cmd[i][1];			
					if(nextX>=0&&nextX<N && nextY>=0&&nextY<N && chess[nextY][nextX]==-1 ) {	//�湮�� �غ����� ������
						q.add(new Node(nextX, nextY, count+1));
					}
				}
			}
			bw.write(chess[destY][destX]+"\n");
		}
		bw.flush();
	}//==============================================================

}
