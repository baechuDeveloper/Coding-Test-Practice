package DFS_BFS;
import java.util.*;
import java.io.*;

// 7562번 - 나이트의 이동
/**
 * BFS로 범위적으로 찾아가보도옥 해보자
 * */
public class p4_chess_knight {

	static int[][] chess;
	static int[][] cmd = { {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {-2,-1}, {-2,1}, {-1,2} };	//8가지 방향
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
			// 정보입력
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int nowX   = Integer.parseInt(st.nextToken());
			int nowY   = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int destX  = Integer.parseInt(st.nextToken());
			int destY  = Integer.parseInt(st.nextToken());

			// 체스판 준비
			chess = new int[N][N];
			for(int i=0; i<N; i++)
				Arrays.fill(chess[i], -1);
			Queue<Node> q = new LinkedList<>();
			q.add(new Node(nowX, nowY, 0));
			
			// BFS 시작
			while(!q.isEmpty()) {
				Node now = q.poll();
				int x = now.x, y = now.y, count = now.count;
				if(chess[y][x] != -1) continue;	//방문을 해본 곳은 사실상 BFS덕분에 최단으로 올수있는 거리로 온것이다. 그러니 방문헀다면 더이상 진행을 안해도 된다.
				if(x == destX && y == destY) {  //목적지에 도달
					chess[destY][destX] = count;
					break;
				}
				
				chess[y][x] = count; //방문을 해봄. 
				
				for(int i=0; i<8; i++) {
					int nextX = x+cmd[i][0];
					int nextY = y+cmd[i][1];			
					if(nextX>=0&&nextX<N && nextY>=0&&nextY<N && chess[nextY][nextX]==-1 ) {	//방문을 해본적이 없을때
						q.add(new Node(nextX, nextY, count+1));
					}
				}
			}
			bw.write(chess[destY][destX]+"\n");
		}
		bw.flush();
	}//==============================================================

}
