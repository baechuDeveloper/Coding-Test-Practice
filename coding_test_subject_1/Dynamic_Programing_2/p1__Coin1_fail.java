package Dynamic_Programing_2;
import java.util.*;
import java.io.*;

// 2293번 동전 1
public class p1__Coin1_fail {

	static int n, k;
	static int[] coin;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 동전의 종류 개수	// 각 종류의 동전은 몇번을 사용해도 괜찮다. 
		k = Integer.parseInt(st.nextToken());	// 동전으로 만들어야 하는 값
		coin = new int[n];
		for(int i=1; i<=n; i++)
			coin[i] = Integer.parseInt(br.readLine());
		/**
		 * dp를 풀어보고자 한다면 해당 문제가 쌓아갈 수 있는 방향성, 작은 문제의 결과가 큰 문제에 전달이 되어 완성되는지를 알아보는것도 방법이겠다. 
		 * 다시보면 점화식을 찾는다 혹은 구성해본다.
		 * 
		 * 예를 들어보자.
		 * 1원, 2원이 있으면 k=5원 을 만들어야 한다고 하자.
		 * 5원을 만드는 경우를 보자.
		 * [1 1 1 1 1]과 [1 1 1 2]와 [1 2 2]이다. 	3가지 경우가 있다.
		 * 4원을 만드는 경우를 보자
		 * [1 1 1 1], [1 1 2], [2 2] 			3가지 경우이다.
		 * 3원을 만드는 경우를 보자 
		 * [1 1 1], [1 2] 						2가지 경우이다
		 * 2원을 만드는 경우를 보자
		 * [1 1], [2]							2가지 경우이다.
		 * 1원을 만드는 경우를 보자 
		 * [1] 									1가지 경우이다.
		 * 만약 6원을 만드는 경우를 보자
		 * [1 1 1 1 1 1], [1 1 1 1 2], [1 1 2 2], [2 2 2] 	4가지 경우이다.
		 * 이런걸까..? dp[4]+coin[2] , dp[5]+coin[1] 으로 6원을 만들수 있는데 , dp[4]는 3가지, dp[5]는 3가지이다.  
		 * 
		 * 2원 3원 5원이 있으면 
		 * 1원을 만든다 []
		 * 2원을 만든다 [2]
		 * 3원을 만든다 [3]
		 * 4원을 만든다 [2 2]
		 * 5원을 만든다 [2 3], [5]
		 * 6원을 만든다 [2 2 2], [3 3]
		 * 7원을 만든다 [2 2 3], [2 5]
		 * 8원을 만든다 [2 2 2 2], [2 3 3], [3 5]
		 * 9원을 만든다 [2 2 2 3], [2 2 5], [3 3 3]  ->[1 8] [2 7] [3 6] [4 5] - [1이 존재 안하므로 무효!] [2 7]은 존재하니 2개
		 *  
		 *  근데 이렇게 하니깐 중복에 대한 부분을 극복하기 어려웠다. 
		 * */
	}

}
