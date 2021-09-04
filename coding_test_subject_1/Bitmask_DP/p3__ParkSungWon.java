package Bitmask_DP;

import java.util.*;
import java.io.*;

// 비트마스크, 메모이제이션, GCD가 사용된다.  
// 이해하기보다는 굉장히 수학적이다. 
// https://redbinalgorithm.tistory.com/144
/**--------------------------------------------------------
 *  * 나머지를 구하는 mod의 새로운 접근 * 
 *  5221에 17에 대한 나머지를 구하기 위해 5221 % 17 이 아니라 
 *  앞에서 부터 
 *  5 % 17 = 5 			이것을 두번째 문자열과 함께
 *  52 % 17  = 1 		이것을 세번째 문자열과 함께
 *  12 % 17  = 12 		이것을 네번째 문자열과 함께
 *  121 % 17 = 0 		마지막까지 했을 때 나온 나머지가 5221 % 17에 대한 나머지를 구하게 된다. 

 이렇게하면 길이가 긴 숫자에 대해서도 나머지를 구할 수가 있다. 
 ----------------------------------------------------------**/
//1086번 박성원 - 박성원은 풀지 못한 문제
public class p3__ParkSungWon {

	static int N, K; //집합의 수 N, 나누려는 K  	N<=15인 자연수 ,	K<=100인 자연수
	static char[][] arr;	 //집합의 원소
	static int [][] dpMod;	 //집합의 원소를 K로 나누어본 나머지 	[현재를 대표하는 mod값][집합의 정수 번호] = getmod메소드를 통해 나온 결과 값
	static long[][] dp;		 //[i][j] - [현재를 대표하는 mod값][bit로 표현한 방문표시]
	static long[] factorial; //결과의 분모 계산을 위함
	static long p, q;		 //p는 가능한 경우의 수(분자), q는 전체 경우의 수(분모)
	//-------------------------------------------------------------------------

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][];
		for(int i=0; i<N; i++) 
			arr[i] = br.readLine().toCharArray();	//해당 줄을 문자하나하나 char배열로 만들어준다.(띄어쓰기도 문자로 포함되더라)
		K = Integer.parseInt(br.readLine());

		dp = new long[K][ 1<<N ];
		dpMod = new int[K][N];
		for(int i=0; i<K; i++) {
			Arrays.fill(dp[i], -1);
			Arrays.fill(dpMod[i], -1);
		}

		//단순히 N!의 값을 구하려는 시도 N이 15보다 작거나 같은 자연수라서 빨리 구해진다. 
		factorial = new long[N+1];
		factorial[1] = 1L;
		for (int i=2; i<=N; i++) 
			factorial[i] = (long)i *  factorial[i-1];

		p = memoi(0, 0, 0); //전제 경우에 대해 K로 mod가 잘 나누어 떨어지는 경우의 수를 구해준다.  
		q =  factorial[N];	//분모에는 팩토리알로서 모든 경우의 수가 들어가게 된다.

		//분자와 분모의 최적화
		if (p == 0) {
			q = 1;
		} else {
			long gcd = GCD(p, q);
			p /= gcd;
			q /= gcd;
		}

		System.out.println(p + "/" + q);
	}//-------------------------------------------------------------------------
	//mod는 현재의 값을 바로 넣는 것이 너무나 큰 값이라서 공간로서도 부적절하니 mod K를 통해서 나온 값도 원래 결국 mod를 할 것이었으니 대표값으로 쓸 수 있게해준다. 
	//	     또한 계속 추후에 나올 정수를 이어붙여 mod를 이어나갈 값을 뜻한다. 
	//now는 현재 살펴본 개수
	//flag는 사용증이다. 비트마스크로 현재 사용되고있는 비트상태를 알려준다.
	static long memoi(int mod, int now, int flag) {	
		if(dp[mod][flag] != -1) //이미 계산한 결과가 있다면 바로 내보내줌.
			return dp[mod][flag];

		if(now == N) //마지막에 도달 했다면 
			return dp[mod][flag] = mod == 0? 1L : 0;	//마지막에 도달한 상태의 mod값이 0. 즉 어떤 원소집합에게 제대로 나누어 떨어질때 해당 값이 가능하기에 카운트로 1을 돌려준다.

		long sum = 0;
		for(int i=0; i<N; i++) { //다음에 해당하는 집합의 정수에 해당하는 번호를 가리킨다. 
			if( (flag & (1<<i)) == 0 ) {	//방문 한곳을 체크하고 있다. 아직 이용 안했다면 이용한다.
				int next_flag = flag|(1<<i);
				sum += memoi( getMod(mod,i), now+1, next_flag );
			}
		}
		return dp[mod][flag] = sum;
	}//-------------------------------------------------------------------------
	//맨 위에 적은 mod를 구한 형식의 공식을 이어간다. 
	static int getMod(int mod, int n) {	//n은 집합의 정수에 해당하는 번호
		if(dpMod[mod][n] != -1) //이미 계산한 결과가 있다면 바로 내보내줌.
			return dpMod[mod][n];

		int cur = mod;
		for(int j=0; j<arr[n].length; j++){	//맨 위에도 적었지만, 한자리씩 계속 넘겨주면서 진행을 하게된다. 집합의 정수의 값이 1234면 1 2 3 4씩 이어붙이면서 전부 계산해준다. 
			cur *= 10;
			cur = (cur + (arr[n][j]-'0') ) % K;	
		}
		return dpMod[mod][n] = cur;
	}//-------------------------------------------------------------------------
	//최대공약수 - 분모와 분자에 대한 알맞은 줄인 형태를 보여주려고.
	static long GCD(long a, long b) {
		if(a>b) {	//아무튼 b를 더 큰 값으로 두게 해서 계산을 진행한다. 
			long temp = a;
			a = b;
			b = temp;
		}
		while(a % b != 0) { //a와 b가 배수가 아닐때 반복
			long temp = a % b;
			a = b;
			b = temp;
		}
		return b;
	}//-------------------------------------------------------------------------

}
