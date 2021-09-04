package Backtracking;

import java.util.*;
import java.io.*;

// N과 M(4) 
public class p3 {
	
	private static int N, M;
	private static int[] arr, want;
	private static StringBuilder sb;
	//------------------------------------------------------------
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i=0; i<N; ) arr[i] = ++i;
		want = new int[M];
		sb = new StringBuilder();
		permutation(0, 0);
		bw.write(sb.toString());
		bw.flush();
	}//------------------------------------------------------------
	
	public static void permutation(int k, int old_k) {	//old_k는 배열의 번호
		if(k == M) {
			for(int i:want)
				sb.append(i+" ");
			sb.append("\n");
			return;
		}
		for(int i=old_k; i<N; i++) {
			want[k] = arr[i];
			permutation(k+1, i);
		}
	}
}

