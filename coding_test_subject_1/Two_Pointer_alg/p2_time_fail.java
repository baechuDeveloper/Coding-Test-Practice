package Two_Pointer_alg;
// https://www.acmicpc.net/problem/2075

// 백준 - N번째 큰 수 

import java.util.*;
import java.io.*;

public class p2_time_fail {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Integer[] arr = new Integer[N*N];
		
		for(int i=0; i<N; i++) {
			int index = i*5;
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				arr[index + j] =  Integer.parseInt(st.nextToken());
		}
		
		/*int[][] arr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				arr[i][j] =  Integer.parseInt(st.nextToken());
		}
		*/
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		//System.out.println(Integer.MAX_VALUE); //2,147,483,647  20억
		
		System.out.println(arr[N-1]);
		
		
		
	}

}
