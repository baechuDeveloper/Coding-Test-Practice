package Toplogical_Sort;
import java.io.*;
import java.util.*;

/**=================================================================
 * 등수가 바뀐 팀의 쌍에서 언급이 된적이 없는 팀은 그 자리를 그대로 유지하게 된다. 
 * 그리고 중요한 생각이 있는데, 문제에서 말한대로 '작년에 비해서 상대적인 순위가 바뀐 팀을 발표' 한다고 한다.
 * 즉, 절대적으로 어떠한 경우에도 기본에 주어진 순위의 상대적인 위치 관계에서.. 그 상대적인 관계가 달라진 것만 언급해주겠다는 뜻이된다.
 * 그래서 언급이 되지 않았다면 원래의 상대적인 순위를 유지해야한다는 말이다. 이게 가장 중요한 포인트가 되겠다.
 * 
 * 그러니 상대적인 위치가 바뀐걸 제시할 때, 왼쪽과 오른쪽이 그 상대적 관계를 제시한게 아니라... 기존 둘의 상대적 관계를 역전 시키겠다는 말이 된다.
 * 
 * 1 2 3 4
 * 
 * 1 2
 * 3 4
 * 2 3
 * 인데
 * 3 1 4 2 가 되어야하는데 이러면 (2 4)와 (1 3)도 포함되어야 한다. 그래서 안된다.
 * ------------------------------------------------------------
 * 
 * '구현 생각' 
 * 
 * 상대적으로 적은 팀의 수에 i번에서 j번으로 가는 걸 빠르게 확인 하도록 2차원 배열로 진입을 하겠다. 
 * 물론 무엇무엇이 있는지 볼때 n번을 거쳐야 하지만 n이 비교적 작은 숫자라서 이 방법이 더 빠르다.
 * '인접리스트'로 값을 확인하고 지우는 get연산이 O(1)은 아니기에 빠르게 값을 확인하고 지울때는 '인접행렬'이 더 빠르다.
 * 안에 무엇무엇이 있는지 확인 할때는 인접리스트로 하면 좋고, 해당 값만 있는지 그걸 빠르게 찾는데는 2차원 배열(인접행렬)로 접근이 좋다.
 * 
 * 역시 위상정렬로서 인접차수가 0인것 부터 1등으로 취해주면 된다. 그렇게 점점 0이 되는되로 순위를 매개주면 되는데 
 * 만약에 큐에 인접차수가 0인게 2개 들어온다면 2가지 경우가 반드시 존재하는 것이라 '?'라는 결과로 남는다.
 * 
 * 간선은 갈수있는 방향이 있음을 알려줌과 동시에 상대적인 위치를 잡혀주는 역활 이기도 하다. 
 * indegree와 함께해서 간선을 큐에 넣으며 indegree가 줄어드는 역활을 해주고, 이를 통해 상대적인 위치를 관계에서 순위를 정해주도록 해준다.
 * 간선을 큐에 넣고 빼는 과정으로 상대적인 위치를 확정적으로 정해줄 수 있다.
 =====================================================================*/
// 3665번 - 최종 순위
public class p2__Final_Ranking {
	static int[][] from_to;
	//static ArrayList< ArrayList<Integer> > from_to;
	static int[] team, indegree;	//i등한 팀이 누군지 알려준다, indegree 인접차수
	//========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			int n = Integer.parseInt(br.readLine());	//팀의 수	1부터 n까지 팀 번호가 있다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			team = new int[n+1];	
			indegree = new int[n+1];
			from_to = new int[n+1][n+1];
			/*from_to = new ArrayList<>(); for(int i=0; i<=n; i++) from_to.add(new ArrayList<>());*/
			
			//작년 순위별로 팀을 넣어준다. 
			for(int i=1; i<=n; i++) {	//i는 순위를 말한다.
				int t = Integer.parseInt(st.nextToken());	//팀 번호
				team[i] = t;	// i등한 팀이 누구인지 알려준다.
			}
			
