package ETC;

import java.io.*;
import java.util.*;
//https://maivve.tistory.com/46
public class p2_pow_nono {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());		// 1ÀÇ Á¦°ö <=
		long max = Long.parseLong(st.nextToken());		// <= 1_000_000ÀÇ Á¦°ö
		
		long a = 1_000_000_000;
		System.out.println(a);
		
		
		
	}//===========================================================

	private static long find_pow(long val, boolean up) {
		long left = 1; 
		long right = 1_000_000 + 1_000;
		long mid = (left+right)/2;
		
		while(left<=right) {
			mid = (left+right)/2;
			
			
			
		}
		
		return 0;
	}//===========================================================
}
