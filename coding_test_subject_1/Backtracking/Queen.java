package Backtracking;

import java.io.*;

// Queen �ð��ʰ�
public class Queen {		
	//�����ϴ� �������·� ������ �ؾ��Ѵ�. ü���� �Ϸķ� ����� ���� ��ó���� �δ� ���� A��� �Ҷ� �� A�� ��� ������ ������ �ϰ� �� �ڿ��� ��� ���� ���� �ؾ��Ѵ�.
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
	
	public static void permutation(int k, int old_x, int old_y) {	//old_k�� �迭�� ��ȣ
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
		//����
		for(int i=0; i<N; i++) board[y][i] += val; 
		//����
		for(int i=0; i<N; i++) board[i][x] += val;
		// 1��и�
		t_x = x; t_y = y;
		while(true) 
			if( (++t_x<N) && (++t_y<N) ) 
				board[t_y][t_x] += val;
			else break; 
		// 2��и�
		t_x = x; t_y = y;
		while(true) 
			if( (--t_x>=0) && (++t_y<N) ) 
				board[t_y][t_x] += val;
			else break; 
		// 3��и�
		t_x = x; t_y = y;
		while(true) 
			if( (--t_x>=0) && (--t_y>=0) ) 
				board[t_y][t_x] += val;
			else break; 
		//4��и�
		t_x = x; t_y = y;
		while(true) 
			if( (++t_x<N) && (--t_y>=0) ) 
				board[t_y][t_x] += val;
			else break; 
	}//------------------------------------------------------------
}

