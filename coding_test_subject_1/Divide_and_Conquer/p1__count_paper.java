package Divide_and_Conquer;

import java.util.*;
import java.io.*;

// 1780번 - 종이의 개수
public class p1__count_paper {

	static int N;
	static int[][] arr;		//[행][열]
	static int[] result;	//[0] : -1, [1] : 0, [2] : 1
	//============================================================
	
	static void cal(int start_raw, int start_cal, int end_raw, int end_cal) {
		boolean check = true;
		int val = arr[start_raw][start_cal];
		
		for(int i=start_raw; i<end_raw; i++) {
			for(int j=start_cal; j<end_cal; j++) {
				if(val != arr[i][j]) {
					check = false;
					break;
				}
			}
			if(!check) break;
		}
		
		if(check) {
			if	  (val==-1) result[0]++;
			else if(val==0) result[1]++;
			else if(val==1) result[2]++;	
		}
		else {
			int len = (end_raw-start_raw)/3;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					int next_raw = start_raw + len*i;
					int next_cal = start_cal + len*j;
					cal(next_raw, next_cal, next_raw+len, next_cal+len);
				}
			}
		}
	
	}//============================================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		result = new int[3];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		cal(0, 0, N, N);
		System.out.println(result[0]+"\n"+result[1]+"\n"+result[2]);
	}//============================================================
}
