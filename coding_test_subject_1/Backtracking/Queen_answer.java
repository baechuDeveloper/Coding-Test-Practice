package Backtracking;

import java.io.*;

// Queen 행에 대한 개념을 약간 참조해봄
/**
 간과했던것이 한번 정해진 '행과 열'은 그 다음 시도에서 절대로 포함이 되지 않게 된다. 
 
그렇기에 맨위에 행부터 시작해서 한개씩 아래로 내려가보자. 첫번째 행에서 하나의 x를 선택하면 두번째 행에서는 해당 x열을 제외하고 가능한것을 선택한다. 
 
 =============================================================**/
public class Queen_answer {		
	private static int N, count=0;
	private static int[] pos;		// [row]안에 번호는 선택된 행 그 값은 열(col)을 의미한다. 
	private static boolean[] visit; //	[col]안에 번호는 현재까지 남아있는 열을 의미한다. 남아있는 열이 무엇이 있는지 알려줄것이다. 좋은 생각했다.
	//------------------------------------------------------------

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		pos = new int[N];
		visit = new boolean[N];
		real_Queen(0);
		bw.write(count+"");
		bw.flush();
	}//------------------------------------------------------------
	
	public static void real_Queen(int row) {	// 결국 k는 원래 선택한 개수도 의미하지만, 현재의 '행 번호'를 의미하는것도 되어진다. 
		if(row==N) {	//끝자리 행을 지나왔음을 알리는 의미
			++count;
			return;
		}
		for(int col=0; col<N; col++) {					// i는 현재의 행이 선택해본 '열 번호'가 된다.
			if(visit[col] == false && cond(row, col)) {	// 이렇게 visit로 되면 맨위 행은 모든 진행을 해볼수 있을것이다. 그 다음 행부터는 조건에 맞게 처리될것이고. 
				visit[col] = true;
				pos[row] = col;
				real_Queen(row+1);
				visit[col] = false;
			}
		}
	}//------------------------------------------------------------
															//  num_row : 현재까지의 행 개수, 위에서부터 보면 행의 번호가 된다
	public static boolean cond(int num_row, int now_col) {	//	now_col : 현재골라본 열 번호. 
 		int col, diff_x, diff_y;
		if(num_row==0)
 			return true;
		for(int row=0; row<num_row; row++) {
			col = pos[row];
			diff_x = num_row - row;
			diff_y = now_col - col;
			//행에 대해서는 같을리가 없다. 열도 이미 걸러 내고 시작하기에 상관이 없다.
			if(diff_x - diff_y == 0)	// 기울기가 +1
				return false;
			if(diff_x + diff_y == 0)	// 기울기가 -1
				return false;
		}
		return true;
	}//------------------------------------------------------------
}

