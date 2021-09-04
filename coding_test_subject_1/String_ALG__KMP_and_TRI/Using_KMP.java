package String_ALG__KMP_and_TRI;

import java.util.*;
import java.io.*;

public class Using_KMP {
	/*실패함수를 배열로 담는 메소드*/
	static int[] makePi(String pattern) {
		int M = pattern.length();
		char[] p = pattern.toCharArray();
		int[] pi = new int[M];	
		int j=0;
		
		for(int i=1; i<M; i++) {	//pi[0]은 무조건 0이다. 어차피 실패해도 안해도 맨처음이라서 0이다. 그래서 i=1부터 시작해서 만들어간다.
			while(j>0 && p[i] != p[j]) 
				j = pi[j-1];
		
			if(p[i]==p[j]) 
				 pi[i] = ++j;
		}
		return pi;
	}//=========================================================
	/*KMP 알고리즘*/
	static ArrayList<Integer> kmp(String str, String pattern){
		ArrayList<Integer> ans = new ArrayList<>();
		int N = str.length();
		int M = pattern.length();
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		int[] pi = makePi(pattern);
		int j = 0;
		
		for(int i=0; i<N; i++) {
			while(j>0 && s[i] != p[j]) {
				j = pi[j-1];
			}
			if(s[i] == p[j]) {
				if(j == M-1) {
					ans.add(i-(M-1)); //i는 0부터 시작할 때, 매칭되는 문자열이 시작하는 인덱스 위치
					j = pi[j];
				}
				else {
					j++;
				}
			}
		}
		
		return ans;
	}//=========================================================
	/*main함수*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String A = br.readLine();
		String B = br.readLine();
		ArrayList<Integer> ans = kmp(A, B);
		
		bw.write(ans.size()+"\n");
		for(int i:ans)
			bw.write(i+"\n");	//i는 0부터 시작하는 기준에서의 매칭된 문자열의 시작위치
		bw.flush();
	}//=========================================================

}
