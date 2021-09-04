package Two_Pointer_2;
import java.util.*;
import java.io.*;

// 1806�� - �κ���
// �� �����ͷ� ������ ���� ����. ���� ���� �������� ����¡
// ����� ������ ������ ����̱� �ϴ� .
// ���ΰ��� ���ۿ��� �ݺ����� �����ϴ� ����� left<=right�� ������ ��� ������ �ε������� �˻�� �غ����ϴ� �� ����� true�� ���� ȿ���� ����� right�� N�� ������ �������� �ؾ��Ѵ�.
public class p3__some_sum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		//�� ������ �˰��� ����
		int left=-1, right=-1, sum=0, min=Integer.MAX_VALUE, count=0;
		// left<  <=right �̷� ������ ������ ����ȴ�. 
		// ��ó�� �ƹ��͵� ���� ������ �����Ѵ�.
		// while���� ���� ������ left�� right�� ������ ���¸� �ǹ��ϸ� if���ǹ��� ������ sum�� ������ �����̴�.
		/** �����غ��� S�� 0�϶� ��� �����ϸ� sum�� 0�� �ɶ� 'right'�� �÷��� ������ �÷��ִ� �������� ���������Ѵ�. **/
		while(left<=right) {	//��� �� �������� �������� �������̴�. ������ sum<=S���ǹ����� �׷��� ���ϰ� ���Ƶξ���. �׷��� �ؾ� ù��° �ε������� ������ �ε������� ������ �غ����ϴϱ�. ���߿� ������ �ȵǴ� �� ���ǹ��� ���°ų� ���������� true�� ����.

			if(sum <= S) {	//���� �۰ų� ������ right�� �÷��� ���� �÷��ش�.
				right++;
				if(right>=N) break;
				sum += arr[right];
			}
			else {	//���� �ʰ��̸� left�� �÷��� ���� �����ش�. 
				left++;
				if(left>=N) break;	//��� �� ���ǹ��� �ߵ��ɸ��� ����. ���� 0���� �ּ��ϋ��� �׻� ���� ���ǹ����θ� ������.
				sum -= arr[left];
			}
			
			count = right-left; // left�ʰ� right���� ����
			if(sum>=S && min>count) {	//S��� ���� �Ѵ´ٸ� ���̰� ���� ª�� �͸� ���ϸ�ȴ�.
				min = count;
			}
		}//�� ������ �˰��� ����
		
		if(min == Integer.MAX_VALUE)
			System.out.println(0);	
		else System.out.println(min);
	}

}
