package ETC;

import java.io.*;
import java.util.*;

// 1017번 - 소수쌍 
public class prime_pair {

	static int N;
	static boolean[] prime;
	static int[] arr;
	static ArrayList<ArrayList<Integer>> list;
	static Set<Integer> set;
	//=========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int num = 2001;
		prime = new boolean[num+1];    //false 인것은 모두 소수 
		prime[0] = prime[1] = true; //true가 된것은 prime소수가 '될 수 없는 자'들이다.
	
		//2 부터 숫자를 키워가며 배수들을 제외
		for(int i=2; i*i<=num; i++) {	//i는 1씩 증가하면서 새로 배치될 배수를 정해주는 역활
			for(int j=i*i; j<=num; j+=i) { //j는 해당 i의 제곱부터 배수만큼 올라간다. (제곱에 배수를 더한 형태는 수학적으로 소수가 아님을 보여주게 되었나라카드라)
				prime[j] = true;        //2를 제외한 2의 배수 true
			}
		}
		// prime값이 false인 것들이 소수가 된다. 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) 
			list.add(new ArrayList<>());
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				
			}
		}

	}//=========================================================

}
