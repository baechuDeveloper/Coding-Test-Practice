package Queue;

import java.util.*;
import java.io.*;
// 2164번-카드2
public class p3 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) 
			q.add(i);
		
		while(q.size()>1) {
			q.poll(); //먼저 맨 위에 카드를 제거
			q.add( q.poll() );	//그리고 그 다음 맨 위의 카드는 맨 아래로 둔다.
		}
		System.out.println(q.poll());
	}

}
