package Binary_search;

import java.util.*;
import java.io.*;

//12015�� - ���� �� �����ϴ� �κ� ���� 2 LIS
public class LIS_LowerBound {

	public static void main(String[] args) throws IOException{	//O(N*logN)���
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
			index = Lower_bound_for_LIS(list, arr[i]);	//list���� arr[i] ������ ���ų� 'ū' �ϳ��� ���� ��� index�� �ִ��� Ȯ���� �Ѵ�. Ȥ�� ��
			if(list.size()-1 == index && list.get(index) < arr[i]) {	//�� ���ڸ� �ε����� ���Դٴ� ���� �ᱹ �ּ� ���� ���� �ִٴ� ���̰�, �̴� ����� ���� ������ �����ʿ� �־ ������ ����. ���� ū���̸� �翬�� �����ʿ� �θ� �ȴ�. 
				list.add(arr[i]);	 	
			}
			else if(list.get(index) > arr[i]) {
				list.set(index, arr[i]);  //System.out.println("�ش���ġ �� ���� ");
			}
		}
		System.out.println(list.size());	
	}//=========================================================
	
	// LIS�� ���� ������ Lower_bound
	public static int Lower_bound_for_LIS(List<Integer> list, int target) {
		int start = 0;
		int end = list.size()-1;	//�̰� �´� �ε����� ���ѰŴ� �´�. ��Ȯ�ϴ�. �׸��� �Ʒ����� �Ȱ���. 100�� �´�.
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
