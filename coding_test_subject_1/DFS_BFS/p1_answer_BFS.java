package DFS_BFS;

import java.util.*;
import java.io.*;

//1697�� - ���ٲ��� _ BFS
// �ѹ� ���� ���� �ᱹ �Ȱ��� ��길 �þ ���̴� �ߺ� ����� ���ϵ��� visit�� �����д�. 
// - �̰� ���� ���غ�. ������ ���� �޸������̼��� ��Ȱó�� �ߺ������ ����.

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
		boolean[] visit = new boolean[100_001];	//0���� 100,000����
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
			}//------------------------q_now�ݺ��� ��

		}//----------------------------------------�ݺ��� ��ü ��
		
		System.out.println(time);
	}

}
