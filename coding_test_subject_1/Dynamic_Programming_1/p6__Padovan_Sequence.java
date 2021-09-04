package Dynamic_Programming_1;

import java.io.*;

// 9461번 - 파도반 수열
public class p6__Padovan_Sequence {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_n = Integer.parseInt(br.readLine());
		for(int z=0; z<test_n; z++) {
			int N = Integer.parseInt(br.readLine());
			
			long[] pado = new long[101];
			
			pado[1] = 1;
			pado[2] = 1;
			pado[3] = 1;
			pado[4] = 2;
			pado[5] = 2;
			pado[6] = 3;
			pado[7] = 4;
			pado[8] = 5;
			pado[9] = 7;
			pado[10] = 9;
			for(int i=11; i<=N; i++) 
				pado[i] = pado[i-1] + pado[i-5];
			
			bw.write(pado[N]+"\n");
		}
		bw.flush();
	}
}
