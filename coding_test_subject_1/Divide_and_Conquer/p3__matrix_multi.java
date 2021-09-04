package Divide_and_Conquer;

import java.util.*;
import java.io.*;

public class p3__matrix_multi {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] A = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				A[i][j] = Integer.parseInt(st.nextToken());	
		}
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		int K = Integer.parseInt(st.nextToken());
		int[][] B = new int[M][K];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<K; j++) 
				B[i][j] = Integer.parseInt(st.nextToken());	
		}
		
		int[][] mul = new int[N][K];
		
		for(int n=0; n<N; n++) {
			for(int k=0; k<K; k++) {
				for(int m=0; m<M; m++) {
					mul[n][k] += A[n][m] * B[m][k];
				}
				sb.append(mul[n][k]+" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
	}

}
