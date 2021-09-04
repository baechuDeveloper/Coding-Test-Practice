package Two_Pointer_alg;


// 백준 - 회전 초밥 - 2531번
// https://www.acmicpc.net/problem/2531

/**=================================================================
 * 이 손님은 항상 연속으로 먹는다. 
 * 행사 규칙
 * 1 .원래 회전 초밥은 손님이 마음대로 초밥을  고르고, 먹은 초밥만큼 식대를 계산하지만, 
 * 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공한다. 
 * 
 * 2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고, 1번 행사에 참가할 경우 이 쿠폰에 적혀진 
 * 종류의 초밥 하나를 추가로 무료로 제공한다. 만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 
 * 요리사가 새로 만들어 손님에게 제공한다.  
 * ----------------------------------------------------------
 * 쿠폰을 사용해서 추가로 먹는게 가능해서 먹은 초밥의 종류를 늘릴수 있다.
 ====================================================================**/

import java.util.*;
import java.io.*;

public class p6__Sliding_Window_Sushi {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 범위는 2 ≤ N ≤ 30,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d 이다.
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 회전 초밥 벨트에 놓인 접시의 수
		/*int d =*/ Integer.parseInt(st.nextToken());	// 초밥의 가짓수, 근데 최대 가지수를 가지고 사용할일은 없다. 최대 번호를 알려준것.
		int k = Integer.parseInt(st.nextToken());	// 연속해서 먹은 접시의 수
		int c = Integer.parseInt(st.nextToken());	// 쿠폰 번호
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(br.readLine());

		Set<Integer> set = new HashSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		map.put(c, 0);
		for(int i=0; i<k; i++) {
			set.add(arr[i]);
			if(map.containsKey(arr[i])) {
				int temp = map.get(arr[i]);
				map.put(arr[i], temp+1);
			}
			else 
				map.put(arr[i], 1);
		}
		
		int L = 0;				// 0번째부터 제거 시작
		int R = k; 				// k번째 부터 추가 시작 
		int count = set.size();	// 먹은 종류 개수
		if(map.get(c)==0) count++;
		int temp_count = 0;
		int temp = 0;
		boolean circle = false;	//R이 한바퀴 돌았는가.

		while(true) {
			//System.out.println((L+1)+" "+R);
			//map.forEach( (a,b) -> {System.out.println("key:"+a+" cont:"+b);});
			
			//왼쪽 제거
			if(map.get(arr[L]) == 1) 
				set.remove(arr[L]);
			temp = map.get(arr[L]);
			map.put(arr[L], temp-1);
			++L;
		
			//오른쪽 추가
			set.add(arr[R]);
			if(map.containsKey(arr[R])) {
				temp = map.get(arr[R]);
				map.put(arr[R], temp+1);
			}
			else 
				map.put(arr[R], 1);
			++R;
			
			//개수 체크
			temp_count = set.size();
			
			if(map.get(c)==0) 
				temp_count++;
			
			if(count<temp_count)
				count = temp_count;
			//System.out.println("temp:"+temp_count+"\n");
		
			//원형체크
			if(R==N) {
				R=0;
				circle=true;
			}
			//종료
			if(circle==true && R==k) 
				break;
		}
		
		bw.write(count+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
