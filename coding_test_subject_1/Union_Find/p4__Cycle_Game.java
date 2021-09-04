package Union_Find;
import java.util.*;
import java.io.*;

// 20040번 - 사이클 게임
public class p4__Cycle_Game {
	static int[] group;
	//======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	//정점 개수  	번호는 0부터시작
		int m = Integer.parseInt(st.nextToken());	//진행된 차례의 개수
		group = new int[n];
		Arrays.fill(group, -1);
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if( merge(a, b)==false ) {	//서로 같은 집합에 속해있다는 말이된다. 이 말이 중복된 간선을 넣지않았기에 만약 a,b간선을 그대로 받아주었다면 싸이클이 생길것이다.
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}//======================================================
	static int find(int a) {
		if(group[a]==-1) return a;
		return group[a] = find(group[a]);	
	}//======================================================
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//======================================================
}
