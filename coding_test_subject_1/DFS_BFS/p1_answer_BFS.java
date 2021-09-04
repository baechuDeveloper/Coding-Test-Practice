package DFS_BFS;

import java.util.*;
import java.io.*;

//1697번 - 숨바꼭질 _ BFS
// 한번 나온 값은 결국 똑같은 계산만 늘어날 뿐이니 중복 계산을 피하도록 visit를 만들어둔다. 
// - 이걸 생각 못해봄. 굉장히 유용 메모이제이션의 역활처럼 중복계산을 피함.

public class p1_answer_BFS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if(N==K) {System.out.println(0); return;}
		
		Queue<Integer> q_now;
		Queue<Integer> q_next = new LinkedList<>();
		int time = 0;
		boolean isComplete = false; 
		boolean[] visit = new boolean[100_001];	//0부터 100,000까지
		q_next.add(N);
		visit[N] = true;
		
		while(!isComplete) {
			time++;
			q_now = q_next;
			q_next = new LinkedList<>();
			
			while(!q_now.isEmpty()) {
				int num = q_now.poll();	
				if(num>100_000) continue;
				visit[num] = true;
				int[] cal = {num-1, num+1, num<<1};
				
				for(int i : cal) {
					if(i==K) {
						isComplete = true;
						break;
					}
					else if(i>=0 && i<=100_000 && visit[i]==false)
						q_next.add(i);
				}
				if(isComplete) break;
			}//------------------------q_now반복문 끝

		}//----------------------------------------반복문 전체 끝
		
		System.out.println(time);
	}

}
