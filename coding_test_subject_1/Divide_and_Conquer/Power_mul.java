package Divide_and_Conquer;
// 재귀를 응용하는 알고리즘, 분할 정복.

import java.util.*;
import java.io.*;

// 1629번 백준 곱셈 - 거듭제곱에 대해 분할정복으로 빠르게 계산하는 방법.
/**
 * 아주 효과적인 mod의 방법!
  A mod B ... 즉 A % B를 한다면 
  A = (a*b*c*d) 일때
  (a*b*c*d) % B 라는 형태가 되는건 알고있을텐데 이와 항상 같은 값은
  ( (a%B) * (b%B) * (c%B) * (d%B) ) % B  이다.
   그리고
   ( (110 % 23) * (45 % 23) * 120 * 68) % 23 이렇게 A 안에 부분적으로 mod를 진행해도 괜찮다.
   마지막에 mod B를 하는 식이 라면 이렇게 A 안에 몇번의 mod B가 들어와도 문제가 없다.
  
    결론적으로 가장 바깥 mod B를 계속 ( A )%B 의 형태를 가지고 있는 한 
  A의 부분적인 인수에 대해서 몇번의 mod B가 들어가도 상관이 없다. 
  A의 모든 인수에 대해'% B' 를 해주고 "최종적으로" 마지막에도 꼭! '% B'를 해주는 것을 진행 하면 된다.    
  
 * **/
public class Power_mul {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long A, B, C; // A를 B번 곱한 수를 C로 나누어서 해당 나머지 값을 구한다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken()); //피제곱수
		B = Long.parseLong(st.nextToken()); //제곱수
		C = Long.parseLong(st.nextToken()); //mod C
		//어차피 "마지막에는 mod c"를 할 식이 생긴다면 이러한 방법이 적용될 수 있다.
		System.out.println(power(A%C,B,C));	//결과적으로 C로 나머지를 구해둘건데 해당 값에 대해서 먼저 mod를 해도 아래 처럼 무관 하다.
		
		/*System.out.println( (5*6*112*23*56)%9 );
		System.out.println( ( (5%9) * (6%9) * (112%9) * (23%9) * (56%9) ) %9 ); 
		System.out.println( (5%9 * 6%9 * 112%9 * 23%9 * 56%9)%9); //괄호를 뺴도 된다. 마지막도
		System.out.println( 5%9 * 6%9 * 112%9 * 23%9 * 56%9 %9); //괄호를 뺴도 된다. 마지막도
		System.out.println( ( 5%9 * 6%9 * 112 * 23%9 * 56 )%9); //이렇게 선택적으로 해도 안해도 결과는 같다. 이 안에서 몇번의 mod가 들어가도 동일하다. 
		*/
		// 결론적으로 가장 바깥 mod B를 계속 ( A )%B 의 형태를 가지고 있는 한 
		// A의 부분적인 인수에 대해서 몇번의 mod B가 들어가도 상관이 없다. 
			
	}
	public static long power(long a, long b, long c) {
		
		if(b==0) return 1;
		if(b==1) return a;
		
		long temp = power(a%c, b/2, c);
		
		if(b%2 == 0) {
			return (temp * temp) % c;
		}
		else {
			return (((temp*temp)%c)*a)%c;	//mod c가 안에 몇번이나 들어가도 생관은 없으나 long의 최대크기를 넘지않도록 곳곳에 mod c를 해주는것도 방법이다. 
		}
	}

}
