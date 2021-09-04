package Queue;

import java.util.*;
import java.io.*;
// 2164��-ī��2
public class p3 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) 
			q.add(i);
		
		while(q.size()>1) {
			q.poll(); //���� �� ���� ī�带 ����
			q.add( q.poll() );	//�׸��� �� ���� �� ���� ī��� �� �Ʒ��� �д�.
		}
		System.out.println(q.poll());
	}

}
