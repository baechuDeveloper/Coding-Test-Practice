package Graph_short_path;
import java.util.*;
import java.io.*;
// 플로이드 와샬을 응용할 수 있다.
// 원래 dist[i][i]의 거리는 사용하지 않고 0으로 초기화해두었는데 
// 1956번 - 운동
public class p7__Floyd_exercise {

	static int V, E;		//마을 개수, 도로 개수   
	static int[][] dist;	//[i][j] i마을에서 j마을로 가는 최단거리 값. (붙어있지 않더라도, 다른 마을과의 연결을 통해서 만들어진 최단거리)
	static int INF = 100_000_000;
	//==========================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	//마을 개수. 2 <= V <= 400
		E = Integer.parseInt(st.nextToken());	//도로 개수. 0 <= E <= V(V-1)
		dist = new int[V+1][V+1];
		for(int i=0; i<=V; i++) {
			Arrays.fill(dist[i], INF);
			//dist[i][i] = 0;
		}

		//정보 입력
		for(int z=0; z<E; z++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[a][b] = Math.min(dist[a][b], c);
		}
		
		//플로이드-와샬 알고리즘
		for(int k=1; k<=V; k++)	//k번 정점을 경유하여
			for(int i=1; i<=V; i++)	//시작해서
				for(int j=1; j<=V; j++) {//도착할때
					//최단거리면 업데이트 해준다.
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
		
		//플로이드라는 작동방식덕분에 [i][i]에 대해서 알아보장. 
		//하나의 정점을 거쳐가는 느낌이 살려져서 [i][i]는 싸이클이 되어진다. [i][i]중 가장 최소인것이 가장 작은 싸이클이다.
		int minVal = INF;
		for(int i=1; i<=V; i++) 
			minVal = Math.min(minVal, dist[i][i]);
		if(minVal==INF) minVal = -1;
		System.out.println(minVal);
	}//==========================================



}
