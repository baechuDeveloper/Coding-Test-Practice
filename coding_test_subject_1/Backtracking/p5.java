package Backtracking;
import java.util.*;
import java.io.*;
//15650번 - N과 M(2)
public class p5 {
	
	static int N, M, visit=0;	//nCm //visit는 비트마스크
	static StringBuilder sb;
	//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		permutation(0, 0);
		bw.write(sb.toString());
		bw.flush();
	}//========================================================
	static void permutation(int index, int count) {
		if(count==M) {
			for(int i=0; i<N; i++) 
				if( (visit & (1<<i)) == 1<<i ) {
					sb.append((i+1)+" ");
				}
			sb.append("\n");
			return;
		}
		
		for(int i=index; i<N; i++) {
			if( (visit & (1<<i)) == 0 ){	//아직 방문을 하지 않음
				visit = visit | (1<<i);
				permutation(i+1, count+1);
				visit = visit & ~(1<<i);
			}
		}
	}//========================================================
}
