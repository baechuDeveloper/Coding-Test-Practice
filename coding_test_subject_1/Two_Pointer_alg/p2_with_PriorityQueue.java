package Two_Pointer_alg;

// ���� - N��° ū �� _ �켱���� ť Ȱ�� 
// https://www.acmicpc.net/problem/2075

/**============================================================
 * PriorityQueue<Integer> minHeap = new PriorityQueue<>();
   System.out.println("�ּ� ��");	// �������� , ���� ������ ū ������

   PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
   System.out.println("�ִ� ��");	// �������� , ū ������ ���� ������
 * =======================================================
  
 * ������ �ּ������� ó�� N������ �켱����ť�� ����ΰ� �� �̻���ʹ� �ְ� pop�ϰ� �ݺ��ϸ�
 * N���� �����ϸ� ���翡�� ���� ���� ���� ���δ� ������ ���Ͼ��ϰ� ���Ҽ� �ִ�. 
 ==============================================================**/
import java.util.*;
import java.io.*;

public class p2_with_PriorityQueue {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			if(i==0) {
				for(int j=0; j<N; j++) 
					pq.add(Integer.parseInt(st.nextToken()));
			}
			else {
				for(int j=0; j<N; j++) {
					pq.add(Integer.parseInt(st.nextToken()));
					pq.remove();
				}
			}
		}

		bw.write(pq.peek()+"");
		bw.flush();
		
		br.close();
		bw.close();

	}

}
