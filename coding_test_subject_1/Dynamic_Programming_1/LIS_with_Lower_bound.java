package Dynamic_Programming_1;

import java.io.*;
import java.util.*;
// 11053�� - ���� �� �����ϴ� �κ� ���� LIS
// 12015�� - ���� �� �����ϴ� �κ� ���� 2 LIS
/**-------------------------------------------
10
100 200 4 8 6 7 8 9 4 10 		���� 4 6 7 8 9 10
  
7
10 20 30 1 2 3 4

7
10 20 30 1 2 3 40

4
1 1 1 1
 ---------------------------------------------**/
public class LIS_with_Lower_bound{

	//main �Լ�
	public static void main(String[] args) throws IOException{
		//LIS_1();	// O(n^2)���		// dp��� 
		LIS_2();	// O(n*log n)���	// Lower_Bound��� (binary search����)
	}//=========================================================
	/**-------------------------------------------------------------------------
	 * O(N^2) �˰����� ��� ���̳��� ���α׷����� ������ ����� ����� �ʰ� ������ �� ������ŭ �����ϴ�. 
	 * ������ ���� ��ȭ������ N^2�ð��� ���� �� �ֽ��ϴ�.
	 * dp[i] = i ��° ���Ҹ� ���������� �ϴ� LIS�� ����
	 * dp[i] = 1 ~ (i-1)������ ���ҵ鿡��, i��° ���Һ��� ���� �����͵� ��, ���� ū dp�� + 1
	 * ��ó: https://seungkwan.tistory.com/8 [Seungkwan's Lab.]
	---------------------------------------------------------------------------**/
	public static void LIS_1() throws IOException{	//O(N^2)���
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dp = new int[N+1];	//  ���ݱ��� ���ӵ� Ƚ��
		int[] arr = new int[N+1];
		int ans = -1;
		for(int i=1; i<=N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
			int here = 0; // �������� �ڽź��� ������������ ���ӵ� ������ ���� ���� .. ��ü�� ������� ���� ū here�� dp���� ã�´�.
			for(int j=1; j<i; ++j) {	//�� (i-1)��° ����
				if(arr[i] > arr[j])
					here = Math.max(here, dp[j]);
			}
			dp[i] = here + 1;
			ans = Math.max(ans, dp[i]);
		}
	
		System.out.println(ans);
	}//=========================================================
	
