package Deque;

import java.util.*;
import java.io.*;

/**
push X: ���� X�� ť�� �ִ� �����̴�.
pop: ť���� ���� �տ� �ִ� ������ ����, �� ���� ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
size: ť�� ����ִ� ������ ������ ����Ѵ�.
empty: ť�� ��������� 1, �ƴϸ� 0�� ����Ѵ�.
front: ť�� ���� �տ� �ִ� ������ ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
back: ť�� ���� �ڿ� �ִ� ������ ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
 **/
public class queue_2 {
	//�������
	private static StringBuilder sb;
	//=======================================================================
	//main�Լ�
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
			if(st.countTokens()==1) 	//���� ��ū�� �ִٸ� �װ� push�� ���� ��
				val = Integer.parseInt(st.nextToken());
			q.command(cmd, val);
		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================================
	//���� Ŭ����
	static class My_Queue{
		private Deque<Integer> dq;
		My_Queue(){
			dq = new LinkedList<>();
		}
		public void command(String cmd, int val) {
			if(cmd.equals("push")) {
				dq.offer(val); //add�� �ӵ����ΰ� �ٸ��� �ʾҴ�.
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
