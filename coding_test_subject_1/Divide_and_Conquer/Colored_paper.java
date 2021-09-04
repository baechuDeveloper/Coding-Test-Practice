package Divide_and_Conquer;

import java.util.*;
import java.io.*;

// 2630�� ������ �����
// ���������� ��͸� �����ؼ� ���� ũ�⸦ �ٿ����� ����̴�.
public class Colored_paper {
	
	static int N, white=0, blue=0;
	static int[][] arr;
	//================================================================

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
	
		find_color(N, 1, 1);	//������ġ 1, 1�� �ִ� ũ�Ⱑ N�� ���簢������ ���Ѵ�.
		
		System.out.println(white+"\n"+blue);
	}//================================================================

	static void find_color(int size, int x, int y) { //x�� y�� ���� ��ġ, ���簢������ �̿��� ������ġ�� size�� � ���簢������ �� �� �ִ�.
		int color = arr[y][x];
		int fin_x = x+size-1;
		int fin_y = y+size-1;
		boolean check = false;
		
		for(int i=y; i<=fin_y; i++) 
			for(int j=x; j<=fin_x; j++) 
				if(arr[i][j]!=color) {
					check = true;
					break;
				}
			
		if(check == true) {		//����
			int half_x = (x+fin_x)/2 + 1;
			int half_y = (y+fin_y)/2 + 1;
			find_color(size/2, x, y);
			find_color(size/2, half_x, y);
			find_color(size/2, x, half_y);
			find_color(size/2, half_x, half_y);
		}
		else if(color == 0){	//�Ͼ��
			++white;
		}
		else if(color == 1) {	//�Ķ���
			++blue;
		}
		
	}//================================================================
	
}
