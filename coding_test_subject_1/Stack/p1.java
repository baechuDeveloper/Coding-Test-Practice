package Stack;

import java.util.*;
import java.io.*; 

public class p1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = st.countTokens();
			String cmd = st.nextToken();
			if(count == 2)
				count = Integer.parseInt(st.nextToken());

			if(cmd.equals("push")) {
				stack.push(count);
			}
			else if(cmd.equals("pop")) {
				if(stack.isEmpty()) 
					sb.append("-1\n");
				else
					sb.append(stack.pop()+"\n");	
			}
			else if(cmd.equals("size")) {
				sb.append(stack.size()+"\n");
			}
			else if(cmd.equals("empty")) {
				if(stack.isEmpty()) 
					sb.append("1\n");
				else
					sb.append("0\n");
			}
			else if(cmd.equals("top")) {
				if(stack.isEmpty()) 
					sb.append("-1\n");
				else
					sb.append(stack.peek()+"\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

}
