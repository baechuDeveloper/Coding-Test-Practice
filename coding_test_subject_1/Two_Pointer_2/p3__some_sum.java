package Two_Pointer_2;
import java.util.*;
import java.io.*;

// 1806번 - 부분합
// 투 포인터로 범위의 합을 진행. 서로 같은 시작점이 좋겠징
// 양수라서 진행이 가능한 방법이긴 하다 .
// 서로같은 시작에서 반복문을 종료하는 방법은 left<=right도 있지만 사실 마지막 인덱스까지 검사는 해봐야하니 이 방법은 true와 같은 효과로 만들고 right가 N을 넘을때 끝나도록 해야한다.
public class p3__some_sum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		//투 포인터 알고리즘 시작
		int left=-1, right=-1, sum=0, min=Integer.MAX_VALUE, count=0;
		// left<  <=right 이런 범위의 합으로 진행된다. 
		// 맨처음 아무것도 없는 값에서 시작한다.
		// while문에 들어온 현재의 left와 right는 현재의 상태를 의미하며 if조건문에 들어가기전 sum도 현재의 상태이다.
		/** 생각해보면 S가 0일때 계속 진행하면 sum도 0이 될때 'right'를 올려서 범위를 늘려주는 방향으로 고려해줘야한다. **/
		while(left<=right) {	//사실 이 조건으로 끝나지는 않을것이다. 사전에 sum<=S조건문에서 그러지 못하게 막아두었다. 그렇게 해야 첫번째 인덱스부터 마지막 인덱스까지 범위는 해봐야하니깐. 도중에 끝나면 안되니 이 조건문은 없는거나 마찬가지로 true와 같다.

			if(sum <= S) {	//합이 작거나 같으면 right를 올려서 값을 늘려준다.
				right++;
				if(right>=N) break;
				sum += arr[right];
			}
			else {	//합이 초과이면 left를 올려서 값을 내려준다. 
				left++;
				if(left>=N) break;	//사실 이 조건문도 발동될리가 없다. 합이 0으로 최소일떄는 항상 위의 조건문으로만 들어가서다.
				sum -= arr[left];
			}
			
			count = right-left; // left초과 right이하 개수
			if(sum>=S && min>count) {	//S라는 값만 넘는다면 길이가 가장 짧은 것만 구하면된다.
				min = count;
			}
		}//투 포인터 알고리즘 종료
		
		if(min == Integer.MAX_VALUE)
			System.out.println(0);	
		else System.out.println(min);
	}

}
