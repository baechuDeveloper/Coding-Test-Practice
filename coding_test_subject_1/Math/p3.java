package Math;
import java.util.*;
import java.io.*;

// 10757¹ø - Å« ¼ö A+B
public class p3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder A = new StringBuilder(st.nextToken());
		StringBuilder B = new StringBuilder(st.nextToken());
		int diff = A.length() - B.length();
		StringBuilder temp = new StringBuilder();
	
		if( diff>0 ) {
			for(int i=0; i<diff; i++) 
				temp.append("0");
			B = temp.append(B);
		}
		else if( diff<0 ) {
			diff = -diff;
			for(int i=0; i<diff; i++) 
				temp.append("0");
			A = temp.append(A);
		}
		
		StringBuilder answer = new StringBuilder();
		int len = A.length();
		int end = len;
		int up_plus = 0;
		while(end-->0) {
			
			int sum = up_plus + A.charAt(end) + B.charAt(end) - 96; //48+48
			if(sum>=10) up_plus = 1;
			else up_plus = 0;
			answer.insert(0, sum%10);
		}
		if(up_plus==1)
			answer.insert(0, 1);
		System.out.println(answer.toString());
	}

}
