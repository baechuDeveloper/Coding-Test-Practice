package Two_Pointer_alg;

/**==========================================================
 * 백준 - 수들의 합 2 - https://www.acmicpc.net/problem/2003
 * N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다. 
 * 이 수열의 i번째 수부터 j번째 수까지의 합 A[i]+A[i+1]+…+A[j-1]+A[j]가 
 * M이 되는 경우의 수를 구하는 프로그램을 작성하시오.
 ============================================================**/

import java.util.*;
import java.io.*;

public class p1 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st =  new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// N , 배열의 원소 개수 
		int M = Integer.parseInt(st.nextToken());	// M , 전체 원소의 합
		
		st = new StringTokenizer(br.readLine());
		int len = st.countTokens();					// 배열의 원소 개수 , 사실 N과 동일 
		/*int[] arr = new int[len];					// 배열
		for(int i=0; i<len; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		*/
		int[] arr = new int[len+1];
		for(int i=1; i<=len; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		// 현재의 sum에 대해 진행을 하고. 현재의 sum을 만든 지금의 범위는 L과 R로 구성됨. 
		// if조건에 들어가면 sum의 값이 바뀌고 그에 따른 L혹은 R의 값도 바뀌게됨. 
		int L=0;		// 현재 왼쪽 인덱스
		int R=0;		// 현재 오른쪽 인덱스
		long sum=0;		// 현재까지 sum
		int count = 0;	// 경우의 수
		/*
		// L에는 너가 제거해볼 값이있어, R에는 너가 더해볼 값이 있어 라고 알려주는 형태가 된다. R은 N-1까지 존재하며 N은 컴파일상 존재 하지 않는다.
		while(L<=R) {	// 투 포인터 알고리즘의 기본적인 조건 방식 , 문제에 양식에 맞게 조절이 되거나 바꾸어 더 알맞은 종료조건을 만든다.
						// 단, 이렇게 R과 L의 맞물림으로 인해 종료가 될수 있도록 조건을 만들어야 할것이다.
						// L초과 R이하의 범위의 합	
						// 알수있듯이 L과 R이 같을때는 해당 번호에서 멈추어 '아무것도 범위로 가지지 않아서 합이 0 인상태'이다. 
						// 그러기에 L이 R을 넘는다는거은 우리의 밑의 경우에 따라서 일어나지 않지만 그래도 의미상 부여해주는 것이된다.
						// 그렇기에 true로 조건을 맞추어도 상관이 없다. 
			if(sum >= M) {			// 이곳에 들어오면 L이 늘어나서 범위가 줄어들어 sum은 작아진다.
				if(sum == M) 
					count++;
				sum -= arr[L];
				L++;		//왼쪽 인덱스 이동
			}	
			else{					// 이곳에 들어오면 R이 늘어나서 범위가 늘어나서 sum은 커진다.
				if(R>=N) 	//또 다른 빠져나오는 조건
					break;
				sum += arr[R];
				R++;		//오른쪽 인덱스 이동 
			}
			
		}*/
		
		// L <  <= R  (L초과 R이하의 범위의 합)
		// 위에는 자 이제 내가 이런 조건으로 해본다. 이고
		// 이건 직관적으로 현재의 범위를 알려주며 현재의 합에 대한 정보를 직관적으로 받아들인다. 물론 둘다 같다. 대신 인덱스 0부터 시작하느냐 인덱스 1부터 시작하게 하느냐로 직관적으로 변하고 선위연산자로 다시 while로 돌아올때 직관적으로 해준다.  
		while(true) {	
			if(sum>=M) {
				if(sum==M)
					count++;
				sum -= arr[++L];
			}
			else {
				if(R>=N)
					break;
				sum += arr[++R];
			}
		}
		
		bw.write(count+"");
		bw.flush();
		
		br.close();
		bw.close();
	}

}