			//작년 순위를 토대로 각 간선을 만들어주고 인접차수를 만들어준다. 이 간선이 갈수있는 방향을 알려주고 인접차수를 통해서 어느것이 큐에 들어가서 상대적으로 높은순위인지 알려준다.
			for(int i=1; i<=n; i++) {
				for(int j=i+1; j<=n; j++) {	//높은 순위 i가 낮은 순위 j로만 갈수있는 간선이다.
					//from_to.get(team[i]).add(team[j]);	
					from_to[ team[i] ][ team[j] ] = 1;  //i등한 팀보다 순위가 낮은 팀들에 대해 가는 길(간선)이 있다고 알려준다.
					indegree[ team[j] ]++;				//해당 간선으로 들어오는 팀이 생기니 받는 팀(순위가 낮은)의 indegree를 눌려준다.
				}
			}
			
			//상대적 등수가 바뀐 팀이 있다면 그 간선의 방향을 바꾸어준다. 
			int m = Integer.parseInt(br.readLine());	//등수가 바뀐 팀의 쌍의 개수 
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int t1 = Integer.parseInt(st.nextToken());	//팀의 번호를 받는다.
				int t2 = Integer.parseInt(st.nextToken());
				if( from_to[ t1 ][ t2 ] == 1 ) {
					from_to[ t1 ][ t2 ] = 0;
					from_to[ t2 ][ t1 ] = 1;
					indegree[t1]++;
					indegree[t2]--;
				}
				else {
					from_to[ t1 ][ t2 ] = 1;
					from_to[ t2 ][ t1 ] = 0;
					indegree[t1]--;
					indegree[t2]++;
				}
				
				/*if(from_to.get(t1).contains(t2)) {
					from_to.get(t1).remove(new Integer(t2));	//이렇게 해야 index로 행하는 remove가 안되고 값으로 빠지는게 된다.
					from_to.get(t2).add(t1);
					indegree[t1]++;
					indegree[t2]--;
				}
				else {
					from_to.get(t2).remove(new Integer(t1));
					from_to.get(t1).add(t2);
					indegree[t1]--;
					indegree[t2]++;
				}*/
			}
			
			/**위상정렬 시작**/
			Queue<Integer> q = new LinkedList<>();
			for(int i=1; i<=n; i++) {
				if(indegree[i]==0) 
					q.add(i);
			}
			StringBuilder sb = new StringBuilder();
			boolean isfinish = true;
			
			// 팀의 개수만큼 순위를 정해주어야 한다. 
			for(int cnt=0; cnt<n; cnt++) {	
				if(q.size()==0) {
					bw.write("IMPOSSIBLE\n"); //싸이클이 존재해서 위상정렬이 먹히지 않는 경우 - 일관성이 없는 데이터로 순위를 정해줄수 없다.
					isfinish = false;
					break;
				}
				else if(q.size()>=2) {
					bw.write("?\n");	//둘중의 무엇이 먼저 나오냐에 따라 순위가 달라져서 확실한 순위를 정해줄 수 없는 경우.
					isfinish = false;
					break;
				}
				int nowteam = q.poll();
				sb.append(nowteam+" ");	//현재 순위에 맞는 팀을 내보낸다.
				
				//이 팀이 지워지면서 현재 팀을 통해 들어갈 수 있는 간선을 통해 해당 팀의 인접차수를 줄여준다.
				for(int next=1; next<=n; next++) {
					if(from_to[ nowteam ][ next ] == 1) {
						indegree[next]--;
						if(indegree[next] == 0)
							q.add(next);
					}
				}
				
				/*for(int next : from_to.get(nowteam)) {
					indegree[next]--;
					if(indegree[next]==0) 
						q.add(next);	
				}*/	
			}
			if(isfinish) {
				sb.append("\n");
				bw.write(sb.toString());
			}
		}
		
		bw.flush();
	}//========================================================
}
