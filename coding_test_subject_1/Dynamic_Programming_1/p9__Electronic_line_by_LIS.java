package Dynamic_Programming_1;

import java.util.*;
import java.io.*;

// 2565번 - 전깃줄 
public class p9__Electronic_line_by_LIS {

	static class Node implements Comparable<Node>{
		int start, end;
		Node(int l, int r){
			start=l; end=r;
		}
		public int compareTo(Node o) {
			return start - o.start;
		}
	}//=====================================
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] list = new Node[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			list[i] = new Node(L, R);
		}
		Arrays.sort(list);
		List<Integer> dp = new ArrayList<>();
		dp.add(list[0].end);
		
		for(int i=1; i<N; i++) {
			int target = list[i].end;
			int where = LB(dp, target);
			if(dp.size()==where) 
				dp.add(target);	
			else 
				dp.set(where, target);	
		}
		
		System.out.println( N - dp.size() );
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
