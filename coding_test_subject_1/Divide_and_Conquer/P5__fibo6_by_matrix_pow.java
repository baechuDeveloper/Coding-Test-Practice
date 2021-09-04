package Divide_and_Conquer;

import java.util.*;
import java.io.*;
//11444�� - �Ǻ���ġ �� 6
/**===============================================
 * ���� �� ��� '�������'�� Ȱ���� ����
 * '����������'�� '����� ����'�� �ξ �۾��� �غ���.
 * ax+by = [a 0] [x]
 * 		   [0 b] [y]
 * 
 * 
 * Fn+2 = Fn+1 + Fn;
 * 		= [1 1] [Fn+1]		
 * 			    [Fn	 ]			   
 * 		
 * Fn+1 = Fn+1 + 0*Fn
 * 		= [1 0] [Fn+1]
 *  			[Fn	]
 * 
 * [Fn+2] = [1 1] [Fn+1]
 * [Fn+1]	[1 0] [Fn  ]
 * 
 * �� ���¸� �̷��� ġȯ�ؼ� �����մ�. 
 *  Qn+1  =   A  *  Qn
 * 
 * �ٽ� �� ���´�	Q1 = A*Q0
 * 				Q2 = A*Q1	 = A^2 * Q0
 * 				Qn = A*Qn-1  = A^n * Q0
 * 
 * �� ���¸� ����� ������ ���� ������� ������ �Ҽ��ְ� �ȴ�.
 * 				   
 * A = [1 1]	  Q0 = [F1]	= [1]		Qn = [Fn+1]
 * 	   [1 0]	,	   [F0]	  [0]	, 		 [Fn  ]
 *===================================================*/
//https://st-lab.tistory.com/252
public class P5__fibo6_by_matrix_pow {
	//��� �ڷᱸ��
	static class Mat{
		long[][] mat;
		Mat(long[][] m){
			mat = m.clone();
		}
	}//========================================================
	//Ŭ���� ����
	static Long N;
	static Map<Long, Mat> map;	//A����� �����ٿ� ���� ���� ���ִ�. 
	//========================================================
	//�Ǻ���ġ �Լ�
	static void fibo_find_A(long n) {
		if(map.containsKey(n)) 
			return;
		long L = n/2;
		long R = n-L;
		fibo_find_A(L);
		fibo_find_A(R);
		long[][] lmat = map.get(L).mat;
		long[][] rmat = map.get(R).mat;
		long[][] smat = new long[2][2];
		
		for(int x=0; x<2; x++) {
			for(int z=0; z<2; z++) {
				for(int y=0; y<2; y++) {
					smat[x][z] += (lmat[x][y] * rmat[y][z]);
				}
				smat[x][z] %= 1_000_000_007;
			}
		}
		map.put(n, new Mat(smat));
	}//========================================================
	//main �Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		if(N==0) { System.out.println(0); return; }
		long[][] A = { {1, 1}, 
					   {1, 0} };
		map = new HashMap<>();
		map.put(1L, new Mat(A));	//��� A�� �̸� �־��ش�. 
		fibo_find_A(N);	// A^N �� ����� �������� ã�´�. 
		
		//Qn = A^N * Q0
		//Fn�� A^N�� 2�� 1���� �ȴ�.
		long fibo_n = map.get(N).mat[1][0];
		System.out.println(fibo_n);
	}//========================================================
}
