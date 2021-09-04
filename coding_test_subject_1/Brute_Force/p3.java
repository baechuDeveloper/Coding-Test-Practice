package Brute_Force;

import java.util.*;
import java.io.*;

// ü���� �ٽ� ĥ�ϱ�
public class p3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] chess = new String[N];	//N�� �� ����, M�� �� ����
		for(int i=0; i<N; i++) 
			chess[i] = br.readLine();

		String case_B = "BWBWBWBW";	// ���� B�� �����ϴ� ��
		String case_W = "WBWBWBWB"; // ���� W�� �����ϴ� ��

		int min_count = Integer.MAX_VALUE;	

		for(int i=0; i<=N-8; i++) {		
			for(int j=0; j<=M-8; j++) {	// i�� j�� ���� ��ġ�� �˷��ش�.
				int count_1=0, count_2 = 0;
				for(int k=0; k<4; k++) {	//��ó���� case_B�϶�
					count_1 += compareS_8(case_B, chess[i+ k*2].substring(j, j+8));		//������ ��ȣ�� ������ ��Ű�� �ʴ´�. 
					count_1 += compareS_8(case_W, chess[i+ k*2 + 1].substring(j, j+8));
				}

				for(int k=0; k<4; k++) {	//��ó���� case_B�϶�
					count_2 += compareS_8(case_W, chess[i+ k*2].substring(j, j+8));
					count_2 += compareS_8(case_B, chess[i+ k*2 + 1].substring(j, j+8));
				}
				if(min_count > count_1) min_count = count_1;
				if(min_count > count_2) min_count = count_2;
			}
		}
		
		bw.write(min_count+"");
		bw.flush();
		bw.close();
		br.close();
	}//==================================================

	public static int compareS_8(String a, String b) {
		int count = 0;
		for(int i=0; i<8; i++) 
			if(a.charAt(i) != b.charAt(i))
				count++;
		return count;
	}//==================================================

}
