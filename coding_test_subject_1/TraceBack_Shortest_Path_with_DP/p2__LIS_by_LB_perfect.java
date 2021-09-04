package TraceBack_Shortest_Path_with_DP;
import java.util.*;
import java.io.*;

// 14003�� - ���� �� �����ϴ� �κ� ���� 5
// LIS�� ���ϰ� ��Ȯ�� �迭�� ���غ���
public class p2__LIS_by_LB_perfect {
	//Ŭ��������
	static int N;
	static int[] arr;		// �־��� ���� ��
	static int[] seqindex;	// �ش� ���� ��ȣ�� �ش��ϴ� LIS�� �ο��� ����		... �̷��� �ϴ� Node �ڷᱸ���� �̾�� �۾��� ��ġ�� �ʰ� �Ǹ��ϰ� ���� ��Ȱ�� �� �� �־���. �������� �´ٸ� �̰� ��ü�� �ڷᱸ��ó�� Ȱ���� �ȴ�. 
	//=================================================================
	
	//main�Լ�
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		seqindex = new int[N];
		ArrayList<Integer> dp = new ArrayList<>();
		Deque<Integer> stack = new ArrayDeque<>();	//������ ������ Deque�� 

		// LowerBound�� �̿��ؼ� LIS�� ���غ��� ������ �ùٸ� �迭�� ���غ� (�迭�� �������� �����ϼ������� ��Ȯ�� ������ �̾����� �迭�̾�� �Ѵ�)
		for(int i=0; i<N; i++){
			int count = dp.size();
			int where = Lower_bound_for_LIS(dp, arr[i]);
			seqindex[i] = where;
			
			if(where == count)   //���̿� ���� ũ��� ����Ʈ���� ��� ������ ���ο� ���� ���� �ȴ�.
				dp.add(arr[i]);
			else 
				dp.set(where, arr[i]);
		}
		
		// ���� LIS�� �޸� ��Ȯ�� ������ ���� �迭�� �̾ƺ���.
		StringBuilder sb = new StringBuilder();
		int lastIndex = dp.size()-1;
		
		for(int i=N-1; i>=0; i--) {
			if(lastIndex == seqindex[i]) {
				stack.push(arr[i]); 	//deque���� addFirst()�ҽ� push�� ���� ȿ���� ���´�. 
				lastIndex--;
				if(lastIndex < 0) break;
			}
		}
		//stack�� Ȱ���ؼ� �������� ���� �̾Ƴ���.
		while(!stack.isEmpty())		
			sb.append(stack.pop()+" "); //deque���� poll()�� ���� �ǹ̰� �ȴ�.
		
		bw.write(dp.size()+"\n");
		bw.write(sb.toString());
		bw.flush();
	}//=================================================================
	//Lower_Bound �޼ҵ�
	/** �ٷ� �ڱⰡ ��ü�� ��ġ�� �˷��ش�. ���� '10'�ϳ��� ������ ����Ʈ�� ��ȣ�� 0�� ������ ���⼭ '1'�� �������ָ� list '1'�� �־��ָ�ǰ�,
	 * '0'�̸� list�� �ִ� 0�� ��ü���ָ� �ȴ�.
	 * LB�� �� ���� ����Ʈ���� �ڽŰ� ���ų� �� �ܰ� ū ���� ������� �˷��ش�.
	 * �� �ܰ� ū���� �˷��شٸ� �� ������ ���� �� �۱⶧���� ��ü���־ ����Ʈ�� LIS���� ������ ���� �� ���� LIS�� ������ ��쵵 ���� �� �ִ�.**/
	static int Lower_bound_for_LIS(List<Integer> list, int target) {
		int start = 0;
		int end = list.size();  //end�� N�̶� �迭�� ������ �Ѿ�� mid���� ��ó�� ������ �δ� ������.
		int mid;
		while(start < end) {
			mid = (start+end)/2;
			if(list.get(mid) < target)
				start = mid + 1;
			else
				end = mid;
		}
		return end;
	}//=================================================================

}
