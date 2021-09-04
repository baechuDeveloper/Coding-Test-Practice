package TraceBack_Shortest_Path_with_DP;

import java.util.*;
import java.io.*;

// 9019�� - DSLR
public class p6__DSLR {

	//prev�� command�� ���ο� ���� ó������ ������ �Ǹ� �� ���� ���� �ո����� �ּҹ���� �ȴ�. BFS���·� ������ �ؼ�.
	static int A, B;		 // ���� ��, ���߷��� ��
	static int[] prev;		 // [���簪] = ���� ���� ���� ���� ���� �� 
	static char[] command;	 // [���簪] = ���� ���� ���� ���� ���� ������ ��Ÿ�� ��ɾ� 
	static Queue<Integer> q; // BFS�� ���� ť
	static char[] cal_name = {'D','S','L','R'};
	//=========================================================
	
	//main �Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		
		while(testcase-->0) {
			
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			prev = new int[10000];
			command = new char[10000];
			Arrays.fill(prev, -1);
		
			q = new LinkedList<>();
			q.add(A);

			//---BFS����---
			while(!q.isEmpty()) {
				int now = q.poll();
				//��� ������ �߰�
				if(now == B) break; 
				//4���� �۾�
				int[] com = { D(now), S(now), L(now), R(now) };
				for(int i=0; i<4; i++) {
					int next = com[i];
					if(prev[next] == -1) {
						prev[next] = now;
						command[next] = cal_name[i];
						q.add(next);
					}
				}
			}//---BFS����---

			//�������ؼ� ��� ���� ���ÿ� �ִ´�.
			Deque<Character> stack = new ArrayDeque<>();
			int trace = B;
			while(trace != A) {
				stack.push( command[trace] );
				trace = prev[trace];
			}
			
			//���ÿ� �ִ� �͵��� ������ش�.
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			} sb.append("\n");
			bw.write(sb.toString());
		
		}//��� �׽�Ʈ ���̽� ����
		bw.flush();
	}//=========================================================
	
	static int D(int i) {
		return (i*2) % 10_000;
	}//=========================================================
	static int S(int i) {
		if(i==0) return 9999;
		return i-1;
	}//=========================================================
	static int L(int i) {
		int body = (i % 1_000) * 10;
		int tail = i / 1_000;
		return body + tail;
	}//=========================================================
	static int R(int i) {
		int body = i / 10;
		int head = (i % 10) * 1_000;
		return head + body;
	}//=========================================================

}
