package String_ALG__KMP_and_TRI;
import java.util.*;
import java.io.*;

/**==========================================================================
 * 환영문자열이라는 문제이다. 
 * 이 경우 ABCDEF와 CDEFAB 가 서로 원형으로 보면 같은 문자열로 생각하는 것이다. 
 * 푸는 방식을 보았을 때 이걸 어떻게 KMP로 활용하지 생각했는데, 와 사람들이 진짜 머리가 좋다.
 * 환영 문자열을 KMP로 해결할려면 검색의 대상이 되는 문자열 str을 2배로 늘려서 pattern에 대한 것이 있는지 확인하는 것이다.
 * 이렇게하면 순서만 다른 환영 문자열에서 2배한 str에서 포함이 되어지기에 환영 문자열이면 반드시 kmp에서 나타난다.
  
 * str = "ABCDEF" 와 pattern = "CDEFAB" 라면 
 * "ABCDEFABCDEF" 에서 "CDEFAB"가 있는지 KMP로 하면 되는 것이다. 

 * 이 문제에서는 시계바늘에 맞추어 그 시각에 있으면 1 없으면 0을 주는 형태로 하겠다.
 * 이 형태를 일련의 문자열처럼 10101101101 같이 보이는 것의 문자열 탐색을 하는 것과 유사하게 만들어 주는 것이다.
 =============================================================================*/

// 10266번 - 시계 사진
public class p4__Watch_Picture_KMP {
	
	static int[] A_clock, B_clock;
	//========================================================
	//main함수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer A = new StringTokenizer(br.readLine());
		StringTokenizer B = new StringTokenizer(br.readLine());
		A_clock = new int[2*360_000];
		B_clock = new int[360_000];
		for(int i=0; i<N; i++) {
			int a = Integer.parseInt(A.nextToken());
			int b = Integer.parseInt(B.nextToken());
			A_clock[a]   = 1;
			A_clock[a+360_000] = 1;
			B_clock[b]   = 1;
		}
		
		if( kmp() ) 
			System.out.println("possible");
		else 
			System.out.println("impossible");
		
	}//========================================================
	//실패함수 이곳에 맞게
	static int[] getPi() {
		int lenB = B_clock.length;
		int[] pi = new int[lenB];
		int j = 0;
		
		for(int i=1; i<lenB; i++) {
			while(j>0 && B_clock[i]!=B_clock[j]) 
				j = pi[j-1];			
			if(B_clock[i]==B_clock[j]) 
				pi[i] = ++j;			
		}
		return pi;
	}//========================================================
	//KMP알고리즘 이곳에 맞게
	static boolean kmp() {
		int[] pi = getPi();
		int lenA = A_clock.length;
		int lenB = B_clock.length;
		int j = 0;

		for(int i=0; i<lenA; i++) {
			while(j>0 && A_clock[i]!=B_clock[j]) {
				j = pi[j-1];
			}
			if(A_clock[i] == B_clock[j]) {
				if(j == lenB-1) {
					return true;
				}
				else {
					j++;
				}
			}
		}
		return false;
	}//========================================================

}
