package Two_Pointer_2;
import java.util.*;
import java.io.*;

// 2470�� - �� ���
//���� Ư���� ������ �ϰ� �� ���� ���� �����غ��� ������ ��������.�糡�� ���

// ������ �غ��� ���� left�� ����Ų�� �������� ���߿� ����� ���� ����Ų�ٸ�? �������. -900 1 2 10 400 �̷��� �ִٸ� ���� �꼺 1�� �꼺 2�̴�.  
// -18 -2 -1 1 5 10 20  
// ��� ��׿� ���� ������ �غ��� �ؾ��Ѵ�.�װ� ȿ�������� �� �Ҽ��ִ�. 
public class p2__two_liquid {
	
	//==========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		//�� ������ �˰��� ����
		int left=0, right=N-1, sum=0, min=2_000_000_000;	//min�� ������� 0�� ���� ����� ��, ���밪���� ����.
		int[] index = new int[2]; index[0]=0; index[1]=N-1;
		
		while(left<right) {	//�� ���� ���̴� ���� ���� �ݺ��� ������.
			sum = arr[left]+arr[right];
			int abs_sum = Math.abs(sum);

			//�� 0�� ����� ���� �ִٸ� ����
			if(min > abs_sum) {	
				 min = abs_sum;
				 index[0] = left;
				 index[1] = right;
			}
			
			if(sum<0) {	//���� ���� �� Ŀ���̴� left�� �÷��� ���� Ű���. 
				left++;
			}
			else {	//���� ���� �� Ŀ���̴� right�� ������ ���� ������.
				if(sum==0) { //���� 0�� �ϳ��� ������ �װ͸� �����൵ �ȴ�.
					break;
				}
				right--;
			}
		
		}//�� ������ �˰��� ����
		
		System.out.println(arr[index[0]]+" "+arr[index[1]]);
		
	}//==========================================================

}
