package Divide_and_Conquer;

import java.util.*;
import java.io.*;

// 2630번 색종이 만들기
// 분할정복은 재귀를 응용해서 점점 크기를 줄여가는 방법이다.
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
	
		find_color(N, 1, 1);	//시작위치 1, 1에 있는 크기가 N인 정사각형에서 구한다.
		
		System.out.println(white+"\n"+blue);
	}//================================================================

	static void find_color(int size, int x, int y) { //x와 y는 시작 위치, 정사각형임을 이용해 시작위치와 size로 어떤 정사각형인지 알 수 있다.
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
			
		if(check == true) {		//분할
			int half_x = (x+fin_x)/2 + 1;
			int half_y = (y+fin_y)/2 + 1;
			find_color(size/2, x, y);
			find_color(size/2, half_x, y);
			find_color(size/2, x, half_y);
			find_color(size/2, half_x, half_y);
		}
		else if(color == 0){	//하얀색
			++white;
		}
		else if(color == 1) {	//파란색
			++blue;
		}
		
	}//================================================================
	
}
