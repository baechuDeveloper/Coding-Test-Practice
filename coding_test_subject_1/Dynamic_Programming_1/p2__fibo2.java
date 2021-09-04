package Dynamic_Programming_1;

import java.io.*;
//피보나치 함수
public class p2__fibo2{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int test_n = Integer.parseInt(br.readLine());
		for(int z=0; z<test_n; z++) {
			int N = Integer.parseInt(br.readLine());
			if(N==0) {
				bw.write(1+" "+0+"\n");
				continue;
			}
			long [][] fib = new long[N+1][2];	// [i][0] 은 0의 출력개수 , [i][1]은 1을 출력해본 개수
			fib[0][0] = 1;
			fib[0][1] = 0;
			fib[1][0] = 0;
			fib[1][1] = 1;

			for(int i=2; i<N+1; i++) {
				fib[i][0] = fib[i-1][0] + fib[i-2][0];
				fib[i][1] = fib[i-1][1] + fib[i-2][1];	
			}
			bw.write(fib[N][0]+" "+fib[N][1]+"\n");
		
		}
		bw.flush();
	}

}