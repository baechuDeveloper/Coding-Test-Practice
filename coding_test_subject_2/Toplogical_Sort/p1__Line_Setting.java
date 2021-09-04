package Toplogical_Sort;
import java.io.*;
import java.util.*;

// 2252번 - 줄 세우기
public class p1__Line_Setting {
	/*클래스변수*/
	static int N, M;								//학생수, 키를 비교한 횟수
	static ArrayList< ArrayList<Integer> > edge;	//간선의 정보가 담긴 배열리스트
	static int[] indegree;							//자신에게 들어오는 다른 정점이 몇개인지.
	static StringBuilder sb = new StringBuilder();
	//================================================================
	/*main함수*/
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		//학생번호는 1부터 시작한다.
		indegree = new int[N+1]; 
		
		edge = new ArrayList<>();
		for(int i=0; i<=N; i++) 
			edge.add(new ArrayList<>());
		
		//단방향 연결 설정
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			edge.get(L).add(R);	//실제 방향대로 넣는거면서도, 왼쪽에서 오른쪽으로 가는 정점이라고 생각하고 이렇게 명칭해주고 넣어주었다. 
			indegree[R]++;	//자신으로 들어오는 정점이 있다면 카운트를 늘려준다. 
		}
		
		toplogicalSort();
		
		bw.write(sb.toString());
		bw.flush();
	}//================================================================
	/*위상 정렬*/
	static void toplogicalSort() {
		Queue<Integer> q = new LinkedList<>();
		
		//초기: 선행 정점을 가지지 않는 정점을 큐에 삽입
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0)
				q.add(i);
		}
		
		//int count=0; //삭제된 횟수, 혹은 방문한 횟수
		
		//정점의 수 만큼 반복했을 때 큐의 모든 내용이 제거되어야 한다.
		while(!q.isEmpty()) {	//N번만큼만 돌리고 나면 그것이 되었는지 안되었는지 알수있으므로 직관적으로 for문으로 N번 돌리긴한다. 다만 절대 N명을 초과하지 않는다.
			//해당 정점 방문
			int now = q.poll();	
			sb.append(now+" "); //정점 출력	
			
			//다음에 갈 수 있는 정점에게 자신이 지워졌음을 알려주면서 새로히 큐에 들어올수있는지 확인한다.  
			for(int next : edge.get(now)) {
				indegree[next]--;	//now인 정점이 삭제가 되면서 해당next정점으로 가는 간선이 줄어들어 indegree를 줄여준다.
				//만약 해당 next정점도 indegree가 0이 되었다면 
				if(indegree[next]==0) {
					q.add(next);
				}
			}
			//count++; // 만약 삭제된 횟수가 N명만큼 되었다면 제대로 위상정렬이 완료되었고, 그렇지 않았다면 싸이클이 존재해서 되지않았다. 
					   //count는 사람의 인원을 초과하지 못한다. 대신 이 문제에서는 반드시 위상정렬이 되는 문제를 주었기에 쓰이지는 않았다.
					   //'count라는 표현 대신 for문의 i로 대체해서 사용하는것도 방법이다.' 
		}
	}//================================================================

}
