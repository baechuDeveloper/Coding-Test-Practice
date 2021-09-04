package Divide_and_Conquer;

import java.io.*;

// 1992번 쿼드트리
// 전체 배열이 1로만 있다면 '1'만 쓰면 된다. 0으로만 있다면 '0'만 쓰면된다.
// 분할 압축이 있을때 ( ) 으로 표현이된다.
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
	
	static void find_quad(int N, int x, int y) {	//재귀적으로 돌아가면서 압축속에 압축도 진행을 해가며 압축을 끝내며 완성을 해간다.
		
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
			
		if(check == true) {		//분할
			//System.out.println("분할");
			sb.append("(");	// 현재 압축의 시작을 알리는 표현
			int half_x = (x+fin_x)/2 + 1;
			int half_y = (y+fin_y)/2 + 1;
			find_quad(N/2, x, y);			//왼쪽 위
			find_quad(N/2, half_x, y);		//오른쪽 위
			find_quad(N/2, x, half_y);		//왼쪽 아래
			find_quad(N/2, half_x, half_y);	//오른쪽 아래
			sb.append(")"); // 현재의 압축을 끝내는 표현
		}
		else if(color == 0) {	//0으로만 압축됨
			sb.append(0+"");
		}
		else if(color == 1) {	//1로만 압축됨
			sb.append(1+"");
		}
		
	}//================================================================
}
