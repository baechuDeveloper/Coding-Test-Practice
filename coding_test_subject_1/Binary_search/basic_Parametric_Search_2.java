package Binary_search;

import java.io.*;
import java.util.*;

// 2805��-���� �ڸ���
//�ִ밪 �ּҰ��� �̿��� �̺�Ž��
public class basic_Parametric_Search_2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// ������ ��
		int M = Integer.parseInt(st.nextToken());	// ������ ���������� ������ ����
		st = new StringTokenizer(br.readLine());
		long[] namu = new long[N];
		for(int i=0; i<N; i++) 
			namu[i] = Long.parseLong(st.nextToken());
		
		System.out.println( cut_tree(M, namu) );
	}
	
	public static long cut_tree(int M, long[] namu) {
		Arrays.sort(namu);
		int len = namu.length;
		long left = 1;
		long right = namu[len-1];
		long mid = (left+right)/2;	//�ڸ� ũ�� 
		long sum = 0;				//�߶� ���� �͵��� ���Ѵ�.
		long answer = 0;
		
		while(left <= right) {
			mid = (left+right)/2;
			sum = 0;
			for(int i=0; i<len; i++) 
				if(namu[i]>mid) 
					sum += (namu[i] - mid);
			
			if(sum > M) {	//���������� �� ���� ���� ��������. -> ���ܱ� ���̸� ������ �Ѵ�. 
				left = mid+1; 
				if(mid > answer) 	//�� �������� �� �߼��ϰ� ���� ��ȴ�. �̽��� " ������ �ʿ��� ��ŭ�� ������ ���������� �Ѵ�." ��� �ߴµ� ��� ���� ������ �������ִ�. 
					answer = mid;	// "��� M������ ������ ���� �������� ���ؼ�" �̰� ����. ��;��
			}
			else if (sum < M) { //���ܱ� ���̸� ���߾�� �Ѵ�.
				right = mid-1;
			}
			else { 
				left = mid+1;
				if(mid > answer) 
					answer = mid;
			}
			System.out.println("left "+left);
			System.out.println("right "+right);
			System.out.println("mid "+mid);
			System.out.println("sum : "+sum);
			System.out.println("------------");
		}
		
		//return right; �� ���� ����̱⵵ �ϴ�.
		return answer;
	}

}
