package String_ALG__KMP_and_TRI;

import java.io.*;

/**
 * 접두사와 접미사를 이용하는 것 
 * 현재의 광고판을 기준으로 최악은 N개의 길이를 가진 광고를 반복
 * 최고는 1개의 길이를 가진 광고를 반복 
 * 실패함수를 통해서 접두사와 접미사의 이어지는 관계를 확인하는데
 * 여기서 더 생각하면 좋은건 
 * 문자열 접미사의 마지막 문자는 이와 해당되는 접두사의 문자 그 뒤에 문자열로 이어질 수 있다는 보이지않는 상상을 할수가있다. 
 * 그래서 마지막 접미사를 기준으로 접두사의 어디에 위치한지 알아두면 아마 해당 접두사를 반복하는 광고로 인식할수가 있게된다.
 * "abcdab" 라면 접미사기준으로 접미사는 ab로 잡을수있다.(물론 cdab로 잡아도 되지만 실패함수를 바로 이용할것이면 전자를 따른다.)
 * 그렇게 되면 N - pi[N-1]까지 한 "abcd"가 가장 짧은 광고로서 계속 반복되고 있다고 상상을 할수있다.
 * */
public class p3__ADs {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());	//광고판의 크기, 사실 없어도 되겠더라.
		String str = br.readLine();	//현재 광고판의 보이는 문자열 
		int[] pi = makePi(str);
		for(int i:pi) System.out.println(i);
		System.out.println(L - pi[L-1]);
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
