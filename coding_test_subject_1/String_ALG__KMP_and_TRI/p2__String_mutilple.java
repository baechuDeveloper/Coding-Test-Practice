package String_ALG__KMP_and_TRI;

import java.io.*;
/**----------------------------------------------------------
 * 실패함수를 구하는 방법을 응용해보았다.
 * 왜냐하면 접두사에 대해서 그 접두사가 얼마큼 반복되고 있는지 파악하는데 실패함수만큼 유용한게 없다. 
 * 접두사를 통해 반복이 진행되는게 맞는지 확인. 그때 마지막에 해당하는 pi[len-1]의 값은 정상적이라면 접두사의 마지막을 의미할테니, 이를 이용해서 구해본다. 
 * 그런 것은 다 1로 처리하면 될것이다.
 -----------------------------------------------------------*/
// 4354번 - 문자열 제곱 
public class p2__String_mutilple {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = "";
		while((str=br.readLine())!=null && str.length()!=0) {
			if(str.length()==1 && str.charAt(0)=='.') {
				break;
			}
			int[] pi = makePi(str);
			int len = str.length();
			//for(int i:pi) System.out.print(i+" "); System.out.println();
			
			int cycleStr = len - pi[len-1];	//반복 될 수 있는 최대길이
			if(len/2>=cycleStr && len%cycleStr==0) 	//len의 절반보다 크지 않고, 반복될 수 있는 최대길이가 len으로 나누어 떨어질 수 있다면.
				bw.write(len/cycleStr+"\n");
			else
				bw.write(1+"\n");
		}
		bw.flush();

	}//========================================================
	
	static int[] makePi(String pattern) {
		int M = pattern.length();
		int[] pi = new int[M];
		char[] p = pattern.toCharArray();
		int j=0;
		
		for(int i=1; i<M; i++) {
			while(j>0 && p[i]!=p[j]) 
				j = pi[j-1];
			if(p[i] == p[j]) 
				pi[i] = ++j;	
		}

		return pi;
	}//========================================================

}
