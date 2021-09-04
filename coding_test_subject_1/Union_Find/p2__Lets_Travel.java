package Union_Find;
import java.util.*;
import java.io.*;

// 1976번 - 여행 가자
public class p2__Lets_Travel {
	static int[][] road;
	static int[] group;
	//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	//도시의 수	200이하
		int M = Integer.parseInt(br.readLine());	//여행계획에 속한 도시들의 수	1000이하
		road = new int[N+1][N+1];
		group = new int[N+1];
		Arrays.fill(group, -1);
		//연결된 도시정보를 입력
		//도시번호는 1부터, i번도시와 j번도시가 연결되어있으면 1, 아니면 0으로 주어진다.
		//이 정보를 토대로 현재 연결된 도시끼리의 '그룹'을 만들수있다. 같은 그룹에 있다면 서로 오고갈수있고, 같은 그룹이 아니면 오고갈 수 없다.
		//이곳에서는 merge작업을 통해 도시끼리의 그룹을 형성하게 해준다.
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
				if(road[i][j] == 1) {
					merge(i, j);	//i와 j번 도시를 같은 그룹으로 둔다.
				}
			}
		}
		
		//여행 계획
		//이제 제시해준 도시들이 서로 같은 그룹인지 체크하면 된다. 
		int whereGroup = -1;		//현재 진행되고있는 그룹이 무엇인지 확인하며 이것과 다른 그룹이라면 못가는 곳이다. 초기에 -1로 아무것도 지정안해주고, 정해지면 그 값을 유지한다.
		boolean isConnect = true;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) {
			int city = Integer.parseInt(st.nextToken());
			int nowGroup = find(city);
			if(whereGroup==-1) 
				whereGroup = nowGroup;
			else if(whereGroup!=nowGroup) {
				isConnect = false;
				break;	
			}
		}
		
		System.out.println( (isConnect? "YES" : "NO") );
	}//========================================================
	static int find(int a) {
		if(group[a]==-1)
			return a;
		return group[a] = find(group[a]);
	}//========================================================
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//========================================================
}
