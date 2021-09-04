package Binary_search;

import java.io.*;
import java.util.*;

// 2805번-나무 자르기
//최대값 최소값을 이용한 이분탐색
public class basic_Parametric_Search_2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 나무의 수
		int M = Integer.parseInt(st.nextToken());	// 집으로 가져가려는 나무의 길이
		st = new StringTokenizer(br.readLine());
		long[] namu = new long[N];
		for(int i=0; i<N; i++) 
			namu[i] = Long.parseLong(st.nextToken());
		
		System.out.println( cut_tree(M, namu) );
	}
	
	public static long cut_tree(int M, long[] namu) {
		Arrays.sort(namu);
		int len = namu.length;
		long left = 1;
		long right = namu[len-1];
		long mid = (left+right)/2;	//자를 크기 
		long sum = 0;				//잘라서 남은 것들을 더한다.
		long answer = 0;
		
		while(left <= right) {
			mid = (left+right)/2;
			sum = 0;
			for(int i=0; i<len; i++) 
				if(namu[i]>mid) 
					sum += (namu[i] - mid);
			
			if(sum > M) {	//가져가려는 거 보다 많이 가져갔다. -> 절단기 높이를 높여야 한다. 
				left = mid+1; 
				if(mid > answer) 	//저 문제에서 더 야속하게 말을 흐렸다. 이쌕히 " 나무를 필요한 만큼만 집으로 가져가려고 한다." 라고 했는데 사실 나무 개수는 정해져있다. 
					answer = mid;	// "적어도 M미터의 나무를 집에 가져가기 위해서" 이게 맞지. ㅆ;ㅂ
			}
			else if (sum < M) { //절단기 높이를 낮추어야 한다.
				right = mid-1;
			}
			else { 
				left = mid+1;
				if(mid > answer) 
					answer = mid;
			}
			System.out.println("left "+left);
			System.out.println("right "+right);
			System.out.println("mid "+mid);
			System.out.println("sum : "+sum);
			System.out.println("------------");
		}
		
		//return right; 와 같은 결과이기도 하다.
		return answer;
	}

}
