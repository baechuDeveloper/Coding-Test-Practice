package Two_Pointer_2;
import java.util.*;
import java.io.*;
// 3273�� - �� ���� ��
// �������͸� �̿��Ͽ� �ۼ��غ���. 
// �������Ϳ��� left�� right�� ������ �������� �ٸ���. �׷��� ���� ��ġ���� �����ص��ǰ�, �� ������ �����ص� �ǰ� ������ ���� ���� �˾Ƽ� ����. (�̺�Ž���� �糡�̱� ����)
// �� 2���� �����Ͱ� � ���������� ������� �����ϴ°� �߿��Ѱ� ����. ������ ���� �����ؼ� ������ ���� ���ϰų� �ߴµ�(���� ���̵�),
// ���⼭�� ������ ���� �� ���ܿ��� ������ �غ����� �Ѵ�.(�̺�Ž�� ��������� mid���� ������ �ƴ϶�� ��, �������Ϳ� �̺�Ž���� �������Ͱ� �� �� ���� ����ó�� ��������. ���� ���ԵǴ� ������ü�� �ƴϱ�������.)

// �ٸ� ���������� �糡���� �̿��Ҷ� ������ �ʿ� ���°͵� �����Ŵ�. �׷��� �糡���� ���ؼ��� �ݵ�� �����ؾ��ϴ°� �ƴ�, ������ ���ؼ� ������ �ؾ��Ѵٰ� �´� ���̴�.
public class p1__two_value_sum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		if(N==1) System.out.println(0);
		
		Arrays.sort(arr);//������ ���ش�.
		
		//�� ������ ����
		int left=0, right=N-1, sum=0, count=0;
		
		while(left<right) {	//���� ���� ���������� �ʴ´ٰ� ������ ����
			sum = arr[left]+arr[right];
			
			if(sum<X) { //���� �۴ٸ� ���� ���. 
				left++;
			}
			else {	//���� ũ�ų� ������ ���� ���δ�. 
				if(sum==X) {	//��Ȯ�� ���� ����.
					count++;
				}
				right--;
			}
		}
		System.out.println(count);
	}

}
