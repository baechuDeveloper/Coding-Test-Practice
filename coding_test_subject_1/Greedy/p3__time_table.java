package Greedy;
import java.util.*;
import java.io.*;

// 1931번 - 회의실배정
// 그리디를 적용시켜 매 순간마다 최선의 방법으로 최대한 많은 회의를 진행해보자. 최선의 방법을 찾기위해 반례가 없는 규칙을 찾아야 한다.
/* 1. 회의를 일찍 시작하는 순으로 접근하면?
  	   일찍 시작해도 끝나는 시간이 엄청 길다면 무용지물이다.
 * 2. 회의가 짧은 순으로 접근하면?
 	   그 짧은 회의로 인해 2가지의 회의가 중첩 안되고 그로 인해 최대 회의가 되지 않을 수 있다.
 * 3. 일찍 끝나는 회의를 기준으로 잡은면?
          일찍 끝나면 더 회의를 진행할수 있고 이를 기준으로 다음 끝나는 회의 중 가능한 경우를 찾기 더 좋아진다. 
          최대 회의 수를 구성하는 경우가 여러 개 일 수 있어도, 그 개수는 동일하다.
      (1,4)를 맨 처음으로 시작헀는데 사실 (1,4)대신 (3,5)를 선택해도 된다. 그 다음이 빨리 끝나는 시간이 (5,7)이기에 서로만 바뀔 뿐이다.
       이는 다시말해 이 일찍 끝나는 시간으로 잡는게 매우 유용하다는 예시이다. 가장 빨리끝나는 회의로 잡아두고 그 다음 빨리는 끝나는 회의를 알아보는 것이
       최대 회의수를 보장해주며, (3,5)처럼 다른 예시로 갈 수도 있지만 더 짧은 회의를 고른다는것이 더 우선순위가 높다. 혹여나 (4,5)가 있으면 어떨지 말이다.
       빨리 끝나는 회의를 기준으로 그 다음 시작시간을 찾아가는 것은 최대 회의를 보장해준다. 
       
       그만큼 선택 범위(개수)를 넓혀주었고, 그만큼 더 선택을 위한 우선순위가 높은 순서대(더 짧고 가까움)로 나열해주었기 떄문이다. 그렇다면 반드시 보장이 된다.
 ==================================================================*/ 
public class p3__time_table {
	//자료구조 클래스
	static class Node implements Comparable<Node>{
		int start, end;	//회의 시작, 끝
		Node(int a, int b){
			start = a; end = b;
		}
		
		public int compareTo(Node o) {
			if(end > o.end) {	//상대가 더 짧다
				return 1;   //내가 오름차순 기준으로 더 뒤로 가겠다.
			}
			else if(end < o.end) { //내가 더 짧다
				return -1;	//내가 오름차순 기준으로 더 뒤로 가겠다.
			}
			else {	//서로 끝나는 시간이 같다. 
				if(start > o.start)  //상대가 더 짧다
					return 1;
				else
					return -1;	//내가 더 짧다.
			}
		}
	}//======================================================
	//main함수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] arr = new Node[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());	// 시작 시간
			int end = Integer.parseInt(st.nextToken());	// 끝나는 시간
			arr[i] = new Node(start, end);
		}
		
		Arrays.sort(arr);
		//for(int i=0; i<N; i++) System.out.println(arr[i].start+" "+arr[i].end);
		
		int cnt = 0;
		int now_end = 0;
		for(int i=0; i<N; i++) {
			if(now_end <= arr[i].start) {
				now_end = arr[i].end;
				cnt++;
			}
		}
		System.out.println(cnt);
	}//======================================================

}
