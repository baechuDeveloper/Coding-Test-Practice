package Dynamic_Programming_1;

import java.io.*;
//피보나치수 2
public class p1__fibo{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		long [] fib = new long[N+1];
		fib[0] = 0;
		fib[1] = 1;
		
		for(int i=2; i<N+1; i++) 
			fib[i] = fib[i-1] + fib[i-2];
		
		bw.write(fib[N]+"");
		bw.flush();
	}

}