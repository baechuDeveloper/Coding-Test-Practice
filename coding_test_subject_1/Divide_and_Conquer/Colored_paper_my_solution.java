package Divide_and_Conquer;

import java.util.*;
import java.io.*;

// 2630번 색종이 만들기
public class Colored_paper_my_solution {
	
	static int N, white, blue;
	static int[][] arr;
	static Queue<square> q;
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
		
		white=0;
		blue=0;
		q = new LinkedList<>();
		q.add(new square(1,1,N,N));

		while(!q.isEmpty()) {
			square box = q.poll(); //System.out.println("now: "+box.x_1+" "+box.y_1+" "+box.x_2+" "+box.y_2);
			int test = find_color(box);
			
			if(test==-1) { //4분할을 해야함 
				//System.out.println("분할--");
				int half_x = (box.x_1+box.x_2)/2;
				int half_y = (box.y_1+box.y_2)/2;
				q.add(new square(box.x_1, box.y_1, half_x, half_y));     //System.out.println(box.x_1+" "+box.y_1+" "+half_x+" "+half_y);
				q.add(new square(box.x_1, half_y+1, half_x, box.y_2));   //System.out.println(box.x_1+" "+(half_y+1)+" "+half_x+" "+box.y_2);
				q.add(new square(half_x+1, box.y_1, box.x_2, half_y));   //System.out.println(half_x+1+" "+box.y_1+" "+box.x_2+" "+half_y);
				q.add(new square(half_x+1, half_y+1, box.x_2, box.y_2)); //System.out.println(half_x+1+" "+(half_y+1)+" "+box.x_2+" "+box.y_2);
				//System.out.println("----");
			}
			else if(test==0) 
				++white; //System.out.println("---하얀색!");
			else if(test==1) 
				++blue; //System.out.println("---파란색!");
		}
		
		System.out.println(white+"\n"+blue);
	}//================================================================

	static int find_color(square box) {
		int start_x = box.x_1, start_y = box.y_1;
		int fin_x = box.x_2, fin_y = box.y_2;
		int color = arr[start_y][start_x]; //첫번째 도트의 색깔을 가져옴
		
		for(int i=start_y; i<=fin_y; i++) 
			for(int j=start_x; j<=fin_x; j++) 
				if(arr[i][j]!=color) 
					return -1;
		return color;
	}//================================================================
	
	static class square{
		int x_1, y_1, x_2, y_2;
		square(int a, int b, int c, int d){
			x_1 = a;
			y_1 = b;
			x_2 = c;
			y_2 = d;
		}
	}//================================================================
}
