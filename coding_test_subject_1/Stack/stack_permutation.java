package Stack;

import java.io.*;
import java.util.*;

public class stack_permutation {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		int N = Integer.parseInt(br.readLine());
		int count = 1; //다음에 push 할 수 있는 숫자.
		boolean check = false;

		while(--N>=0) {
			int num = Integer.parseInt(br.readLine());
			
			if(stack.peek()<num) {	// 만들어주고 빼는 과정까지
				while(count<=num) {	// 더욱이 적어도 count는 항상 num보다 작거나 같은 경우만 나올것이다. 그 이유는 '실패'라고 적은 코드 덕분에 count가 num보다 큰 경우가 나오기도 전에 이미 no라는 대답을 해주기 떄문에 아예 끝난 상황이다. 
					stack.push(count++);
					answer.append("+\n");
				}	
				stack.pop();
				answer.append("-\n");
			}
			else if(stack.peek() == num){	//오직 빼는 과정
				stack.pop();
				answer.append("-\n");
			}
			else { // 실패 
				check = true;
				break;
			}

		}

		if(check == true)
			bw.write("NO\n");
		else 
			bw.write(answer.toString());
		bw.flush();
	}

}
