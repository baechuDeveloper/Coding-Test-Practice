package Deque;

import java.util.*;
import java.io.*;
// ���� 10866�� - �� (ť�� Ȳ���Ͽ� ������ �ڷᱸ�� ����)
/**-----------------------------------------------------------------
 * ������ �����ϴ� ��(Deque)�� ������ ����, �Է����� �־����� ����� ó���ϴ� ���α׷��� �ۼ��Ͻÿ�.
 * ����� �� ���� �����̴�.

 * push_front X: ���� X�� ���� �տ� �ִ´�.
 * push_back X: ���� X�� ���� �ڿ� �ִ´�.
 * pop_front: ���� ���� �տ� �ִ� ���� ����, �� ���� ����Ѵ�. ����, ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
 * pop_back: ���� ���� �ڿ� �ִ� ���� ����, �� ���� ����Ѵ�. ����, ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
 * size: ���� ����ִ� ������ ������ ����Ѵ�.
 * empty: ���� ��������� 1��, �ƴϸ� 0�� ����Ѵ�.
 * front: ���� ���� �տ� �ִ� ������ ����Ѵ�. ���� ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
 * back: ���� ���� �ڿ� �ִ� ������ ����Ѵ�. ���� ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
 -------------------------------------------------------------------**/
public class deque_1 {
	//�������
	private static StringBuilder sb = new StringBuilder();
	//================================================================
	
	//�� �ڷᱸ�� ���� Ŭ����	--- ���߿� �Ȱǵ� Deque<Integer>�ڷ��� ��ü�� ����ϴ°� �� ���ƺ��δ�.
	static class Deque_class{
		private LinkedList<Integer> list;	//List �ڷ����� �ƴ� �׳� �ٷ� LinkedList�� �ڽ��� �޼ҵ� �� Ȱ���غ��� ����. ���׷��� ��ũ�帮��Ʈ����ü
		//--------------------------------------------------------------
		Deque_class(){
			list = new LinkedList<>();
		}//--------------------------------------------------------------
		public void command(String cmd, int num) {
			/*sb.append("now "); for(int i:list) sb.append(i+" "); sb.append("\n");*/
			if(cmd.equals("push_front")) {		//���� X�� ���� �տ� �ִ´�.
				if(list.isEmpty()) list.add(num);
				else list.addFirst(num);
			}
			else if(cmd.equals("push_back")) {	//���� X�� ���� �ڿ� �ִ´�.
				list.add(num);
			}
			else if(cmd.equals("pop_front")) {	//���� ���� �տ� �ִ� ���� ����, �� ���� ����Ѵ�. ����, ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
				if(list.isEmpty()) 
					sb.append(-1+"\n");
				else {
					int temp = list.pollFirst();
					sb.append(temp+"\n");
				}
			}
			else if(cmd.equals("pop_back")) {	//���� ���� �ڿ� �ִ� ���� ����, �� ���� ����Ѵ�. ����, ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
				if(list.isEmpty()) 
					sb.append(-1+"\n");
				else {
					int temp = list.pollLast();
					sb.append(temp+"\n");
				}
			}
			else if(cmd.equals("size")) {		//���� ����ִ� ������ ������ ����Ѵ�.
				sb.append(list.size()+"\n");
			}
			else if(cmd.equals("empty")) {		//���� ��������� 1��, �ƴϸ� 0�� ����Ѵ�.
				if(list.isEmpty()) sb.append(1+"\n");
				else sb.append(0+"\n");
			}
			else if(cmd.equals("front")) {		//���� ���� �տ� �ִ� ������ ����Ѵ�. ���� ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
				if(list.isEmpty()) sb.append(-1+"\n");
				else sb.append(list.getFirst()+"\n");
			}
			else if(cmd.equals("back")) {		//���� ���� �ڿ� �ִ� ������ ����Ѵ�. ���� ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
				if(list.isEmpty()) sb.append(-1+"\n");
				else sb.append(list.getLast()+"\n");
			}
		}//--------------------------------------------------------------

	}//================================================================

	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Deque_class dq = new Deque_class();

		for(int z=0; z<N; z++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int num = -1;
			if(st.countTokens() == 1) 	//���� ��ū�� �ִٸ�  
				num = Integer.parseInt(st.nextToken());
			dq.command(cmd, num);
		}
		bw.write(sb.toString());
		bw.flush();
		
	}//================================================================
}
