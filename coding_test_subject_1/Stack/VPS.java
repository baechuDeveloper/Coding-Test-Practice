package Stack;

import java.io.*;
import java.util.*;

// °ýÈ£ ¹®ÀÚ¿­ (Valid PS)
public class VPS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Stack<Character> stack;

		while(--N>=0) {
			stack = new Stack<>();
			boolean check = false;
			String cmd = br.readLine();
			int len = cmd.length();
			char c;
			for(int i=0; i<len; i++) {
				c = cmd.charAt(i);
				if(c == '(') {
					stack.push(c);
				}
				else {
					if(stack.isEmpty()) {
						bw.write("NO\n");
						check = true;
						break;
					}
					else {
						stack.pop();
					}

				}
			}

			if(check == false) {
				if(stack.isEmpty())
					bw.write("YES\n");
				else
					bw.write("NO\n");
			}

		}
		bw.flush();
	}

}
