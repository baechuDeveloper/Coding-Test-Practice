package Backtracking;

import java.util.*;
import java.io.*;

// 15651번 - N과 M(3)
// (1)과 문제가 다르긴한데... 이게 엄청난 속도 차이가 있다. p2의 객체 obj를 만들어서 진행을 하는것보다. 곧바로 static형태로 main으로 진행할때 굉장히 시간단축이 되었다.
//	2172ms -> 488ms
//  그리고 bw.write를 여러번 하는것보다. sb.append로 진행을 하는것이 더욱 시간 단축이 되었다. 앞으로 sb를 여러번하고 마지막에 bw.write를 진행해두는게 좋은 방법 일것같다.
//  대신에!! 메모리적 양 측면에서는 bw.write로 바로 진행하는게 더욱 더 효율적이다.
//  648ms -> 488ms  
public class p2 {
	
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
		permutation(0);
		bw.write(sb.toString());
		bw.flush();
	}//------------------------------------------------------------
	
	public static void permutation(int k) {
		if(k == M) {
			for(int i:want)
				sb.append(i+" ");
			sb.append("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			want[k] = arr[i];
			permutation(k+1);
		}
	}
}

