package Toplogical_Sort;
import java.io.*;
import java.util.*;
/*===========================================================================
 * 조건이 추가된 스킬트리 문제
 * 먼저 푸는게 좋은 문제가 있다면 그 문제부터 반드시 풀어야 한다.
 * 일단 모든 문제 번호에 대한 순서대로 정해주기위해 일일이 간선을 만들어주는 행위는 32000^2이므로 9억을 넘는다. 정말 말도 안되는 수치로 연산이되어서 
 * 이 방법은 안되니 1부터 순서대로 작업을 할려고하는데 혹시 이 때 작업에서 해야만 하는 작업 남아있는지 보는 것이다. 
  

 * 가장 중요한점은 현재 문제를 풀어야하는 타이밍일 때, 그 타이밍에서 현재 가아아장 쉬운문제를 넣어야 한다는 것이다. 
  
 * 5 2
 * 1 4
 * 5 2 
 * 답은 1 3 4 5 2 이다. 
 * 각 문제를 푸는 타이밍이란 각 공간에 답을 적어야 하는 시점이다. 첫 번째 공간에 문제를 푸는 타이밍에 가장 쉬운문제에서 indegree가 0인것은 1번 문제이다
 * 두 번째 공간에 문제를 푸는 타이밍에 가장 쉬운 문제에서 indegree가 0인 것은 3번 문제이다.
 * 세 번째 공간에 문제를 푸는 타이밍에 가장 쉬운 문제에서 indegree가 0인 것은 4번 문제이다. 4번 문제는 1번문제를 풀때 그 indegree값이 0으로 되었다.
 * 네 번째 공간에 문제를 푸는 타이밍에 가장 쉬운 문제에서 indegree가 0인 것은 5번 문제이다. 
 * 다섯번째 공간에 문제를 푸는 타이밍에 가장 쉬운 문제에서 indegree가 0인 것은 2번 문제이다. 5번 문제를 풀면서 2번 문제의 indegree가 0이 되었고 다시 큐에 들어와서 가장 쉬운문제로 선택되었다.
 ============================================================================*/

// 1766번 - 문제집
public class p4__StudyGuide {
	/*클래스 변수*/
	static int N, M; 
	static int[] indegree;
	static ArrayList< ArrayList<Integer> > list;
	static boolean[] finish;	//해당 문제를 풀어냈다면 true를 준다, 아니면 false
	static boolean[] include;	//해당 문제 번호가 큐에 아직 포함되어있는지, 문제를 풀었거나 혹은 아직 못풀어서 큐에서 빠져왔을때 false으로 준다. 
	static StringBuilder sb = new StringBuilder();
	//=========================================================
	/*main함수*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//문제의 수	1<=N<=32,000
		M = Integer.parseInt(st.nextToken());	//먼저 푸는 것이 좋은 문제에 대한 정보 개수	1<=M<=100,000
		indegree = new int[N+1];
		list = new ArrayList<>();
		for(int i=0; i<=N; i++)
			list.add(new ArrayList<>());
		
		//먼저 풀어야할 것을 체크
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	//A번 문제는 B번 문제보다 먼저 푸는 것이 좋다.
			int B = Integer.parseInt(st.nextToken());
			list.get(A).add(B);	//A문제를 풀고나서는 B문제를 풀기 쉬워진다.
			indegree[B]++;		//B문제는 아직 앞서서 풀어야할 문제가 있다.
		}
		
		//이번 위상정렬에 필요한 추가 구성요소 공간할당
		finish = new boolean[N+1];
		include = new boolean[N+1];
		Arrays.fill(include, true);
		
		//우선순위큐로 두어서 현재 풀어야 할 문제중 가장 쉬운 문제가 무엇일지 제시해준다. 만약 도중에 indegree가 0인게 들어오면 그 문제를 쉬운 정도에 맞게 넣어준다.
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++)
			pq.add(i);	//우선 모든 문제를 넣어서 진행을 한다. 문제 번호가 곧 문제의 난이도다.
		
		/*---위상정렬 시작---*/
		for(int cnt=0; cnt<N; ) {
			if(pq.isEmpty()) break; //문제에서 이럴 일은 주지 않는다고 했다. 즉 싸이클이 존재하는 경우를 만들지 않겠다고 했다.
			
			//현재 이 문제를 풀어볼지 now를 꺼내온다.
			int now = pq.poll();	
			
			//큐에는 now가 더이상 없다.
			include[now] = false;	
			
			//현재에 대해 아직 풀어야 할 문제가 있으면 다음 문제로 넘어가자.
			if(indegree[now]!=0)	
				continue;	

			//더 풀어야 할 문제가 없다면 너를 풀었다고 체크.
			finish[now] = true; 
			sb.append(now+" ");
			
			//다음에 쉬워지는 문제가 무엇일지 체크해준다.
			for(int easynext : list.get(now)) {	
				if(finish[easynext]) 		//이미 푼 문제라면 넘어간다. 물론 싸이클을 만들지 않아서 여기를 들어오는 경우는 없을 것이다.
					continue; 	
				indegree[easynext]--;		//앞선 문제가 풀어졌다고 알려준다.
				if(indegree[easynext]==0 && !include[easynext]) { 	//만약 더 쉬워지는 문제가 더이상 앞에 풀게 없어졌는데 큐에 안들어가 있다면 넣어준다.
					pq.add(easynext);	
					include[easynext] = true;
				}
			}
			
			//내가 문제를 풀어서 푼 개수를 올려준다.
			cnt++;		
		}/*---위상정렬 종료---*/
		
		bw.write(sb.toString());
		bw.flush();
	
		/** --------------------------------------------------
		//이건 더 현재 타이밍때 가장 쉬운문제를 풀어야한다는 기준을 충족 못시켜준다.
		// 근데 좀만 바꾸면 가능하겠네 
		//먼저 풀어야할 것을 체크
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	//A번 문제는 B번 문제보다 먼저 푸는 것이 좋다.
			int B = Integer.parseInt(st.nextToken());
			list.get(B).add(A);	//반대로 B를 하기위해서 필요한게 무엇인지로 변형해서 간선으로 넣어준다.		
		}
		for(int i=1; i<=N; i++)
			list.get(i).sort(null);
		for(int cnt=0; cnt<N; ) {
			if(visit[now]) continue;
			preStudy(now);
			cnt++;
		}-----------------------------------------------------**/
	
	}//=========================================================
	
	/**static void preStudy(int prenow) {
		visit[prenow] = true;
		for(int prenext : list.get(prenow)) {
			if(visit[prenext]) continue;
			preStudy(prenext);
		}
		sb.append(prenow+" ");
	}//=========================================================
	**/
}



