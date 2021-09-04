package Backtracking;

import java.util.*;
import java.io.*;

// 15649번 - N과 M(1)
public class p1 {
	// 멤버변수
	private BufferedReader br;
	private BufferedWriter bw;
	private StringTokenizer st;
	private int N, M;
	private int[] arr, want;
	private boolean[] visit;
	//------------------------------------------------------------
	// main
	public static void main(String[] args) throws IOException{
		p1 obj = new p1(); 
		obj.permutation(0);
	}//------------------------------------------------------------
	// 생성자
	public p1() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i=0; i<N; ) arr[i] = ++i;
		want = new int[M];
		visit = new boolean[N];
	}//------------------------------------------------------------
	// 수열
	public void permutation(int k) throws IOException {
		if(k == M) {
			for(int i:want) 
				bw.write(i+" ");
			bw.write("\n");
			bw.flush();
			return;
		}
		for(int i=0; i<N; i++) {
			if(visit[i] == false) {
				visit[i] = true;
				want[k] = arr[i];
				permutation(k+1);
				visit[i] = false;
			}
		}
	}//------------------------------------------------------------
}

