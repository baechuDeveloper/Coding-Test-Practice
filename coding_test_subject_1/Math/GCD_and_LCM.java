package Math;

import java.io.*;
import java.util.*;

// 2609번 최대공약수와 최대공배수 
public class GCD_and_LCM {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int gcd = GCD(a, b);
		int lcm = LCM(a, b, gcd);
		System.out.println(gcd+"\n"+lcm);
	}//===============================================
	
	//최대공약수 
	static int GCD(int a, int b) {
		while(b != 0) {
			int r = a%b;
			a = b;
			b = r;
		}
		return a;
	}//===============================================
	
	//최소공배수
	static int LCM(int a, int b, int gcd) {
		return a*b/gcd;
	}//===============================================
}
