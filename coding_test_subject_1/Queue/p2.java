package Queue;

import java.util.*;
import java.io.*;

// 11866��-�似Ǫ�� ���� 0
public class p2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=1; i<=N; i++) 
			list.add(i);
		
		int now_index = 0;
		bw.write("<");
		// ������� ���������� ������ ���Ҵ�. ������ ť�� �����ϱ⿡�� �ڿ��� ���� ���� ����.
		while(N-->0) {
			now_index += (K-1);
			now_index %= (N+1);
			bw.write(list.remove(now_index)+"");
			if(N!=0)
				bw.write(", ");
		}
		
		bw.write(">");
		bw.flush();
	}

}
