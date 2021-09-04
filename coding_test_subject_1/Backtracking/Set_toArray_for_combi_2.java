package Backtracking;

import java.util.*;
import java.io.*;

//완전히 잘못된 발상 
public class Set_toArray_for_combi_2 {

	private static int N, max, min;
	private static int[] num, select;
	private static boolean[] visit;
	private static StringBuilder sb;
	//---------------------------------------------------------------------
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		select = new int[N];
		visit = new boolean[N];
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			num [i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(i==0) 		// +
				for(int j=0; j<n; j++) sb.append("+");
			else if(i==1)	// -
				for(int j=0; j<n; j++) sb.append("-");
			else if(i==2)  // *
				for(int j=0; j<n; j++) sb.append("*");
			else if(i==3)  // /
				for(int j=0; j<n; j++) sb.append("/");	
		}
		System.out.println(1/3+" "+(-1/3)+" "+(-4/3)+" "+(4/3));
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		permutation(0);
		bw.write(max+"\n"+min);
		bw.flush();
	}//---------------------------------------------------------------------
	
	public static void permutation(int k) {
		if(k==N) {
			cal_max_min();
			return;
		}
		for(int i=0; i<N; i++) {
			if(visit[i] == false) {
				visit[i] = true;
				select[k] = num[i];
				permutation(k+1);
				visit[i] = false;
			}
		}
	}//---------------------------------------------------------------------

	public static void cal_max_min() {
		int cnt = sb.length()-1;
		int R;
		int sum = select[0];
		for(int i=0; i<cnt; i++) {
			char c = sb.charAt(i);
			R = select[i+1];
			if(c=='+') 
				sum += R;
			else if(c=='-') 
				sum -= R;
			else if(c=='*') 
				sum *= R;
			else if(c=='/') 
				sum /= R;
		}
		if(max < sum) max = sum;
		if(min > sum) min = sum;
	}
}
