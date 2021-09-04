package Dynamic_Programming_1;

import java.util.*;
import java.io.*;
// 11054�� - ���� �� ������� �κ� ����
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
		
		//���ʺ��� ������ ���빰
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
		
			L_DP[i] = Lwhere+1;			//���� ���ʿ��� ���������� �����̱� �����ؼ� �� ��ġ������ �ش� �� ��ŭ�� ���̸� ���� LIS�� ������ ����
			R_DP[N-1-i] = Rwhere+1;		//���� �����ʿ��� �������� �����̱� �����ؼ� �� ��ġ������ �ش� �� ��ŭ�� ���̸� ���� LIS�� ������ ���� 
		}
		
		int max = 0;
		for(int i=0; i<N; i++) 
			max = Math.max(max, L_DP[i]+R_DP[i] - 1);	//�ߺ��ΰ� �ϳ� �ְ� �ǹǷ� 1���� ���� 
		System.out.println(max);
	}//=====================================

	// Lower_Bound�� ã�����ϴ� target�� ���� ��ġ
	static int LB(List<Integer> list, int target) {
		int start = 0;
		int end = list.size();
		int mid;		
		while(start < end) {
			mid = (start + end)/2;
			if(list.get(mid) < target)		// upper bound��� arr[mid] <= target �̸� �ȴ�.
				start = mid + 1;
			else
				end = mid;
		}
		return end;	//�ᱹ end�� �츮�� ���ϴ� ���¸� ��������.
	}//=====================================

}
