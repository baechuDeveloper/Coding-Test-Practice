package Backtracking;

import java.io.*;

// Queen �̿��� �ð��ʰ� // ���� for�� 2���� ������ �� for�� ��ü�� ������ ������ �ؼ� ������ ����� �̹� ���̳� ���� �����ϸ� �������ʿ䰡 ���µ� �̰� �����ϸ� �����ؼ� �� �������µ� �ϴ�.
/**
 �̹� ���̵��� ���� ���϶� ���� �� �϶� �ٷ� Ż�� ��Ű��
 ���Ⱑ 1 Ȥ�� -1�� ���� �ɶ� Ż�� ��Ű�� �Ѵٸ� ������ ���带 ������ ������ �����ʰ� ��Ȯ�ϰ� ó���� �ɰ��̴�.
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
		
		if(now_count == 0)	//���� �ϳ��� ������ ���� ������.
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
			else if( (diff_x + diff_y) == 0) // -1 ���� 
				return false;
			else if( (diff_x - diff_y) == 0) // +1 ����
				return false;
		}
		// ���� ����ߴٸ� ��������!
		return true;
	}//------------------------------------------------------------
}

