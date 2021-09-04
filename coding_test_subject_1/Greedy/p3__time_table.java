package Greedy;
import java.util.*;
import java.io.*;

// 1931�� - ȸ�ǽǹ���
// �׸��� ������� �� �������� �ּ��� ������� �ִ��� ���� ȸ�Ǹ� �����غ���. �ּ��� ����� ã������ �ݷʰ� ���� ��Ģ�� ã�ƾ� �Ѵ�.
/* 1. ȸ�Ǹ� ���� �����ϴ� ������ �����ϸ�?
  	   ���� �����ص� ������ �ð��� ��û ��ٸ� ���������̴�.
 * 2. ȸ�ǰ� ª�� ������ �����ϸ�?
 	   �� ª�� ȸ�Ƿ� ���� 2������ ȸ�ǰ� ��ø �ȵǰ� �׷� ���� �ִ� ȸ�ǰ� ���� ���� �� �ִ�.
 * 3. ���� ������ ȸ�Ǹ� �������� ������?
          ���� ������ �� ȸ�Ǹ� �����Ҽ� �ְ� �̸� �������� ���� ������ ȸ�� �� ������ ��츦 ã�� �� ��������. 
          �ִ� ȸ�� ���� �����ϴ� ��찡 ���� �� �� �� �־, �� ������ �����ϴ�.
      (1,4)�� �� ó������ �������µ� ��� (1,4)��� (3,5)�� �����ص� �ȴ�. �� ������ ���� ������ �ð��� (5,7)�̱⿡ ���θ� �ٲ� ���̴�.
       �̴� �ٽø��� �� ���� ������ �ð����� ��°� �ſ� �����ϴٴ� �����̴�. ���� ���������� ȸ�Ƿ� ��Ƶΰ� �� ���� ������ ������ ȸ�Ǹ� �˾ƺ��� ����
       �ִ� ȸ�Ǽ��� �������ָ�, (3,5)ó�� �ٸ� ���÷� �� ���� ������ �� ª�� ȸ�Ǹ� ���ٴ°��� �� �켱������ ����. Ȥ���� (4,5)�� ������ ��� ���̴�.
       ���� ������ ȸ�Ǹ� �������� �� ���� ���۽ð��� ã�ư��� ���� �ִ� ȸ�Ǹ� �������ش�. 
       
       �׸�ŭ ���� ����(����)�� �����־���, �׸�ŭ �� ������ ���� �켱������ ���� ������(�� ª�� �����)�� �������־��� �����̴�. �׷��ٸ� �ݵ�� ������ �ȴ�.
 ==================================================================*/ 
public class p3__time_table {
	//�ڷᱸ�� Ŭ����
	static class Node implements Comparable<Node>{
		int start, end;	//ȸ�� ����, ��
		Node(int a, int b){
			start = a; end = b;
		}
		
		public int compareTo(Node o) {
			if(end > o.end) {	//��밡 �� ª��
				return 1;   //���� �������� �������� �� �ڷ� ���ڴ�.
			}
			else if(end < o.end) { //���� �� ª��
				return -1;	//���� �������� �������� �� �ڷ� ���ڴ�.
			}
			else {	//���� ������ �ð��� ����. 
				if(start > o.start)  //��밡 �� ª��
					return 1;
				else
					return -1;	//���� �� ª��.
			}
		}
	}//======================================================
	//main�Լ�
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] arr = new Node[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());	// ���� �ð�
			int end = Integer.parseInt(st.nextToken());	// ������ �ð�
			arr[i] = new Node(start, end);
		}
		
		Arrays.sort(arr);
		//for(int i=0; i<N; i++) System.out.println(arr[i].start+" "+arr[i].end);
		
		int cnt = 0;
		int now_end = 0;
		for(int i=0; i<N; i++) {
			if(now_end <= arr[i].start) {
				now_end = arr[i].end;
				cnt++;
			}
		}
		System.out.println(cnt);
	}//======================================================

}
