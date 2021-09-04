package Graph_short_path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
5
3
1 3 1
3 4 2
4 5 3 

 */
// 최단 경로를 구했지만 실제 경로를 출력해보고 싶었다. 알고리즘은 플로이드로 해보겠다.
// path라는 배열을 통해서 path[i]는 i번째 정점으로 오는 그 전 단계 정점을 알려준다.
// 11780번 - 플로이드 2
public class Show_Path_floyd {

	//클래스변수
	static int N, M;		//도시개수, 버스개수
	static int[][] dist;	//간선(버스) 정보. 인접리스트
	static int INF = 100_000_000;	//1억 
	static int[][] path;	//경로를 위해 추가
	//==========================================
	//정점 자료구조
	static class Node {
		int index, dist;
		Node(int i, int d){
			index = i; dist = d;
		}
	}//==========================================
	//main함수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N+1][N+1];
		path = new int[N+1][N+1];
		for(int i=0; i<=N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		//정보 입력 
		for(int z=0; z<M; z++) {	//문제상 M개의 버스 정보를 제공해준다. 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	//시작 도시
			int b = Integer.parseInt(st.nextToken());	//도착 도시
			int c = Integer.parseInt(st.nextToken());	//한번 타는데 필요한 비용
			dist[a][b] = Math.min(dist[a][b], c);
		}

		//플로이드-와샬 알고리즘
		for(int k=1; k<=N; k++)	//k번 정점
			for(int i=1; i<=N; i++)	//시작
				for(int j=1; j<=N; j++) { //도착
					if(dist[i][j] > dist[i][k]+dist[k][j]) {
						dist[i][j] = dist[i][k]+dist[k][j];
						path[i][j] = k; //거쳐온 정점, i에서 J로 가는 그 사이에 '딱 하나'만 있는게 아니다. 
						//알고리즘상 i와 j사이 최단경로에는 여러개의 정점이 존재 할수있고, 
						//이전의 갱신한 정보가 계속 누적되어 오기 때문에 가장 최신에 해당하는 정보로 그사이에 k가 있다는 정보'일 뿐'이다. 
						//이를 이용해서 다시 i와 k사이 k와 j사이의 중간 값이 무엇이 있을지 알아본다. 
						//최단경로로 완성되었기에 그 중간 정점도 최단경로에 맞는 정보로만 들어있는게 수학적으로 맞다.
					}
				}
		//출력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(dist[i][j]==INF) sb.append(0+" ");
				else sb.append(dist[i][j]+" ");
			}sb.append("\n");
		}System.out.println();

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(path[i][j]+" ");

			}System.out.println();
		}
		bw.write(sb.toString());
		bw.flush();
		br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start==-1) break;
			if(dist[start][end]==INF) { 
				System.out.println("경로가 존재하지않네요"); 
				continue;
			}
			
			System.out.print("v"+start+"->");
			View(start, end);
			System.out.println("v"+end);
		}
	}//==========================================

	static void View(int start, int end) {	//중간의 값들을 모두 내뱉게 한다. 
		if(path[start][end] != 0){
			View(start, path[start][end] );
			System.out.print("v"+ path[start][end] +"->");
			View(path[start][end] , end);
		}
	}
}
