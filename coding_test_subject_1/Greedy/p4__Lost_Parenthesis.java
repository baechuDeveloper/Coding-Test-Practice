package Greedy;

import java.io.*;

//1541번 - 잃어버린 괄호 
/* =================================================================
 * 바로 느낌이 왔는데 이게 그리디가 맞을까...문자열 처리같다. 
 * 결국 +,- 연산들의 순서를 어떻게 하냐에 차이로 최소 값을 구하라고 한다면 
 * 
 * 핵심적인 차이는 괄호로 인해 그 안의 부호가 역으로 된다는 점을 이용하는 것이다. 
 * 
 *  -(50 + 40) 으로 괄호를 치면 -90이라느 결과가 나오듯 앞에 -를 이용해서 뒤에 +를 가능한 -로 바꾸어 주면 된다. 
 * 
 * -40+50-23-45-67+13 = -(40+50)-23-45-(67+13) 
 * 50+40+50+30+50-34-45+23+24+54 = (50+40+50+30+50)-34-(45+23+24+54)
 * 
 * '-'가 한번이라도 나오면 그 뒤에 나오는 모든 숫자는 -로 바꾸어도 된다.
 * 설령 10-40+30-45+23 이라해도 앞에 -와 뒤에 -로 인해 처음 -가 나온 이후로 모두 -가 된다. 
 *=================================================================== */
public class p4__Lost_Parenthesis {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(br.readLine());
		if(sb.charAt(0)=='-') sb.insert(0, 0);
		int len = sb.length();
		int ans = 0;
		int start = 0;
		boolean minus = false;
		
		for(int i=0; i<len; i++) {
			char c = sb.charAt(i);
			if(c=='+') {
				if(minus==false) 
					ans += Integer.parseInt(sb.substring(start, i));	//start부터 i까지(미포함)
				else 
					ans -= Integer.parseInt(sb.substring(start, i));	//start부터 i까지(미포함)
				start = i+1;
			}
			else if(c=='-') {
				if(minus==false) 
					ans += Integer.parseInt(sb.substring(start, i));	//start부터 i까지(미포함)
				else 
					ans -= Integer.parseInt(sb.substring(start, i));	//start부터 i까지(미포함)
				minus = true;
				start = i+1;
			}
		}
		
		if(minus==false) 
			ans+=Integer.parseInt(sb.substring(start, len));	//start부터 마지막까지(미포함)
		else 
			ans-=Integer.parseInt(sb.substring(start, len));	//start부터 마지막까지(미포함)
		
		System.out.println(ans);
	}

}
