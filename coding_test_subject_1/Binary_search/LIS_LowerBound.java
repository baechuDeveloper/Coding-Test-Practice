package Binary_search;

import java.util.*;
import java.io.*;

//12015번 - 가장 긴 증가하는 부분 수열 2 LIS
public class LIS_LowerBound {

	public static void main(String[] args) throws IOException{	//O(N*logN)방식
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> list = new ArrayList<>();	
		int[] arr = new int[N+1];

		for(int i=1; i<=N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		list.add(arr[1]);
	
		int index;
		for (int i=2; i<=N; i++ ) {
			index = Lower_bound_for_LIS(list, arr[i]);	//list에서 arr[i] 값보다 같거나 '큰' 하나의 값이 어느 index에 있는지 확인을 한다. 혹은 그
			if(list.size()-1 == index && list.get(index) < arr[i]) {	//맨 끝자리 인덱스가 나왔다는 것은 결국 최소 같은 값이 있다는 것이고, 이는 명백히 원래 값보다 오른쪽에 있어도 문제가 없다. 또한 큰값이면 당연히 오른쪽에 두면 된다. 
				list.add(arr[i]);	 	
			}
			else if(list.get(index) > arr[i]) {
				list.set(index, arr[i]);  //System.out.println("해당위치 값 변경 ");
			}
		}
		System.out.println(list.size());	
	}//=========================================================
	
	// LIS를 위해 개량한 Lower_bound
	public static int Lower_bound_for_LIS(List<Integer> list, int target) {
		int start = 0;
		int end = list.size()-1;	//이거 맞다 인덱스에 대한거니 맞다. 정확하다. 그리고 아래랑도 똑같다. 100퍼 맞다.
		int mid;
		while(start < end) {
			mid = (start+end)/2;
			if(list.get(mid) < target) 
				start = mid + 1;
			else 
				end = mid;
		}
		return end;
	}//=========================================================
}
