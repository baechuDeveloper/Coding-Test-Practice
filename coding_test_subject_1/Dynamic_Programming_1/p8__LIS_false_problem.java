package Dynamic_Programming_1;
/**
 * 결론적으로 왼쪽에서 시작한 가장 긴 길이의 LIS와 그 뒤에 반대 LIS
 * 혹은 
 * 오른쪽에서 시작한 가장 긴 길이의 LIS와 그 뒤에 반대 LIS에서
 * 이 2가지 경우에서만 가장 긴 바이토닉 부분 수열이 나온다는 보장이 전혀 없었다. 
 * 즉 모든 각 원소위치마다의 LIS와 반대 LIS에 대해서 모든 길이를 비교하는게 맞는 표현이었다. 
 * 
 * 물론 이 각각을 비교하는데 있어서 LB를 매우 유용하게 사용할수가 있게된다. 
 * 
 */
import java.util.*;
import java.io.*;

// 11054번 - 가장 긴 바이토닉 부분 수열 
public class p8__LIS_false_problem {

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
		
		int Lstart_max = 0, Rstart_max=0;
		int Lend = 0, Rend=0;
		int Lval = 0, Rval=0;
		//왼쪽부터 시작한 내용물
		List<Integer> Lstart = new ArrayList<>();
		List<Integer> Rstart = new ArrayList<>();
		Lstart.add(list[0]);
		for(int i=1; i<N; i++) {
			int where = LB(Lstart, list[i]);
			if(Lstart.size() == where) {
				Lstart.add(list[i]);
				Lend = i; Lval=list[i];
			}
			else {
				Lstart.set(where, list[i]);
				if(Lval == list[i])
					Lend = i;
			}
		}
		
		for(int i=N-1; i>Lend; i--) {
			if(Rstart.size()==0) {
				for(int z=N-1; z>=0; z--) 
					if(list[Lend]>list[z]) {
						Rstart.add(list[z]);
						i = z;
						break;
					}			
			}
			else {
				int where = LB(Rstart, list[i]);
				if(list[Lend]<=list[i]) 
					continue;	
				if(Rstart.size() == where) {
					Rstart.add(list[i]);
				}
				else {
					Rstart.set(where, list[i]);
				}
			}
		}

		Lstart_max = Lstart.size() + Rstart.size();
		
		//오른쪽부터 시작한 내용물 
		Lstart = new ArrayList<>();
		Rstart = new ArrayList<>();
		Rstart.add(reverse[0]);
		for(int i=1; i<N; i++) {
			int where = LB(Rstart, reverse[i]);
			if(Rstart.size() == where) {
				Rstart.add(reverse[i]);
				Rend = i; Rval = reverse[i];
			}
			else {
				Rstart.set(where, reverse[i]);
				if(Rval == reverse[i])
					Rend = i;
			}
		}
		
		for(int i=N-1; i>Rend; i--) {
			if(Lstart.size()==0) {
				for(int z=N-1; z>=0; z--) 
					if(reverse[Rend]>reverse[z]) {
						Lstart.add(reverse[z]);
						i = z;
						break;
					}			
			}
			else {
				int where = LB(Lstart, reverse[i]);
				if(reverse[Rend]<=reverse[i]) 
					continue;
				if(Lstart.size() == where) {
					Lstart.add(reverse[i]);
				}
				else {
					Lstart.set(where, reverse[i]);
				}
			}
		}

		Rstart_max = Rstart.size() + Lstart.size();
		System.out.println(Lstart_max+" "+ Rstart_max);
		System.out.println(Math.max(Lstart_max, Rstart_max));
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
