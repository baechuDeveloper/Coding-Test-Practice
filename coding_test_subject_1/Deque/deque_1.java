package Deque;

import java.util.*;
import java.io.*;
// 백준 10866번 - 덱 (큐를 황용하여 만들어보는 자료구조 형태)
/**-----------------------------------------------------------------
 * 정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
 * 명령은 총 여덟 가지이다.

 * push_front X: 정수 X를 덱의 앞에 넣는다.
 * push_back X: 정수 X를 덱의 뒤에 넣는다.
 * pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * size: 덱에 들어있는 정수의 개수를 출력한다.
 * empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
 * front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 -------------------------------------------------------------------**/
public class deque_1 {
	//멤버변수
	private static StringBuilder sb = new StringBuilder();
	//================================================================
	
	//덱 자료구조 내부 클래스	--- 나중에 안건데 Deque<Integer>자료형 자체를 사용하는게 더 좋아보인다.
	static class Deque_class{
		private LinkedList<Integer> list;	//List 자료형이 아닌 그냥 바로 LinkedList로 자식의 메소드 다 활용해보면 좋다. 말그래도 링크드리스트그자체
		//--------------------------------------------------------------
		Deque_class(){
			list = new LinkedList<>();
		}//--------------------------------------------------------------
		public void command(String cmd, int num) {
			/*sb.append("now "); for(int i:list) sb.append(i+" "); sb.append("\n");*/
			if(cmd.equals("push_front")) {		//정수 X를 덱의 앞에 넣는다.
				if(list.isEmpty()) list.add(num);
				else list.addFirst(num);
			}
			else if(cmd.equals("push_back")) {	//정수 X를 덱의 뒤에 넣는다.
				list.add(num);
			}
			else if(cmd.equals("pop_front")) {	//덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				if(list.isEmpty()) 
					sb.append(-1+"\n");
				else {
					int temp = list.pollFirst();
					sb.append(temp+"\n");
				}
			}
			else if(cmd.equals("pop_back")) {	//덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				if(list.isEmpty()) 
					sb.append(-1+"\n");
				else {
					int temp = list.pollLast();
					sb.append(temp+"\n");
				}
			}
			else if(cmd.equals("size")) {		//덱에 들어있는 정수의 개수를 출력한다.
				sb.append(list.size()+"\n");
			}
			else if(cmd.equals("empty")) {		//덱이 비어있으면 1을, 아니면 0을 출력한다.
				if(list.isEmpty()) sb.append(1+"\n");
				else sb.append(0+"\n");
			}
			else if(cmd.equals("front")) {		//덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				if(list.isEmpty()) sb.append(-1+"\n");
				else sb.append(list.getFirst()+"\n");
			}
			else if(cmd.equals("back")) {		//덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				if(list.isEmpty()) sb.append(-1+"\n");
				else sb.append(list.getLast()+"\n");
			}
		}//--------------------------------------------------------------

	}//================================================================

	//main함수
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
			if(st.countTokens() == 1) 	//남은 토큰이 있다면  
				num = Integer.parseInt(st.nextToken());
			dq.command(cmd, num);
		}
		bw.write(sb.toString());
		bw.flush();
		
	}//================================================================
}
