package Stack;

import java.io.*;
import java.util.*;

public class p3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		Stack<Character> stack;
	
		while(true) {
			stack = new Stack<>();
			StringBuilder cmd = new StringBuilder(br.readLine());
			int len = cmd.length();
			if(len==1 && cmd.charAt(0)=='.')
				break;
		
			while(cmd.lastIndexOf(".") == -1) {
				String add = br.readLine();
				cmd.append(" "+add);
				len += cmd.length()+1;
			}
			
			boolean check = false;
			for(int i=0; i<len; i++) {
				char c = cmd.charAt(i);
				
				if(c=='(' || c=='[') {
					stack.push(c);
				}
				else if(c==')') {
					if(stack.isEmpty() || stack.peek() != '(') {
						answer.append("no\n");
						check = true;
						break;
					}
					else if(stack.peek() == '(') {
						stack.pop();
					}
				}
				else if(c==']') {
					if(stack.isEmpty() || stack.peek() != '[') {
						answer.append("no\n");
						check = true;
						break;
					}
					else if(stack.peek() == '[') {
						stack.pop();
					}
				}
			}
			
			if(check == false) {
				if(stack.isEmpty())
					answer.append("yes\n");
				else 
					answer.append("no\n");
			}
		}
		bw.write(answer.toString());
		bw.flush();
	}

}
