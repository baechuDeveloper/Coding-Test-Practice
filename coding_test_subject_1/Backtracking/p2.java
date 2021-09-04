package Backtracking;

import java.util.*;
import java.io.*;

// 15651�� - N�� M(3)
// (1)�� ������ �ٸ����ѵ�... �̰� ��û�� �ӵ� ���̰� �ִ�. p2�� ��ü obj�� ���� ������ �ϴ°ͺ���. ��ٷ� static���·� main���� �����Ҷ� ������ �ð������� �Ǿ���.
//	2172ms -> 488ms
//  �׸��� bw.write�� ������ �ϴ°ͺ���. sb.append�� ������ �ϴ°��� ���� �ð� ������ �Ǿ���. ������ sb�� �������ϰ� �������� bw.write�� �����صδ°� ���� ��� �ϰͰ���.
//  ��ſ�!! �޸��� �� ���鿡���� bw.write�� �ٷ� �����ϴ°� ���� �� ȿ�����̴�.
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

