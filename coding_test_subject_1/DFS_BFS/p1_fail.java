package DFS_BFS;

import java.util.*;
import java.io.*;

//1697�� - ���ٲ��� 
public class p1_fail {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if(N==K) {System.out.println(0); return;}
		
		Queue<Integer> q_now = new LinkedList<>();
		Queue<Integer> q_next= new LinkedList<>();
		int time = 0;
		boolean isComplete = false; 
		q_next.add(N);
		while(!isComplete) {
			time++;
			q_now = q_next;
			q_next = new LinkedList<>();
			while(!q_now.isEmpty()) {
				int num = q_now.poll();
				if(num-1==K) {
					isComplete = true;
					break;
				}
				else if(num-1>=0)
					q_next.add(num-1);
				
				if(num+1==K) {
					isComplete = true;
					break;
				}
				else if(num+1>=0)
					q_next.add(num+1);
				
				if(num<<1==K) {
					isComplete = true;
					break;
				}
				else if(num<<1 >= 0)
					q_next.add(num<<1);
				
			}//q_now�ݺ��� ��

		}//�ݺ��� ��ü ��
		
		System.out.println(time);
	}

}
