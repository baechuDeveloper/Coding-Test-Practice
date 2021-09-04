package Graph_short_path;

import java.util.*;
import java.io.*;

// 9370번 - 미확인 도착지
//목표는 목적지에 도달하는 최단경로중에 g-h를 지나가는 최단경로가 있는가. 있으면 해당 목적지를 오름차순으로 나열해준다. 
public class p3__Unknown_Arrival {
	//클래스변수
	static int[][] arr;		 //[정점a][정점b] 사이의 거리
	static ArrayList<ArrayList<Integer>> list;	//정점끼리 연결된 간선을 보여줌 
	static int[] dest, dist; //목적지후보, s시작지점에서 해당 인덱스까지 최소가 될 거리(진행이 다 끝났을때)
	static boolean[] check;	 //방문체크
	static int INF = 10_000_000;
	//======================================================
	//정점을 뜻한다.
	static class Node implements Comparable<Node>{
		int index, weight;	//index는 해당 정점을 말합니다.
							//weight는 해당 정점까지 거쳐오는 동안 얻어와본 가중치의 값
		Node(int i, int w){
			index=i; weight = w;
		}
		public int compareTo(Node o) {
			return weight - o.weight;	//내가 크면 나에게 양수 즉 내가 더 크다. 오름차순시 오름차순으로 적용
		}
	}//======================================================
	//main함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine());
		
		while(test_case-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());	//교차로 개수 //교차로는 교차되는 도로 즉, 한 지점인 정점을 뜻한다.
			int m = Integer.parseInt(st.nextToken());	//도로 개수
			int t = Integer.parseInt(st.nextToken());	//목적지 후보 개수
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());	//시작지점
			int g = Integer.parseInt(st.nextToken());	//교차로.	이 두 교차로 사이에 1개 이상의 지점이 존재한다. 
			int h = Integer.parseInt(st.nextToken());	//교차로. 교차로 사이에는 도로가 많아봐야 1개이다. 
														//이 도로는 반드시 주어지며, 목적지 후보들 중 적어도 1개로 향하는 최단경로의 일부이다. 
			//그래프 정보 저장
			list = new ArrayList<>();
			for(int i=0; i<=n; i++)
				list.add(new ArrayList<>());
			arr = new int[n+1][n+1];
			for(int x=0; x<arr.length; x++)
				Arrays.fill(arr[x], INF);
			for(int z=0; z<m; z++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				arr[a][b] = arr[b][a]= 2*d;			//거리를 2배씩 늘려서 짝수로 두었다.
				list.get(a).add(b);
				list.get(b).add(a);
			}
			arr[h][g] = arr[g][h] = arr[h][g]-1;	//g와 h사이를 지나는 도로의 거리만 홀수로 두었다. 아니면 float형으로 두어 해당 값만 소수점 값을 넣는다.
			
			//목적지에 해당하는 후보들
			dest = new int[t];	
			for(int z=0; z<t; z++) 
				dest[z] = Integer.parseInt(br.readLine());
			Arrays.sort(dest); //미리 오름차순으로 정렬을 해두어 정답에서 바로바로 나오게 함.
			
			//시작지점 s에서 최적거리에 해당하는 거리 
			dist = new int[n+1];
			Arrays.fill(dist, INF);	//다익스트라를 적용시키도록 큰 값을 지정해준다.
			
			//방문했는지 체크
			check = new boolean[n+1];
			
			/**다익스트라 알고리즘 적용**/
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(s, 0));
			dist[s] = 0;
			
			while(!pq.isEmpty()) {
				Node nowNode = pq.poll();
				int now = nowNode.index;
				if(check[now]) continue; //이미 방문했다면 패스 
				check[now] = true;
				for(int next : list.get(now)) {
					if( check[next]==false && dist[next] > dist[now] + arr[now][next] ) {
						dist[next] = dist[now] + arr[now][next];
						pq.add( new Node(next, dist[next]) );	//현재까지 누적된 거리 합이 가장 작은 걸 우선시함으로 Node를 만들어서 진행하였다. 
																//if조건에 dist[now] 대신 nowNode.weight를 해도 되며, 원래 한번 갱신된 dist[now]는 두번 다시 낮은 값으로 갱신될 일이 없다. 다익스트라에 따르면 
					}
				}
			}/**알고리즘 끝**/
			
			//정답 출력
			for(int i : dest) {
				if(dist[i] % 2 == 1) //해당 도착지점까지의 값이 존재했다면 그 값이 s지점에서의 최소인건 당연하다. 거기에 그 값이 홀수라면 g와 h사이를 건너온 값 임을 알수있다. 
					sb.append(i+" ");
			}
			sb.append("\n");
		}//전체 테스트케이스 종료
		
		bw.write(sb.toString());
		bw.flush();
	}//======================================================
	
}
