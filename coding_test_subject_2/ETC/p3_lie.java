package ETC;

import java.io.*;
import java.util.*;
// 1043번 - 거짓말 
public class p3_lie {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//사람의 수
		int M = Integer.parseInt(st.nextToken());	//파티의 수
		boolean[] TRUETRUE = new boolean[N+1];
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for(int i=0; i<M; i++)
			list.add(new ArrayList<>());
		
		st = new StringTokenizer(br.readLine());
		int truenum = Integer.parseInt(st.nextToken());	//진실을 아는 사람 수
		for(int i=0; i<truenum; i++) {
			TRUETRUE[ Integer.parseInt(st.nextToken()) ] = true;
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int partysize = Integer.parseInt(st.nextToken());
			for(int j=0; j<partysize; j++) {
				
			}
		}
	}

}
