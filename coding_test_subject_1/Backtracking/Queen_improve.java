package Backtracking;

import java.io.*;

// Queen 이역시 시간초과 // 역시 for문 2개를 돌릴때 이 for문 전체를 수많이 진행을 해서 문제가 생기며 이미 행이나 열을 선정하면 진행할필요가 없는데 이걸 포함하며 진행해서 더 느려지는듯 하다.
/**
 이번 아이디어는 같은 행일때 같은 열 일때 바로 탈락 시키고
 기울기가 1 혹은 -1로 같게 될때 탈락 시키게 한다면 이전에 보드를 일일이 갱신을 하지않고 정확하게 처리가 될것이다.
 =============================================================**/
public class Queen_improve {		
	private static int N, count=0;
	private static int[][] pos;
	//------------------------------------------------------------

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		pos = new int [N][2];
		for_The_Queen(0, 0, 0);
		bw.write(count+"");
		bw.flush();
	}//------------------------------------------------------------
	
	
	public static void for_The_Queen(int k, int old_x, int old_y) {
		if(k==N) {
			++count;
			return;
		}
		
		int x=old_x, y=old_y;

		for( ; y<N; y++) {
			for( ; x<N; x++) {
				if(cond(k, x, y) == true) {
					pos[k][0] = x;
					pos[k][1] = y;
					for_The_Queen(k+1, x, y);
				}
			}
			x = 0;
		}
	}//------------------------------------------------------------
	
	public static boolean cond(int now_count, int x, int y) {
		int t_x, t_y, diff_x, diff_y;
		
		if(now_count == 0)	//아직 하나도 정해진 퀸이 없을때.
			return true;
		for(int i=0; i<now_count; i++) {
			t_x = pos[i][0];
			t_y = pos[i][1];
			diff_x = t_x - x; 
			diff_y = t_y - y;
			
			if(t_x == x)
				return false;
			else if(t_y == y)
				return false;
			else if( (diff_x + diff_y) == 0) // -1 기울기 
				return false;
			else if( (diff_x - diff_y) == 0) // +1 기울기
				return false;
		}
		// 전부 통과했다면 괜찮구나!
		return true;
	}//------------------------------------------------------------
}

