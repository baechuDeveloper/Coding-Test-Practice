package Backtracking;

import java.io.*;

// Queen �࿡ ���� ������ �ణ �����غ�
/**
 �����ߴ����� �ѹ� ������ '��� ��'�� �� ���� �õ����� ����� ������ ���� �ʰ� �ȴ�. 
 
�׷��⿡ ������ ����� �����ؼ� �Ѱ��� �Ʒ��� ����������. ù��° �࿡�� �ϳ��� x�� �����ϸ� �ι�° �࿡���� �ش� x���� �����ϰ� �����Ѱ��� �����Ѵ�. 
 
 =============================================================**/
public class Queen_answer {		
	private static int N, count=0;
	private static int[] pos;		// [row]�ȿ� ��ȣ�� ���õ� �� �� ���� ��(col)�� �ǹ��Ѵ�. 
	private static boolean[] visit; //	[col]�ȿ� ��ȣ�� ������� �����ִ� ���� �ǹ��Ѵ�. �����ִ� ���� ������ �ִ��� �˷��ٰ��̴�. ���� �����ߴ�.
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
	
	public static void real_Queen(int row) {	// �ᱹ k�� ���� ������ ������ �ǹ�������, ������ '�� ��ȣ'�� �ǹ��ϴ°͵� �Ǿ�����. 
		if(row==N) {	//���ڸ� ���� ���������� �˸��� �ǹ�
			++count;
			return;
		}
		for(int col=0; col<N; col++) {					// i�� ������ ���� �����غ� '�� ��ȣ'�� �ȴ�.
			if(visit[col] == false && cond(row, col)) {	// �̷��� visit�� �Ǹ� ���� ���� ��� ������ �غ��� �������̴�. �� ���� ����ʹ� ���ǿ� �°� ó���ɰ��̰�. 
				visit[col] = true;
				pos[row] = col;
				real_Queen(row+1);
				visit[col] = false;
			}
		}
	}//------------------------------------------------------------
															//  num_row : ��������� �� ����, ���������� ���� ���� ��ȣ�� �ȴ�
	public static boolean cond(int num_row, int now_col) {	//	now_col : ������ �� ��ȣ. 
 		int col, diff_x, diff_y;
		if(num_row==0)
 			return true;
		for(int row=0; row<num_row; row++) {
			col = pos[row];
			diff_x = num_row - row;
			diff_y = now_col - col;
			//�࿡ ���ؼ��� �������� ����. ���� �̹� �ɷ� ���� �����ϱ⿡ ����� ����.
			if(diff_x - diff_y == 0)	// ���Ⱑ +1
				return false;
			if(diff_x + diff_y == 0)	// ���Ⱑ -1
				return false;
		}
		return true;
	}//------------------------------------------------------------
}