	// Lower bound��� ���� �˾ƾ��Ѵ�. 
	// ������ ����ϴ�. ���� �߿��� �� �̰����� �����ϴ�'�帧'�� �����̴�. ���ʺ��� ���������� �񱳸� �ϸ鼭 �����ϴ� �κ� ������ ������� 10 20 30
	//  �� ������ 1�� ���´ٸ� 10�� �ִ� ���� 1�� �־ 1 20 30 �� �����. lowerbound�� ���ϰ� �Ǵ� ���� �ٲپ�µ�...
	// ���� 10 20 30 ������ 3���� �ִ� ���� ���� 1�� ���͵� ������ ���� 3�̹Ƿ� �� ���� �������� ����ϸ� 1�� ���ذ��� ���� �ǹ̸� �ټ��ִ�. 
	// �̹� �� ������ �ȿ� �� ���������� ������ ���Ѵٸ� �ִ���̿��� ���̻� ������ ���� ������ �ᱹ �������� ����ϴ� �������� ������ �ȴ�. 
	// ���� 10 20 30 1 2 3 4 �̸� --- 10 20 30 �̾��ٰ� 1 2 3 ���� ���ؿ������̰� ������ 4�� �����鼭 1 2 3 4 �� ���̰� �þ��.
	// ���� �� ���̸� �̷��� ì�ܰ������� ���� ������ �Ų����� ����.
	// ���� 10 20 30 1 2 3 40 �̸� --- 10 20 30 40 ���� ������ ������ 1 2 3 40���� ������ ������ �ǹ̸� �������ش�. 
	// �� ���ϱ� �� 10 20 30 ���� �þ �� �־��ٸ� 1 2 3 4������ �þ�� �ִ�. �ݸ鿡 1 2 3���� �þ���־ 10 20 30������ �þ������.
	// ������ �ݿ��� ���ϴ� �˰����̶�� ������ �ȴ�.
	// �׸��� �� ������ ���� ���� ���Ҵ� ���� �ȴ�.
	/**-------------------------------------------------------------------------
	 * Binary Search: ���ϴ� �� K�� ã�� ����
	 * Lower Bound: ���ϴ� �� K �̻��� �� �� ó�� ������ ��ġ�� ã�� ����
	 * Upper Bound: ���ϴ� �� K �ʰ��� �� �� ó�� ������ ��ġ�� ã�� ����
	 
	 * O(N log  N) �˰����� ��������, lower bound��� ���� �˾ƾ� �մϴ�. 
	 * ��� ���ĵ� �迭 arr���� ��� �� val�� lower bound�� arr�� ���ĵ� ���·� �����ϸ鼭 val�� ���Ե� �� �ִ� ��ġ�� �� ���� �ε����� ���� ���Դϴ�.
	 * ���� [1, 3, 3, 6, 7] �̶�� �迭���� 1�� lower bound�� 1�̰�, 3�� lower bound�� 2 �̸�, 5�� lower bound�� 4�Դϴ�. 
	 * (�� �ۿ��� �迭�� �ε����� 1���� �����Ѵٴ� �Ϳ� �������ּ���). 
	 * ���� upper bound��� ���䵵 �ִµ�, �̰��� �ݴ�� ���Ե� �� �ִ� ��ġ�� �� ���� �ε����� ū ���Դϴ�.
	 * lower bound�� ���� Ž���� ���� log N �ð��� ���� �� ������, 
	 * C++�� ��� STL�� �̹� ������ �Ǿ� �ֱ� ������ ���� ������ �ʿ䵵 �����ϴ�. 
	 * C++�� lower_bound �Լ��� ���ؼ��� �Ʒ��� ��ũ�� �����Ͻñ� �ٶ��ϴ�. 
	 * ��ó: https://seungkwan.tistory.com/8 [Seungkwan's Lab.]
	 * https://sudeky.tistory.com/131
	----------------------------------------------------------------------------**/
	public static void LIS_2() throws IOException{	//O(N*logN)���
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> dp = new ArrayList<>();	
		int[] arr = new int[N+1];

		for(int i=1; i<=N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		dp.add(arr[1]); System.out.println("��ó�� "+dp.get(0));
	
		int index;
		for (int i=2; i<=N; i++ ) {
			dp.forEach( a-> System.out.print(a+" ")); 
			index = Lower_bound_for_LIS(dp, arr[i]);	//dp���� arr[i] ������ ���ų� 'ū' �ϳ��� ���� ��� index�� �ִ��� Ȯ���� �Ѵ�. Ȥ�� ��
			System.out.println("--- "+index);
			if(dp.size()-1 == index && dp.get(index) < arr[i]) {	//�� ���ڸ� �ε����� ���Դٴ� ���� �ᱹ �ּ� ���� ���� �ִٴ� ���̰�, �̴� ����� ���� ������ �����ʿ� �־ ������ ����. ���� ū���̸� �翬�� �����ʿ� �θ� �ȴ�. 
				dp.add(arr[i]);	 System.out.println("�ڿ� �߰�");		//ã�Ҵ�! Ʋ�ȴ� �κ�! 4�� 1 1 1 1 �̷��� �ϸ� ���� 1�ε� 2�� ���Դ�. ���ǿ���  dp.get(index) <= arr[i] ���� �ؼ��̴�. < ���� ���� �ξ���. 
			}
			else if(dp.get(index) > arr[i]) {
				dp.set(index, arr[i]);  System.out.println("�ش���ġ �� ���� ");
			}
			// �׸��� ���࿡ ���࿡ ���� ���̸� ���� 2������ �����ϰԵȴ�.
			dp.forEach( a-> System.out.print(a+" "));System.out.println("\n");
		}
		
		System.out.println(dp.size());	
	}//=========================================================
	// LIS�� ���� ������ Lower_bound 
	// ���⼭ -1�� ���ܵδ� ������ �ƹ��͵� ������� ���� list������ ����� �ȵǰ�, -1�� ���� ���� if���ǵ� �� �߰� �Ǵ°͵� �ְ�, �� �ǹ̰� �ణ ����ȴ�. 
	public static int Lower_bound_for_LIS(List<Integer> list, int target) {
		int start = 0;
		int end = list.size()-1;	//�� �κп� ���ؼ��� -1�� �����ʰ� ����ص� �迭������ �Ѿ�� �ʴ´�. mid�� ���ؼ� �Ѿ�� ���Ѵ�. //�ٸ� ���⼭�� -1�� �ϰ� ������ ������ ���־���.
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
	//����Ʈ�� Lower_bound
	/**-------------------------------------------------------------------------
	 * ���ĵ� �����Ϳ���..
	 * Binary Search: ���ϴ� �� K�� ã�� ����
	 * Lower Bound: ���ϴ� �� K �̻��� �� �� ó�� ������ ��ġ�� ã�� ����. �´�	k�� �����Ҽ� �ִ�.
	 * Upper Bound: ���ϴ� �� K �ʰ��� �� �� ó�� ������ ��ġ�� ã�� ����. �´�	k�� ���������� �ʴ´�.
	 * ��ó : https://codemcd.github.io/algorithm/DataStructure-%EC%9D%B4%EB%B6%84-%ED%83%90%EC%83%89/
	 * 1 3 6 8 10 ���� target�� 8�̸� lower�� 4��°���� �˷��ְ�, upper�� �ʰ��� ���� 10�� ��ġ 5��°�� �˷��ش�.
	 ----------------------------------------------------------------------------**/
	public static int Lower_bound(int[] arr, int target) {
		int start = 0;
		int end = arr.length - 1;
		int mid;
		while(start < end) {
			mid = (start + end)/2;
			if(arr[mid] < target)		// upper bound��� arr[mid] <= target �̸� �ȴ�.
				start = mid + 1;
			else
				end = mid;
		}
		return end;	//�ᱹ end�� �츮�� ���ϴ� ���¸� ��������.
	}//=========================================================
}
