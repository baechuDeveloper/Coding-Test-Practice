package Divide_and_Conquer;

import java.util.*;
import java.io.*;

// 10830번 - 행렬 제곱 
public class p4__matrix_pow {

	static class Matrix{
		int[][] mat;
		Matrix(int[][] m){
			mat = m.clone();
		}
	}//======================================================
	//클래스 변수
	static int N;
	static Map<Long, Matrix> map;	//이걸 통하면 DP로 공간을 미리 확보하지 않고 메모이제이션 역활을 해줄 수 있다.
	//======================================================
	//해결함수 
	static void cal(long b) {
		//이미 값을 구해보았다면 넘어간다.
		if(map.containsKey(b)) 
			return;
		//반으로 나누어 진행을 한다. 
		long L = b/2;	
		long R = b-L;
		
		cal(L);
		cal(R);
		int[][] lmat = map.get(L).mat;
		int[][] rmat = map.get(R).mat;
		int[][] smat = new int[N][N];
		//행렬곱 계산
		for(int x=0; x<N; x++) {
			for(int z=0; z<N; z++) {
				for(int y=0; y<N; y++) {
					smat[x][z] += lmat[x][y] * rmat[y][z];
				}
				smat[x][z]%=1000;
			}
		}	
		map.put(b, new Matrix(smat));
	}//======================================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		int[][] A = new int[N][N];
		map = new HashMap<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				A[i][j] = Integer.parseInt(st.nextToken()) % 1000;	//이곳부터도 1000으로 나머지를 해두면 된다. 결국 A^1로서의 의미를 갖는 것이라 이곳부터다.
		}	
		map.put(1L, new Matrix(A));
		cal(B);
		int[][] result = map.get(B).mat;
		pprint(result);
	}//======================================================
	
	static void pprint(int[][] aa) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(aa[i][j]+" ");
			}System.out.println();
		}
	}
}
