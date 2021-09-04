package Deque;

import java.util.*;
import java.io.*;

/**
push X: 정수 X를 큐에 넣는 연산이다.
pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 큐에 들어있는 정수의 개수를 출력한다.
empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 **/
public class queue_2 {
	//멤버변수
	private static StringBuilder sb;
	//=======================================================================
	//main함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		String cmd ="";
		sb = new StringBuilder();
		My_Queue q = new My_Queue();
		
		while((N--)>0) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			int val = -1;
			if(st.countTokens()==1) 	//남은 토큰이 있다면 그건 push를 위한 값
				val = Integer.parseInt(st.nextToken());
			q.command(cmd, val);
		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================================
	//내부 클래스
	static class My_Queue{
		private Deque<Integer> dq;
		My_Queue(){
			dq = new LinkedList<>();
		}
		public void command(String cmd, int val) {
			if(cmd.equals("push")) {
				dq.offer(val); //add와 속도적인건 다르지 않았다.
			}
			else if(cmd.equals("pop")) {
				if(dq.isEmpty()) sb.append(-1+"\n");
				else sb.append(dq.poll()+"\n");
			}
			else if(cmd.equals("size")) {
				sb.append(dq.size()+"\n");
			}
			else if(cmd.equals("empty")) {
				if(dq.isEmpty()) sb.append(1+"\n");
				else sb.append(0+"\n");
			}
			else if(cmd.equals("front")) {
				if(dq.isEmpty()) sb.append(-1+"\n");
				else sb.append(dq.peekFirst()+"\n");
			}
			else if(cmd.equals("back")) {
				if(dq.isEmpty()) sb.append(-1+"\n");
				else sb.append(dq.peekLast()+"\n");
			}
		}
	}//=======================================================================

}
