package Backtracking;

import java.io.*;

// Queen 시간초과
public class Queen {		
	//생각하니 순열형태로 진행을 해야한다. 체스를 일렬로 세우고 보면 맨처음에 두는 퀸을 A라고 할때 이 A를 계속 앞으로 진행을 하고 그 뒤에는 어떠한 퀸도 없게 해야한다.
	private static int N, count=0;
	private static int [][] board;
	//------------------------------------------------------------

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		permutation(0, 0, 0);
		bw.write(count+"");
		bw.flush();
	}//------------------------------------------------------------
	
	public static void prin() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(board[i][j]+"");
			}System.out.println();
		}
		System.out.println();
	}//------------------------------------------------------------
	
	public static void permutation(int k, int old_x, int old_y) {	//old_k는 배열의 번호
		if(k==N) {
			//prin();
			++count;
			return;
		}
		int x = old_x, y = old_y;

		for( ; y<N; y++) {
			for( ; x<N; x++) {
				if(board[y][x] == 0) {
					do_Line(x, y, true);
					permutation(k+1, x, y);
					do_Line(x, y, false);
				}
			}
			x=0;
		}
	}//------------------------------------------------------------

	public static void do_Line(int x, int y, boolean plus) {
		int val, t_x, t_y;
		if(plus == true) val = 1;
		else val = -1;

		board[y][x] -= val;
		//가로
		for(int i=0; i<N; i++) board[y][i] += val; 
		//세로
		for(int i=0; i<N; i++) board[i][x] += val;
		// 1사분면
		t_x = x; t_y = y;
		while(true) 
			if( (++t_x<N) && (++t_y<N) ) 
				board[t_y][t_x] += val;
			else break; 
		// 2사분면
		t_x = x; t_y = y;
		while(true) 
			if( (--t_x>=0) && (++t_y<N) ) 
				board[t_y][t_x] += val;
			else break; 
		// 3사분면
		t_x = x; t_y = y;
		while(true) 
			if( (--t_x>=0) && (--t_y>=0) ) 
				board[t_y][t_x] += val;
			else break; 
		//4사분면
		t_x = x; t_y = y;
		while(true) 
			if( (++t_x<N) && (--t_y>=0) ) 
				board[t_y][t_x] += val;
			else break; 
	}//------------------------------------------------------------
}

