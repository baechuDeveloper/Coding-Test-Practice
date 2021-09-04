package Divide_and_Conquer;

import java.io.*;

// 1992�� ����Ʈ��
// ��ü �迭�� 1�θ� �ִٸ� '1'�� ���� �ȴ�. 0���θ� �ִٸ� '0'�� ����ȴ�.
// ���� ������ ������ ( ) ���� ǥ���̵ȴ�.
public class QuadTree {
	
	static int N;
	static int[][] arr;
	static StringBuilder sb;
	//================================================================

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			String c = br.readLine();
			for(int j=1; j<=N; j++) 
				arr[i][j] = c.charAt(j-1)-48;
		}
		sb = new StringBuilder();
	
		find_quad(N, 1, 1);
		
		System.out.println(sb.toString());
	}//================================================================
	
	static void find_quad(int N, int x, int y) {	//��������� ���ư��鼭 ����ӿ� ���൵ ������ �ذ��� ������ ������ �ϼ��� �ذ���.
		
		int color = arr[y][x];
		int fin_x = x+N-1;
		int fin_y = y+N-1;
		boolean check = false;

		for(int i=y; i<=fin_y; i++) 
			for(int j=x; j<=fin_x; j++) 
				if(arr[i][j]!=color) {
					check = true;
					break;
				}
			
		if(check == true) {		//����
			//System.out.println("����");
			sb.append("(");	// ���� ������ ������ �˸��� ǥ��
			int half_x = (x+fin_x)/2 + 1;
			int half_y = (y+fin_y)/2 + 1;
			find_quad(N/2, x, y);			//���� ��
			find_quad(N/2, half_x, y);		//������ ��
			find_quad(N/2, x, half_y);		//���� �Ʒ�
			find_quad(N/2, half_x, half_y);	//������ �Ʒ�
			sb.append(")"); // ������ ������ ������ ǥ��
		}
		else if(color == 0) {	//0���θ� �����
			sb.append(0+"");
		}
		else if(color == 1) {	//1�θ� �����
			sb.append(1+"");
		}
		
	}//================================================================
}
