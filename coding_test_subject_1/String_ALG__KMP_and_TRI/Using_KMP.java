package String_ALG__KMP_and_TRI;

import java.util.*;
import java.io.*;

public class Using_KMP {
	/*�����Լ��� �迭�� ��� �޼ҵ�*/
	static int[] makePi(String pattern) {
		int M = pattern.length();
		char[] p = pattern.toCharArray();
		int[] pi = new int[M];	
		int j=0;
		
		for(int i=1; i<M; i++) {	//pi[0]�� ������ 0�̴�. ������ �����ص� ���ص� ��ó���̶� 0�̴�. �׷��� i=1���� �����ؼ� ������.
			while(j>0 && p[i] != p[j]) 
				j = pi[j-1];
		
			if(p[i]==p[j]) 
				 pi[i] = ++j;
		}
		return pi;
	}//=========================================================
	/*KMP �˰���*/
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
					ans.add(i-(M-1)); //i�� 0���� ������ ��, ��Ī�Ǵ� ���ڿ��� �����ϴ� �ε��� ��ġ
					j = pi[j];
				}
				else {
					j++;
				}
			}
		}
		
		return ans;
	}//=========================================================
	/*main�Լ�*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String A = br.readLine();
		String B = br.readLine();
		ArrayList<Integer> ans = kmp(A, B);
		
		bw.write(ans.size()+"\n");
		for(int i:ans)
			bw.write(i+"\n");	//i�� 0���� �����ϴ� ���ؿ����� ��Ī�� ���ڿ��� ������ġ
		bw.flush();
	}//=========================================================

}
