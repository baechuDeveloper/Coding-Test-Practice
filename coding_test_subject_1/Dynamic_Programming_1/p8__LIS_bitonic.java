package Dynamic_Programming_1;

import java.util.*;
import java.io.*;
// 11054번 - 가장 긴 바이토닉 부분 수열
public class p8__LIS_bitonic {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] list = new int[N];
		int[] reverse = new int[N];
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
			reverse[N-1-i] = list[i];
		}
		
		//왼쪽부터 시작한 내용물
		List<Integer> Lstart = new ArrayList<>();
		List<Integer> Rstart = new ArrayList<>();
		int[] L_DP = new int[N];
		int[] R_DP = new int[N];
		L_DP[0] = 1;
		R_DP[N-1] = 1;
		Lstart.add(list[0]);
		Rstart.add(reverse[0]);
		
		for(int i=1; i<N; i++) {
			int Lwhere = LB(Lstart, list[i]);
			int Rwhere = LB(Rstart, reverse[i]);
			
			if(Lstart.size() == Lwhere) 
				Lstart.add(list[i]);
			else Lstart.set(Lwhere, list[i]);
		
			if(Rstart.size() == Rwhere)
				Rstart.add(reverse[i]);
			else Rstart.set(Rwhere, reverse[i]);
		
			L_DP[i] = Lwhere+1;			//현재 왼쪽에서 오른쪽으로 움직이기 시작해서 이 위치에서는 해당 값 만큼의 길이를 가진 LIS를 가지고 있음
			R_DP[N-1-i] = Rwhere+1;		//현재 오른쪽에서 왼쪽으로 움직이기 시작해서 이 위치에서는 해당 값 만큼의 길이를 가진 LIS를 가지고 있음 
		}
		
		int max = 0;
		for(int i=0; i<N; i++) 
			max = Math.max(max, L_DP[i]+R_DP[i] - 1);	//중복인게 하나 있게 되므로 1개를 제거 
		System.out.println(max);
	}//=====================================

	// Lower_Bound로 찾고자하는 target의 값의 위치
	static int LB(List<Integer> list, int target) {
		int start = 0;
		int end = list.size();
		int mid;		
		while(start < end) {
			mid = (start + end)/2;
			if(list.get(mid) < target)		// upper bound라면 arr[mid] <= target 이면 된다.
				start = mid + 1;
			else
				end = mid;
		}
		return end;	//결국 end가 우리가 원하는 형태를 내보낸다.
	}//=====================================

}
