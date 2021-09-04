package Stack;

import java.util.*;
import java.io.*;

public class p2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int num;
		
		while(--N>=0) {
			num = Integer.parseInt(br.readLine());
			if(num==0) {
				if(!stack.isEmpty()) 
					stack.pop();
			}
			else {
				stack.push(num);
			}
		}
		
		int sum = 0;
		for(int i:stack) 
			sum+=i;
		
		bw.write(sum+"");
		bw.flush();
	}

}
