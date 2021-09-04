package Divide_and_Conquer;

import java.util.*;
import java.io.*;
//11444번 - 피보나치 수 6
/**===============================================
 * 대학 때 배운 '선형대수'를 활용할 차례
 * '선형방정식'을 '행렬의 형태'로 두어서 작업을 해보자.
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
 * 이 형태를 이렇게 치환해서 볼수잇다. 
 *  Qn+1  =   A  *  Qn
 * 
 * 다시 이 형태는	Q1 = A*Q0
 * 				Q2 = A*Q1	 = A^2 * Q0
 * 				Qn = A*Qn-1  = A^n * Q0
 * 
 * 이 형태를 행렬의 제곱을 통한 방법으로 진행을 할수있게 된다.
 * 				   
 * A = [1 1]	  Q0 = [F1]	= [1]		Qn = [Fn+1]
 * 	   [1 0]	,	   [F0]	  [0]	, 		 [Fn  ]
 *===================================================*/
//https://st-lab.tistory.com/252
public class P5__fibo6_by_matrix_pow {
	//행렬 자료구조
	static class Mat{
		long[][] mat;
		Mat(long[][] m){
			mat = m.clone();
		}
	}//========================================================
	//클래스 변수
	static Long N;
	static Map<Long, Mat> map;	//A행렬의 제곱근에 대한 값이 들어가있다. 
	//========================================================
	//피보나치 함수
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
	//main 함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		if(N==0) { System.out.println(0); return; }
		long[][] A = { {1, 1}, 
					   {1, 0} };
		map = new HashMap<>();
		map.put(1L, new Mat(A));	//행렬 A를 미리 넣어준다. 
		fibo_find_A(N);	// A^N 인 행렬의 제곱근을 찾는다. 
		
		//Qn = A^N * Q0
		//Fn은 A^N의 2행 1열이 된다.
		long fibo_n = map.get(N).mat[1][0];
		System.out.println(fibo_n);
	}//========================================================
}
