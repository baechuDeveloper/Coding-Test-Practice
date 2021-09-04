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
		int count = 1; //������ push �� �� �ִ� ����.
		boolean check = false;

		while(--N>=0) {
			int num = Integer.parseInt(br.readLine());
			
			if(stack.peek()<num) {	// ������ְ� ���� ��������
				while(count<=num) {	// ������ ��� count�� �׻� num���� �۰ų� ���� ��츸 ���ð��̴�. �� ������ '����'��� ���� �ڵ� ���п� count�� num���� ū ��찡 �����⵵ ���� �̹� no��� ����� ���ֱ� ������ �ƿ� ���� ��Ȳ�̴�. 
					stack.push(count++);
					answer.append("+\n");
				}	
				stack.pop();
				answer.append("-\n");
			}
			else if(stack.peek() == num){	//���� ���� ����
				stack.pop();
				answer.append("-\n");
			}
			else { // ���� 
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
