package TraceBack_Shortest_Path_with_DP;

import java.util.*;
import java.io.*;

// 9019번 - DSLR
public class p6__DSLR {

	//prev와 command에 새로운 값이 처음으로 갱신이 되면 그 값이 가장 합리적인 최소방법이 된다. BFS형태로 진행을 해서.
	static int A, B;		 // 시작 값, 맞추려는 값
	static int[] prev;		 // [현재값] = 현재 값을 가기 위한 이전 값 
	static char[] command;	 // [현재값] = 현재 값을 가기 위한 이전 값에서 나타난 명령어 
	static Queue<Integer> q; // BFS를 위한 큐
	static char[] cal_name = {'D','S','L','R'};
	//=========================================================
	
	//main 함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		
		while(testcase-->0) {
			
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			prev = new int[10000];
			command = new char[10000];
			Arrays.fill(prev, -1);
		
			q = new LinkedList<>();
			q.add(A);

			//---BFS시작---
			while(!q.isEmpty()) {
				int now = q.poll();
				//방법 완전히 발견
				if(now == B) break; 
				//4가지 작업
				int[] com = { D(now), S(now), L(now), R(now) };
				for(int i=0; i<4; i++) {
					int next = com[i];
					if(prev[next] == -1) {
						prev[next] = now;
						command[next] = cal_name[i];
						q.add(next);
					}
				}
			}//---BFS종료---

			//역추적해서 결과 값을 스택에 넣는다.
			Deque<Character> stack = new ArrayDeque<>();
			int trace = B;
			while(trace != A) {
				stack.push( command[trace] );
				trace = prev[trace];
			}
			
			//스택에 있는 것들을 출력해준다.
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			} sb.append("\n");
			bw.write(sb.toString());
		
		}//모든 테스트 케이스 종료
		bw.flush();
	}//=========================================================
	
	static int D(int i) {
		return (i*2) % 10_000;
	}//=========================================================
	static int S(int i) {
		if(i==0) return 9999;
		return i-1;
	}//=========================================================
	static int L(int i) {
		int body = (i % 1_000) * 10;
		int tail = i / 1_000;
		return body + tail;
	}//=========================================================
	static int R(int i) {
		int body = i / 10;
		int head = (i % 10) * 1_000;
		return head + body;
	}//=========================================================

}
