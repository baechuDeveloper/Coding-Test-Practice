package Queue;

import java.util.*;
import java.io.*;

// 11866번-요세푸스 문제 0
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
		// 순서대로 빠져나오게 구성해 보았다. 일일히 큐로 진행하기에는 자원을 조금 쓸거 같다.
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
