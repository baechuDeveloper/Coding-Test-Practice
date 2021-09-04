package Dynamic_Programming_1;
/**
 * ��������� ���ʿ��� ������ ���� �� ������ LIS�� �� �ڿ� �ݴ� LIS
 * Ȥ�� 
 * �����ʿ��� ������ ���� �� ������ LIS�� �� �ڿ� �ݴ� LIS����
 * �� 2���� ��쿡���� ���� �� ������� �κ� ������ ���´ٴ� ������ ���� ������. 
 * �� ��� �� ������ġ������ LIS�� �ݴ� LIS�� ���ؼ� ��� ���̸� ���ϴ°� �´� ǥ���̾���. 
 * 
 * ���� �� ������ ���ϴµ� �־ LB�� �ſ� �����ϰ� ����Ҽ��� �ְԵȴ�. 
 * 
 */
import java.util.*;
import java.io.*;

// 11054�� - ���� �� ������� �κ� ���� 
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
		//���ʺ��� ������ ���빰
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
		
		//�����ʺ��� ������ ���빰 
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
