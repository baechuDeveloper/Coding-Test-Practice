package Stack;

import java.util.*;
import java.io.*;

// 17298�� - ��ū��
public class p4__NGE {

	//�ڷᱸ��
	static class Node{
		int idx, val;
		Node(int i, int v){
			idx = i; val = v;
		}
	}//=====================================
	//���� Ŭ��������
	static Deque<Node> stack = new ArrayDeque<>(); 
	static int[] NGE;
	//=====================================
	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		NGE = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			int now = Integer.parseInt(st.nextToken());
	
			while(!stack.isEmpty()) {
				Node cmp = stack.getLast();
				if(cmp.val < now) {
					NGE[cmp.idx] = now;
					stack.removeLast();
				}
				else {
					break;
				}
			}
			stack.add(new Node(i, now));
		}
		
		for(int i=1; i<=N; i++) {
			if(NGE[i]==0)
				sb.append(-1+" ");
			else
				sb.append(NGE[i]+" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}//=====================================

}
