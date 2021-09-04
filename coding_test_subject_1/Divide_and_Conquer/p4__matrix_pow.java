package Divide_and_Conquer;

import java.util.*;
import java.io.*;

// 10830�� - ��� ���� 
public class p4__matrix_pow {

	static class Matrix{
		int[][] mat;
		Matrix(int[][] m){
			mat = m.clone();
		}
	}//======================================================
	//Ŭ���� ����
	static int N;
	static Map<Long, Matrix> map;	//�̰� ���ϸ� DP�� ������ �̸� Ȯ������ �ʰ� �޸������̼� ��Ȱ�� ���� �� �ִ�.
	//======================================================
	//�ذ��Լ� 
	static void cal(long b) {
		//�̹� ���� ���غ��Ҵٸ� �Ѿ��.
		if(map.containsKey(b)) 
			return;
		//������ ������ ������ �Ѵ�. 
		long L = b/2;	
		long R = b-L;
		
		cal(L);
		cal(R);
		int[][] lmat = map.get(L).mat;
		int[][] rmat = map.get(R).mat;
		int[][] smat = new int[N][N];
		//��İ� ���
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
				A[i][j] = Integer.parseInt(st.nextToken()) % 1000;	//�̰����͵� 1000���� �������� �صθ� �ȴ�. �ᱹ A^1�μ��� �ǹ̸� ���� ���̶� �̰����ʹ�.
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
