package Deque;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
push X: 정수 X를 큐에 넣는 연산이다.
pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 큐에 들어있는 정수의 개수를 출력한다.
empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 **/
public class queue_2_fast_zzz {
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
		My_Queue q = new My_Queue(N);
		while((--N)>=0) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			int val = -1;
			if(st.countTokens()==1) 	//남은 토큰이 있다면 그건 push를 위한 값
				val = Integer.parseInt(st.nextToken());
			
			q.command(cmd, val);	//이렇게 해보기도, 아예 저 코드를 여기에 붙여도 봤는데 큰차이가 없고 오히려 이게 조금더 빠르다. 
			//호출을 여러번해도 결국 여러번 스택에 쌓이지 않고 곧바로 사라짐에 있어서 크게 문제는 없고 호출을 할때 비용 정도의 차이가 있지만 크게 신경 안써도 된다. 
		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================================
	//내부 클래스
	static class My_Queue{
		private int[] arr; 
		private int first, last, size;	//last는 까지라는 표현 last자체 까지 번호로 포함이 안되고 그 이전까지 포함
		
		My_Queue(int N){
			arr = new int[N+1];
			first = 0;
			last = 0;
			size = 0;
		}
		public void command(String cmd, int val) {
		
			if(cmd.equals("push")) {
				arr[last++] = val;
				++size;
			}
			else if(cmd.equals("pop")) {
				if(size==0) 
					sb.append(-1+"\n");
				else {
					sb.append(arr[first++]+"\n");
					--size;
				}
			}
			else if(cmd.equals("size")) {
				sb.append(size+"\n");
			}
			else if(cmd.equals("empty")) {
				if(size==0) sb.append(1+"\n");
				else sb.append(0+"\n");
			}
			else if(cmd.equals("front")) {
				if(size==0) sb.append(-1+"\n");
				else sb.append(arr[first]+"\n");
			}
			else if(cmd.equals("back")) {
				if(size==0) sb.append(-1+"\n");
				else sb.append(arr[last-1]+"\n");
			}
		}
	}//=======================================================================

}
