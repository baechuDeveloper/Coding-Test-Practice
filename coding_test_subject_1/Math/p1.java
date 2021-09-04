package Math;

import java.util.*;
import java.io.*;

// 5086번 - 배수와 약수
// factor - 약수, multiple - 배수, neither - 둘 다 아님
public class p1 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); // 두 수가 같은 경우는 없다.
			if(a==0 && b==0) break;
			
			if( a<b && b%a==0 ) {	//a는 b의 약수
				sb.append("factor\n");
			}
			else if( a>b && a%b==0 ) {	//a는 b의 배수
				sb.append("multiple\n");
			}
			else {	// a는 b와 아무것도 아니다.
				sb.append("neither\n");
			}	
		}
		
		bw.write(sb.toString());
		bw.flush();
		
	}

}
